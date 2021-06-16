package board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import board.model.vo.Board;
import common.JDBCTemplate;


public class BoardDao {

	public ArrayList<Board> selectAllList(Connection conn, int currentPage) {
		// Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Board> bList = null;
		String query = "SELECT * FROM (SELECT ROW_NUMBER() OVER(ORDER BY CS_NO DESC) AS NUM, CS_NO, CS_TITLE, CS_CONTENTS,CS_DATE,CS_PUBLIC, CS_ID,PASSWORD,CS_FILEPATH,CS_FILESIZE,CS_FILENAME,CS_UPLOADTIME FROM CS_BOARD) WHERE NUM BETWEEN ? AND ?"; // 숫자는
																																														
		// 현재 페이지 : 1, 한 페이지당 보여줄 게시물의 갯수 : 10, 5
		// start : 1, end : 10
		// start : 1, end : 5
		// 현재 페이지 : 2
		// start : 11, end : 20
		// start : 6, end : 10
		int recordCountPerPage = 10;
		int start = currentPage * recordCountPerPage - (recordCountPerPage - 1);
		int end = currentPage * recordCountPerPage;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			bList = new ArrayList<Board>();
			while (rset.next()) {
				Board board = new Board();
				board.setCsNo(rset.getInt("CS_NO"));
				board.setCsTitle(rset.getString("CS_TITLE"));
				board.setCsContents(rset.getString("CS_CONTENTS"));
				board.setCsDate(rset.getDate("CS_DATE"));
				board.setCsPublic(rset.getString("CS_PUBLIC"));
				board.setCsId(rset.getString("CS_ID"));
				board.setPwd(rset.getString("PASSWORD"));
				board.setCsFilePath(rset.getString("CS_FILEPATH"));
				board.setCsFileSize(rset.getInt("CS_FILESIZE"));
				board.setCsFileName(rset.getString("CS_FILENAME"));
				board.setCsUploadTime(rset.getTimestamp("CS_UPLOADTIME"));

				bList.add(board);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return bList; // null이면 무조건 실패 떠요
	}

	public String getPageNavi(Connection conn, int currentPage) {

		int recordTotalCount = totalCount(conn);
		int recordCountPerPage = 10;
		int pageTotalCount = 0;
		if (recordTotalCount % recordCountPerPage > 0) {
			pageTotalCount = recordTotalCount / recordCountPerPage + 1;
		} else {
			pageTotalCount = recordTotalCount / recordCountPerPage;
		}
		// 오류방지 코드
		if (currentPage < 1) {
			currentPage = 1;
		} else if (currentPage > pageTotalCount) {
			currentPage = pageTotalCount;
		}
		int naviCountPerPage = 10;
		int startNavi = ((currentPage - 1) / naviCountPerPage) * naviCountPerPage + 1;
		// currentPage는 사용자가 선택한 값이 넘어와야 되기 때문에 매개변수로 전달받아요
		int endNavi = startNavi + naviCountPerPage - 1;
		// 오류방지 코드
		if (endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}
		boolean needPrev = true;
		boolean needNext = true;
		if (startNavi == 1) {
			needPrev = false;
		}
		if (endNavi == pageTotalCount) {
			needNext = false;
		}
		StringBuilder sb = new StringBuilder();
		if (needPrev) {
			sb.append("<a href='/board/list?currentPage=" + (startNavi - 1) + "'> < </a>");
		}
		for (int i = startNavi; i <= endNavi; i++) {
			sb.append("<a href='/board/list?currentPage=" + i + "'>" + i + " </a>");
		}
		if (needNext) {
			sb.append("<a href='/board/list?currentPage=" + (endNavi + 1) + "'> > </a>");
		}
		return sb.toString();
	}

	public int totalCount(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		String query = "SELECT COUNT(*) AS TOTALCOUNT FROM CS_BOARD";
		int recordTotalCount = 0;
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			if (rset.next()) {
				recordTotalCount = rset.getInt("TOTALCOUNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		return recordTotalCount;
	}

	public Board selectOne(Connection conn, int boardNo) {
		PreparedStatement pstmt = null; // 위치홀더 사용함
		ResultSet rset = null;
		Board board = null;
		String query = "SELECT * FROM CS_BOARD WHERE CS_NO = ?"; // 위치홀더는 물음표
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				//System.out.println("잘되나확인해보자");
				board = new Board();
				board.setCsNo(rset.getInt("CS_NO"));
				board.setCsTitle(rset.getString("CS_TITLE"));
				board.setCsContents(rset.getString("CS_CONTENTS"));
				board.setCsDate(rset.getDate("CS_DATE"));
				board.setCsPublic(rset.getString("CS_PUBLIC"));
				board.setCsId(rset.getString("CS_ID"));
				board.setPwd(rset.getString("PASSWORD"));
				board.setCsFilePath(rset.getString("CS_FILEPATH"));
				board.setCsFileSize(rset.getInt("CS_FILESIZE"));
				board.setCsFileName(rset.getString("CS_FILENAME"));
				board.setCsUploadTime(rset.getTimestamp("CS_UPLOADTIME"));
				//여기까진 널이 아님
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		
		return board;
	}

	public int insertBoard(Connection conn, Board board) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO CS_BOARD VALUES(SEQ_CS_BOARD.NEXTVAL,?,?,SYSDATE,'Y',?,null,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, board.getCsTitle());
			pstmt.setString(2, board.getCsContents());
			//3번이 y<- 공개여부 흐린눈
			//4번부터 나도몰라..
			pstmt.setString(3, board.getCsId());
			pstmt.setString(4, board.getCsFilePath());
			pstmt.setLong(5, board.getCsFileSize());
			pstmt.setString(6, board.getCsFileName());
			pstmt.setTimestamp(7, board.getCsUploadTime());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int deleteBoard(Connection conn, int Cs_No) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "DELETE FROM CS_BOARD WHERE CS_NO = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, Cs_No);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int updateBoard(Connection conn, Board board) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE CS_BOARD " + "SET CS_TITLE = ?, CS_CONTENTS =? WHERE CS_NO = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, board.getCsTitle());
			pstmt.setString(2, board.getCsContents());
			pstmt.setInt(3, board.getCsNo());

			result = pstmt.executeUpdate();
			// executeUpdate() 안쓰면 쿼리문 실행안해요
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<Board> selectSearchList(Connection conn, String search, int currentPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM (SELECT ROW_NUMBER() OVER(ORDER BY CS_NO DESC) AS NUM, CS_NO, CS_TITLE, CS_CONTENTS,CS_DATE,CS_PUBLIC, CS_ID,PASSWORD,CS_FILEPATH,CS_FILESIZE,CS_FILENAME,CS_UPLOADTIME FROM CS_BOARD WHERE CS_TITLE LIKE ?) WHERE NUM BETWEEN ? AND ?";
																																																					// 위치홀더
																																	// 작성
																																																					// 및
																																																					// 세미콜론
																																																					// 삭제
		ArrayList<Board> bList = null;
		int recordCountPerPage = 10;
		int start = currentPage * recordCountPerPage - (recordCountPerPage - 1);
		int end = currentPage * recordCountPerPage;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + search + "%");
			pstmt.setInt(2, start); //////////////////////
			pstmt.setInt(3, end); //////////////////////
			rset = pstmt.executeQuery(); ////////////////////////////////
			bList = new ArrayList<Board>();
			while (rset.next()) {
				Board board = new Board();
				board.setCsNo(rset.getInt("CS_NO"));
				board.setCsTitle(rset.getString("CS_TITLE"));
				board.setCsContents(rset.getString("CS_CONTENTS"));
				board.setCsDate(rset.getDate("CS_DATE"));
				board.setCsPublic(rset.getString("CS_PUBLIC"));
				board.setCsId(rset.getString("CS_ID"));
				board.setPwd(rset.getString("PASSWORD"));
				board.setCsFilePath(rset.getString("CS_FILEPATH"));
				board.setCsFileSize(rset.getInt("CS_FILESIZE"));
				board.setCsFileName(rset.getString("CS_FILENAME"));
				board.setCsUploadTime(rset.getTimestamp("CS_UPLOADTIME"));

				bList.add(board);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return bList;
	}

	public String getSearchPageNavi(Connection conn, String search, int currentPage) {
		int recordCountPerPage = 10;
		int naviCountPerPage = 10;
		int recordTotalCount = searchTotalCount(conn, search);
		int pageTotalCount = 0;
		if (recordTotalCount % recordCountPerPage > 0) {
			pageTotalCount = recordTotalCount / recordCountPerPage + 1;
		} else {
			pageTotalCount = recordTotalCount / recordCountPerPage;
		}

		if (currentPage < 1) {
			currentPage = 1;
		} else if (currentPage > pageTotalCount) {
			currentPage = pageTotalCount;
		}
		int startNavi = ((currentPage - 1) / naviCountPerPage) * naviCountPerPage + 1;
		int endNavi = startNavi + naviCountPerPage - 1;
		if (endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}
		boolean needPrev = true;
		boolean needNext = true;
		if (startNavi == 1) {
			needPrev = false;
		}
		if (endNavi == pageTotalCount) {
			needNext = false;
		}

		StringBuilder sb = new StringBuilder();
		if (needPrev) {
			sb.append("<a href='/board/search?searchKeyword=" + search + "&currentPage=" + (startNavi - 1)
					+ "'> 이전 </a>");
		}
		for (int i = startNavi; i <= endNavi; i++) {
			sb.append("<a href='/board/search?searchKeyword=" + search + "&currentPage=" + i + "'>" + i + " </a>");
		}
		if (needNext) {
			sb.append(
					"<a href='/board/search?searchKeyword=" + search + "&currentPage=" + (endNavi + 1) + "'> 다음 </a>");
		}
		return sb.toString();
	}

	private int searchTotalCount(Connection conn, String search) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT COUNT(*) AS TOTALCOUNT FROM CS_BOARD WHERE CS_TITLE LIKE ?";
		int recordTotalCount = 0;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + search + "%");
			rset = pstmt.executeQuery();
			if (rset.next()) {
				recordTotalCount = rset.getInt("TOTALCOUNT");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return recordTotalCount;
	}

	

	public int deleteNotice(Connection conn, int cs_No) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	

	public int deleteFile(Connection conn, String filePath, String fileUser) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "DELETE FROM FILETBL WHERE FILEPATH = ? AND FILEUSER = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, filePath);
			pstmt.setString(2, fileUser);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	
	
}
