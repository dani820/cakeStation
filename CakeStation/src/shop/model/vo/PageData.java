package shop.model.vo;

import java.util.ArrayList;

public class PageData {
	private ArrayList<ShopAndImg> shopAndImg;
	private String pageNavi;
	
	public PageData () {}

	public ArrayList<ShopAndImg> getShopAndImg() {
		return shopAndImg;
	}

	public void setShopAndImg(ArrayList<ShopAndImg> shopAndImg) {
		this.shopAndImg = shopAndImg;
	}

	public String getPageNavi() {
		return pageNavi;
	}

	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}

	@Override
	public String toString() {
		return "PageData [shopAndImg=" + shopAndImg + ", pageNavi=" + pageNavi + "]";
	}
	
}
