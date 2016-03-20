package cn.edu.nenu.lryx.test.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.edu.nenu.lryx.dto.ArticleListItemDto;
import cn.edu.nenu.lryx.dto.ArticleListWithPageInfoDto;
import cn.edu.nenu.lryx.model.Article;
import cn.edu.nenu.lryx.model.ArticleCategory;
import cn.edu.nenu.lryx.util.StringUtil;
import cn.nenu.edu.lryx.service.ArticleService;
import cn.nenu.edu.lryx.service.UserService;

/**   
* @Title: ShowArticleTest.java 
* @Package cn.edu.nenu.lryx.test.service 
* @Description: TODO
* @author py pengyang813@foxmail.com
* @date 2016年3月7日 下午9:41:49 
* @version V1.0   
*/

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/cn/edu/nenu/lryx/resource/applicationContext.xml"})
public class ShowArticleTest {
		@Autowired
		private ArticleService as;
		@Autowired
		private UserService us;

	@Test
	public void showAllArticleTest(){
		List<ArticleCategory> categoriess = as.getAllArticleCategory();
		testSa(categoriess);
		categoriess = as.getAll2ndPageCategory();
		testSa(categoriess);
	}
	@Test
	public void show2ndPageByCategory(){
		List<ArticleCategory> categoriess = as.getAll2ndPageCategory();
		for(ArticleCategory ca : categoriess){
			List<Article> list = as.getAllArticleByCategoryName(ca.getName());
			assertEquals(1,list.size());
		}
	}
	@Test
	public void showArticleByPageNo(){
		List<ArticleCategory> infomationCa = as.getAllArticleCategory();
		for(ArticleCategory ca : infomationCa){
			//第一页
			testArPn(ca, 1, 2);
			//正常一页
			testArPn(ca, 2, 2);
			//最后一页
			testArPn(ca, 3, 2);
			//页数为负
			testArPn(ca, -1, 5);
			//页数过大不存在
			testArPn(ca, 100, 5);
			//页数为0
			testArPn(ca, 0, 5);
			//每页文档数为负数
			testArPn(ca, 1, -5);
			//每页文档数为0
			testArPn(ca, 1, 0);
		}
	}
	public void testIndexArticle(){
		List<ArticleListItemDto> articles = as.getOnIndexArticles();
		assertNotNull(articles);
		for(ArticleListItemDto ar : articles){
			assertEquals(true, ar.isOnTop());
		}
	}
	private void testArPn(ArticleCategory ca , int pageNo, int step){
		ArticleListWithPageInfoDto dto =  as.getAllArticleByCategoryNameAndPageNo(ca.getName(), pageNo , step );
		assertNotNull(dto);
		int articleCn = dto.getArticleCn() ;
		if(articleCn > 0 && pageNo > 0 &&  step > 0){
//			assertNotNull(dto.getArticles());
			int leftArticleCn = articleCn  - (pageNo - 1 )* step;
			if( leftArticleCn >= step){
				int articleSize = dto.getArticles().size();
				assertEquals(articleSize, step);
			}else if(leftArticleCn > 0){
				int articleSize = dto.getArticles().size();
				assertEquals(articleSize, leftArticleCn);
			}else 
				assertNull(dto.getArticles());
		}else{
			assertNull(dto.getArticles());
		}
	}
	private void testSa(List<ArticleCategory> categoriess){
		for(ArticleCategory ca : categoriess){
			List<Article> articles  = as.getAllArticleByCategoryName(ca.getName());
			boolean allOnTop = true;
			String lastArTime = StringUtil.getCurrentTimeStr();
			for(Article ar : articles){
				assertEquals(ca.getId(), ar.getCategory().getId());
				int isbig = lastArTime.compareTo(ar.getLastModifyTime());
				if(allOnTop){
					if(!ar.isOnTop()){
						allOnTop =false;
					}else{
						assertEquals(true, isbig >= 0);
					}
				}else{
					assertEquals(false, ar.isOnTop());
					assertEquals(true, isbig >= 0);
				}
				lastArTime = ar.getLastModifyTime();
			}
		}
	}
	

}
