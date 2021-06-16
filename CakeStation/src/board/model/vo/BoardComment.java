package board.model.vo;

import java.sql.Date;
import java.sql.Timestamp;


public class BoardComment {
	
	private int csReplyNo;
	private String csReplyContents;
	private String csReplyWriter;
	private Date csReplyDate;
	private int csNo;
	private String csPublic;
	
	public int getCsReplyNo() {
		return csReplyNo;
	}
	public void setCsReplyNo(int csReplyNo) {
		this.csReplyNo = csReplyNo;
	}
	public String getCsReplyContents() {
		return csReplyContents;
	}
	public void setCsReplyContents(String csReplyContents) {
		this.csReplyContents = csReplyContents;
	}
	public String getCsReplyWriter() {
		return csReplyWriter;
	}
	public void setCsReplyWriter(String csReplyWriter) {
		this.csReplyWriter = csReplyWriter;
	}
	public Date getCsReplyDate() {
		return csReplyDate;
	}
	public void setCsReplyDate(Date csReplyDate) {
		this.csReplyDate = csReplyDate;
	}
	public int getCsNo() {
		return csNo;
	}
	public void setCsNo(int csNo) {
		this.csNo = csNo;
	}
	
	public String getCsPublic() {
		return csPublic;
	}
	public void setCsPublic(String csPublic) {
		this.csPublic = csPublic;
	}
	@Override
	public String toString() {
		return "BoardComment [csReplyNo=" + csReplyNo + ", csReplyContents=" + csReplyContents + ", csReplyWriter="
				+ csReplyWriter + ", csReplyDate=" + csReplyDate + ", csNo=" + csNo + ", csPublic=" + csPublic + "]";
	}
	
	
	
}
