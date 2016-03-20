package cn.nenu.edu.lryx.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.edu.nenu.lryx.dao.ArticleDao;
import cn.edu.nenu.lryx.dao.UserDao;
import cn.edu.nenu.lryx.dto.ArticleListItemDto;
import cn.edu.nenu.lryx.dto.ArticleListWithPageInfoDto;
import cn.edu.nenu.lryx.model.Article;
import cn.edu.nenu.lryx.model.ArticleCategory;
import cn.edu.nenu.lryx.model.User;
import cn.edu.nenu.lryx.util.StringUtil;

/**   
* @Title: ArticleService.java 
* @Package cn.nenu.edu.lryx.service 
* @Description: TODO
* @author py pengyang813@foxmail.com
* @date 2016年3月6日 下午3:23:40 
* @version V1.0   
*/
@Component
public class ArticleService {
	@Resource
	private ArticleDao  ad;
	@Resource
	private UserDao ud;
	
	public UserDao getUd() {
		return ud;
	}

	public void setUd(UserDao ud) {
		this.ud = ud;
	}

	public ArticleService() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArticleDao getAd() {
		return ad;
	}

	public void setAd(ArticleDao ad) {
		this.ad = ad;
	}
	/**
	 * 
	* @Title: getAllCategoryHasList 
	* @Description: 得到所有的消息类别
	* @return List<ArticleCategory> 
	* @throws
	 */
	public List<ArticleCategory> getAllArticleCategory(){
		return ad.findCategory(false);
	}

	/**
	 * 
	* @Title: getAll2ndPageCategory 
	* @Description: 得到所有二级页面的类别
	* @return List<ArticleCategory> 
	* @throws
	 */
	public List<ArticleCategory> getAll2ndPageCategory(){
		return ad.findCategory(true);
	}
	/**
	 * 
	* @Title: getAllArticleByCategoryName 
	* @Description: 得到指定名称的类型下所有的文章
	* @param cname
	* @return List<Article> 
	* @throws
	 */
	public List<Article> getAllArticleByCategoryName(String cname){
		return ad.findArticleByCategoryName(cname);
		
	}
	public ArticleListWithPageInfoDto getAllArticleByCategoryNameAndPageNo(String cname, int pageNo,int step){
		int size = ad.findArticleSizeByCategoryName(cname);
		ArticleListWithPageInfoDto dto = new ArticleListWithPageInfoDto();
		dto.setArticleCn(size);
		dto.setPageNo(pageNo);
		dto.setStep(step);
		if(pageNo > 0 && step > 0 && size > (pageNo - 1) * step){
			List<Article> list = ad.findArticlesByCategoryNameAndPaging(cname, pageNo, step);
			List<ArticleListItemDto> dtol = new ArrayList<>();
			for (Article  ar :list ){
				dtol.add(ArticleListItemDto.bulid(ar));
			}
			if(list.size() != 0) dto.setArticles(dtol);
		}
		return dto;
	}
	/**
	 * 
	* @Title: getArticleById 
	* @Description: 得到指定id的文章
	* @param id
	* @return Article 
	* @throws
	 */
	public Article getArticleById(int id){
		return ad.findArticleById(id);
	}
	/**
	 * 
	* @Title: addArticle 
	* @Description: 添加教师
	* @param article
	* @returnint
	* @throws
	 */
	public int addArticle(Article article , boolean is2ndPage){
		if(article == null) return -1;
		String nowTime = StringUtil.getCurrentTimeStr();
		article.setPublishTime(nowTime);
		article.setLastModifyTime(nowTime);
		ArticleCategory  category = ad.findCategoryById(article.getCategory().getId());
		article.setCategory(category);
		if(!is2ndPage){
			User u = ud.findUserById(article.getAuthor().getId());
			article.setAuthor(u);
			u = ud.findUserById(article.getLastModifyUser().getId());
			article.setLastModifyUser(u);
		}
		return ad.addArticle(article);
	}
	public ArticleCategory findArticleCategoryName(String cname){
		return ad.findCategoryByName(cname);
	}
	
	public boolean deleteArticleById(int id){
		Article article = ad.findArticleById(id);
		if(article == null) return false;
		ad.deleteArticle(article);
		return true;
	}
	
	public boolean modifyArticle(Article article){
		System.out.println(article);
		if(article == null )  return false;
		Article oarticle = ad.findArticleById(article.getId());
		if(oarticle == null) return false;
		if(oarticle.getCategory().isIs2ndPage() ){
			if(oarticle.getCategory().getId() != article.getCategory().getId())
				return false;
		}
		
		ArticleCategory nCategory = ad.findCategoryById(article.getCategory().getId());
		if(nCategory == null) return false;
		
		article.setCategory(nCategory);
		
		User nuser = ud.findUserById(article.getLastModifyUser().getId());
		article.setLastModifyUser(nuser);
		article.setLastModifyTime(StringUtil.getCurrentTimeStr());
		
		oarticle.setContent(article.getContent());
		oarticle.setCategory(article.getCategory());
		oarticle.setHighlight(article.isHighlight());
		oarticle.setImgUrl(article.getImgUrl());
		oarticle.setLastModifyTime(article.getLastModifyTime());
		oarticle.setLastModifyUser(article.getLastModifyUser());
		oarticle.setOnIndex(article.isOnIndex());
		oarticle.setOnTop(article.isOnTop());
		oarticle.setTitle(article.getTitle());
		oarticle.setVisitNum(article.getVisitNum());
		
		ad.modifArticle(oarticle);
		return true;
	}
	
	public boolean addCategory(ArticleCategory category){
		if(category == null) return false;
		if(ad.hasCategory(category.getName())) return false;
		ad.addCategory(category);
		return true;
	}
	public List<ArticleListItemDto> getOnIndexArticles(){
		List<ArticleListItemDto> dtol = new ArrayList<>();
		for (Article ar :ad.findArticleOnIndex()){
				dtol.add(ArticleListItemDto.bulid(ar));
		}
		return dtol;
	}
}
