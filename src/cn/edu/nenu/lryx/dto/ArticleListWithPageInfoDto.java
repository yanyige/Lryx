package cn.edu.nenu.lryx.dto;

import java.util.List;

import cn.edu.nenu.lryx.model.Article;

/**   
* @Title: ArticleListWithPageInfoDto.java 
* @Package cn.edu.nenu.lryx.dto 
* @Description: TODO
* @author py pengyang813@foxmail.com
* @date 2016年3月16日 下午10:05:15 
* @version V1.0   
*/
public class ArticleListWithPageInfoDto {
	private List<ArticleListItemDto > articles;
	private int articleCn;
	private int pageNo;
	private int step;
	
	

	public ArticleListWithPageInfoDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public List<ArticleListItemDto> getArticles() {
		return articles;
	}


	public void setArticles(List<ArticleListItemDto> articles) {
		this.articles = articles;
	}


	public int getArticleCn() {
		return articleCn;
	}
	public void setArticleCn(int articleCn) {
		this.articleCn = articleCn;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
}
