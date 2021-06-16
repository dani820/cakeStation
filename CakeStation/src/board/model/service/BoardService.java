package board.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import board.model.dao.BoardDao;
import board.model.vo.Board;
import board.model.vo.BoardData;
import common.JDBCTemplate;



public class BoardService {
	
		private JDBCTemplate factory;
		
		public BoardService() {
			factory = JDBCTemplate.getConnection();
		}

		public BoardData printAllList(int currentPage) {
			Connection conn = null;
			BoardData bd = new BoardData();
			try {
				conn = factory.createConnection();
				bd.setBoardList(new BoardDao().selectAllList(conn, currentPage));
				bd.setPageNavi(new BoardDao().getPageNavi(conn, currentPage));
				bd.setTotalCount(new BoardDao().totalCount(conn));
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(conn);
			}
			return bd; 
		
		}
		
		

		public Board printOne(int Cs_No) {
			Connection conn = null;
			Board board = null;
			try {
				conn = factory.createConnection();
				board = new BoardDao().selectOne(conn, Cs_No);
				
				//지금 여기 board가 널임
				System.out.println(board.toString());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(conn);
			}
			return board;
		}

		public int registerBoard(Board board) {
			Connection conn = null;
			int result = 0;
			try {
				conn = factory.createConnection();
				result = new BoardDao().insertBoard(conn, board);
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

		

		public int modifyBoard(Board board) {
			Connection conn = null;
			int result = 0;
			try {
				conn = factory.createConnection();
				result = new BoardDao().updateBoard(conn, board);
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

		public BoardData printSearchList(String search, int currentPage) {
			Connection conn = null;
			BoardData bd = new BoardData();
			try {
				conn = factory.createConnection();
				bd.setBoardList(new BoardDao().selectSearchList(conn, search, currentPage));
				bd.setPageNavi(new BoardDao().getSearchPageNavi(conn, search, currentPage));
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(conn);
			}
			return bd;  
		}

		public int deleteBoard(int cs_No) {
			Connection conn = null;
			int result = 0;
			try {
				conn = factory.createConnection();
				result = new BoardDao().deleteBoard(conn, cs_No);
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












