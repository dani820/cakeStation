package cakeinfo.model.vo;

import java.sql.Timestamp;

public class CakeInfo {
	// 멤버변수
		private int cakeNo;
		private String shopId;
		private String cakeName;
		private int cakePrice;
		private String cakeChar;
		private String dayRec;
		private String dayPickUp;
		private String cakeThema;
		private int cakeAvgScore;
		private String cakeFilePath;
		private long cakeFileSize;
		private Timestamp cakeUploadTime;
		private String cakeFileName;
		private String cakeType;

		// 기본생성자
		public CakeInfo() {
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

		@Override
		public String toString() {
			return "CakeInfo [cakeNo=" + cakeNo + ", shopId=" + shopId + ", cakeName=" + cakeName + ", cakePrice="
					+ cakePrice + ", cakeChar=" + cakeChar + ", dayRec=" + dayRec + ", dayPickUp=" + dayPickUp
					+ ", cakeThema=" + cakeThema + ", cakeAvgScore=" + cakeAvgScore + ", cakeFilePath=" + cakeFilePath
					+ ", cakeFileSize=" + cakeFileSize + ", cakeUploadTime=" + cakeUploadTime + ", cakeFileName="
					+ cakeFileName + ", cakeType=" + cakeType + "]";
		}

}
