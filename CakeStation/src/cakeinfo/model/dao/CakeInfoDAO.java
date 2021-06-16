package cakeinfo.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import cakeinfo.model.vo.CakeAndShop;
import cakeinfo.model.vo.CakeInfo;
import cakeinfo.model.vo.MainCakeInfo;
import cakeinfo.model.vo.MainSearchCakeInfo;
import common.JDBCTemplate;

// 사진가져올때 메인화면 사진은 type값 쿼리에 포함!!!
public class CakeInfoDAO {

//	등록한 케익 리스트 전체 출력
	public ArrayList<CakeInfo> selectAllCake(Connection conn, int currentPage, String shopId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<CakeInfo> cList = null;
		String query = "SELECT * FROM(SELECT ROW_NUMBER() OVER(ORDER BY CAKE_NO DESC) AS NUM, CAKE_NO, CAKE_NAME, CAKE_PRICE, CAKE_CHAR, DAY_REC, DAY_PICKUP, CAKE_THEMA, CAKE_TYPE, CAKE_AVG_SCORE, CAKE_FILEPATH, CAKE_FILESIZE, CAKE_UPLOADTIME, CAKE_FILENAME FROM CAKE_INFO WHERE SHOP_ID=?) WHERE NUM BETWEEN ? AND ?";
		
		int recordCountPerPage = 4;
		int start = currentPage * recordCountPerPage - (recordCountPerPage - 1);
		int end = currentPage * recordCountPerPage;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, shopId);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			cList = new ArrayList<CakeInfo>();
			
			while(rset.next()) {
				CakeInfo cInfo = new CakeInfo();
				cInfo.setCakeNo(rset.getInt("CAKE_NO"));
				cInfo.setCakeName(rset.getString("CAKE_NAME"));
				cInfo.setCakePrice(rset.getInt("CAKE_PRICE"));
				cInfo.setCakeChar(rset.getString("CAKE_CHAR"));
				cInfo.setDayRec(rset.getString("DAY_REC"));
				cInfo.setDayPickUp(rset.getString("DAY_PICKUP"));
				cInfo.setCakeThema(rset.getString("CAKE_THEMA"));
				cInfo.setCakeType(rset.getString("CAKE_TYPE"));
				cInfo.setCakeAvgScore(rset.getInt("CAKE_AVG_SCORE"));
				cInfo.setCakeFilePath("/img/" + rset.getString("CAKE_FILENAME"));
				cInfo.setCakeFileSize(rset.getLong("CAKE_FILESIZE"));
				cInfo.setCakeUploadTime(rset.getTimestamp("CAKE_UPLOADTIME"));
				cInfo.setCakeFileName(rset.getString("CAKE_FILENAME"));
				cList.add(cInfo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		} 
		return cList;
	}
	
	public int insertCakeInfo(Connection conn, CakeInfo cInfo) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		if( cInfo.getDayPickUp() != null) {
			String query = "INSERT INTO CAKE_INFO(CAKE_NO, SHOP_ID, CAKE_NAME, CAKE_PRICE, CAKE_CHAR, DAY_REC, DAY_PICKUP, CAKE_THEMA, CAKE_TYPE, CAKE_FILEPATH, CAKE_FILESIZE, CAKE_UPLOADTIME, CAKE_FILENAME)"
					+ "VALUES (SEQ_CAKE_INFO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, cInfo.getShopId());
				pstmt.setString(2, cInfo.getCakeName());
				pstmt.setInt(3, cInfo.getCakePrice());
				pstmt.setString(4, cInfo.getCakeChar());
				pstmt.setString(5, cInfo.getDayRec());
				pstmt.setString(6, cInfo.getDayPickUp());
				pstmt.setString(7, cInfo.getCakeThema());
				pstmt.setString(8, cInfo.getCakeType());
				pstmt.setString(9, cInfo.getCakeFilePath());
				pstmt.setLong(10, cInfo.getCakeFileSize());
				pstmt.setTimestamp(11, cInfo.getCakeUploadTime());
				pstmt.setString(12, cInfo.getCakeFileName());
				
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(pstmt);
			}
		} else {
			String query = "INSERT INTO CAKE_INFO(CAKE_NO, SHOP_ID, CAKE_NAME, CAKE_PRICE, CAKE_CHAR, DAY_REC, DAY_PICKUP, CAKE_THEMA, CAKE_TYPE, CAKE_FILEPATH, CAKE_FILESIZE, CAKE_UPLOADTIME, CAKE_FILENAME)"
					+ "VALUES (SEQ_CAKE_INFO.NEXTVAL, ?, ?, ?, ?, ?, 'N', ?, ?, ?, ?, ?, ?)";

			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, cInfo.getShopId());
				pstmt.setString(2, cInfo.getCakeName());
				pstmt.setInt(3, cInfo.getCakePrice());
				pstmt.setString(4, cInfo.getCakeChar());
				pstmt.setString(5, cInfo.getDayRec());
//				pstmt.setString(6, cInfo.getDayPickUp());
				pstmt.setString(6, cInfo.getCakeThema());
				pstmt.setString(7, cInfo.getCakeType());
				pstmt.setString(8, cInfo.getCakeFilePath());
				pstmt.setLong(9, cInfo.getCakeFileSize());
				pstmt.setTimestamp(10, cInfo.getCakeUploadTime());
				pstmt.setString(11, cInfo.getCakeFileName());
				
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(pstmt);
			}
		}
		return result;
	}

	
	public CakeInfo selectCakeOne(Connection conn, int cakeNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM CAKE_INFO WHERE CAKE_NO = ?";
		CakeInfo cInfo = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, cakeNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				cInfo = new CakeInfo();
				cInfo.setCakeNo(rset.getInt("CAKE_NO"));
				cInfo.setCakeName(rset.getString("CAKE_NAME"));
				cInfo.setCakePrice(rset.getInt("CAKE_PRICE"));
				cInfo.setCakeChar(rset.getString("CAKE_CHAR"));
				cInfo.setDayRec(rset.getString("DAY_REC"));
				cInfo.setDayPickUp(rset.getString("DAY_PICKUP"));
				cInfo.setCakeThema(rset.getString("CAKE_THEMA"));
				cInfo.setCakeType(rset.getString("CAKE_TYPE"));
				cInfo.setCakeAvgScore(rset.getInt("CAKE_AVG_SCORE"));
				cInfo.setCakeFilePath("/img/" + rset.getString("CAKE_FILENAME"));
				cInfo.setCakeFileSize(rset.getLong("CAKE_FILESIZE"));
				cInfo.setCakeUploadTime(rset.getTimestamp("CAKE_UPLOADTIME"));
				cInfo.setCakeFileName(rset.getString("CAKE_FILENAME"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return cInfo;
	}

	// 케익 정보 수정 메소드
	public int modifyCakeInfo(Connection conn, CakeInfo cInfo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE CAKE_INFO SET CAKE_NAME = ?, CAKE_PRICE = ?, CAKE_CHAR = ?, DAY_REC = ?, DAY_PICKUP = ?, CAKE_THEMA = ?, CAKE_TYPE = ?, CAKE_FILEPATH = ?, CAKE_FILESIZE = ?, CAKE_UPLOADTIME = ?, CAKE_FILENAME = ? WHERE CAKE_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, cInfo.getCakeName());
			pstmt.setInt(2, cInfo.getCakePrice());
			pstmt.setString(3, cInfo.getCakeChar());
			pstmt.setString(4, cInfo.getDayRec());
			pstmt.setString(5, cInfo.getDayPickUp());
			pstmt.setString(6, cInfo.getCakeThema());
			pstmt.setString(7, cInfo.getCakeType());
			pstmt.setString(8, cInfo.getCakeFilePath());
			pstmt.setLong(9, cInfo.getCakeFileSize());
			pstmt.setTimestamp(10, cInfo.getCakeUploadTime());
			pstmt.setString(11, cInfo.getCakeFileName());
			pstmt.setInt(12, cInfo.getCakeNo());
			
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	// 케익 정보 삭제 메소드
	public int deleteCakeInfo(Connection conn, int cakeNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "DELETE FROM CAKE_INFO WHERE CAKE_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, cakeNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
	
		return result;
	}
	
	// 페이지 네비 ( 한 페이지당 게시물 4개, 페이지 네비 10개 )
		public String getPageNavi(Connection conn, int currentPage, String shopId) {
			int recordTotalCount = ctotalCount(conn, shopId); // 전체 게시물 갯수
			int recordCountPerPage = 4; // 한 페이지당 보여줄 게시물 갯수
			int pageTotalCount = 0; // 전체 페이지 갯수
			
			// 페이지 범위 제한 조건
			if(recordTotalCount % recordCountPerPage > 0) {
				pageTotalCount = recordTotalCount / recordCountPerPage + 1;
			}else {
				pageTotalCount = recordTotalCount / recordCountPerPage;
			}
			
			// 오류방지코드
			if(currentPage < 1) {
				currentPage = 1;
			}else if(currentPage > pageTotalCount) {
				currentPage = pageTotalCount;
			}
			
			// 현재 페이지에서 페이지의 갯수(네비)를 몇 개씩 보여줄지에 대한 변수
			// currentPage 는 사용자가 선택한 값이 넘어와야 되기 때문에 매개변수로 전달 받는다.
			int naviCountPerPage = 10;
			int startNavi = ((currentPage - 1) / naviCountPerPage) * naviCountPerPage + 1;
			int endNavi = startNavi + naviCountPerPage - 1;
			
			if(endNavi > pageTotalCount) {
				endNavi = pageTotalCount;
			}
			
			// 이전 다음
			boolean needPrev = true;
			boolean needNext = true;
			
			if(startNavi == 1) {
				needPrev = false;
			}
			if(endNavi == pageTotalCount) {
				needNext = false;
			}
			
			StringBuilder sb = new StringBuilder();
			if(needPrev) {
				sb.append("<a href='/shopMy/cakeList?currentPage=" + (startNavi - 1) + "'> < </a>");
			}
			for(int i = startNavi; i <= endNavi; i++) {
				sb.append("<a href='/shopMy/cakeList?currentPage=" + i + "'> " + i + " </a>");
			}
			if(needNext) {
				sb.append("<a href='/shopMy/cakeList?currentPage=" + (endNavi + 1) + "'> > </a>");
			}
			
			return sb.toString();
		}

		private int ctotalCount(Connection conn, String shopId) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			String query = "SELECT COUNT(*) AS TOTALCOUNT FROM CAKE_INFO WHERE SHOP_ID=?";
			int recordTotalCount = 0;
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, shopId);
				rset = pstmt.executeQuery();
				
				if(rset.next()) {
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

	// 케이크 찾기 화면의 전체 리스트 띄우기 및 페이징 처리
	public ArrayList<CakeAndShop> selectAllList(Connection conn, int currentPage) {
		// 쿼리문 작성시 케이크정보 + 업체정보 조인하여 CakeAndShop 객체의 변수 부분 같이 가져오기
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<CakeAndShop> cList = null;
		
		String query = "SELECT * FROM (SELECT ROW_NUMBER() OVER(ORDER BY CAKE_NO DESC) AS NUM, CAKE_NO, SHOP_ID, CAKE_NAME, CAKE_PRICE, CAKE_FILEPATH, CAKE_FILENAME, SHOP_NAME FROM (SELECT * FROM CAKE_INFO JOIN SHOP_MEMBER USING (SHOP_ID))) WHERE NUM BETWEEN ? AND ?";
		int recordCountPerPage = 9;
		int start = currentPage * recordCountPerPage - (recordCountPerPage - 1);
		int end = currentPage * recordCountPerPage;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();

			cList = new ArrayList<CakeAndShop>();
			while (rset.next()) {
				CakeAndShop cakeAndShop = new CakeAndShop();
				cakeAndShop.setCakeNo(rset.getInt("CAKE_NO"));
				cakeAndShop.setCakeName(rset.getString("CAKE_NAME"));
				cakeAndShop.setCakePrice(rset.getInt("CAKE_PRICE"));
				cakeAndShop.setShopId(rset.getString("SHOP_ID"));
				cakeAndShop.setShopName(rset.getString("SHOP_NAME"));

				cakeAndShop.setCakeFilePath("/img/" + rset.getString("CAKE_FILENAME"));
				cList.add(cakeAndShop);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return cList;
	}

	public String getPageNavi(Connection conn, int currentPage) {
		int recordTotalCount = totalCount(conn);
		int recordCountPerPage = 9;
		int pageTotalCount = 0;
		if (recordTotalCount % recordCountPerPage > 0) {
			pageTotalCount = recordTotalCount / recordCountPerPage + 1;
		} else {
			pageTotalCount = recordTotalCount / recordCountPerPage;
		}

		// 오류방지
		if (currentPage < 1) {
			currentPage = 1;
		} else if (currentPage > pageTotalCount) {
			currentPage = pageTotalCount;
		}

		// 페이지당 navi 10개
		int naviCountPerPage = 10;
		int startNavi = ((currentPage - 1) / naviCountPerPage) * naviCountPerPage + 1;
		int endNavi = startNavi + naviCountPerPage - 1;

		// 오류방지코드
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

		// 이미지 넣을거면 이미지 <img src = > 로 수정
		if (needPrev) {
			sb.append("<a href='/cake/list?currentPage=" + (startNavi - 1) + "'> < </a>");

		}
		for (int i = startNavi; i <= endNavi; i++) {
			sb.append("<a href='/cake/list?currentPage=" + i + "'>" + i + " </a>");
		}
		if (needNext) {
			sb.append("<a href='/cake/list?currentPage=" + (endNavi + 1) + "'> > </a>");
		}
		return sb.toString();
	}

	public int totalCount(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		String query = "SELECT COUNT(*) AS TOTALCOUNT FROM CAKE_INFO";
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

	// 케이크 상세검색
	// conn, cakeType, cakePrice, shopLAdd, dayRec, dayPick, array, currentPage
	//
	public ArrayList<CakeAndShop> selectSearchList(Connection conn, String cakeType, String cakePrice, String shopLAdd,
			String dayRec, String dayPick, String array, int currentPage) {
		Statement stmt = null;
		ResultSet rset = null;
		String all = "all";
		//쿼리문 작성시
		ArrayList<CakeAndShop> csList = null;
		
		int recordCountPerPage = 9;
		int start = currentPage*recordCountPerPage - (recordCountPerPage-1);
		int end = currentPage*recordCountPerPage;
		//WITH CSEARCH AS (SELECT CAKE_NO, SHOP_ID, CAKE_NAME, SHOP_NAME, CAKE_FILEPATH, CAKE_TYPE, CAKE_PRICE, SHOP_L_ADD, DAY_REC, DAY_PICKUP, CAKE_AVG_SCORE, RCOUNT FROM (SELECT CAKE_NO, SHOP_ID, CAKE_NAME, SHOP_NAME, CAKE_FILEPATH, CAKE_TYPE, CAKE_PRICE, SHOP_L_ADD, DAY_REC, DAY_PICKUP, CAKE_AVG_SCORE FROM CAKE_INFO JOIN SHOP_MEMBER USING(SHOP_ID)) LEFT OUTER JOIN (SELECT CAKE_NO, COUNT(*) AS RCOUNT FROM (SELECT CAKE_NO FROM CAKE_INFO JOIN REVIEW USING(CAKE_NO)) GROUP BY CAKE_NO) USING(CAKE_NO))
		//SELECT * FROM (SELECT ROW_NUMBER() OVER(ORDER BY CAKE_NO DESC) AS NUM, CAKE_NO, SHOP_ID, CAKE_NAME, SHOP_NAME, CAKE_FILEPATH, CAKE_TYPE, CAKE_PRICE, SHOP_L_ADD, DAY_REC, DAY_PICKUP, CAKE_AVG_SCORE, NVL(RCOUNT,0) FROM (SELECT CAKE_NO, SHOP_ID, CAKE_NAME, SHOP_NAME, CAKE_FILEPATH, CAKE_TYPE, CAKE_PRICE, SHOP_L_ADD, DAY_REC, DAY_PICKUP, CAKE_AVG_SCORE, RCOUNT FROM CSEARCH 
		StringBuilder query = new StringBuilder();
		query.append("WITH CSEARCH AS (SELECT A.CAKE_NO, SHOP_ID, CAKE_NAME, SHOP_NAME, CAKE_FILENAME, CAKE_TYPE, CAKE_PRICE, SHOP_L_ADD, DAY_REC, DAY_PICKUP, NVL(RCOUNT,0) AS RCOUNT, NVL(CAKE_SCORE,0) AS CAKE_AVG_SCORE FROM (SELECT * FROM (SELECT * FROM CAKE_INFO JOIN SHOP_MEMBER USING(SHOP_ID)) LEFT OUTER JOIN (SELECT CAKE_NO, COUNT(*) AS RCOUNT FROM (SELECT CAKE_NO FROM CAKE_INFO JOIN REVIEW USING(CAKE_NO)) GROUP BY CAKE_NO) USING(CAKE_NO)) A LEFT OUTER JOIN (SELECT CAKE_NO, ROUND(AVG(REVIEW_SCORE)) AS CAKE_SCORE FROM REVIEW GROUP BY CAKE_NO) B ON A.CAKE_NO = B.CAKE_NO) ");
		query.append("SELECT * FROM (SELECT ROW_NUMBER() OVER(ORDER BY CAKE_NO DESC) AS NUM, CAKE_NO, SHOP_ID, CAKE_NAME, SHOP_NAME, CAKE_FILENAME, CAKE_TYPE, CAKE_PRICE, SHOP_L_ADD, DAY_REC, DAY_PICKUP, NVL(RCOUNT,0) AS RCOUNT, NVL(CAKE_AVG_SCORE,0) AS CAKE_AVG_SCORE FROM (SELECT CAKE_NO, SHOP_ID, CAKE_NAME, SHOP_NAME, CAKE_FILENAME, CAKE_TYPE, CAKE_PRICE, SHOP_L_ADD, DAY_REC, DAY_PICKUP, NVL(RCOUNT,0) AS RCOUNT, NVL(CAKE_AVG_SCORE,0) AS CAKE_AVG_SCORE FROM CSEARCH ");
		
		if(!cakeType.equals(all)||!cakePrice.equals(all)||!shopLAdd.equals(all)||!dayRec.equals(all)||!dayPick.equals(all)) {
			query.append("WHERE ");			
		
			if(!cakeType.equals(all))
				query.append("CAKE_TYPE = '" + cakeType + "'");
			if(!cakeType.equals(all)&&!cakePrice.equals(all))
				query.append(" AND ");
			if(!cakePrice.equals(all)) {
				int endSPrice = Integer.parseInt(cakePrice)+9999;
				int endBPrice = Integer.parseInt(cakePrice)+10000000;
				if(!cakePrice.equals("50000")) {
					query.append("(CAKE_PRICE BETWEEN '" + cakePrice + "' AND '" + endSPrice +"')");					
				}else {
					query.append("(CAKE_PRICE BETWEEN '" + cakePrice + "' AND '" + endBPrice +"')");
				}
			}
			if(!cakePrice.equals(all)&&!shopLAdd.equals(all))
				query.append(" AND ");
			if(!shopLAdd.equals(all))
				query.append("SHOP_L_ADD LIKE '%" + shopLAdd + "%'");
			if(!shopLAdd.equals(all)&&!dayRec.equals(all))
				query.append(" AND ");
			if(!dayRec.equals(all))
				query.append("DAY_REC = '" + dayRec + "'");
			if(!dayRec.equals(all)&&!dayPick.equals(all))
				query.append(" AND ");
			if(!dayPick.equals(all))
				query.append("DAY_PICKUP = '" + dayPick + "'");
		}	
			if(array.equals("CAKE_AVG_SCORE")) //평점순
				query.append(") ORDER BY CAKE_AVG_SCORE DESC)");
			if(array.equals("RCOUNT")) //후기순
				query.append(") ORDER BY RCOUNT DESC)");		

			query.append("WHERE NUM BETWEEN "+ start + " AND " + end);
			
			
		System.out.println(query);
		//WHERE CAKE_TYPE = '비건' AND (CAKE_PRICE BETWEEN 30000 AND 39999) AND SHOP_L_ADD LIKE '%서울%' AND DAY_REC = '배송' AND DAY_PICKUP = 'N' ORDER BY RCOUNT DESC
				

		//String cakeType, String cakePrice, String shopLAdd,
		//String dayRec, String dayPick, String array
		try {
			stmt = conn.createStatement();

			rset = stmt.executeQuery(query.toString());
			csList = new ArrayList<CakeAndShop>();
			while(rset.next()) {
				CakeAndShop cakeAndShop = new CakeAndShop();
				cakeAndShop.setCakeNo(rset.getInt("CAKE_NO"));
				cakeAndShop.setCakeName(rset.getString("CAKE_NAME"));
				cakeAndShop.setCakePrice(rset.getInt("CAKE_PRICE"));
				cakeAndShop.setShopId(rset.getString("SHOP_ID"));
				cakeAndShop.setShopName(rset.getString("SHOP_NAME"));

				cakeAndShop.setCakeFilePath("/img/" + rset.getString("CAKE_FILENAME"));
				csList.add(cakeAndShop);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		
		return csList;

	}

	// 케이크 상세검색
	// conn, cakeType, cakePrice, shopLAdd, dayRec, dayPick, currentPage
	//
	public String getSearchPageNavi(Connection conn, String cakeType, String cakePrice, String shopLAdd, String dayRec,
			String dayPick, String array, int currentPage) {

		int recordTotalCount = searchTotalCount(conn, cakeType, cakePrice, shopLAdd, dayRec, dayPick);
		int recordCountPerPage = 9;
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
			sb.append("<a href='/cake/search?currentPage=" + (startNavi - 1) + "&kinds=" + cakeType + "&price="
					+ cakePrice + "&area=" + shopLAdd + "&pickup=" + dayRec + "&daypick=" + dayPick + "&array=" + array
					+ "'> < </a>"); // currentPage에 하나 적은 값을 넣어 (ex. 11-1 = 10) 다시 Servlet~NoticeDAO.getPageNavi()가
									// 작동하면 startNavi가 1로 변경 -> 1~10 navi가 생김
		}
		// http://localhost:8888/cake/search?kinds=all&price=all&area=all&pickup=all&daypick=all&array=CAKE_AVG_SCORE
		// /cake/search?currentPage=1&kinds=cakeType&price=cakePrice&area=shopLAdd&pickup=dayRec&daypick=dayPick&array=array
		// String cakeType, String cakePrice, String shopLAdd, String dayRec, String
		// dayPick
		for (int i = startNavi; i <= endNavi; i++) {
			sb.append("<a href='/cake/search?currentPage=" + i + "&kinds=" + cakeType + "&price=" + cakePrice + "&area="
					+ shopLAdd + "&pickup=" + dayRec + "&daypick=" + dayPick + "&array=" + array + "'>" + i + " </a>"); // 앵커
																														// 태그
																														// 작동:
																														// currentPage에
																														// 맞는
																														// 게시물
																														// 출력
																														// +
																														// currentPage에
																														// 맞는
																														// pageNavi가
																														// 출력됨
		} // selectAllList() getPageNavi()
		if (needNext) {
			sb.append("<a href='/cake/search?currentPage=" + (endNavi + 1) + "&kinds=" + cakeType + "&price="
					+ cakePrice + "&area=" + shopLAdd + "&pickup=" + dayRec + "&daypick=" + dayPick + "&array=" + array
					+ "'> > </a>"); // 사용자가 (currentPage 10+1 = 11)을 누른 효과
		}
		return sb.toString();
	}

	public int searchTotalCount(Connection conn, String cakeType, String cakePrice, String shopLAdd, String dayRec,
			String dayPick) {

		Statement stmt = null;
		ResultSet rset = null;
		// WHERE CAKE_TYPE = '비건' AND (CAKE_PRICE BETWEEN 30000 AND 39999) AND
		// SHOP_L_ADD LIKE '%서울%' AND DAY_REC = '배송' AND DAY_PICKUP = 'N'
		// String query = "SELECT COUNT(*) AS TOTALCOUNT FROM CAKE_INFO WHERE CAKE_TYPE
		// = ? AND (CAKE_PRICE BETWEEN ? AND ?) AND SHOP_L_ADD LIKE ? AND DAY_REC = ?
		// AND DAY_PICKUP = ?";
		StringBuilder query = new StringBuilder();
		String all = "all";
		query.append("SELECT COUNT(*) AS TOTALCOUNT FROM CAKE_INFO");
		if (!cakeType.equals(all) || !cakePrice.equals(all) || !shopLAdd.equals(all) || !dayRec.equals(all)
				|| !dayPick.equals(all)) {
			query.append("WHERE ");

			if (!cakeType.equals(all))
				query.append("CAKE_TYPE = '" + cakeType + "'");
			if (!cakeType.equals(all) && !cakePrice.equals(all))
				query.append(" AND ");
			if (!cakePrice.equals(all)) {
				int endSPrice = Integer.parseInt(cakePrice) + 9999;
				int endBPrice = Integer.parseInt(cakePrice) + 10000000;
				if (!cakePrice.equals("50000")) {
					query.append("(CAKE_PRICE BETWEEN '" + cakePrice + "' AND '" + endSPrice + "')");
				} else {
					query.append("(CAKE_PRICE BETWEEN '" + cakePrice + "' AND '" + endBPrice + "')");
				}
			}
			if (!cakePrice.equals(all) && !shopLAdd.equals(all))
				query.append(" AND ");
			if (!shopLAdd.equals(all))
				query.append("SHOP_L_ADD LIKE '%" + shopLAdd + "%'");
			if (!shopLAdd.equals(all) && !dayRec.equals(all))
				query.append(" AND ");
			if (!dayRec.equals(all))
				query.append("DAY_REC = '" + dayRec + "'");
			if (!dayRec.equals(all) && !dayPick.equals(all))
				query.append(" AND ");
			if (!dayPick.equals(all))
				query.append("DAY_PICKUP = '" + dayPick + "'");
		}

		int recordTotalCount = 0;
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query.toString());
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
		System.out.println(recordTotalCount);

		System.out.println("totalcount" + query);
		return recordTotalCount;
	}


	// 케익 상세화면
	public CakeAndShop selectOne(Connection conn, int cakeNo, String shopId) {
		Statement stmt = null;
		ResultSet rset = null;
		CakeAndShop cakeAndShop = null;
		String query = "SELECT CAKE_NO, CAKE_NAME, CAKE_PRICE, SHOP_NAME, SHOP_L_ADD, CHAT_URL, CAKE_CHAR, (SELECT ROUND(AVG(REVIEW_SCORE)) FROM REVIEW WHERE CAKE_NO = " + cakeNo + "GROUP BY CAKE_NO) AS CAKE_AVG_SCORE, DAY_REC, CAKE_FILEPATH, CAKE_FILENAME, SHOP_ID, DAY_PICKUP, CAKE_THEMA, CAKE_TYPE, OPENING_START, OPENING_END FROM CAKE_INFO JOIN SHOP_MEMBER USING(SHOP_ID) WHERE CAKE_NO =" + cakeNo + " AND SHOP_ID = '" + shopId + "'";
		// 쿼리로 cake와 해당 업체정보를 조인하여 cakeandshop객체에 담기
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			if (rset.next()) {
				cakeAndShop = new CakeAndShop();
				cakeAndShop.setCakeNo(rset.getInt("CAKE_NO"));
				cakeAndShop.setCakeName(rset.getString("CAKE_NAME"));
				cakeAndShop.setCakePrice(rset.getInt("CAKE_PRICE"));
				cakeAndShop.setShopName(rset.getString("SHOP_NAME"));
				cakeAndShop.setShopLAdd(rset.getString("SHOP_L_ADD"));
				cakeAndShop.setChatUrl(rset.getString("CHAT_URL"));
				cakeAndShop.setCakeChar(rset.getString("CAKE_CHAR"));
				cakeAndShop.setCakeAvgScore(rset.getInt("CAKE_AVG_SCORE"));
				cakeAndShop.setDayRec(rset.getString("DAY_REC"));

				cakeAndShop.setCakeFilePath("/img/" + rset.getString("CAKE_FILENAME"));
				cakeAndShop.setShopId(rset.getString("SHOP_ID"));
				cakeAndShop.setDayPickUp(rset.getString("DAY_PICKUP"));
				cakeAndShop.setCakeThema(rset.getString("CAKE_THEMA"));
				cakeAndShop.setCakeType(rset.getString("CAKE_TYPE"));
				cakeAndShop.setOpeningStart(rset.getInt("OPENING_START"));
				cakeAndShop.setOpeningEnd(rset.getInt("OPENING_END"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		System.out.println("DAO: " + cakeAndShop.toString());
		return cakeAndShop;
	}

	// 오버로드, 업체별 케익 목록, 업체 상세화면에서 사용
	public ArrayList<CakeInfo> selectSearchList(Connection conn, String shopId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT CAKE_NO, SHOP_ID, CAKE_NAME, CAKE_PRICE, DAY_REC, CAKE_FILEPATH, CAKE_FILENAME, SHOP_NAME FROM CAKE_INFO JOIN SHOP_MEMBER USING (SHOP_ID) WHERE SHOP_ID = ?";
		ArrayList<CakeInfo> cList = null;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, shopId);
			rset = pstmt.executeQuery();
			cList = new ArrayList<CakeInfo>();
			while (rset.next()) {
				CakeInfo cakeInfo = new CakeInfo();
				cakeInfo.setCakeNo(rset.getInt("CAKE_NO"));
				cakeInfo.setShopId(rset.getString("SHOP_ID"));
				cakeInfo.setCakeName(rset.getString("CAKE_NAME"));
				cakeInfo.setCakePrice(rset.getInt("CAKE_PRICE"));
				cakeInfo.setDayRec(rset.getString("DAY_REC"));

				cakeInfo.setCakeFilePath("/img/" + rset.getString("CAKE_FILENAME"));

				cakeInfo.setCakeFilePath("/img/" + rset.getString("CAKE_FILENAME"));
				cList.add(cakeInfo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return cList;
	}

	  // 메인 조건 검색
	   public ArrayList<MainSearchCakeInfo> searchMain(Connection conn, String searchVal) {
	      // 조건 - 지역 / 케이크 종류 / 테마 / 수령방법
	      Statement stmt = null;
	      ResultSet rset = null;
	      String query = "";
	      ArrayList<MainSearchCakeInfo> searchCake = null;
	      
	      if(searchVal.equals("지역")) {
	         query = "SELECT * FROM CAKE_INFO JOIN SHOP_MEMBER ON (CAKE_INFO.SHOP_ID = SHOP_MEMBER.SHOP_ID) WHERE NOT CAKE_FILEPATH IS NULL ORDER BY SHOP_L_ADD ASC"; //WHERE NOT CAKE_FILENAME IS NULL 
	      }else if(searchVal.equals("케이크 종류")) {
	         query = "SELECT * FROM CAKE_INFO JOIN SHOP_MEMBER ON (CAKE_INFO.SHOP_ID = SHOP_MEMBER.SHOP_ID) WHERE NOT CAKE_FILENAME IS NULL ORDER BY CAKE_TYPE ASC";
	      }else if(searchVal.equals("테마")) {
	         query = "SELECT * FROM CAKE_INFO JOIN SHOP_MEMBER ON (CAKE_INFO.SHOP_ID = SHOP_MEMBER.SHOP_ID) WHERE NOT CAKE_FILENAME IS NULL ORDER BY CAKE_THEMA ASC";
	      }else if(searchVal.equals("수령방법")) {
	         query = "SELECT * FROM CAKE_INFO JOIN SHOP_MEMBER ON (CAKE_INFO.SHOP_ID = SHOP_MEMBER.SHOP_ID) WHERE NOT CAKE_FILENAME IS NULL ORDER BY DAY_REC ASC";
	      }
	      /*
	       * switch(searchVal) { case "지역" : query =
	       * "SELECT * FROM CAKE_INFO JOIN SHOP_MEMBER ON (CAKE_INFO.SHOP_ID = SHOP_MEMBER.SHOP_ID) ORDER BY SHOP_L_ADD ASC"
	       * ; break; case "케이크 종류" : query =
	       * "SELECT * FROM CAKE_INFO JOIN SHOP_MEMBER ON (CAKE_INFO.SHOP_ID = SHOP_MEMBER.SHOP_ID) ORDER BY CAKE_TYPE ASC"
	       * ; break; case "테마" : query =
	       * "SELECT * FROM CAKE_INFO JOIN SHOP_MEMBER ON (CAKE_INFO.SHOP_ID = SHOP_MEMBER.SHOP_ID) ORDER BY CAKE_THEMA ASC"
	       * ; break; case "수령방법" : query =
	       * "SELECT * FROM CAKE_INFO JOIN SHOP_MEMBER ON (CAKE_INFO.SHOP_ID = SHOP_MEMBER.SHOP_ID) ORDER BY DAY_REC ASC"
	       * ; break; }
	       */
	      System.out.println(query);
	      try {
	         stmt = conn.createStatement();
	         rset = stmt.executeQuery(query);
	         searchCake = new ArrayList<MainSearchCakeInfo>();
	         while(rset.next()) {
	            MainSearchCakeInfo cake = new MainSearchCakeInfo();
	            // 조건 / 케이크 이름, 샵 네임, 케이크 가격, 파일패스
	            cake.setShopId(rset.getString("SHOP_ID"));
	            cake.setCakeNo(rset.getInt("CAKE_NO"));
	            cake.setShopLAdd(rset.getString("SHOP_L_ADD"));
	            cake.setCakeType(rset.getString("CAKE_TYPE"));
	            cake.setCakeThema(rset.getString("CAKE_THEMA"));
	            cake.setDayRec(rset.getString("DAY_REC"));
	            
	            cake.setCakeName(rset.getString("CAKE_NAME"));
	            cake.setCakePrice(rset.getInt("CAKE_PRICE"));
	            cake.setShopName(rset.getString("SHOP_NAME"));
	            cake.setCakeFilePath("/img/" + rset.getString("CAKE_FILENAME"));
	            //cakeAndShop.setCakeFilePath("/img/" + rset.getString("CAKE_FILENAME"));
	            searchCake.add(cake);
	         }
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      } finally {
	         JDBCTemplate.close(rset);
	         JDBCTemplate.close(stmt);
	      }
	      return searchCake;
	   }

	   // 메인 - 별점(평점)순 노출
	   public ArrayList<MainCakeInfo> mainAvgCake(Connection conn) {
	      Statement stmt = null;
	      ResultSet rset = null;
	      ArrayList<MainCakeInfo> avgCake = null;
	      String query = "SELECT CAKE_NO, CAKE_FILENAME, SHOP_ID, CAKE_NAME, CAKE_PRICE, NVL(ROUND(CAKE_SCORE),0) AS CAKE_AVG_SCORE FROM SHOP_MEMBER JOIN (SELECT * FROM CAKE_INFO LEFT OUTER JOIN (SELECT CAKE_NO, AVG(REVIEW_SCORE) AS CAKE_SCORE FROM REVIEW GROUP BY CAKE_NO) USING (CAKE_NO)) USING (SHOP_ID) WHERE ROWNUM < 7 AND NOT CAKE_FILENAME IS NULL ORDER BY CAKE_AVG_SCORE DESC";
	      try {
	         stmt = conn.createStatement();
	         rset = stmt.executeQuery(query);
	         avgCake = new ArrayList<MainCakeInfo>();
	         while(rset.next()) {
	            MainCakeInfo cake = new MainCakeInfo();
	            cake.setShopId(rset.getString("SHOP_ID"));
	            cake.setCakeNo(rset.getInt("CAKE_NO"));
	            cake.setCakeName(rset.getString("CAKE_NAME")); 
	            cake.setCakePrice(rset.getInt("CAKE_PRICE"));
	            cake.setCakeAvgScore(rset.getInt("CAKE_AVG_SCORE"));
	            cake.setCakeFilePath("/img/" + rset.getString("CAKE_FILENAME"));
	            avgCake.add(cake);
	         }
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }finally {
	         JDBCTemplate.close(rset);
	         JDBCTemplate.close(stmt);
	      }
	      return avgCake;
	   }

	   // 메인 - 관리자순 노출(가격 낮은 순)
	   public ArrayList<MainCakeInfo> mainMDCake(Connection conn) {
	      Statement stmt = null;
	      ResultSet rset = null;
	      ArrayList<MainCakeInfo> mdCake = null;
	      String query = "SELECT * FROM CAKE_INFO WHERE ROWNUM < 7 AND NOT CAKE_FILENAME IS NULL ORDER BY CAKE_PRICE ASC";
	      
	      try {
	         stmt = conn.createStatement();
	         rset = stmt.executeQuery(query);
	         mdCake = new ArrayList<MainCakeInfo>();
	         while(rset.next()) {
	            MainCakeInfo cake = new MainCakeInfo();
	            cake.setShopId(rset.getString("SHOP_ID"));
	            cake.setCakeNo(rset.getInt("CAKE_NO"));
	            cake.setCakeName(rset.getString("CAKE_NAME"));
	            cake.setCakePrice(rset.getInt("CAKE_PRICE"));
	            cake.setCakeAvgScore(rset.getInt("CAKE_AVG_SCORE"));
//	            cake.setCakeFilePath("/img/" + rset.getString("CAKE_FILENAME"));
	            cake.setCakeFilePath("/img/" + rset.getString("CAKE_FILENAME"));
	            mdCake.add(cake);
	         }
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }finally {
	         JDBCTemplate.close(rset);
	         JDBCTemplate.close(stmt);
	      }
	      return mdCake;
	   }

	   // 메인 - 후기순 노출
	   public ArrayList<MainCakeInfo> mainReviewCake(Connection conn) {
	      Statement stmt = null;
	      ResultSet rset = null;
	      ArrayList<MainCakeInfo> reviewCake = null;
	      String query = "SELECT CAKE_NO, CAKE_FILENAME, SHOP_ID, CAKE_NAME, CAKE_PRICE, CAKE_AVG_SCORE, NVL(RCOUNT,0) AS RCOUNT FROM SHOP_MEMBER JOIN (SELECT * FROM CAKE_INFO LEFT OUTER JOIN (SELECT CAKE_NO, COUNT(*) AS RCOUNT FROM REVIEW GROUP BY CAKE_NO) USING (CAKE_NO)) USING (SHOP_ID) WHERE ROWNUM < 7 AND NOT CAKE_FILENAME IS NULL ORDER BY RCOUNT DESC";
	      ///SELECT * FROM CAKE_INFO JOIN REVIEW ON CAKE_INFO.CAKE_NO = REVIEW.CAKE_NO WHERE ROWNUM < 7 AND NOT CAKE_FILENAME IS NULL ORDER BY REVIEW_SCORE
	      try {
	         stmt = conn.createStatement();
	         rset = stmt.executeQuery(query);
	         reviewCake = new ArrayList<MainCakeInfo>();
	         while(rset.next()) {
	            MainCakeInfo cake = new MainCakeInfo();
	            cake.setShopId(rset.getString("SHOP_ID"));
	            cake.setCakeNo(rset.getInt("CAKE_NO"));
	            cake.setCakeName(rset.getString("CAKE_NAME"));
	            cake.setCakePrice(rset.getInt("CAKE_PRICE"));
	            cake.setCakeAvgScore(rset.getInt("CAKE_AVG_SCORE"));
//	            cake.setReviewContent(rset.getString("REVIEW_CONTENT"));
	            cake.setCakeFilePath("/img/" + rset.getString("CAKE_FILENAME"));
	            cake.setReviewCount(rset.getInt("RCOUNT"));
	            reviewCake.add(cake);
	         }
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
	      return reviewCake;
	   }

}