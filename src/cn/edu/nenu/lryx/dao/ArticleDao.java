package cn.edu.nenu.lryx.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import cn.edu.nenu.lryx.model.Article;
import cn.edu.nenu.lryx.model.ArticleCategory;

/**   
 * @Title: ArticleDao.java 
 * @Package cn.edu.nenu.lryx.dao 
 * @Description: TODO
 * @author py pengyang813@foxmail.com
 * @date 2016年3月6日 下午3:23:54 
 * @version V1.0   
 */
@Component
public class ArticleDao {
	@Resource
	private SessionFactory sf;

	public ArticleDao(SessionFactory sf) {
		super();
		this.sf = sf;
	}

	public ArticleDao() {
		super();
	}
	private Session getCurrentSession() {
		return sf.getCurrentSession();
	}

	public boolean hasCategory(String cname){
		Object obj = getCurrentSession().createCriteria(ArticleCategory.class).add(Restrictions.eq("name", cname)).uniqueResult();
		if (obj == null) return false;
		else return true;
	}

	public boolean addCategory(ArticleCategory category){
		//		System.out.println(category.getId()+"  "+category.getName());
		getCurrentSession().save(category);
		return true;
	}

	public List<ArticleCategory> findCategory(boolean is2ndPage){
		return getCurrentSession()
				.createCriteria(ArticleCategory.class)
				.add(Restrictions.eq("is2ndPage", is2ndPage)).list();
	}

	public ArticleCategory findCategoryById(int id){
		return (ArticleCategory) getCurrentSession()
				.createCriteria(ArticleCategory.class)
				.add(Restrictions.idEq(id)).uniqueResult();
	}
	public ArticleCategory findCategoryByName(String cname){
		return (ArticleCategory)getCurrentSession()
				.createCriteria(ArticleCategory.class)
				.add(Restrictions.eq("name", cname))
				.uniqueResult();
	}
	public int addArticle(Article article){
		return (int) getCurrentSession().save(article);
	}
	public void modifArticle(Article article){
		getCurrentSession().update(article);
	}
	public List<Article> findArticleByCategoryName(String cname){
		Criteria criteria = getCurrentSession().createCriteria(Article.class);
		criteria.createAlias("category", "c"); 
		return criteria.add(Restrictions.eq("c.name",cname))
				.addOrder(Order.desc("onTop"))
				.addOrder(Order.desc("lastModifyTime"))
				.list();
	}

	public Article findArticleById(int id){
		return (Article) getCurrentSession()
				.createCriteria(Article.class)
				.add(Restrictions.idEq(id)).uniqueResult();
	}
	public List<Article>findArticlesByCategoryNameAndPaging(String cname, int pageNo, int step){
		Criteria criteria = getCurrentSession().createCriteria(Article.class);
		criteria.createAlias("category", "c"); 
		List<Article>list = 
				criteria.add(Restrictions.eq("c.name",cname))
				.addOrder(Order.desc("onTop"))
				.addOrder(Order.desc("lastModifyTime"))
				.setMaxResults(step)
				.setFirstResult((pageNo -  1) * step)
				.list();
		return list;
	}
	public int findArticleSizeByCategoryName(String cname){
		Criteria criteria = getCurrentSession().createCriteria(Article.class);
		criteria.createAlias("category", "c"); 
		return criteria.add(Restrictions.eq("c.name",cname)).list().size();
	}
	public List<Article> findArticleOnIndex(){
		return getCurrentSession()
					.createCriteria(Article.class)
					.add(Restrictions.eq("onIndex", true))
					.list();
	}
	public void deleteArticle(Article article){
		getCurrentSession().delete(article);
	}
}