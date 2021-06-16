package board.model.vo;

import java.sql.Date;
import java.sql.Timestamp;

public class Board {
	private int csNo;
	private String csTitle;
	private String csContents;
	private Date csDate;
	private String csPublic;
	private String csId;
	private String Pwd;
	private String csFilePath;
	private long csFileSize;
	private String csFileName;
	private Timestamp csUploadTime;
	
	
	public Board() {}


	public int getCsNo() {
		return csNo;
	}


	public void setCsNo(int csNo) {
		this.csNo = csNo;
	}


	public String getCsTitle() {
		return csTitle;
	}


	public void setCsTitle(String csTitle) {
		this.csTitle = csTitle;
	}


	public String getCsContents() {
		return csContents;
	}


	public void setCsContents(String csContents) {
		this.csContents = csContents;
	}


	public Date getCsDate() {
		return csDate;
	}


	public void setCsDate(Date csDate) {
		this.csDate = csDate;
	}


	public String getCsPublic() {
		return csPublic;
	}


	public void setCsPublic(String csPublic) {
		this.csPublic = csPublic;
	}


	public String getCsId() {
		return csId;
	}


	public void setCsId(String csId) {
		this.csId = csId;
	}


	public String getPwd() {
		return Pwd;
	}


	public void setPwd(String pwd) {
		Pwd = pwd;
	}


	public String getCsFilePath() {
		return csFilePath;
	}


	public void setCsFilePath(String csFilePath) {
		this.csFilePath = csFilePath;
	}


	


	public String getCsFileName() {
		return csFileName;
	}


	public void setCsFileName(String csFileName) {
		this.csFileName = csFileName;
	}


	public Timestamp getCsUploadTime() {
		return csUploadTime;
	}


	public void setCsUploadTime(Timestamp csUploadTime) {
		this.csUploadTime = csUploadTime;
	}


	public long getCsFileSize() {
		return csFileSize;
	}


	public void setCsFileSize(long csFileSize) {
		this.csFileSize = csFileSize;
	}


	@Override
	public String toString() {
		return "Board [csNo=" + csNo + ", csTitle=" + csTitle + ", csContents=" + csContents + ", csDate=" + csDate
				+ ", csPublic=" + csPublic + ", csId=" + csId + ", Pwd=" + Pwd + ", csFilePath=" + csFilePath
				+ ", csFileSize=" + csFileSize + ", csFileName=" + csFileName + ", csUploadTime=" + csUploadTime + "]";
	}


	


	

	
	
	
}
