package review.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import board.model.dao.BoardCommentDao;
import board.model.vo.BoardComment;
import board.model.vo.BoardCommentData;
import common.JDBCTemplate;
import review.model.dao.ReviewCommDAO;
import review.model.vo.Review;
import review.model.vo.ReviewComment;
import review.model.vo.ReviewCommentData;

public class ReviewCommService {

	// 연결 생성 위해 JDBCTemplate 클래스 객체 생성
	JDBCTemplate factory;

	// 기본생성자로 연결 생성 준비
	public ReviewCommService() {
		factory = JDBCTemplate.getConnection();
	}
	
	public ReviewCommentData printAllComm(int reviewNo) {
		Connection conn = null;
		ReviewCommentData rcd = new ReviewCommentData();
		try {
			conn = factory.createConnection();
			rcd.setReviewCommentList(new ReviewCommDAO().selectAllComm(conn,reviewNo));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return rcd; 
	}
	
	

//		public int enrollComm(ReviewComment reviewComm) {
//		Connection conn = null;
//		int result = 0;
//		result = new ReviewCommDAO().insertComm(conn, reviewComm);
//		return 0;
//	}
//	
//	public int updateComm(ReviewComment reviewComm) {
//		Connection conn = null;
//		int result = 0;
//		result = new ReviewCommDAO().modifyComm(conn, reviewComm);
//		
//		return result;
//	}
//	
//	public int deleteComm(int commNum) {
//		Connection conn = null;
//		int result = 0;
//		result = new ReviewCommDAO().deleteComm(conn, commNum);
//		
//		return result;
//	}
	
	// 등록
	
	
	public int registerReviewComm(ReviewComment rc) {
		Connection conn = null;
		int result = 0;
		try {
			conn = factory.createConnection();
			result = new ReviewCommDAO().insertReviewComm(conn, rc);
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

	// 수정
	public int modifyReviewComm(ReviewComment reviewComm) {
		Connection conn = null;
		int result = 0;

		result = new ReviewCommDAO().updateReviewComm(conn, reviewComm);

		return 0;
	}

	// 삭제
	public int deleteReviewComm(int replyNo) {
		Connection conn = null;
		int result = 0;
		try {
			conn = factory.createConnection();
			result = new ReviewCommDAO().deleteReviewComm(conn, replyNo);
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
	
	
	

	// 후기댓글 가져오기
	public ReviewComment printOne(int reviewNo) {
		Connection conn = null;

		ReviewComment reviewComm = new ReviewCommDAO().selectOne(conn, reviewNo);

		return null;
	}
}
