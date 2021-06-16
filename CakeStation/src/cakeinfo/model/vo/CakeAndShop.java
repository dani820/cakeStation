package cakeinfo.model.vo;

import java.sql.Timestamp;

public class CakeAndShop {
	
	private int cakeNo;
	private String shopId;
	private String cakeName;
	private int cakePrice;
	private String cakeChar;
	private String dayRec; //수령방법
	private String dayPickUp; //당일 수령가능 여부
	private String cakeThema;
	private int cakeAvgScore;
	private String cakeFilePath;
	private long cakeFileSize;
	private Timestamp cakeUploadTime;
	private String cakeFileName;
	private String cakeType;
	//업체명, 업체 위치
	private String shopName;
	private String shopLAdd;
	private String shopMAdd;
	private String shopSAdd;
	private String shopUrl;
	private String chatUrl;
	//OPENING_START OPENING_END
	private int openingStart;
	private int openingEnd;
	
	public CakeAndShop() {}

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

	public int getCakePrice() {
		return cakePrice;
	}

	public void setCakePrice(int cakePrice) {
		this.cakePrice = cakePrice;
	}

	public String getCakeChar() {
		return cakeChar;
	}

	public void setCakeChar(String cakeChar) {
		this.cakeChar = cakeChar;
	}

	public String getDayRec() {
		return dayRec;
	}

	public void setDayRec(String dayRec) {
		this.dayRec = dayRec;
	}

	public String getDayPickUp() {
		return dayPickUp;
	}

	public void setDayPickUp(String dayPickUp) {
		this.dayPickUp = dayPickUp;
	}

	public String getCakeThema() {
		return cakeThema;
	}

	public void setCakeThema(String cakeThema) {
		this.cakeThema = cakeThema;
	}

	public int getCakeAvgScore() {
		return cakeAvgScore;
	}

	public void setCakeAvgScore(int cakeAvgScore) {
		this.cakeAvgScore = cakeAvgScore;
	}

	public String getCakeFilePath() {
		return cakeFilePath;
	}

	public void setCakeFilePath(String cakeFilePath) {
		this.cakeFilePath = cakeFilePath;
	}

	public long getCakeFileSize() {
		return cakeFileSize;
	}

	public void setCakeFileSize(long cakeFileSize) {
		this.cakeFileSize = cakeFileSize;
	}

	public Timestamp getCakeUploadTime() {
		return cakeUploadTime;
	}

	public void setCakeUploadTime(Timestamp cakeUploadTime) {
		this.cakeUploadTime = cakeUploadTime;
	}

	public String getCakeFileName() {
		return cakeFileName;
	}

	public void setCakeFileName(String cakeFileName) {
		this.cakeFileName = cakeFileName;
	}

	public String getCakeType() {
		return cakeType;
	}

	public void setCakeType(String cakeType) {
		this.cakeType = cakeType;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
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

	public String getShopUrl() {
		return shopUrl;
	}

	public void setShopUrl(String shopUrl) {
		this.shopUrl = shopUrl;
	}

	public String getChatUrl() {
		return chatUrl;
	}

	public void setChatUrl(String chatUrl) {
		this.chatUrl = chatUrl;
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

	@Override
	public String toString() {
		return "CakeAndShop [cakeNo=" + cakeNo + ", shopId=" + shopId + ", cakeName=" + cakeName + ", cakePrice="
				+ cakePrice + ", cakeChar=" + cakeChar + ", dayRec=" + dayRec + ", dayPickUp=" + dayPickUp
				+ ", cakeThema=" + cakeThema + ", cakeAvgScore=" + cakeAvgScore + ", cakeFilePath=" + cakeFilePath
				+ ", cakeFileSize=" + cakeFileSize + ", cakeUploadTime=" + cakeUploadTime + ", cakeFileName="
				+ cakeFileName + ", cakeType=" + cakeType + ", shopName=" + shopName + ", shopLAdd=" + shopLAdd
				+ ", shopMAdd=" + shopMAdd + ", shopSAdd=" + shopSAdd + ", shopUrl=" + shopUrl + ", chatUrl=" + chatUrl
				+ ", openingStart=" + openingStart + ", openingEnd=" + openingEnd + "]";
	}

}