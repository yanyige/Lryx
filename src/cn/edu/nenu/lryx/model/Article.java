package cn.edu.nenu.lryx.model;
/**   
* @Title: Article.java 
* @Package cn.edu.nenu.lryx.model 
* @Description: TODO
* @author py pengyang813@foxmail.com
* @date 2016年3月6日 上午11:19:04 
* @version V1.0   
*/

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Article{
	private int id;
	//标题
	private String title;
	//内容
	private String content;
	//作者
	private User author;
	//最后修改的用户
	private User lastModifyUser ;
	//最后修改时间
	private String lastModifyTime;
	//发表时间
	private String publishTime;
	//访问人数
	private int visitNum;
	//是否置顶
	private boolean isOnTop;
	//是否突出显示
	private boolean isHighlight;
	//文章类别
	private ArticleCategory category;
	//主页图片墙图片地址
	private String imgUrl;
	//是否显示在主页图片墙
	private boolean isOnIndex;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@ManyToOne
	public User getLastModifyUser() {
		return lastModifyUser;
	}
	public void setLastModifyUser(User lastModifyUser) {
		this.lastModifyUser = lastModifyUser;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public boolean isOnIndex() {
		return isOnIndex;
	}
	public void setOnIndex(boolean isOnIndex) {
		this.isOnIndex = isOnIndex;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column(columnDefinition = "text")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	@ManyToOne
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public String getLastModifyTime() {
		return lastModifyTime;
	}
	public void setLastModifyTime(String lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}
	public String getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}
	public int getVisitNum() {
		return visitNum;
	}
	public void setVisitNum(int visitNum) {
		this.visitNum = visitNum;
	}
	public boolean isOnTop() {
		return isOnTop;
	}
	public void setOnTop(boolean isOnTop) {
		this.isOnTop = isOnTop;
	}
	public boolean isHighlight() {
		return isHighlight;
	}
	public void setHighlight(boolean isHighlight) {
		this.isHighlight = isHighlight;
	}
	@ManyToOne(fetch = FetchType.EAGER)  
	public ArticleCategory getCategory() {
		return category;
	}
	public void setCategory(ArticleCategory category) {
		this.category = category;
	}
	
}
