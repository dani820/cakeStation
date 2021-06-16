package shop.model.vo;

import java.sql.Date;

public class ShopMember {
	private String shopId;
	private String shopPwd;
	private String shopName;
	private String CEOName;
	private String shopCRN;
	private String shopLAdd;
	private String shopMAdd;
	private String shopSAdd;
	private String shopPhone;
	private String shopEmail;
	private Date shopEnroll;
	private String lat;
	private String lng;
	private String shopRecieve;
	private String approvalYN;
	private String withdrawYN;
	private int shopAvgScore;
	private String approvalYn;
	private int openingStart;
	private int openingEnd;
	private String closed;
	private String introduced;
	private String shopWebsite; //업체웹사이트
	private String chatUrl; //오픈채팅url
	

	public ShopMember() {}


	public String getShopId() {
		return shopId;
	}


	public void setShopId(String shopId) {
		this.shopId = shopId;
	}


	public String getShopPwd() {
		return shopPwd;
	}


	public void setShopPwd(String shopPwd) {
		this.shopPwd = shopPwd;
	}


	public String getShopName() {
		return shopName;
	}


	public void setShopName(String shopName) {
		this.shopName = shopName;
	}


	public String getCEOName() {
		return CEOName;
	}


	public void setCEOName(String cEOName) {
		CEOName = cEOName;
	}


	public String getShopCRN() {
		return shopCRN;
	}


	public void setShopCRN(String shopCRN) {
		this.shopCRN = shopCRN;
	}


	public String getShopLAdd() {
		return shopLAdd;
	}


	public void setShopLAdd(String shopLAdd) {
		this.shopLAdd = shopLAdd;
	}


	public String getShopMAdd() {
		return shopMAdd;
	}


	public void setShopMAdd(String shopMAdd) {
		this.shopMAdd = shopMAdd;
	}


	public String getShopSAdd() {
		return shopSAdd;
	}


	public void setShopSAdd(String shopSAdd) {
		this.shopSAdd = shopSAdd;
	}


	public String getShopPhone() {
		return shopPhone;
	}


	public void setShopPhone(String shopPhone) {
		this.shopPhone = shopPhone;
	}


	public String getShopEmail() {
		return shopEmail;
	}


	public void setShopEmail(String shopEmail) {
		this.shopEmail = shopEmail;
	}


	public Date getShopEnroll() {
		return shopEnroll;
	}


	public void setShopEnroll(Date shopEnroll) {
		this.shopEnroll = shopEnroll;
	}


	public String getLat() {
		return lat;
	}


	public void setLat(String lat) {
		this.lat = lat;
	}


	public String getLng() {
		return lng;
	}


	public void setLng(String lng) {
		this.lng = lng;
	}


	public String getShopRecieve() {
		return shopRecieve;
	}


	public void setShopRecieve(String shopRecieve) {
		this.shopRecieve = shopRecieve;
	}


	public String getApprovalYN() {
		return approvalYN;
	}


	public void setApprovalYN(String approvalYN) {
		this.approvalYN = approvalYN;
	}


	public String getWithdrawYN() {
		return withdrawYN;
	}


	public void setWithdrawYN(String withdrawYN) {
		this.withdrawYN = withdrawYN;
	}


	public int getShopAvgScore() {
		return shopAvgScore;
	}


	public void setShopAvgScore(int shopAvgScore) {
		this.shopAvgScore = shopAvgScore;
	}


	public String getApprovalYn() {
		return approvalYn;
	}


	public void setApprovalYn(String approvalYn) {
		this.approvalYn = approvalYn;
	}


	public int getOpeningStart() {
		return openingStart;
	}


	public void setOpeningStart(int openingStart) {
		this.openingStart = openingStart;
	}


	public int getOpeningEnd() {
		return openingEnd;
	}


	public void setOpeningEnd(int openingEnd) {
		this.openingEnd = openingEnd;
	}


	public String getClosed() {
		return closed;
	}


	public void setClosed(String closed) {
		this.closed = closed;
	}


	public String getIntroduced() {
		return introduced;
	}


	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}


	public String getShopWebsite() {
		return shopWebsite;
	}


	public void setShopWebsite(String shopWebsite) {
		this.shopWebsite = shopWebsite;
	}


	public String getChatUrl() {
		return chatUrl;
	}


	public void setChatUrl(String chatUrl) {
		this.chatUrl = chatUrl;
	}

	@Override
	public String toString() {
		return "ShopMember [shopId=" + shopId + ", shopPwd=" + shopPwd + ", shopName=" + shopName + ", CEOName="
				+ CEOName + ", shopCRN=" + shopCRN + ", shopLAdd=" + shopLAdd + ", shopMAdd=" + shopMAdd + ", shopSAdd="
				+ shopSAdd + ", shopPhone=" + shopPhone + ", shopEmail=" + shopEmail + ", shopEnroll=" + shopEnroll
				+ ", lat=" + lat + ", lng=" + lng + ", shopRecieve=" + shopRecieve + ", approvalYN=" + approvalYN
				+ ", withdrawYN=" + withdrawYN + ", shopAvgScore=" + shopAvgScore + ", approvalYn=" + approvalYn
				+ ", openingStart=" + openingStart + ", openingEnd=" + openingEnd + ", closed=" + closed
				+ ", introduced=" + introduced + ", shopWebsite=" + shopWebsite + ", chatUrl=" + chatUrl + "]";
	}

}
