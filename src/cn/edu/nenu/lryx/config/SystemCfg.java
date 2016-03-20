package cn.edu.nenu.lryx.config;

import java.util.HashMap;
import java.util.Map;

/**   
* @Title: SystemCfg.java 
* @Package cn.edu.nenu.lryx.config 
* @Description: TODO
* @author py pengyang813@foxmail.com
* @date 2016年3月6日 下午3:48:24 
* @version V1.0   
*/
public class SystemCfg {
	public static Map<Integer,String> _ArticleCategoryMap_ = new HashMap<>();
	public static Map<Integer,String> _2ndPageCategoryMap_ = new HashMap<>();
	static{
		//新闻，公告，学术, 专题
		_ArticleCategoryMap_.put(Integer.valueOf(1), "新闻");
		_ArticleCategoryMap_.put(Integer.valueOf(2), "公告");
		_ArticleCategoryMap_.put(Integer.valueOf(3), "学术");
		_ArticleCategoryMap_.put(Integer.valueOf(4), "专题");
		
		//项目介绍，招生简章，报名流程，生活信息，学生组织，学员活动，课程安排，考试安排
		_2ndPageCategoryMap_.put(Integer.valueOf(5), "学校介绍");
		_2ndPageCategoryMap_.put(Integer.valueOf(5), "组织介绍");
		
		_2ndPageCategoryMap_.put(Integer.valueOf(6), "招生简章");
		_2ndPageCategoryMap_.put(Integer.valueOf(7), "报名流程");
		_2ndPageCategoryMap_.put(Integer.valueOf(8), "生活信息");
		_2ndPageCategoryMap_.put(Integer.valueOf(9), "学生组织");
		_2ndPageCategoryMap_.put(Integer.valueOf(10), "学员活动");
		_2ndPageCategoryMap_.put(Integer.valueOf(11), "课程安排");
		_2ndPageCategoryMap_.put(Integer.valueOf(12), "考试安排");
	}
}
