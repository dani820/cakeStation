package review.model.vo;

import java.util.ArrayList;



public class ReviewCommentData {
private ArrayList<ReviewComment> rcList;
	
	
	public ReviewCommentData() {}

	public  ArrayList<ReviewComment> getReviewCommentList() {
		return rcList;
	}

	public void setReviewCommentList(ArrayList<ReviewComment> rcList) {
		this.rcList = rcList;
	}
}
