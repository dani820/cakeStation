package shop.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import file.model.vo.CRNImg;
import file.model.vo.ShopImg;
import shop.model.dao.ShopMemDAO;
import shop.model.vo.PageData;
import shop.model.vo.ShopAndImg;
import shop.model.vo.ShopMember;

public class ShopMemService {

	private JDBCTemplate factory;

	public ShopMemService() {
		factory = JDBCTemplate.getConnection();
	}

	// 업체 회원가입 (insert)
	public int registerMember(ShopMember shopMember) {
		int result = 0;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			result = new ShopMemDAO().insertMember(conn, shopMember);
			if (result > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
			System.out.println("test : " + result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}

	// 업체 회원 로그인
	public ShopMember selectOneMember(String userId, String userPwd) {
		Connection conn = null;
		ShopMember shopMember = null;

		try {
			conn = factory.createConnection();
			shopMember = new ShopMemDAO().selectOneMember(conn, userId, userPwd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return shopMember;
	}

	// 아이디 찾기
	public ShopMember findId(String email) {
		Connection conn = null;
		ShopMember shopMember = null;

		try {
			conn = factory.createConnection();
			shopMember = new ShopMemDAO().findId(conn, email);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return shopMember;
	}

	// 비밀번호 재설정
	public int resetPwd(String id, String pwd) {
		Connection conn = null;
		int result = 0;

		try {
			conn = factory.createConnection();
			result = new ShopMemDAO().resetPwd(conn, id, pwd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}

	// CRN_IMG 삽입
	public int registerCrnFile(CRNImg crnImgFile) {
		Connection conn = null;
		int imgResult = 0;

		try {
			conn = factory.createConnection();
			imgResult = new ShopMemDAO().registerCrnFile(conn, crnImgFile);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}

		return imgResult;
	}

	// 업체 회원 정보 수정
	public int modifyShop(ShopMember sMember) {
		Connection conn = null;
		int result = 0;

		try {
			conn = factory.createConnection();
			result = new ShopMemDAO().updateShop(conn, sMember);

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

	// 업체회원 아이디 중복 체크 검사
	public int shopCheckId(String userId) {
		Connection conn = null;
		int result = 0;
		try {
			conn = factory.createConnection();
			result = new ShopMemDAO().shopCheckId(conn, userId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}

	// 탈퇴 신청시 비밀번호 비교 메소드
	public ShopMember checkShopPw(String shopId) {
		Connection conn = null;
		ShopMember sMember = null;
		try {
			conn = factory.createConnection();
			sMember = new ShopMemDAO().compareWithPw(conn, shopId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return sMember;
	}

	// 비밀번호 일치 후 탈퇴 신청 여부 업로드 메소드
	public int removeShopMem(String shopId, String withdrawY) {
		Connection conn = null;
		int result = 0;
		try {
			conn = factory.createConnection();
			result = new ShopMemDAO().withdrawShop(conn, shopId, withdrawY);

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

	// 업체 회원 1명의 업체 정보 및 가입 정보 가져오기 (업체회원 로그인 메소드 오버로딩)
	public ShopMember selectOneMember(String shopId) {
		Connection conn = null;
		ShopMember sMember = null;
		try {
			conn = factory.createConnection();
			sMember = new ShopMemDAO().selectOneMember(conn, shopId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}

		return sMember;
	}

	// 업체 회원 정보 수정 - 사업자등록증
	public int modifyShop(CRNImg crnImg) {
		Connection conn = null;
		int result = 0;

		try {
			conn = factory.createConnection();
			result = new ShopMemDAO().updateShop(conn, crnImg);

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

	// 업체 이미지 조회
	public ShopImg selectShopImg(String shopId) {
		Connection conn = null;
		ShopImg shopImg = null;

		try {
			conn = factory.createConnection();
			shopImg = new ShopMemDAO().selectShopImg(conn, shopId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return shopImg;
	}

	// 업체 이미지 등록
	public int registerShopInfo(ShopImg sImg) {
		Connection conn = null;
		int result = 0;
		try {
			conn = factory.createConnection();
			result = new ShopMemDAO().enrollShopInfo(conn, sImg);

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

	// 업체 가게 정보 수정
	public int updateShopInfo(ShopMember sMember) {
		Connection conn = null;
		int result = 0;
		try {
			conn = factory.createConnection();
			result = new ShopMemDAO().modifyShopInfo(conn, sMember);
			System.out.println(result);
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

	// 사업자등록증 수정
	public int updateShopInfo(ShopImg sImg) {
		Connection conn = null;
		int result = 0;
		try {
			conn = factory.createConnection();
			result = new ShopMemDAO().modifyShopInfo(conn, sImg);

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

	// 사업자등록증 조회
	public CRNImg selectCrnImg(String shopId) {
		Connection conn = null;
		CRNImg crnImg = null;

		try {
			conn = factory.createConnection();
			crnImg = new ShopMemDAO().selectCrnImg(conn, shopId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return crnImg;
	}

	// 업체 찾기 전체 리스트
	public PageData printAllList(int currentPage) {
		Connection conn = null;
		PageData pageData = new PageData();
		try {
			conn = factory.createConnection();
			pageData.setShopAndImg(new ShopMemDAO().selectAllList(conn, currentPage));
			pageData.setPageNavi(new ShopMemDAO().getPageNavi(conn, currentPage));
			// pageNavi란? 1 2 3 4 5 6 7 8 9 10
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		System.out.println("서비스");
		return pageData;
	}

	// 업체 지역 설정 목록 띄우기
	public PageData printSearchList(String shopLAdd, String array, int currentPage) {
		Connection conn = null;
		PageData pageData = new PageData();
		System.out.println("서비스 위");
		try {
			conn = factory.createConnection();
			pageData.setShopAndImg(new ShopMemDAO().selectSearchList(conn, shopLAdd, array, currentPage));
			pageData.setPageNavi(new ShopMemDAO().getSearchPageNavi(conn, shopLAdd, array, currentPage));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		System.out.println("서비스:" + pageData.toString());
		return pageData;
	}

	// 업체 정보 상세화면
	public ShopAndImg printOne(String shopId) {
		Connection conn = null;
		ShopAndImg shopAndImg = null;

		try {
			conn = factory.createConnection();
			shopAndImg = new ShopMemDAO().selectOne(conn, shopId);
			System.out.println("서비스:" + shopAndImg);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return shopAndImg;
	}

	public ShopMember selectOneById(String shopId) {
		ShopMember sMember = null;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			sMember = new ShopMemDAO().selectOneById(conn, shopId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return sMember;
	}

	public int updateMember(ShopMember sMember) {
		Connection conn = null;
		int result = 0;
		try {
			conn = factory.createConnection();
			result = new ShopMemDAO().updateMember(conn, sMember);
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

	// 정렬된 목록, 필요할지도 모르니까 킵
//	public PageData printArrayList(String array, int currentPage) {
//		Connection conn = null;
//		PageData pageData = new PageData();
//		
//		ArrayList<ShopMemDAO> sList = new ShopMemDAO().selectArrayList(conn, array, currentPage);
//		
//		return pageData;
//	}
}
