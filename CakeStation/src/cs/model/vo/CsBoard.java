package cs.model.vo;

import java.sql.Date;
import java.sql.Timestamp;

public class CsBoard {
	private int csNum;
	private String csTitle;
	private String Contents;
	private Date csDate;
	private String csPublic;
	private String csId;
	private String Pwd;
	private String csFilePath;
	private long csFileSize;
	private Timestamp csUploadTime;
	private String csFileName;

	public CsBoard() {}

	public int getCsNum() {
		return csNum;
	}

	public void setCsNum(int csNum) {
		this.csNum = csNum;
	}

	public String getCsTitle() {
		return csTitle;
	}

	public void setCsTitle(String csTitle) {
		this.csTitle = csTitle;
	}

	public String getContents() {
		return Contents;
	}

	public void setContents(String contents) {
		Contents = contents;
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

	public long getCsFileSize() {
		return csFileSize;
	}

	public void setCsFileSize(long csFileSize) {
		this.csFileSize = csFileSize;
	}

	public Timestamp getCsUploadTime() {
		return csUploadTime;
	}

	public void setCsUploadTime(Timestamp csUploadTime) {
		this.csUploadTime = csUploadTime;
	}

	public String getCsFileName() {
		return csFileName;
	}

	public void setCsFileName(String csFileName) {
		this.csFileName = csFileName;
	}

	@Override
	public String toString() {
		return "CsBoard [csNum=" + csNum + ", csTitle=" + csTitle + ", Contents=" + Contents + ", csDate=" + csDate
				+ ", csPublic=" + csPublic + ", csId=" + csId + ", Pwd=" + Pwd + ", csFilePath=" + csFilePath
				+ ", csFileSize=" + csFileSize + ", csUploadTime=" + csUploadTime + ", csFileName=" + csFileName + "]";
	}

}
