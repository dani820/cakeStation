package review.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import board.model.dao.BoardDao;
import board.model.vo.Board;
import board.model.vo.BoardData;
import cakeinfo.model.vo.PageData;
import common.JDBCTemplate;
import review.model.dao.ReviewDAO;
import review.model.vo.Review;
import review.model.vo.ReviewAndComm;
import review.model.vo.ReviewData;

public class ReviewService {
	private JDBCTemplate factory;
	
	public ReviewService() {
		factory = JDBCTemplate.getConnection();
	}


	//등록
	public int registerReview(Review review) {
		Connection conn = null;
		int result = 0;
		try {
			conn = factory.createConnection();
			result = new ReviewDAO().insertReview(conn, review);
			if(result > 0) {
				JDBCTemplate.commit(conn);
			}else {
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
	

	
	//수정
	public int modifyReview(Review review) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = factory.createConnection();
			result = new ReviewDAO().updateReview(conn, review);
			if(result > 0) {
				JDBCTemplate.commit(conn);
			}else {
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
	
	//삭제
	public int deleteReview(int reviewNo) {
		Connection conn = null;
		int result = 0;
		try {
			conn = factory.createConnection();
			result = new ReviewDAO().deleteReview(conn, reviewNo);
			if(result > 0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}
	
	//한개 조회
	public Review printOne(int reviewNo) {
		Connection conn = null;
		Review review = null;
		
		try {
			conn = factory.createConnection();
			review = new ReviewDAO().selectOne(conn, reviewNo);
			
			//지금 여기 board가 널임
			System.out.println(review.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return review;
	}
	
	

	//케이크 상세화면에 해당 후기를 뿌려주기 위해  작성된 메소드(cakeinfodetailservlet에서 불러옵니다.)
		public ArrayList<ReviewAndComm> printSearchList(int cakeNo) { //currentPage삭제
			Connection conn = null;	
			ArrayList<ReviewAndComm> rcList = null;
			try {
				conn = factory.createConnection();
				rcList = new ReviewDAO().selectSearchList(conn, cakeNo);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(conn);
			}
			
			return rcList;
		}

		//업체 상세화면 후기
		public ArrayList<ReviewAndComm>  printSearchList(String shopId) { //currentPage삭제
			Connection conn = null;
			ArrayList<ReviewAndComm> rcList = null;
			
			try {
				conn = factory.createConnection();
				rcList = new ReviewDAO().selectSearchList(conn, shopId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(conn);
			}

			return rcList;
		}

	public PageData printSearchList(String shopId, int currentPage) {
		Connection conn = null;
		PageData pageData = new PageData();
		
		ArrayList<Review> rList = new ReviewDAO().selectSearchList(conn, shopId, currentPage);
		return null;
	}
	
	
//		pd.setRcList(new ReviewCommDAO().selectAllComment(conn, currentPage,));
	
	
	public ReviewData printAllList(int currentPage, String shopId) {
		Connection conn = null;
		ReviewData rd = new ReviewData();
		try {
			conn = factory.createConnection();
			
			rd.setReviewList(new ReviewDAO().selectAllList(conn, currentPage, shopId));
			rd.setPageNavi(new ReviewDAO().getPageNavi(conn, currentPage, shopId));
//			rd.setTotalCount(new ReviewDAO().totalCount(conn));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return rd; 
	
	}
	
	
}
