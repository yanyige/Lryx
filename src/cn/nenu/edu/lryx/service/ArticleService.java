package cn.nenu.edu.lryx.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.edu.nenu.lryx.dao.ArticleDao;
import cn.edu.nenu.lryx.dao.UserDao;
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
	public List<ArticleCategory> getAllInformationCategory(){
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
		if(!is2ndPage){
			User u = ud.findUserById(article.getAuthor().getId());
			article.setAuthor(u);
			u = ud.findUserById(article.getLastModifyUser().getId());
			article.setLastModifyUser(u);
		}
		return ad.addArticle(article);
	}
	
	public boolean deleteArticleById(int id){
		return false;
	}
	
	public boolean modifyArticle(Article article){
		return false;
	}
	
	public boolean addCategory(ArticleCategory category){
		if(category == null) return false;
		if(ad.hasCategory(category.getName())) return false;
		ad.addCategory(category);
		return true;
	}

}
