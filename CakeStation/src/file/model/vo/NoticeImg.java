package file.model.vo;

import java.sql.Timestamp;

public class NoticeImg {
	// 멤버 변수
	private int noticeFileNo;
	private String noticeFilePath;
	private String noticeFileSize;
	private String noticeFileUser;
	private String noticeFileName;
	private Timestamp noticeUploadTime;
	private int noticeNo;

	// 생성자
	public NoticeImg() {}

	// getter/setter
	public int getNoticeFileNo() {
		return noticeFileNo;
	}

	public void setNoticeFileNo(int noticeFileNo) {
		this.noticeFileNo = noticeFileNo;
	}

	public String getNoticeFilePath() {
		return noticeFilePath;
	}

	public void setNoticeFilePath(String noticeFilePath) {
		this.noticeFilePath = noticeFilePath;
	}

	public String getNoticeFileSize() {
		return noticeFileSize;
	}

	public void setNoticeFileSize(String noticeFileSize) {
		this.noticeFileSize = noticeFileSize;
	}

	public String getNoticeFileUser() {
		return noticeFileUser;
	}

	public void setNoticeFileUser(String noticeFileUser) {
		this.noticeFileUser = noticeFileUser;
	}

	public String getNoticeFileName() {
		return noticeFileName;
	}

	public void setNoticeFileName(String noticeFileName) {
		this.noticeFileName = noticeFileName;
	}

	public Timestamp getNoticeUploadTime() {
		return noticeUploadTime;
	}

	public void setNoticeUploadTime(Timestamp noticeUploadTime) {
		this.noticeUploadTime = noticeUploadTime;
	}

	public int getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}

	// toString
	@Override
	public String toString() {
		return "NoticePhoto [noticeFileNo=" + noticeFileNo + ", noticeFilePath=" + noticeFilePath + ", noticeFileSize="
				+ noticeFileSize + ", noticeFileUser=" + noticeFileUser + ", noticeFileName=" + noticeFileName
				+ ", noticeUploadTime=" + noticeUploadTime + ", noticeNo=" + noticeNo + "]";
	}

}
