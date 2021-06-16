package shop.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.JDBCTemplate;
import file.model.vo.CRNImg;
import file.model.vo.ShopImg;
import shop.model.vo.ShopAndImg;
import shop.model.vo.ShopMember;

public class ShopMemDAO {
	// 업체회원 로그인
	public ShopMember selectOneMember(Connection conn, String userId, String userPwd) {
		Statement stmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM SHOP_MEMBER WHERE SHOP_ID = '" + userId + "' AND SHOP_PWD = '" + userPwd + "'";
		ShopMember shopMember = null;

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			if (rset.next()) {
				shopMember = new ShopMember();
				shopMember.setShopId(rset.getString("SHOP_ID"));
				shopMember.setShopPwd(rset.getString("SHOP_PWD"));
				shopMember.setShopName(rset.getString("SHOP_NAME"));
				shopMember.setCEOName(rset.getString("CEO_NAME"));
				shopMember.setShopCRN(rset.getString("SHOP_CRN"));
				shopMember.setShopLAdd(rset.getString("SHOP_L_ADD"));
				shopMember.setShopMAdd(rset.getString("SHOP_M_ADD"));
				shopMember.setShopSAdd(rset.getString("SHOP_S_ADD"));
				shopMember.setShopPhone(rset.getString("SHOP_PHONE"));
				shopMember.setShopEmail(rset.getString("SHOP_EMAIL"));
				shopMember.setLat(rset.getString("LAT"));
				shopMember.setLng(rset.getString("LNG"));
				shopMember.setShopRecieve(rset.getString("SHOP_RECIEVE"));
				shopMember.setApprovalYN(rset.getString("APPROVAL_YN"));
				shopMember.setShopAvgScore(rset.getInt("SHOP_AVG_SCORE"));
				shopMember.setWithdrawYN(rset.getString("WITHDRAW_YN"));
				shopMember.setShopEnroll(rset.getDate("SHOP_ENROLL"));
				shopMember.setOpeningStart(rset.getInt("OPENING_START"));
				shopMember.setOpeningEnd(rset.getInt("OPENING_END"));
				shopMember.setClosed(rset.getString("CLOSED"));
				shopMember.setShopWebsite(rset.getString("SHOP_WEBSITE"));
				shopMember.setIntroduced(rset.getString("INTRODUCE"));
				shopMember.setChatUrl(rset.getString("CHAT_URL"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		return shopMember;
	}

	// 업체 회원가입(insert)
	public int insertMember(Connection conn, ShopMember shopMember) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO SHOP_MEMBER(SHOP_ID, SHOP_PWD, SHOP_NAME, CEO_NAME, SHOP_CRN, SHOP_L_ADD, SHOP_M_ADD, SHOP_S_ADD, SHOP_PHONE, SHOP_EMAIL, SHOP_RECIEVE, SHOP_ENROLL, SHOP_WEBSITE, CHAT_URL) VALUES(?,?,?,?,?,?,?,?,?,?,?,SYSDATE,?,?)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, shopMember.getShopId());
			pstmt.setString(2, shopMember.getShopPwd());
			pstmt.setString(3, shopMember.getShopName());
			pstmt.setString(4, shopMember.getCEOName());
			pstmt.setString(5, shopMember.getShopCRN());
			pstmt.setString(6, shopMember.getShopLAdd());
			pstmt.setString(7, shopMember.getShopMAdd());
			pstmt.setString(8, shopMember.getShopSAdd());
			pstmt.setString(9, shopMember.getShopPhone());
			pstmt.setString(10, shopMember.getShopEmail());
			pstmt.setString(11, shopMember.getShopRecieve());
			pstmt.setString(12, shopMember.getShopWebsite());
			pstmt.setString(13, shopMember.getChatUrl());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	// 아이디 찾기
	public ShopMember findId(Connection conn, String email) {
		Statement stmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM SHOP_MEMBER WHERE SHOP_EMAIL='" + email + "'";
		ShopMember shopMember = null;

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			if (rset.next()) {
				shopMember = new ShopMember();
				shopMember.setShopId(rset.getString("SHOP_ID"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		return shopMember;
	}

	// 비밀번호 재설정
	public int resetPwd(Connection conn, String id, String pwd) {
		int result = 0;
		Statement stmt = null;
		String query = "UPDATE SHOP_MEMBER SET SHOP_PWD='" + pwd + "' WHERE SHOP_ID='" + id + "'";

		try {
			stmt = conn.createStatement();
			result = stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(stmt);
		}

		return result;
	}

	// CRN_IMG 삽입
	public int registerCrnFile(Connection conn, CRNImg crnImgFile) {
		int imgResult = 0;
		PreparedStatement pstmt = null;
		String query = "INSERT INTO CRN_IMG VALUES(SEQ_CRN_IMG.NEXTVAL,?,?,?,?,?,?)";
		// 패스, 사이즈, 유저, 네임, 업로드타임, 샵 아이디
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, crnImgFile.getCrnFilePath());
			pstmt.setLong(2, crnImgFile.getCrnFileSize());
			pstmt.setString(3, crnImgFile.getCrnFileUser());
			pstmt.setString(4, crnImgFile.getCrnFileName());
			pstmt.setTimestamp(5, crnImgFile.getCrnUploadTime());
			pstmt.setString(6, crnImgFile.getCrnFileUser());
			imgResult = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}

		return imgResult;
	}

	// 업체회원 아이디 중복 체크 검사
	public int shopCheckId(Connection conn, String userId) {
		Statement stmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "SELECT COUNT(SHOP_ID) AS CNT FROM SHOP_MEMBER WHERE SHOP_ID = '" + userId + "'";

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			if (rset.next()) {
				result = rset.getInt("CNT");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		return result;
	}

	// 업체 회원 정보 수정
	public int updateShop(Connection conn, ShopMember sMember) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE SHOP_MEMBER SET SHOP_PWD = ?, SHOP_NAME = ?, CEO_NAME = ?, SHOP_L_ADD = ?, SHOP_M_ADD = ?, SHOP_S_ADD = ?, SHOP_PHONE = ?, SHOP_EMAIL = ?, SHOP_WEBSITE = ?, CHAT_URL = ? WHERE SHOP_ID = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, sMember.getShopPwd());
			pstmt.setString(2, sMember.getShopName());
			pstmt.setString(3, sMember.getCEOName());
			pstmt.setString(4, sMember.getShopLAdd());
			pstmt.setString(5, sMember.getShopMAdd());
			pstmt.setString(6, sMember.getShopSAdd());
			pstmt.setString(7, sMember.getShopPhone());
			pstmt.setString(8, sMember.getShopEmail());
			pstmt.setString(9, sMember.getShopWebsite());
			pstmt.setString(10, sMember.getChatUrl());
			pstmt.setString(11, sMember.getShopId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	// 탈퇴 신청시 비밀번호 비교 메소드
	public ShopMember compareWithPw(Connection conn, String shopId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ShopMember sMember = null;
		String query = "SELECT SHOP_PWD FROM SHOP_MEMBER WHERE SHOP_ID = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, shopId);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				sMember = new ShopMember();
				sMember.setShopPwd(rset.getString("SHOP_PWD"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		System.out.println(sMember);
		return sMember;
	}

	// 비밀번호 일치 후 탈퇴 신청 여부 업로드 메소드
	public int withdrawShop(Connection conn, String shopId, String withdrawY) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE SHOP_MEMBER SET WITHDRAW_YN = ? WHERE SHOP_ID = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, withdrawY);
			pstmt.setString(2, shopId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		System.out.println(withdrawY);
		return result;
	}

	// 업체 회원 1명의 업체 정보 및 가입 정보 가져오기 (업체회원 로그인 메소드 오버로딩)
	public ShopMember selectOneMember(Connection conn, String shopId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM SHOP_MEMBER WHERE SHOP_ID = ?";
		ShopMember sMember = null;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, shopId);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				sMember = new ShopMember();
				sMember.setShopId(rset.getString("SHOP_ID"));
				sMember.setShopPwd(rset.getString("SHOP_PWD"));
				sMember.setShopName(rset.getString("SHOP_NAME"));
				sMember.setCEOName(rset.getString("CEO_NAME"));
				sMember.setShopCRN(rset.getString("SHOP_CRN"));
				sMember.setShopLAdd(rset.getString("SHOP_L_ADD"));
				sMember.setShopMAdd(rset.getString("SHOP_M_ADD"));
				sMember.setShopSAdd(rset.getString("SHOP_S_ADD"));
				sMember.setShopPhone(rset.getString("SHOP_PHONE"));
				sMember.setShopEmail(rset.getString("SHOP_EMAIL"));
				sMember.setLat(rset.getString("LAT"));
				sMember.setLng(rset.getString("LNG"));
				sMember.setShopRecieve(rset.getString("SHOP_RECIEVE"));
				sMember.setApprovalYN(rset.getString("APPROVAL_YN"));
				sMember.setShopAvgScore(rset.getInt("SHOP_AVG_SCORE"));
				sMember.setWithdrawYN(rset.getString("WITHDRAW_YN"));
				sMember.setShopEnroll(rset.getDate("SHOP_ENROLL"));
				sMember.setOpeningStart(rset.getInt("OPENING_START"));
				sMember.setOpeningEnd(rset.getInt("OPENING_END"));
				sMember.setClosed(rset.getString("CLOSED"));
				sMember.setShopWebsite(rset.getString("SHOP_WEBSITE"));
				sMember.setIntroduced(rset.getString("INTRODUCE"));
				sMember.setChatUrl(rset.getString("CHAT_URL"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return sMember;
	}

	// 업체 회원 정보 수정 - 사업자등록증
	public int updateShop(Connection conn, CRNImg crnImg) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE CRN_IMG SET CRN_FILENO = SEQ_CRN_IMG.NEXTVAL, CRN_FILEPATH = ?, CRN_FILESIZE = ?, CRN_FILEUSER = ?, CRN_FILENAME = ?, CRN_UPLOADTIME = ? WHERE SHOP_ID = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, crnImg.getCrnFilePath());
			pstmt.setLong(2, crnImg.getCrnFileSize());
			pstmt.setString(3, crnImg.getCrnFileUser());
			pstmt.setString(4, crnImg.getCrnFileName());
			pstmt.setTimestamp(5, crnImg.getCrnUploadTime());
			pstmt.setString(6, crnImg.getCrnFileUser());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	// 업체 이미지 등록
	public int enrollShopInfo(Connection conn, ShopImg sImg) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO SHOP_IMG VALUES (SHOP_FILENO, SHOP_FILEPATH, SHOP_FILESIZE, SHOP_FILEUSER, SHOP_FILENAME, SHOP_UPLOADTIME) = (SEQ_SHOP_IMG.NEXT_VAL,?,?,?,?,?,?)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, sImg.getShopFilePath());
			pstmt.setLong(2, sImg.getShopFileSize());
			pstmt.setString(3, sImg.getShopFileUser());
			pstmt.setString(4, sImg.getShopFileName());
			pstmt.setTimestamp(5, sImg.getShopUploadTime());
			pstmt.setString(6, sImg.getShopFileUser());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	// 업체 가게 정보 수정
	public int modifyShopInfo(Connection conn, ShopMember sMember) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE SHOP_MEMBER SET SHOP_NAME = ?, SHOP_L_ADD = ?, SHOP_M_ADD = ?, SHOP_S_ADD = ?, OPENING_START = ?, OPENING_END = ?, CLOSED = ?, SHOP_WEBSITE = ?, INTRODUCE = ?, CHAT_URL = ? WHERE SHOP_ID = ?";
		System.out.println(sMember.toString());
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, sMember.getShopName());
			pstmt.setString(2, sMember.getShopLAdd());
			pstmt.setString(3, sMember.getShopMAdd());
			pstmt.setString(4, sMember.getShopSAdd());
			pstmt.setInt(5, sMember.getOpeningStart());
			pstmt.setInt(6, sMember.getOpeningEnd());
			pstmt.setString(7, sMember.getClosed());
			pstmt.setString(8, sMember.getShopWebsite());
			pstmt.setString(9, sMember.getIntroduced());
			pstmt.setString(10, sMember.getChatUrl());
			pstmt.setString(11, sMember.getShopId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	// 업체 가게 사진 정보 수정
//	public int modifyShopInfo(Connection conn, ShopImg sImg) {
//		PreparedStatement pstmt = null;
//		int result = 0;
//		String query = "UPDATE SHOP_IMG SET SHOP_FILEPATH = ?, SHOP_FILESIZE = ?, SHOP_FILEUSER = ?, SHOP_FILENAME = ?, SHOP_UPLOADTIME = ? WHERE SHOP_ID = ?";
//
//		try {
//			pstmt = conn.prepareStatement(query);
//			pstmt.setString(1, sImg.getShopFilePath());
//			pstmt.setLong(2, sImg.getShopFileSize());
//			pstmt.setString(3, sImg.getShopFileUser());
//			pstmt.setString(4, sImg.getShopFileName());
//			pstmt.setTimestamp(5, sImg.getShopUploadTime());
//			pstmt.setString(6, sImg.getShopFileUser());
//			result = pstmt.executeUpdate();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			JDBCTemplate.close(pstmt);
//		}
//		return result;
//	}
	
	public int modifyShopInfo(Connection conn, ShopImg sImg) {
	PreparedStatement pstmt = null;
	int result = 0;
	String query = "UPDATE SHOP_IMG SET SHOP_FILEPATH = ?, SHOP_FILESIZE = ?, SHOP_FILEUSER = ?, SHOP_FILENAME = ?, SHOP_UPLOADTIME = ? WHERE SHOP_ID = ?";

	try {
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, sImg.getShopFilePath());
		pstmt.setLong(2, sImg.getShopFileSize());
		pstmt.setString(3, sImg.getShopFileUser());
		pstmt.setString(4, sImg.getShopFileName());
		pstmt.setTimestamp(5, sImg.getShopUploadTime());
		pstmt.setString(6, sImg.getShopFileUser());
		result = pstmt.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
		JDBCTemplate.close(pstmt);
	}
	return result;
	}

	public ShopImg selectShopImg(Connection conn, String shopId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM SHOP_IMG WHERE SHOP_ID = ?";
		ShopImg shopImg = new ShopImg();

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, shopId);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				shopImg.setShopFileName(rset.getString("SHOP_FILENAME"));
				shopImg.setShopFilePath("/img/" + rset.getString("SHOP_FILENAME"));
				shopImg.setShopFileSize(rset.getLong("SHOP_FILESIZE"));
				shopImg.setShopFileUser(rset.getString("SHOP_FILEUSER"));
				shopImg.setShopFileNo(rset.getInt("SHOP_FILENO"));
				shopImg.setShopFileName(rset.getString("SHOP_FILENAME"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return shopImg;
	}

	// 사업자등록증 조회
	public CRNImg selectCrnImg(Connection conn, String shopId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM CRN_IMG WHERE SHOP_ID = ?";
		CRNImg crnImg = new CRNImg();

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, shopId);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				crnImg.setCrnFileName(rset.getString("CRN_FILENAME"));
				crnImg.setCrnFilePath("/img/" + rset.getString("CRN_FILENAME"));
				crnImg.setCrnFileSize(rset.getLong("CRN_FILESIZE"));
				crnImg.setCrnFileUser(rset.getString("CRN_FILEUSER"));
				crnImg.setCrnUploadTime(rset.getTimestamp("CRN_UPLOADTIME"));
				crnImg.setCrnFileNo(rset.getInt("CRN_FILENO"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return crnImg;
	}

	// 전체 목록
	public ArrayList<ShopAndImg> selectAllList(Connection conn, int currentPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<ShopAndImg> sList = null;
		String query = "SELECT * FROM (SELECT ROW_NUMBER() OVER(ORDER BY SHOP_NAME) AS NUM, SHOP_ID, SHOP_NAME, INTRODUCE, SHOP_AVG_SCORE, SHOP_FILEPATH, SHOP_FILENAME FROM (SELECT * FROM SHOP_MEMBER LEFT OUTER JOIN SHOP_IMG USING(SHOP_ID))) WHERE NUM BETWEEN ? AND ?";
		int recordCountPerPage = 5;
		int start = currentPage * recordCountPerPage - (recordCountPerPage - 1);
		int end = currentPage * recordCountPerPage;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();

			sList = new ArrayList<ShopAndImg>();
			while (rset.next()) {
				// SHOP_NAME, INTRODUCE, SHOP_AVG_SCORE, CRN_FILEPATH
				ShopAndImg sAndI = new ShopAndImg();
				sAndI.setShopId(rset.getString("SHOP_ID"));
				sAndI.setShopName(rset.getString("SHOP_NAME"));
				sAndI.setIntroduced(rset.getString("INTRODUCE"));
				sAndI.setShopAvgScore(rset.getInt("SHOP_AVG_SCORE"));
//				sAndI.setShopFilePath(rset.getString("SHOP_FILEPATH"));
				sAndI.setShopFilePath("/img/" + rset.getString("SHOP_FILENAME"));
				// SHOP_FILENAME
				sList.add(sAndI);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		if (sList.isEmpty())
			System.out.println("안");
		return sList;
	}

	public String getPageNavi(Connection conn, int currentPage) {
		int recordTotalCount = totalCount(conn);
		int recordCountPerPage = 5;
		int pageTotalCount = 0;
		if (recordTotalCount % recordCountPerPage > 0) {
			pageTotalCount = recordTotalCount / recordCountPerPage + 1;
		} else {
			pageTotalCount = recordTotalCount / recordCountPerPage;
		}
		// 오류방지 코드 1
		if (currentPage < 1) {
			currentPage = 1;
		} else if (currentPage > pageTotalCount) {
			currentPage = pageTotalCount;
		}

		int naviCountPerPage = 10;
		int startNavi = ((currentPage - 1) / naviCountPerPage) * naviCountPerPage + 1;
		int endNavi = startNavi + naviCountPerPage - 1;

		// 오류방지 코드 2: endNavi값을 최대값을 제한함
		if (endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}

		boolean needPrev = true;
		boolean needNext = true;
		if (startNavi == 1) { // currentPage가 1~10 중 하나인 경우
			needPrev = false;
		}
		if (endNavi == pageTotalCount) { // currentPage가 마지막 pageNavi 중 하나인 경우(11~13)
			needNext = false;
		}
		StringBuilder sb = new StringBuilder(); // String 값이 누적됨, 메모리 절약 방식
		if (needPrev) {
			sb.append("<a href='/shop/list?currentPage=" + (startNavi - 1) + "'> < </a>"); // currentPage에 하나 적은 값을 넣어
																							// (ex. 11-1 = 10) 다시
																							// Servlet~NoticeDAO.getPageNavi()가
																							// 작동하면 startNavi가 1로 변경 ->
																							// 1~10 navi가 생김
		}
		for (int i = startNavi; i <= endNavi; i++) {
			sb.append("<a href='/shop/list?currentPage=" + i + "'>" + i + " </a>"); // 앵커 태그 작동: currentPage에 맞는 게시물 출력
																					// + currentPage에 맞는 pageNavi가 출력됨
		} // selectAllList() getPageNavi()
		if (needNext) {
			sb.append("<a href='/shop/list?currentPage=" + (endNavi + 1) + "'> > </a>"); // 사용자가 (currentPage 10+1 =
																							// 11)을 누른 효과
		}
		System.out.println("start/endnavi:" + startNavi + endNavi);
		System.out.println("pagetotalcout:" + pageTotalCount);
		System.out.println();
		System.out.println(sb.toString());
		return sb.toString();
	}

	public int totalCount(Connection conn) {

		Statement stmt = null;
		ResultSet rset = null;
		String query = "SELECT COUNT(*) AS TOTALCOUNT FROM SHOP_MEMBER";
		int recordTotalCount = 0;
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			if (rset.next()) {
				recordTotalCount = rset.getInt("TOTALCOUNT");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		System.out.println("recordTotalCount:" + recordTotalCount);
		return recordTotalCount;
	}

	// 케이크 지역 + 평점 정렬 목록
	public ArrayList<ShopAndImg> selectSearchList(Connection conn, String shopLAdd, String array, int currentPage) {
		Statement stmt = null;
		ResultSet rset = null;
		String all = "all";
		ArrayList<ShopAndImg> siList = null;
		System.out.println(all);
		int recordCountPerPage = 5;
		int start = currentPage * recordCountPerPage - (recordCountPerPage - 1);
		int end = currentPage * recordCountPerPage;
		StringBuilder query = new StringBuilder();
		query.append(
				"SELECT * FROM (SELECT ROW_NUMBER() OVER(ORDER BY SHOP_NAME) AS NUM, SHOP_ID, SHOP_NAME, SHOP_L_ADD, INTRODUCE, NVL(SHOP_AVG_SCORE,0), SHOP_FILENAME, NVL(RCOUNT,0) AS RCOUNT, NVL(SHOP_AVG_SCORE,0) AS SHOP_AVG_SCORE FROM (SELECT A.SHOP_ID, SHOP_NAME, SHOP_L_ADD, INTRODUCE, SHOP_FILENAME, NVL(RCOUNT,0) AS RCOUNT, NVL(SHOP_SCORE, 0) AS SHOP_AVG_SCORE FROM (SELECT * FROM (SELECT * FROM SHOP_MEMBER LEFT OUTER JOIN SHOP_IMG USING(SHOP_ID)) LEFT OUTER JOIN (SELECT SHOP_ID, COUNT(*) AS RCOUNT FROM REVIEW GROUP BY SHOP_ID) USING(SHOP_ID)) A LEFT OUTER JOIN (SELECT SHOP_ID, ROUND(AVG(REVIEW_SCORE)) AS SHOP_SCORE FROM REVIEW GROUP BY SHOP_ID) B ON A.SHOP_ID = B.SHOP_ID) ");
		if (!(shopLAdd.equals(all))) {
			query.append(" WHERE SHOP_L_ADD LIKE '%" + shopLAdd + "%' ");
		}
		if (array.equals("SHOP_AVG_SCORE")) // 평점순
			query.append("ORDER BY SHOP_AVG_SCORE DESC");
		if (array.equals("RCOUNT")) // 후기순
			query.append("ORDER BY RCOUNT DESC");
		query.append(") WHERE NUM BETWEEN " + start + " AND " + end);

		System.out.println(query.toString());

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query.toString());
			siList = new ArrayList<ShopAndImg>();
			while (rset.next()) {
				ShopAndImg sAndI = new ShopAndImg();
				sAndI.setShopId(rset.getString("SHOP_ID"));
				sAndI.setShopName(rset.getString("SHOP_NAME"));
				sAndI.setIntroduced(rset.getString("INTRODUCE"));
				sAndI.setShopAvgScore(rset.getInt("SHOP_AVG_SCORE"));
//				sAndI.setShopFilePath(rset.getString("SHOP_FILEPATH"));
				sAndI.setShopFilePath("/img/" + rset.getString("SHOP_FILENAME"));
				siList.add(sAndI);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		System.out.println("dao: " + siList.size());
		return siList;
	}

	// String array,
	public String getSearchPageNavi(Connection conn, String shopLAdd, String array, int currentPage) {
		int recordTotalCount = searchTotalCount(conn, shopLAdd);
		int recordCountPerPage = 5;
		int pageTotalCount = 0;
		if (recordTotalCount % recordCountPerPage > 0) {
			pageTotalCount = recordTotalCount / recordCountPerPage + 1;
		} else {
			pageTotalCount = recordTotalCount / recordCountPerPage;
		}
		// 오류방지 코드 1
		if (currentPage < 1) {
			currentPage = 1;
		} else if (currentPage > pageTotalCount) {
			currentPage = pageTotalCount;
		}

		int naviCountPerPage = 10;
		int startNavi = ((currentPage - 1) / naviCountPerPage) * naviCountPerPage + 1;
		int endNavi = startNavi + naviCountPerPage - 1;

		// 오류방지 코드 2: endNavi값을 최대값을 제한함
		if (endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}

		boolean needPrev = true;
		boolean needNext = true;
		if (startNavi == 1) { // currentPage가 1~10 중 하나인 경우
			needPrev = false;
		}
		if (endNavi == pageTotalCount) { // currentPage가 마지막 pageNavi 중 하나인 경우(11~13)
			needNext = false;
		}
		// http://localhost:8888/shop/search?currentPage=1 + "&area=" + shopLAdd +
		// "&array=" + array
		StringBuilder sb = new StringBuilder(); // String 값이 누적됨, 메모리 절약 방식
		if (needPrev) {
			sb.append("<a href='/shop/search?currentPage=" + (startNavi - 1) + "&area=" + shopLAdd + "&array=" + array
					+ "'> < </a>"); // currentPage에 하나 적은 값을 넣어 (ex. 11-1 = 10) 다시 Servlet~NoticeDAO.getPageNavi()가
									// 작동하면 startNavi가 1로 변경 -> 1~10 navi가 생김
		}
		for (int i = startNavi; i <= endNavi; i++) {
			sb.append("<a href='/shop/search?currentPage=" + i + "&area=" + shopLAdd + "&array=" + array + "'>" + i
					+ " </a>"); // 앵커 태그 작동: currentPage에 맞는 게시물 출력 + currentPage에 맞는 pageNavi가 출력됨
		} // selectAllList() getPageNavi()
		if (needNext) {
			sb.append("<a href='/shop/search?currentPage=" + (endNavi + 1) + "&area=" + shopLAdd + "&array=" + array
					+ "'> > </a>"); // 사용자가 (currentPage 10+1 = 11)을 누른 효과
		}
		return sb.toString();
	}

	public int searchTotalCount(Connection conn, String shopLAdd) {

		Statement stmt = null;
		ResultSet rset = null;
		String query = "SELECT COUNT(*) AS TOTALCOUNT FROM SHOP_MEMBER";
		if (!shopLAdd.equals("all")) {
			query += " WHERE SHOP_L_ADD LIKE '%" + shopLAdd + "%'";
		}
		int recordTotalCount = 0;
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			if (rset.next()) {
				recordTotalCount = rset.getInt("TOTALCOUNT");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		return recordTotalCount;
	}

	// 업체 정보 상세화면
	public ShopAndImg selectOne(Connection conn, String shopId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ShopAndImg shopAndImg = null;
		String query = "SELECT SHOP_ID, SHOP_NAME, SHOP_AVG_SCORE, INTRODUCE, SHOP_WEBSITE, SHOP_FILEPATH, SHOP_FILENAME, CHAT_URL, SHOP_L_ADD, SHOP_M_ADD, SHOP_S_ADD, OPENING_START, OPENING_END, CLOSED FROM SHOP_MEMBER LEFT OUTER JOIN SHOP_IMG USING (SHOP_ID) WHERE SHOP_ID = ?";
		// CHAT_URL, SHOP_L_ADD, SHOP_M_ADD, SHOP_S_ADD, OPENING_START, OPENING_END,
		// CLOSED
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, shopId);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				shopAndImg = new ShopAndImg();
				shopAndImg.setShopId(rset.getString("SHOP_ID"));
				shopAndImg.setShopName(rset.getString("SHOP_NAME"));
				shopAndImg.setShopAvgScore(rset.getInt("SHOP_AVG_SCORE"));
				shopAndImg.setIntroduced(rset.getString("INTRODUCE"));
				shopAndImg.setShopWebsite(rset.getString("SHOP_WEBSITE"));
//				shopAndImg.setShopFilePath(rset.getString("SHOP_FILEPATH"));
				shopAndImg.setShopFilePath("/img/" + rset.getString("SHOP_FILENAME"));
				shopAndImg.setChatUrl(rset.getString("CHAT_URL"));
				shopAndImg.setShopLAdd(rset.getString("SHOP_L_ADD"));
				shopAndImg.setShopMAdd(rset.getString("SHOP_M_ADD"));
				shopAndImg.setShopSAdd(rset.getString("SHOP_S_ADD"));
				shopAndImg.setOpeningStart(rset.getInt("OPENING_START"));
				shopAndImg.setOpeningEnd(rset.getInt("OPENING_END"));
				shopAndImg.setClosed(rset.getString("CLOSED"));
				String addrAll = rset.getString("SHOP_L_ADD") + " " + rset.getString("SHOP_M_ADD") + " "
						+ rset.getString("SHOP_S_ADD");
				String addrMap = rset.getString("SHOP_L_ADD") + " " + rset.getString("SHOP_M_ADD");
				shopAndImg.setAddrAll(addrAll);
				shopAndImg.setAddrMap(addrMap);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		System.out.println("DAO: " + shopAndImg.toString());
		return shopAndImg;
	}

	public ShopMember selectOneById(Connection conn, String shopId) {
		Statement stmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM SHOP_MEMBER WHERE SHOP_ID = '" + shopId + "'";
		ShopMember sMember = null;
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			if (rset.next()) {
				sMember = new ShopMember();
				sMember.setShopId(rset.getString("SHOP_ID"));
				sMember.setShopPwd(rset.getString("SHOP_PWD"));
				sMember.setShopName(rset.getString("SHOP_NAME"));
				sMember.setCEOName(rset.getString("CEO_NAME"));
				sMember.setShopCRN(rset.getString("SHOP_CRN"));
				sMember.setShopLAdd(rset.getString("SHOP_L_ADD"));
				sMember.setShopMAdd(rset.getString("SHOP_M_ADD"));
				sMember.setShopSAdd(rset.getString("SHOP_S_ADD"));
				sMember.setShopPhone(rset.getString("SHOP_PHONE"));
				sMember.setShopEmail(rset.getString("SHOP_EMAIL"));
				sMember.setLat(rset.getString("LAT"));
				sMember.setLng(rset.getString("LNG"));
				sMember.setShopRecieve(rset.getString("SHOP_RECIEVE"));
				sMember.setApprovalYN(rset.getString("APPROVAL_YN"));
				sMember.setShopAvgScore(rset.getInt("SHOP_AVG_SCORE"));
				sMember.setWithdrawYN(rset.getString("WITHDRAW_YN"));
				sMember.setShopEnroll(rset.getDate("SHOP_ENROLL"));
				sMember.setOpeningStart(rset.getInt("OPENING_START"));
				sMember.setOpeningEnd(rset.getInt("OPENING_END"));
				sMember.setClosed(rset.getString("CLOSED"));
				sMember.setShopWebsite(rset.getString("SHOP_WEBSITE"));
				sMember.setIntroduced(rset.getString("INTRODUCE"));
				sMember.setChatUrl(rset.getString("CHAT_URL"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(stmt);
			JDBCTemplate.close(rset);
		}
		return sMember;
	}

	public int updateMember(Connection conn, ShopMember sMember) {
		PreparedStatement pstmt = null;
		String query = "UPDATE SHOP_MEMBER SET SHOP_PWD=?, SHOP_NAME=?, CEO_NAME=?, SHOP_L_ADD=?, SHOP_M_ADD=?, SHOP_S_ADD=?, SHOP_PHONE=?, SHOP_EMAIL=?, SHOP_CRN=? WHERE SHOP_ID = '"
				+ sMember.getShopId() + "'";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, sMember.getShopPwd());
			pstmt.setString(2, sMember.getShopName());
			pstmt.setString(3, sMember.getCEOName());
			pstmt.setString(4, sMember.getShopLAdd());
			pstmt.setString(5, sMember.getShopMAdd());
			pstmt.setString(6, sMember.getShopSAdd());
			pstmt.setString(7, sMember.getShopPhone());
			pstmt.setString(8, sMember.getShopEmail());
			pstmt.setString(9, sMember.getShopCRN());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

//	필요할지도 모르니까 킵
//	//정렬된 목록
//	public ArrayList<ShopMemDAO> selectArrayList(Connection conn, String array, int currentPage) {
//		//where 서울 orderby 평점 desc/asc
//		return null;
//	}
//
//	public String getArrayPageNavi(Connection conn, String array, int currentPage) {
//		
//		return null;
//	}
//	
//	public int ArrayTotalCount(Connection conn, String search) {
//
//		return 0;
//	}

}