package cakeinfo.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import cakeinfo.model.dao.CakeInfoDAO;
import cakeinfo.model.vo.CakeAndShop;
import cakeinfo.model.vo.CakeInfo;
import cakeinfo.model.vo.MainCakeInfo;
import cakeinfo.model.vo.MainSearchCakeInfo;
import cakeinfo.model.vo.PageData;
import common.JDBCTemplate;

public class CakeInfoService {

	private JDBCTemplate factory;

	public CakeInfoService() {
		factory = JDBCTemplate.getConnection();
	}

	// 등록한 케익 리스트 전체 출력
	public PageData printAllCake(int currentPage, String shopId) {
		Connection conn = null;
		PageData pd = new PageData();

		try {
			conn = factory.createConnection();
			pd.setCakeList(new CakeInfoDAO().selectAllCake(conn, currentPage, shopId));
			pd.setPageNavi(new CakeInfoDAO().getPageNavi(conn, currentPage, shopId));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}

		return pd;
	}

	// 케익 등록 메소드
	public int registerCakeInfo(CakeInfo cInfo) {
		Connection conn = null;
		int result = 0;

		try {
			conn = factory.createConnection();
			result = new CakeInfoDAO().insertCakeInfo(conn, cInfo);
			if (result > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}

		return result;
	}

	// 케익 한개 정보 출력 메소드
	public CakeInfo printCakeOne(int cakeNo) {
		Connection conn = null;
		CakeInfo cakeOne = null;

		try {
			conn = factory.createConnection();
			cakeOne = new CakeInfoDAO().selectCakeOne(conn, cakeNo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return cakeOne;
	}

	// 케익 정보 수정 메소드
	public int updateCakeInfo(CakeInfo cInfo) {
		Connection conn = null;
		int result = 0;

		try {
			conn = factory.createConnection();
			result = new CakeInfoDAO().modifyCakeInfo(conn, cInfo);

			if (result > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}

		return result;
	}

	// 케익 정보 삭제 메소드
	public int deleteCakeOn(int cakeNo) {
		Connection conn = null;
		int result = 0;
		try {
			conn = factory.createConnection();
			result = new CakeInfoDAO().deleteCakeInfo(conn, cakeNo);

			if (result > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}

		return result;
	}

	// 케이크 찾기 화면의 케이크 전체 리스트 띄우기
	public PageData printAllList(int currentPage) {
		Connection conn = null;
		PageData pageData = new PageData();
		try {
			conn = factory.createConnection();
			pageData.setCakeAndShop(new CakeInfoDAO().selectAllList(conn, currentPage));
			pageData.setPageNavi(new CakeInfoDAO().getPageNavi(conn, currentPage));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return pageData;
	}

	// 케이크 검색 버튼 눌렀을 대
//		public PageData printSearchList(String cakeType, String cakePrice, String shopLAdd, String dayRec, int currentPage) {
//			Connection conn = null;
//			PageData pageData = new PageData();
//			
//			ArrayList<CakeInfo> cList = new CakeInfoDAO().selectSearchList(conn, cakeType, cakePrice, shopLAdd, dayRec, currentPage);
//			
//			pageData.setCakeInfoList(cList);
//			
//			return pageData;
//		}

	// 케이크 상세검색: cakeType, cakePrice, shopLAdd, dayRec, dayPick, array, currentPage
	//
	public PageData printSearchList(String cakeType, String shopLAdd, String cakePrice, String dayRec, String dayPick,
			String array, int currentPage) {
		Connection conn = null;
		PageData pageData = new PageData();
		try {
			conn = factory.createConnection();
			pageData.setCakeAndShop(new CakeInfoDAO().selectSearchList(conn, cakeType, cakePrice, shopLAdd, dayRec,
					dayPick, array, currentPage));
			pageData.setPageNavi(new CakeInfoDAO().getSearchPageNavi(conn, cakeType, cakePrice, shopLAdd, dayRec,
					dayPick, array, currentPage)); //
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}

		return pageData;
	}

	// 케이크 상세페이지에 나올 것
	public CakeAndShop printOne(int cakeNo, String shopId) {
		Connection conn = null;
		CakeAndShop cakeAndShop = null;

		try {
			conn = factory.createConnection();
			cakeAndShop = new CakeInfoDAO().selectOne(conn, cakeNo, shopId);
			System.out.println("서비스:" + cakeAndShop);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return cakeAndShop;
	}

	// 오버로딩, 업체 상세화면에 케이크 목록을 띄울 때 사용
	public ArrayList<CakeInfo> printSearchList(String shopId) {
		Connection conn = null;
		ArrayList<CakeInfo> cList = new ArrayList<CakeInfo>();

		try {
			conn = factory.createConnection();
			cList = new CakeInfoDAO().selectSearchList(conn, shopId);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return cList;
	}

	// 메인 조건 검색
	public ArrayList<MainSearchCakeInfo> searchMain(String searchVal) {
		Connection conn = null;
		ArrayList<MainSearchCakeInfo> searchCake = null;

		try {
			conn = factory.createConnection();
			searchCake = new CakeInfoDAO().searchMain(conn, searchVal);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return searchCake;
	}

	// 메인 - 별점(평점)순 노출
	public ArrayList<MainCakeInfo> mainAvgCake() {
		ArrayList<MainCakeInfo> avgCake = null;
		Connection conn = null;

		try {
			conn = factory.createConnection();
			avgCake = new CakeInfoDAO().mainAvgCake(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}

		return avgCake;
	}

	// 메인 - 관리자순 노출(가격 낮은 순)
	public ArrayList<MainCakeInfo> mainMDCake() {
		Connection conn = null;
		ArrayList<MainCakeInfo> mdCake = null;

		try {
			conn = factory.createConnection();
			mdCake = new CakeInfoDAO().mainMDCake(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}

		return mdCake;
	}

	// 메인 - 후기순 노출
	public ArrayList<MainCakeInfo> mainReviewCake() {
		Connection conn = null;
		ArrayList<MainCakeInfo> reviewCake = null;

		try {
			conn = factory.createConnection();
			reviewCake = new CakeInfoDAO().mainReviewCake(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return reviewCake;
	}

}
