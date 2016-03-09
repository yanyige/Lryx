package cn.edu.nenu.lryx.test.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
	public void showArticleTest(){
		List<ArticleCategory> categoriess = as.getAllInformationCategory();
		testSa(categoriess);
		categoriess = as.getAll2ndPageCategory();
		testSa(categoriess);
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
