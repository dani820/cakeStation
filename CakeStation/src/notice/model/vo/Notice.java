package notice.model.vo;

import java.sql.Date;
import java.sql.Timestamp;

public class Notice {

	private int Notice_No;
	private String Notice_Title;
	private String Notice_Contents;
	private Date Notice_Date;
	private String Member_Id;
	private String Notice_FilePath;
	private long Notice_FileSize;
	private Timestamp Notice_uploadTime;
	private String Notice_FileName;
	
	public Notice () {}

	public int getNotice_No() {
		return Notice_No;
	}

	public void setNotice_No(int notice_No) {
		Notice_No = notice_No;
	}

	public String getNotice_Title() {
		return Notice_Title;
	}

	public void setNotice_Title(String notice_Title) {
		Notice_Title = notice_Title;
	}

	public String getNotice_Contents() {
		return Notice_Contents;
	}

	public void setNotice_Contents(String notice_Contents) {
		Notice_Contents = notice_Contents;
	}

	public Date getNotice_Date() {
		return Notice_Date;
	}

	public void setNotice_Date(Date notice_Date) {
		Notice_Date = notice_Date;
	}

	public String getMember_Id() {
		return Member_Id;
	}

	public void setMember_Id(String member_Id) {
		Member_Id = member_Id;
	}

	public String getNotice_FilePath() {
		return Notice_FilePath;
	}

	public void setNotice_FilePath(String notice_FilePath) {
		Notice_FilePath = notice_FilePath;
	}

	public long getNotice_FileSize() {
		return Notice_FileSize;
	}

	public void setNotice_FileSize(long notice_FileSize) {
		Notice_FileSize = notice_FileSize;
	}

	public Timestamp getNotice_uploadTime() {
		return Notice_uploadTime;
	}

	public void setNotice_uploadTime(Timestamp notice_uploadTime) {
		Notice_uploadTime = notice_uploadTime;
	}

	public String getNotice_FileName() {
		return Notice_FileName;
	}

	public void setNotice_FileName(String notice_FileName) {
		Notice_FileName = notice_FileName;
	}

	@Override
	public String toString() {
		return "Notice [Notice_No=" + Notice_No + ", Notice_Title=" + Notice_Title + ", Notice_Contents="
				+ Notice_Contents + ", Notice_Date=" + Notice_Date + ", Member_Id=" + Member_Id + ", Notice_FilePath="
				+ Notice_FilePath + ", Notice_FileSize=" + Notice_FileSize + ", Notice_uploadTime=" + Notice_uploadTime
				+ ", Notice_FileName=" + Notice_FileName + "]";
	} 
	
	
	
}
