package review.model.vo;

import java.sql.Date;

public class ReviewComment {
	private int replyNo;
	private String replyContents;
	private String replyWriter;
	private Date replyDate;
	private int reviewNo;
	
	public ReviewComment() {}
	
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
	public int getReviewNo() {
		return reviewNo;
	}
	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}
	
	@Override
	public String toString() {
		return "ReviewComment [replyNo=" + replyNo + ", replyContents=" + replyContents + ", replyWriter=" + replyWriter
				+ ", replyDate=" + replyDate + ", reviewNo=" + reviewNo + "]";
	}
	
}
