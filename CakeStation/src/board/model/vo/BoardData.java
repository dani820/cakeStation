package board.model.vo;

import java.util.ArrayList;

public class BoardData {

//
		private ArrayList<Board> boardList;
		private  String pageNavi;
		private int totalCount;
		
		public int getTotalCount() {
			return totalCount;
		}

		public void setTotalCount(int totalCount) {
			this.totalCount = totalCount;
		}

		public BoardData() {}

		public  ArrayList<Board> getBoardList() {
			return boardList;
		}

		public void setBoardList(ArrayList<Board> boardList) {
			this.boardList = boardList;
		}

	public String getPageNavi() {
			return pageNavi;
}

	public void setPageNavi(String pageNavi) {
			this.pageNavi = pageNavi;
		}
		
	}



