package review.model.vo;

import java.sql.Date;
import java.sql.Timestamp;

public class Review {
	private int reviewNo;
	private String reviewContent; 
	private Date reviewDate;
	private float reviewScore;
	private String memberId;
	private int cakeNo;
	private String shopId;
	private String reviewFilePath;
	private long reviewFileSize;
	private Timestamp review_uploadTime;
	private String review_fileName;
	private int numbering;
	
	public int getNumbering() {
		return numbering;
	}


	public void setNumbering(int numbering) {
		this.numbering = numbering;
	}


	public Review() {}


	public int getReviewNo() {
		return reviewNo;
	}


	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}


	public String getReviewContent() {
		return reviewContent;
	}


	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}


	public Date getReviewDate() {
		return reviewDate;
	}


	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}


	public float getReviewScore() {
		return reviewScore;
	}


	public void setReviewScore(float reviewScore) {
		this.reviewScore = reviewScore;
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


	public String getReviewFilePath() {
		return reviewFilePath;
	}


	public void setReviewFilePath(String reviewFilePath) {
		this.reviewFilePath = reviewFilePath;
	}


	public long getReviewFileSize() {
		return reviewFileSize;
	}


	public void setReviewFileSize(long reviewFileSize) {
		this.reviewFileSize = reviewFileSize;
	}


	public Timestamp getReview_uploadTime() {
		return review_uploadTime;
	}


	public void setReview_uploadTime(Timestamp review_uploadTime) {
		this.review_uploadTime = review_uploadTime;
	}


	public String getReview_fileName() {
		return review_fileName;
	}


	public void setReview_fileName(String review_fileName) {
		this.review_fileName = review_fileName;
	}


	@Override
	public String toString() {
		return "Review [reviewNo=" + reviewNo + ", reviewContent=" + reviewContent + ", reviewDate=" + reviewDate
				+ ", reviewScore=" + reviewScore + ", memberId=" + memberId + ", cakeNo=" + cakeNo + ", shopId="
				+ shopId + ", reviewFilePath=" + reviewFilePath + ", reviewFileSize=" + reviewFileSize
				+ ", review_uploadTime=" + review_uploadTime + ", review_fileName=" + review_fileName + "]";
	}

}
