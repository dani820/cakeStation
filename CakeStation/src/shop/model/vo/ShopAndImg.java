package shop.model.vo;

public class ShopAndImg {
	//SHOP_NAME, INTRODUCE, SHOP_AVG_SCORE, CRN_FILEPATH
	private String shopId;
	private String shopName;
	private int shopAvgScore;
	private String introduced;
	private String shopWebsite; //업체 웹페이지
	private String shopFilePath; //SHOP_FILEPATH
	private String chatUrl; //오픈채팅url
	private String shopLAdd;
	private String shopMAdd;
	private String shopSAdd;
	private String addrAll;
	private String addrMap;
	private int openingStart;
	private int openingEnd;
	private String closed;
	
	public ShopAndImg() {}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public int getShopAvgScore() {
		return shopAvgScore;
	}

	public void setShopAvgScore(int shopAvgScore) {
		this.shopAvgScore = shopAvgScore;
	}

	public String getIntroduced() {
		return introduced;
	}

	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}

	public String getShopWebsite() {
		return shopWebsite;
	}

	public void setShopWebsite(String shopWebsite) {
		this.shopWebsite = shopWebsite;
	}

	public String getShopFilePath() {
		return shopFilePath;
	}

	public void setShopFilePath(String shopFilePath) {
		this.shopFilePath = shopFilePath;
	}

	public String getChatUrl() {
		return chatUrl;
	}

	public void setChatUrl(String chatUrl) {
		this.chatUrl = chatUrl;
	}

	public String getShopLAdd() {
		return shopLAdd;
	}

	public void setShopLAdd(String shopLAdd) {
		this.shopLAdd = shopLAdd;
	}

	public String getShopMAdd() {
		return shopMAdd;
	}

	public void setShopMAdd(String shopMAdd) {
		this.shopMAdd = shopMAdd;
	}

	public String getShopSAdd() {
		return shopSAdd;
	}

	public void setShopSAdd(String shopSAdd) {
		this.shopSAdd = shopSAdd;
	}

	public String getAddrAll() {
		return addrAll;
	}

	public void setAddrAll(String addrAll) {
		this.addrAll = addrAll;
	}

	public String getAddrMap() {
		return addrMap;
	}

	public void setAddrMap(String addrMap) {
		this.addrMap = addrMap;
	}

	public int getOpeningStart() {
		return openingStart;
	}

	public void setOpeningStart(int openingStart) {
		this.openingStart = openingStart;
	}

	public int getOpeningEnd() {
		return openingEnd;
	}

	public void setOpeningEnd(int openingEnd) {
		this.openingEnd = openingEnd;
	}

	public String getClosed() {
		return closed;
	}

	public void setClosed(String closed) {
		this.closed = closed;
	}

	@Override
	public String toString() {
		return "ShopAndImg [shopId=" + shopId + ", shopName=" + shopName + ", shopAvgScore=" + shopAvgScore
				+ ", introduced=" + introduced + ", shopWebsite=" + shopWebsite + ", shopFilePath=" + shopFilePath
				+ ", chatUrl=" + chatUrl + ", shopLAdd=" + shopLAdd + ", shopMAdd=" + shopMAdd + ", shopSAdd="
				+ shopSAdd + ", addrAll=" + addrAll + ", addrMap=" + addrMap + ", openingStart=" + openingStart
				+ ", openingEnd=" + openingEnd + ", closed=" + closed + "]";
	}

}