package board.model.service;

import java.sql.Connection;
import java.sql.SQLException;

import board.model.dao.BoardCommentDao;
import board.model.dao.BoardDao;
import board.model.vo.Board;
import board.model.vo.BoardComment;
import board.model.vo.BoardCommentData;
import board.model.vo.BoardData;
import common.JDBCTemplate;

public class BoardCommentService {
	private JDBCTemplate factory;
	
	public BoardCommentService() {
		factory = JDBCTemplate.getConnection();
	}
		
		public int registerBoard(BoardComment bc) {
			Connection conn = null;
			int result = 0;
			try {
				conn = factory.createConnection();
				result = new BoardCommentDao().insertBoardComment(conn, bc);
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
		
		public BoardCommentData printAllList(int csNo) {
			Connection conn = null;
			BoardCommentData bcd = new BoardCommentData();
			try {
				conn = factory.createConnection();
				bcd.setBoardCommentList(new BoardCommentDao().selectAllList(conn,csNo));
			
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(conn);
			}
			return bcd; 
		
		}
		
		public BoardComment printOne(int Cs_No) {
			Connection conn = null;
			BoardComment bc = null;
			try {
				conn = factory.createConnection();
				bc = new BoardCommentDao().selectOne(conn, Cs_No);
				
				//지금 여기 board가 널임
				System.out.println(bc.toString());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(conn);
			}
			return bc;
		}
		
		public int deleteBoardComment(int csNo) {
			Connection conn = null;
			int result = 0;
			try {
				conn = factory.createConnection();
				result = new BoardCommentDao().deleteBoardComment(conn, csNo);
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
		
	
}
