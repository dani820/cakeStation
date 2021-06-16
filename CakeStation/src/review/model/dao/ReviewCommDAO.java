package review.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import board.model.vo.BoardComment;
import common.JDBCTemplate;
import review.model.vo.Review;
import review.model.vo.ReviewComment;

public class ReviewCommDAO {
	public ArrayList<ReviewComment> selectAllComm(Connection conn,int reviewNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<ReviewComment> rcList = null;
		String query = "SELECT * FROM REVIEW_COMMENT WHERE REVIEW_NO =?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, reviewNo);
			rset = pstmt.executeQuery();
			rcList = new ArrayList<ReviewComment>();
			while (rset.next()) {
				ReviewComment rc = new ReviewComment();
				rc.setReplyNo(rset.getInt("REPLY_NO"));
				rc.setReplyContents(rset.getString("REPLY_CONTENTS"));
				rc.setReplyWriter(rset.getString("REPLY_WRITER"));
				rc.setReplyDate(rset.getDate("REPLY_DATE"));
				rc.setReviewNo(rset.getInt("REVIEW_NO"));
				rcList.add(rc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return rcList;
	}
	
	
	
	

	// 등록

	
	public int insertReviewComm(Connection conn, ReviewComment rc) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO REVIEW_COMMENT VALUES(SEQ_REVIEW_COMMENT.NEXTVAL,?,?,SYSDATE,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, rc.getReplyContents());
			pstmt.setString(2, rc.getReplyWriter());
			pstmt.setInt(3, rc.getReviewNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	

	// 수정
	public int updateReviewComm(Connection conn, ReviewComment reviewComm) {

		return 0;
	}
	
	
	

	// 삭제
	public int deleteReviewComm(Connection conn, int replyNo) {

		PreparedStatement pstmt = null;
		int result = 0;
		String query = "DELETE FROM REVIEW_COMMENT WHERE REPLY_NO = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, replyNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	
	
	
//		
//		public int insertComm(Connection conn, ReviewComment reviewComm) {
//			PreparedStatement pstmt = null;
//			int result = 0;
//			String query;
//			
//			return result;
//		}
//	
//		public int modifyComm(Connection conn, ReviewComment reviewComm) {
//			PreparedStatement pstmt = null;
//			int result = 0;
//			String query;
//					
//			return result;
//		}
//	
//		public int deleteComm(Connection conn, int commNum) {
//			PreparedStatement pstmt = null;
//			int result = 0;
//			String query;
//			
//			return 0;
//		}

	// 후기댓글 가져오기
	public ReviewComment selectOne(Connection conn, int reviewNo) {

		return null;
	}

}
