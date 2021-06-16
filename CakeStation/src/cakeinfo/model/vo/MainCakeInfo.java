package cakeinfo.model.vo;

import java.sql.Timestamp;

public class MainCakeInfo {
		private int cakeNo;
		private String shopId;
		private String cakeName; // 케이크명
		private int cakePrice; // 케이크 가격
//		private String cakeChar;
//		private char dayRec;
//		private char dayPickUp;
//		private String cakeThema;
		private int cakeAvgScore; // 케이크별점(평점)
		private String cakeFilePath; // 파일 패스
//		private long cakeFileSize;
//		private Timestamp cakeUploadTime;
		private String cakeFileName; // 파일 네임
		private String reviewContent; // 리뷰 내용
		private int reviewCount; // 리뷰 갯수
	

		public MainCakeInfo() {
		}


		
		public int getReviewCount() {
			return reviewCount;
		}

		public void setReviewCount(int reviewCount) {
			this.reviewCount = reviewCount;
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


		public String getReviewContent() {
			return reviewContent;
		}


		public void setReviewContent(String reviewContent) {
			this.reviewContent = reviewContent;
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


		public String getCakeFileName() {
			return cakeFileName;
		}


		public void setCakeFileName(String cakeFileName) {
			this.cakeFileName = cakeFileName;
		}



		@Override
		public String toString() {
			return "MainCakeInfo [cakeNo=" + cakeNo + ", shopId=" + shopId + ", cakeName=" + cakeName + ", cakePrice="
					+ cakePrice + ", cakeAvgScore=" + cakeAvgScore + ", cakeFilePath=" + cakeFilePath
					+ ", cakeFileName=" + cakeFileName + ", reviewContent=" + reviewContent + ", reviewCount="
					+ reviewCount + "]";
		}




}
