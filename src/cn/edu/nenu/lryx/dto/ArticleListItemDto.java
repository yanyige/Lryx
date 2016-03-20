package cn.edu.nenu.lryx.dto;

import cn.edu.nenu.lryx.model.Article;
import cn.edu.nenu.lryx.model.ArticleCategory;
import cn.edu.nenu.lryx.model.User;

/**   
* @Title: ArticleListDto.java 
* @Package cn.edu.nenu.lryx.dto 
* @Description: TODO
* @author py pengyang813@foxmail.com
* @date 2016年3月19日 上午9:44:41 
* @version V1.0   
*/
public class ArticleListItemDto {
	private int id;
	//标题
	private String title;
	//最后修改时间
	private String lastModifyTime;
	//访问人数
	private int visitNum;
	//是否置顶
	private boolean isOnTop;
	//是否突出显示
	private boolean isHighlight;
	//文章类别
	private ArticleCategory category;
	private String imgUrl;
	private boolean isOnIndex;
	
	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public static ArticleListItemDto bulid(Article ar){
		ArticleListItemDto dto = new ArticleListItemDto();
		dto.setId(ar.getId());
		dto.setTitle(ar.getTitle());
		dto.setVisitNum(dto.visitNum);
		dto.setOnTop(ar.isOnTop());
		dto.setHighlight(ar.isHighlight());
		dto.setCategory(ar.getCategory());
		dto.setOnIndex(ar.isOnTop());
		dto.setImgUrl(ar.getImgUrl());
		dto.setLastModifyTime(ar.getLastModifyTime());
		return dto;
	}
	
	public boolean isOnIndex() {
		return isOnIndex;
	}

	public void setOnIndex(boolean isOnIndex) {
		this.isOnIndex = isOnIndex;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLastModifyTime() {
		return lastModifyTime;
	}
	public void setLastModifyTime(String lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}
	public int getVisitNum() {
		return visitNum;
	}
	public void setVisitNum(int visitNum) {
		this.visitNum = visitNum;
	}
	public boolean isOnTop() {
		return isOnTop;
	}
	public void setOnTop(boolean isOnTop) {
		this.isOnTop = isOnTop;
	}
	public boolean isHighlight() {
		return isHighlight;
	}
	public void setHighlight(boolean isHighlight) {
		this.isHighlight = isHighlight;
	}
	public ArticleCategory getCategory() {
		return category;
	}
	public void setCategory(ArticleCategory category) {
		this.category = category;
	}
	
}
