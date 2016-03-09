package cn.edu.nenu.lryx.test.service;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;
import java.util.Map.Entry;

import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.edu.nenu.lryx.config.SystemCfg;
import cn.edu.nenu.lryx.model.Article;
import cn.edu.nenu.lryx.model.ArticleCategory;
import cn.edu.nenu.lryx.model.User;
import cn.edu.nenu.lryx.util.StringUtil;
import cn.nenu.edu.lryx.service.ArticleService;
import cn.nenu.edu.lryx.service.UserService;

/**   
 * @Title: ArticleServiceTest.java 
 * @Package cn.edu.nenu.lryx.test.service 
 * @Description: TODO
 * @author py pengyang813@foxmail.com
 * @date 2016年3月6日 下午3:45:03 
 * @version V1.0   
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/cn/edu/nenu/lryx/resource/applicationContext.xml"})
public class ArticleServiceTest {
	@Autowired
	private ArticleService as;
	@Autowired
	private UserService us;

	static User u = new User();
	static{
		u.setUsername("py");
		u.setPassword("1");
		u.setName("彭杨");

	}
		@Test
		public void addUserTest(){
	
	
			assertEquals(true,us.addUser(u));
			assertEquals(false,us.addUser(u));
	
			User nu = us.getUserById(u.getId());
			assertEquals(u.getName(),nu.getName());
			assertEquals(u.getUsername(), nu.getUsername());
			assertEquals(nu.getPassword(),nu.getPassword());
			nu = us.getUserByUsername(u.getUsername());
			assertEquals(u.getName(),nu.getName());
			assertEquals(u.getUsername(), nu.getUsername());
			assertEquals(nu.getPassword(),nu.getPassword());
			nu = us.getUserByUsernameAndPassword(u.getUsername(), u.getPassword());
			assertEquals(u.getName(),nu.getName());
			assertEquals(u.getUsername(), nu.getUsername());
			assertEquals(nu.getPassword(),nu.getPassword());
		}
		@Test
		public void addCategoryTest() {
			//测试添加信息文章的类别
			for(Entry<Integer,String> e : SystemCfg._InformationArticleCategoryMap_.entrySet()){
				ArticleCategory c = new ArticleCategory();
				c.setId(e.getKey());
				c.setName(e.getValue());
				c.setIs2ndPage(false);
				assertEquals(true, as.addCategory(c));
			}
			List<ArticleCategory> cas = as.getAllInformationCategory();
			assertEquals(SystemCfg._InformationArticleCategoryMap_.size(), cas.size());
			for(ArticleCategory ca  : cas){
				assertEquals(SystemCfg._InformationArticleCategoryMap_.get(ca.getId()), ca.getName());
			}
			//测试添加2级页面的类别
			for(Entry<Integer,String> e : SystemCfg._2ndPageArticleCategoryMap_.entrySet()){
				ArticleCategory c = new ArticleCategory();
				c.setId(e.getKey());
				c.setName(e.getValue());
				c.setIs2ndPage(true);
				assertEquals(true, as.addCategory(c));
			}
			cas = as.getAll2ndPageCategory();
			assertEquals(SystemCfg._2ndPageArticleCategoryMap_.size(), cas.size());
			for(ArticleCategory ca  : cas){
				assertEquals(SystemCfg._2ndPageArticleCategoryMap_.get(ca.getId()), ca.getName());
			}
			//测试重复添加信息文章的类别
			for(Entry<Integer,String> e : SystemCfg._InformationArticleCategoryMap_.entrySet()){
				ArticleCategory c = new ArticleCategory();
				c.setName(e.getValue());
				c.setIs2ndPage(false);
				assertEquals(false, as.addCategory(c));
			}
			//测试重复添加2级页面的类别
			for(Entry<Integer,String> e : SystemCfg._2ndPageArticleCategoryMap_.entrySet()){
				ArticleCategory c = new ArticleCategory();
				c.setName(e.getValue());
				c.setIs2ndPage(true);
				assertEquals(false, as.addCategory(c));
			}	
		}
		@Test
		public void getCategorysTest(){
			List<ArticleCategory> cas = as.getAllInformationCategory();
			assertEquals(SystemCfg._InformationArticleCategoryMap_.size(), cas.size());
			for(ArticleCategory ca  : cas){
				assertEquals(SystemCfg._InformationArticleCategoryMap_.get(ca.getId()), ca.getName());
			}
	
			cas = as.getAll2ndPageCategory();
			assertEquals(SystemCfg._2ndPageArticleCategoryMap_.size(), cas.size());
			for(ArticleCategory ca  : cas){
				assertEquals(SystemCfg._2ndPageArticleCategoryMap_.get(ca.getId()), ca.getName());
			}
		}
	
		@Test
		public void addInfomationTest(){
			List<ArticleCategory> cas = as.getAllInformationCategory();
			assertEquals(SystemCfg._InformationArticleCategoryMap_.size(), cas.size());
			for(ArticleCategory ca  : cas){
				assertEquals(SystemCfg._InformationArticleCategoryMap_.get(ca.getId()), ca.getName());
			}
			for(int t = 0 ; t < 5 ; ++ t){
				int i = 0 ;
				for(ArticleCategory category : cas){
	Article ar = new Article();
				ArticleCategory ca_ = new ArticleCategory();
				ca_.setId(category.getId());
				ar.setCategory(ca_);
				User u_ = new User();
				u_.setId(u.getId());
				ar.setAuthor(u_);
				User u__ = new User();
				u__.setId(u.getId());
				ar.setLastModifyUser(u__);
				ar.setContent("内容"+category.getName());
				ar.setHighlight(i % 2 == 0);
				ar.setOnTop((t + 1) % 2 == 0);
				ar.setTitle("标题"+category.getName());
				as.addArticle(ar, false);
					
					Article ar_ = as.getArticleById(ar.getId());
					assertEquals(ca_.getId(), ar_.getCategory().getId());
					assertEquals(u_.getId(), ar_.getAuthor().getId());
					assertEquals(u__.getId(), ar_.getLastModifyUser().getId());
					assertEquals(ar.getContent(), ar_.getContent());
					i ++;
				}
			}
		
		}
	@Test
	public void add2ndPageTest(){
		List<ArticleCategory> cas = as.getAll2ndPageCategory();
		assertEquals(SystemCfg._2ndPageArticleCategoryMap_.size(), cas.size());
		for(ArticleCategory category : cas){
			Article ar = new Article();
				ArticleCategory ca_ = new ArticleCategory();
				ca_.setId(category.getId());
				ar.setCategory(ca_);
				ar.setContent("内容"+category.getName());
				as.addArticle(ar ,  true);
		}
		for(ArticleCategory category : cas){
			List<Article> articles = as.getAllArticleByCategoryName(category.getName());
			assertEquals(1, articles.size());
			assertEquals(category.getId(),articles.get(0).getCategory().getId());
		}
	}
	
}
