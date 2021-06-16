package order.model.vo;

import java.sql.Date;

public class Order {
	private int orderNo;
	private String request;
	private String deliveryType;
	private String payType;
	private Date deliveryDate;
	private String pickupTime;
	private Date orderDate;
	private String memberId;
	private int cakeNo;
	private String shopId;
	private String cakeName;
	private int cakeAmount;
	private String deliveryAddr;
	private String ordererName;
	private int cakePrice;
	private String ordererPhone;
	private String cakeFileName;
	private String cakeFilePath;
	private String date;
	private String chatUrl;
	
	
	public Order() {}
	
	public String getCakeFileName() {
		return cakeFileName;
	}
	
	public void setCakeFileName(String cakeFileName) {
		this.cakeFileName = cakeFileName;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getPickupTime() {
		return pickupTime;
	}

	public void setPickupTime(String pickupTime) {
		this.pickupTime = pickupTime;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public int getCakeNo() {
		return cakeNo;
	}

	public void setCakeNo(int cakeNo) {
		this.cakeNo = cakeNo;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getCakeName() {
		return cakeName;
	}

	public void setCakeName(String cakeName) {
		this.cakeName = cakeName;
	}

	public int getCakeAmount() {
		return cakeAmount;
	}

	public void setCakeAmount(int cakeAmount) {
		this.cakeAmount = cakeAmount;
	}

	public String getDeliveryAddr() {
		return deliveryAddr;
	}

	public void setDeliveryAddr(String deliveryAddr) {
		this.deliveryAddr = deliveryAddr;
	}

	public String getOrdererName() {
		return ordererName;
	}

	public void setOrdererName(String ordererName) {
		this.ordererName = ordererName;
	}

	public int getCakePrice() {
		return cakePrice;
	}

	public void setCakePrice(int cakePrice) {
		this.cakePrice = cakePrice;
	}

	public String getOrdererPhone() {
		return ordererPhone;
	}

	public void setOrdererPhone(String ordererPhone) {
		this.ordererPhone = ordererPhone;
	}

	public String getCakeFilePath() {
		return cakeFilePath;
	}

	public void setCakeFilePath(String cakeFilePath) {
		this.cakeFilePath = cakeFilePath;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getChatUrl() {
		return chatUrl;
	}

	public void setChatUrl(String chatUrl) {
		this.chatUrl = chatUrl;
	}

	@Override
	public String toString() {
		return "Order [orderNo=" + orderNo + ", request=" + request + ", deliveryType=" + deliveryType + ", payType="
				+ payType + ", deliveryDate=" + deliveryDate + ", pickupTime=" + pickupTime + ", orderDate=" + orderDate
				+ ", memberId=" + memberId + ", cakeNo=" + cakeNo + ", shopId=" + shopId + ", cakeName=" + cakeName
				+ ", cakeAmount=" + cakeAmount + ", deliveryAddr=" + deliveryAddr + ", ordererName=" + ordererName
				+ ", cakePrice=" + cakePrice + ", ordererPhone=" + ordererPhone + ", cakeFileName=" + cakeFileName
				+ ", cakeFilePath=" + cakeFilePath + ", date=" + date + ", chatUrl=" + chatUrl + "]";
	}

}