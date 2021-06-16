package user.model.vo;

import java.util.ArrayList;

import order.model.vo.Order;
import shop.model.vo.ShopMember;

public class PageData {

	private ArrayList<CustomerMember> ctMemberList;
	private ArrayList<ShopMember> shopMemberList;
	private ArrayList<Order> orderList;
	private String pageNavi;
	
	public PageData() {}
	
	public ArrayList<CustomerMember> getCtMemberList() {
		return ctMemberList;
	}
	public void setCtMemberList(ArrayList<CustomerMember> ctMemberList) {
		this.ctMemberList = ctMemberList;
	}
	public ArrayList<ShopMember> getShopMemberList() {
		return shopMemberList;
	}
	public void setShopMemberList(ArrayList<ShopMember> shopMemberList) {
		this.shopMemberList = shopMemberList;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	public ArrayList<Order> getOrderList() {
		return orderList;
	}
	public void setOrderList(ArrayList<Order> orderList) {
		this.orderList = orderList;
	}
	
}
