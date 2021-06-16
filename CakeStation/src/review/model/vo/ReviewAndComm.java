package review.model.vo;

import java.sql.Date;
import java.sql.Timestamp;

public class ReviewAndComm {
	
	private int reviewNo;
	//후기
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
	
	//댓글
	private int replyNo;
	private String replyContents;
	private String replyWriter;
	private Date replyDate;
	
	public ReviewAndComm() {}

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

	public int getReplyNo() {
		return replyNo;
	}

	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}

	public String getReplyContents() {
		return replyContents;
	}

	public void setReplyContents(String replyContents) {
		this.replyContents = replyContents;
	}

	public String getReplyWriter() {
		return replyWriter;
	}

	public void setReplyWriter(String replyWriter) {
		this.replyWriter = replyWriter;
	}

	public Date getReplyDate() {
		return replyDate;
	}

	public void setReplyDate(Date replyDate) {
		this.replyDate = replyDate;
	}

	@Override
	public String toString() {
		return "ReviewAndComm [reviewNo=" + reviewNo + ", reviewContent=" + reviewContent + ", reviewDate=" + reviewDate
				+ ", reviewScore=" + reviewScore + ", memberId=" + memberId + ", cakeNo=" + cakeNo + ", shopId="
				+ shopId + ", reviewFilePath=" + reviewFilePath + ", reviewFileSize=" + reviewFileSize
				+ ", review_uploadTime=" + review_uploadTime + ", review_fileName=" + review_fileName + ", replyNo="
				+ replyNo + ", replyContents=" + replyContents + ", replyWriter=" + replyWriter + ", replyDate="
				+ replyDate + "]";
	}

}
