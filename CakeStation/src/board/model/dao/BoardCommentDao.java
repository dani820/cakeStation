package board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import board.model.vo.BoardComment;
import common.JDBCTemplate;

public class BoardCommentDao {

	public ArrayList<BoardComment> selectAllList(Connection conn,int csNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<BoardComment> bcList = null;
		String query = "SELECT * FROM CS_REPLY_COMMENT WHERE CS_NO =?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, csNo);
			rset = pstmt.executeQuery();
			bcList = new ArrayList<BoardComment>();
			while (rset.next()) {
				BoardComment bc = new BoardComment();
				bc.setCsReplyNo(rset.getInt("CS_REPLY_NO"));
				bc.setCsReplyContents(rset.getString("CS_REPLY_CONTENTS"));
				bc.setCsReplyWriter(rset.getString("CS_REPLY_WRITER"));
				bc.setCsReplyDate(rset.getDate("CS_REPLY_DATE"));
				bc.setCsNo(rset.getInt("CS_NO"));
				bc.setCsPublic(rset.getString("CS_PUBLIC"));

				bcList.add(bc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return bcList;
	}
	
	public int insertBoardComment(Connection conn, BoardComment bc) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO CS_REPLY_COMMENT VALUES(SEQ_CS_REPLY_COMMENT.NEXTVAL,?,?,SYSDATE,?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bc.getCsReplyContents());
			pstmt.setString(2, bc.getCsReplyWriter());
			pstmt.setInt(3, bc.getCsNo());
			pstmt.setString(4, null);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	
	public BoardComment selectOne(Connection conn, int CsNo) {
		PreparedStatement pstmt = null; // 위치홀더 사용함
		ResultSet rset = null;
		BoardComment bc = null;
		String query = "SELECT * FROM CS_REPLY_BOARD WHERE CS_NO = ?"; // 위치홀더는 물음표
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, CsNo);
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				//System.out.println("잘되나확인해보자");
				bc = new BoardComment();
				bc.setCsReplyNo(rset.getInt("CS_REPLY_NO"));
				bc.setCsReplyContents(rset.getString("CS_REPLY_CONTENTS"));
				bc.setCsReplyWriter(rset.getString("CS_REPLY_WRITER"));
				bc.setCsReplyDate(rset.getDate("CS_DATE"));
				bc.setCsNo(rset.getInt("CS_NO"));
				bc.setCsPublic(rset.getString("CS_PUBLIC"));
				
				//여기까진 널이 아님
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		
		return bc;
	}
	
	public int deleteBoardComment(Connection conn, int csReplyNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "DELETE FROM CS_REPLY_COMMENT WHERE CS_REPLY_NO = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, csReplyNo);
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
