package order.model.service;

import java.sql.Connection;
import java.sql.SQLException;

import common.JDBCTemplate;
import order.model.dao.ShopOrderDAO;
import order.model.vo.Order;
import order.model.vo.PageData;

public class ShopOrderService {
	// 연결 생성 위해 JDBCTemplate 클래스 객체 생성
	JDBCTemplate factory;

	// 기본생성자로 연결 생성 준비
	public ShopOrderService() {
		factory = JDBCTemplate.getConnection();
	}

	// 모든 주문 내역 불러오기
		public PageData printAllOrder(int currentPage, String shopId) {
			Connection conn = null;
			PageData pd = new PageData();
			
			try {
				conn = factory.createConnection();
				pd.setOrderList(new ShopOrderDAO().selectAllOrder(conn, currentPage, shopId));
				pd.setPageNavi(new ShopOrderDAO().getPageNavi(conn, currentPage, shopId));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(conn);
			}
			
			return pd;
		}

		// 주문 내역 취소하기
		public int orderCancel(int orderNum) {
			Connection conn = null;
			int result = 0;
			
			try {
				conn = factory.createConnection();
				result = new ShopOrderDAO().orderDelete(conn, orderNum);
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

	
	//주문서 추가
			public int registerOrder(Order order) {
				Connection conn = null;
				int result = 0;
				
				try {
					conn = factory.createConnection();
					result = new ShopOrderDAO().insertOrder(conn, order);
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
}
