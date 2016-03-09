package cn.edu.nenu.lryx.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**   
* @Title: ArticleCategory.java 
* @Package cn.edu.nenu.lryx.model 
* @Description: 文章类别
* 新闻，公告，学术，专题（多篇）；学校简介，项目介绍，招生简章，报名流程，生活信息，学生组织，学员活动，课程安排，考试安排（当篇 ）
1  新闻
2  公告
3  学术
4  专题
5  项目介绍
6  招生简章
7  报名流程
8  生活信息
9  学生组织
10  学员活动
11  课程安排
12  考试安排
* @author py pengyang813@foxmail.com
* @date 2016年3月6日 上午11:26:50 
* @version V1.0   
*/
@Entity
public class ArticleCategory {
	private int id;
	private String name;
	//是否是二级页面
	private boolean is2ndPage;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public boolean isIs2ndPage() {
		return is2ndPage;
	}
	public void setIs2ndPage(boolean is2ndPage) {
		this.is2ndPage = is2ndPage;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
