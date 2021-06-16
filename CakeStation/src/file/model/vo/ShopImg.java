package file.model.vo;

import java.sql.Timestamp;

public class ShopImg {
	private int shopFileNo;
	private String shopFilePath;
	private long shopFileSize;
	private String shopFileUser;
	private String shopFileName;
	private Timestamp shopUploadTime;
	
	public ShopImg() {}

	public int getShopFileNo() {
		return shopFileNo;
	}

	public void setShopFileNo(int shopFileNo) {
		this.shopFileNo = shopFileNo;
	}

	public String getShopFilePath() {
		return shopFilePath;
	}

	public void setShopFilePath(String shopFilePath) {
		this.shopFilePath = shopFilePath;
	}

	public long getShopFileSize() {
		return shopFileSize;
	}

	public void setShopFileSize(long shopFileSize) {
		this.shopFileSize = shopFileSize;
	}

	public String getShopFileUser() {
		return shopFileUser;
	}

	public void setShopFileUser(String shopFileUser) {
		this.shopFileUser = shopFileUser;
	}

	public String getShopFileName() {
		return shopFileName;
	}

	public void setShopFileName(String shopFileName) {
		this.shopFileName = shopFileName;
	}

	public Timestamp getShopUploadTime() {
		return shopUploadTime;
	}

	public void setShopUploadTime(Timestamp shopUploadTime) {
		this.shopUploadTime = shopUploadTime;
	}

	@Override
	public String toString() {
		return "ShopImg [shopFileNo=" + shopFileNo + ", shopFilePath=" + shopFilePath + ", shopFileSize=" + shopFileSize
				+ ", shopFileUser=" + shopFileUser + ", shopFileName=" + shopFileName + ", shopUploadTime="
				+ shopUploadTime + "]";
	}
	
}
