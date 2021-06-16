package review.model.vo;

import java.util.ArrayList;

public class ReviewData {
	private ArrayList<Review> reviewList;
	private  String pageNavi;
	private int totalCount;
	
	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public ReviewData() {}

	public  ArrayList<Review> getReviewList() {
		return reviewList;
	}

	public void setReviewList(ArrayList<Review> reviewList) {
		this.reviewList = reviewList;
	}

public String getPageNavi() {
		return pageNavi;
}

public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
}
