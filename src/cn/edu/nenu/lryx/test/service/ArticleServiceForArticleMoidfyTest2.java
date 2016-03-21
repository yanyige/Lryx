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
public class ArticleServiceForArticleMoidfyTest2 {
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
	public void modify2ndPageTest(){
		//修改二级页面的内容
		List<ArticleCategory> cas = as.getAll2ndPageCategory();
		for(ArticleCategory category : cas){
			List<Article> list = as.getAllArticleByCategoryName(category.getName());
			assertNotNull(list);
			assertEquals(1, list.size());
			Article ar = list.get(0);
			assertEquals(category.getId(), ar.getCategory().getId());
			ar.setContent("~~~~~~~~~~~");
			assertEquals(true, as.modifyArticle(ar));
		}

		//没有id的页面
		Article ar = new Article();
		assertEquals(false, as.modifyArticle(ar));
	}
}
