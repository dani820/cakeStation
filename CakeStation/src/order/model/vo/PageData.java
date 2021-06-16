package order.model.vo;

import java.util.ArrayList;

import cakeinfo.model.vo.CakeInfo;

public class PageData {

	private ArrayList<CakeInfo> cakeList;
	private ArrayList<Order> orderList;
	private String pageNavi;
	
	public PageData() {}

	public ArrayList<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(ArrayList<Order> orderList) {
		this.orderList = orderList;
	}

	
	public ArrayList<CakeInfo> getCakeList() {
		return cakeList;
	}

	public void setCakeList(ArrayList<CakeInfo> cakeList) {
		this.cakeList = cakeList;
	}


	public String getPageNavi() {
		return pageNavi;
	}
	
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}

	@Override
	public String toString() {
		return "PageData [cakeList=" + cakeList + ", orderList=" + orderList + ", rList=" 
				+ ", pageNavi=" + pageNavi + "]";
	}

	
	
}
