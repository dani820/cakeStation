package file.model.vo;

import java.sql.Timestamp;

public class CRNImg {
	private int crnFileNo;
	private String crnFilePath;
	private long crnFileSize;
	private String crnFileUser;
	private String crnFileName;
	private Timestamp crnUploadTime;
	
	public CRNImg() {}

	public int getCrnFileNo() {
		return crnFileNo;
	}

	public void setCrnFileNo(int crnFileNo) {
		this.crnFileNo = crnFileNo;
	}

	public String getCrnFilePath() {
		return crnFilePath;
	}

	public void setCrnFilePath(String crnFilePath) {
		this.crnFilePath = crnFilePath;
	}

	public long getCrnFileSize() {
		return crnFileSize;
	}

	public void setCrnFileSize(long crnFileSize) {
		this.crnFileSize = crnFileSize;
	}

	public String getCrnFileUser() {
		return crnFileUser;
	}

	public void setCrnFileUser(String crnFileUser) {
		this.crnFileUser = crnFileUser;
	}

	public String getCrnFileName() {
		return crnFileName;
	}

	public void setCrnFileName(String crnFileName) {
		this.crnFileName = crnFileName;
	}

	public Timestamp getCrnUploadTime() {
		return crnUploadTime;
	}

	public void setCrnUploadTime(Timestamp crnUploadTime) {
		this.crnUploadTime = crnUploadTime;
	}

	@Override
	public String toString() {
		return "CRNImg [crnFileNo=" + crnFileNo + ", crnFilePath=" + crnFilePath + ", crnFileSize=" + crnFileSize
				+ ", crnFileUser=" + crnFileUser + ", crnFileName=" + crnFileName + ", crnUploadTime=" + crnUploadTime
				+ "]";
	}
	
}
