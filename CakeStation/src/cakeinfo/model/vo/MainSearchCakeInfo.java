package cakeinfo.model.vo;

import java.sql.Timestamp;

public class MainSearchCakeInfo {
//	CAKE_NAME, CAKE_PRICE, SHOP_NAME, CAKE_FILEPATH(�����ҷ�����) / + ����
		private int cakeNo;
		private String shopId;
		private String shopName;
		private String cakeName;
		private int cakePrice;
//		private String cakeChar;
		private String shopLAdd; // ����
		private String dayRec; // ���ɹ��
//		private char dayPickUp;
		private String cakeThema; // �׸�
		private String cakeType; // ����ũ ����
//		private int cakeAvgScore;
		private String cakeFilePath;
//		private long cakeFileSize;
//		private Timestamp cakeUploadTime;
//		private String cakeFileName;

		
		public MainSearchCakeInfo() {}

		


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




		public String getShopName() {
			return shopName;
		}




		public void setShopName(String shopName) {
			this.shopName = shopName;
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




		public String getShopLAdd() {
			return shopLAdd;
		}




		public void setShopLAdd(String shopLAdd) {
			this.shopLAdd = shopLAdd;
		}




		public String getDayRec() {
			return dayRec;
		}




		public void setDayRec(String dayRec) {
			this.dayRec = dayRec;
		}




		public String getCakeThema() {
			return cakeThema;
		}




		public void setCakeThema(String cakeThema) {
			this.cakeThema = cakeThema;
		}




		public String getCakeType() {
			return cakeType;
		}




		public void setCakeType(String cakeType) {
			this.cakeType = cakeType;
		}




		public String getCakeFilePath() {
			return cakeFilePath;
		}




		public void setCakeFilePath(String cakeFilePath) {
			this.cakeFilePath = cakeFilePath;
		}




		@Override
		public String toString() {
			return "MainSearchCakeInfo [shopName=" + shopName + ", cakeName=" + cakeName + ", cakePrice=" + cakePrice
					+ ", shopLAdd=" + shopLAdd + ", dayRec=" + dayRec + ", cakeThema=" + cakeThema + ", cakeType="
					+ cakeType + ", cakeFilePath=" + cakeFilePath + "]";
		}


		
		
}
