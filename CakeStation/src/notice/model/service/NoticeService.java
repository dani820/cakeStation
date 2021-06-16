package notice.model.service;

import java.sql.Connection;
import java.sql.SQLException;

import common.JDBCTemplate;
import notice.model.dao.NoticeDAO;
import notice.model.vo.Notice;
import notice.model.vo.PageData;

public class NoticeService {
	
	private JDBCTemplate factory;
	
	public NoticeService() {
		factory = JDBCTemplate.getConnection();
	}

	public PageData printAllList(int currentPage) {
		Connection conn = null;
		//ArrayList<Notice> nList = null;
		//String pageNavi = null;
		PageData pd = new PageData();
		try {
			conn = factory.createConnection();
			pd.setnList(new NoticeDAO().selectAllList(conn, currentPage));
			pd.setPageNavi(new NoticeDAO().getPageNavi(conn, currentPage));
			//pageNavi란? 1 2 3 4 5 6 7 8 9 10
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return pd;
		//하나의 메소드는 하나의 값만 리턴할 수 있음
		//두개 이상의 값을 리턴해야 하는 경우 그 값들을 저장하는 클래스를 만들고
		//그 클래스를 리턴함
	}

	public Notice printOne(int noticeNo) {
		Connection conn = null;
		Notice notice = null;
		
		try {
			conn = factory.createConnection();
			notice = new NoticeDAO().selectOne(conn, noticeNo);
			
			//댓글 Has-a 상속관계 일때
			//notice.setComments(new NoticeDAO.selectCommentList(conn, noticeNo));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return notice;
	}

	public int registerNotice(Notice notice) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = factory.createConnection();
			result = new NoticeDAO().insertNotice(conn, notice);
			System.out.println(result);
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

	public int deleteNotice(int noticeNo) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = factory.createConnection();
			result = new NoticeDAO().deleteNotice(conn, noticeNo);
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

	public int modifyNotice(Notice notice) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = factory.createConnection();
			result = new NoticeDAO().updateNotice(conn, notice);
			if(result > 0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.commit(conn);
		}
		return result;
	}

	public PageData printSearchList(String search, int currentPage) {
		Connection conn = null;
		PageData pd = new PageData();
		try {
			conn = factory.createConnection();
			pd.setnList(new NoticeDAO().selectSearchList(conn, search, currentPage));
			pd.setPageNavi(new NoticeDAO().getSearchPageNavi(conn, search, currentPage));
			//반환된 검색된 공지사항 리스트와 page navi를 한꺼번에 리턴해주기 위해 PageData 객체에 저장하여 return
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return pd;
	}

}
