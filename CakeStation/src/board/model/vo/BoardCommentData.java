package board.model.vo;

import java.util.ArrayList;


public class BoardCommentData {
	private ArrayList<BoardComment> bcList;
	
	
	public BoardCommentData() {}

	public  ArrayList<BoardComment> getBoardCommentList() {
		return bcList;
	}

	public void setBoardCommentList(ArrayList<BoardComment> bcList) {
		this.bcList = bcList;
	}


}
