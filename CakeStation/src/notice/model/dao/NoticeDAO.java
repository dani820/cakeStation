package notice.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.JDBCTemplate;
import notice.model.vo.Notice;

public class NoticeDAO {

	public ArrayList<Notice> selectAllList(Connection conn, int currentPage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Notice> nList = null;
		String query = "SELECT NOTICE_NO, NOTICE_TITLE, NOTICE_CONTENTS, NOTICE_DATE, MEMBER_ID, NOTICE_FILEPATH, "
				+ "NOTICE_FILESIZE, NOTICE_UPLOADTIME, NOTICE_FILENAME "
				+ "FROM ( SELECT ROW_NUMBER()OVER(ORDER BY NOTICE_NO DESC) AS NUM, "
				+ "NOTICE_NO, NOTICE_TITLE, NOTICE_CONTENTS, NOTICE_DATE, MEMBER_ID, NOTICE_FILEPATH, "
				+ "NOTICE_FILESIZE, NOTICE_UPLOADTIME, NOTICE_FILENAME FROM NOTICE ORDER BY NOTICE_NO DESC) "
				+ "WHERE NUM >= ? AND NUM<= ? ";
		// (SELECT ROW_NUMBER() OVER(ORDER BY NOTICE_NO DESC) AS NUM, NOTICE_NO,
		// NOTICE_TITLE, NOTICE_CONTENTS, NOTICE_DATE, MEMBER_ID, NOTICE_FILEPATH,
		// NOTICE_FILESIZE, NOTICE_UPLOADTIME, NOTICE_FILENAME FROM NOTICE) WHERE NUM
		// BETWEEN ? AND ?

		int recordCountPerPage = 10;
		int start = currentPage * recordCountPerPage - (recordCountPerPage - 1);
		int end = currentPage * recordCountPerPage;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			nList = new ArrayList<Notice>();
			while (rs.next()) {
				Notice notice = new Notice();
				notice.setNotice_No(rs.getInt("Notice_No"));
				notice.setNotice_Title(rs.getString("Notice_Title"));
				notice.setNotice_Contents(rs.getString("Notice_Contents"));
				notice.setNotice_Date(rs.getDate("Notice_Date"));
				notice.setMember_Id(rs.getString("Member_Id"));
				notice.setNotice_FilePath(rs.getString("Notice_FilePath"));
				notice.setNotice_uploadTime(rs.getTimestamp("Notice_uploadTime"));
				notice.setNotice_FileName(rs.getString("Notice_FileName"));
				nList.add(notice);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}

		return nList;

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
		if (currentPage < 1) {
			currentPage = 1;

		} else if (currentPage > pageTotalCount) {
			currentPage = pageTotalCount;
		}
		int naviCountPerPage = 10;
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
			sb.append("<a href='/notice/list?currentPage=" + (startNavi - 1) + "'> < </a>");

		}
		for (int i = startNavi; i <= endNavi; i++) {
			sb.append("<a href='/notice/list?currentPage=" + i + "'>" + i + " </a>");

		}
		if (needNext) {
			sb.append("<a href = '/notice/list?current=" + (endNavi + 1) + "' > > < /a> ");
		}
		return sb.toString();
	}

	public int totalCount(Connection conn) {
		Statement stmt = null;
		ResultSet rs = null;
		String query = "SELECT COUNT(*) AS TOTALCOUNT FROM NOTICE";
		int recordTotalCount = 0;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				recordTotalCount = rs.getInt("TOTALCOUNT");

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(stmt);

		}
		return recordTotalCount;
	}

	public Notice selectOne(Connection conn, int Notice_No) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Notice notice = null;
		String query = "SELECT NOTICE_NO,NOTICE_TITLE,NOTICE_CONTENTS,MEMBER_ID,NOTICE_DATE,NOTICE_FILENAME,NOTICE_FILEPATH FROM NOTICE WHERE NOTICE_NO =?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, Notice_No);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				notice = new Notice();
				notice.setNotice_No(rs.getInt("NOTICE_NO"));
				notice.setNotice_Title(rs.getString("NOTICE_TITLE"));
				notice.setNotice_Contents(rs.getString("NOTICE_CONTENTS"));
				notice.setMember_Id(rs.getString("MEMBER_ID"));
				notice.setNotice_Date(rs.getDate("NOTICE_DATE"));
				notice.setNotice_FileName(rs.getString("NOTICE_FILENAME"));
				notice.setNotice_FilePath(rs.getString("NOTICE_FILEPATH"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rs);

		}
		System.out.println(notice.toString());
		return notice;

	}

	public int insertNotice(Connection conn, Notice notice) {

		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO NOTICE(NOTICE_NO,MEMBER_ID,NOTICE_TITLE,NOTICE_CONTENTS,NOTICE_DATE,NOTICE_FILENAME,NOTICE_FILEPATH) "
				+ "VALUES(SEQ_NOTICE.NEXTVAL,?,?,?,SYSDATE,?,?) ";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, notice.getMember_Id());
			pstmt.setString(2, notice.getNotice_Title());
			pstmt.setString(3, notice.getNotice_Contents());
			pstmt.setString(4, notice.getNotice_FileName());
			pstmt.setString(5, notice.getNotice_FilePath());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);

		}
		return result;

	}

	public int deleteNotice(Connection conn, int Notice_No) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "DELETE FROM NOTICE WHERE NOTICE_NO =? ";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, Notice_No);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;

	}

	public int updateNotice(Connection conn, Notice notice) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE NOTICE " + "SET NOTICE_TITLE =? , NOTICE_CONTENTS =? WHERE NOTICE_NO = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, notice.getNotice_Title());
			pstmt.setString(2, notice.getNotice_Contents());
			pstmt.setInt(3, notice.getNotice_No());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<Notice> selectSearchList(Connection conn, String search, int currentPage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT * FROM ( SELECT ROW_NUMBER() OVER(ORDER BY NOTICE_NO DESC) AS NUM, NOTICE_NO, NOTICE_TITLE, NOTICE_CONTENTS, MEMBER_ID, NOTICE_DATE FROM NOTICE WHERE NOTICE_TITLE LIKE ? ) WHERE NUM BETWEEN ? AND ?";
		ArrayList<Notice> nList = null;
		int recordCountPerPage = 10;
		int start = currentPage * recordCountPerPage - (recordCountPerPage - 1);
		int end = currentPage * recordCountPerPage;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + search + "%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rs = pstmt.executeQuery();
			nList = new ArrayList<Notice>();
			while (rs.next()) {
				Notice notice = new Notice();
				notice.setNotice_No(rs.getInt("NOTICE_NO"));
				notice.setNotice_Title(rs.getString("NOTICE_TITLE"));
				notice.setNotice_Contents(rs.getString("NOTICE_CONTENTS"));
				notice.setMember_Id(rs.getString("MEMBER_ID"));
				notice.setNotice_Date(rs.getDate("NOTICE_DATE"));
				nList.add(notice);

			}
			System.out.println(nList.size());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);

		}
		return nList;

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
		if (needPrev) { // 1 2 3 4 5, 6 7 8 9 10
			sb.append("<a href='/notice/search?searchKeyword=" + search + "&currentPage=" + (startNavi - 1)
					+ "'> 이전 </a>");
		}
		for (int i = startNavi; i <= endNavi; i++) {
			sb.append("<a href='/notice/search?searchKeyword=" + search + "&currentPage=" + i + "'>" + i + " </a>");
		}
		if (needNext) {
			sb.append(
					"<a href='/notice/search?searchKeyword=" + search + "&currentPage=" + (endNavi + 1) + "'> 다음 </a>");
		}
		return sb.toString();
	}

	private int searchTotalCount(Connection conn, String search) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT COUNT(*) AS TOTALCOUNT FROM NOTICE WHERE NOTICE_TITLE LIKE ?";
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

	public Object selectCommentList(Connection conn, int noticeNo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
