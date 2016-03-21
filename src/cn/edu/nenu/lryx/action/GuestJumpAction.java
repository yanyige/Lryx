package cn.edu.nenu.lryx.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;
import org.junit.experimental.categories.Categories;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.edu.nenu.lryx.dto.ArticleListItemDto;
import cn.edu.nenu.lryx.dto.ArticleListWithPageInfoDto;
import cn.edu.nenu.lryx.dto.CategoryWithTeachersDto;
import cn.edu.nenu.lryx.dto.JobWithTeachersDto;
import cn.edu.nenu.lryx.model.Article;
import cn.edu.nenu.lryx.model.ArticleCategory;
import cn.edu.nenu.lryx.model.Teacher;
import cn.edu.nenu.lryx.model.TeacherCategory;
import cn.edu.nenu.lryx.model.User;
import cn.nenu.edu.lryx.service.ArticleService;
import cn.nenu.edu.lryx.service.TeacherService;
import cn.nenu.edu.lryx.service.UserService;

/**   
* @Title: GuestJumpAction.java 
* @Package cn.edu.nenu.lryx.action 
* @Description: TODO
* @author py pengyang813@foxmail.com
* @date 2016年3月5日 上午10:55:00 
* @version V1.0   
*/
@Component
@Scope(value = "prototype")
@ParentPackage("json-default")
@Namespace(value="/")
public class GuestJumpAction implements SessionAware{
	@Resource
	private TeacherService ts;
	@Resource
	private ArticleService as;
	@Resource
	private UserService us;
	private List<CategoryWithTeachersDto> cwtl;
	private List<JobWithTeachersDto> jwtl;
	private List<TeacherCategory> categories;
	private Teacher teacher;
	private int id = - 1;
	private Article article;
	private String cname;
	private List<Article> alist;
	private int pageNo;
	private int step;
	private ArticleListWithPageInfoDto alwpi;
	private User u ;
	private Map<String,Object> session;
	private List<ArticleListItemDto > onIndexAritcles;
	private List<ArticleListWithPageInfoDto> indexArticleList ;
	

	public List<JobWithTeachersDto> getJwtl() {
		return jwtl;
	}

	public void setJwtl(List<JobWithTeachersDto> jwtl) {
		this.jwtl = jwtl;
	}

	public List<ArticleListWithPageInfoDto> getIndexArticleList() {
		return indexArticleList;
	}

	public void setIndexArticleList(List<ArticleListWithPageInfoDto> indexArticleList) {
		this.indexArticleList = indexArticleList;
	}

	public List<ArticleListItemDto> getOnIndexAritcles() {
		return onIndexAritcles;
	}

	public void setOnIndexAritcles(List<ArticleListItemDto> onIndexAritcles) {
		this.onIndexAritcles = onIndexAritcles;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public UserService getUs() {
		return us;
	}

	public void setUs(UserService us) {
		this.us = us;
	}
	
	public User getU() {
		return u;
	}

	public void setU(User u) {
		this.u = u;
	}

	public ArticleListWithPageInfoDto getAlwpi() {
		return alwpi;
	}

	public void setAlwpi(ArticleListWithPageInfoDto alwpi) {
		this.alwpi = alwpi;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public List<Article> getAlist() {
		return alist;
	}

	public void setAlist(List<Article> alist) {
		this.alist = alist;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public ArticleService getAs() {
		return as;
	}

	public void setAs(ArticleService as) {
		this.as = as;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public GuestJumpAction() {
		super();
	}

	public List<TeacherCategory> getCategories() {
		return categories;
	}

	public void setCategories(List<TeacherCategory> categories) {
		this.categories = categories;
	}

	public List<CategoryWithTeachersDto> getCwtl() {
		return cwtl;
	}

	public void setCwtl(List<CategoryWithTeachersDto> cwtl) {
		this.cwtl = cwtl;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	public TeacherService getTs() {
		return ts;
	}
	public void setTs(TeacherService ts) {
		this.ts = ts;
	}
	/**
	 * 
	* @Title: jumpToTeacherList 
	* @Description: 跳转到教师信息列表
	* @return String 
	* @throws
	 */
	@Action(value = "teacherList",results = {
			@Result(name = "success",location = "/guest/t_l.jsp")}
			,interceptorRefs={@InterceptorRef(value = "defaultStack")})  
	public String jumpToTeacherList(){
		if(id < 0) cwtl = ts.getAllTeachersOrderByCategory();
		else {
			cwtl = new ArrayList<>();
			cwtl.add(ts.getCategoryWithTeacherByCategoryId(id));
		}
		categories = ts.getAllCategory();
		return "success";
	}
	/**
	 * 
	* @Title: jumpToShowTeacher 
	* @Description: 跳转到教师页面
	* @return String 
	* @throws
	 */
	@Action(value = "teacher",results = {
			@Result(name = "success",location = "/guest/t_s.jsp"),
			@Result(name = "notfound",location = "/404.jsp")}
			,interceptorRefs={@InterceptorRef(value = "defaultStack")})  
	public String jumpToShowTeacher(){
		teacher = ts.getTeacherById(id);
		categories = ts.getAllCategory();
		if(teacher == null ) return "notfound";
		else return "success";
	}
	/**
	 * 
	* @Title: jumpToPage 
	* @Description: 跳转到二级页面或新闻列表，url为pagel,参数为cname
	* @return String 
	* @throws
	 */
	@Action(value = "pagel",results = {
			@Result(name = "2nd",location = "/guest/page.jsp"),
			@Result(name = "info",location = "/guest/articleList.jsp"),
			@Result(name = "notfound",location = "/404.jsp")}
			,interceptorRefs={@InterceptorRef(value = "defaultStack")})  
	public String jumpToPage(){
		ArticleCategory ac = as.findArticleCategoryName(cname);
		categories = ts.getAllCategory();
		System.out.println("%%%%%%%");
		for(TeacherCategory ca  : categories){
			System.out.println(ca.getName()+"------------->");
		}
		System.out.print(ac.getName()+"  "+ac.isIs2ndPage());
		if(ac.isIs2ndPage()){
			alist = as.getAllArticleByCategoryName(cname);
			if(alist == null || alist.size() == 0) 
				return "notfound";
			article = alist.get(0);
			return "2nd";
		}else{
			alwpi = as.getAllArticleByCategoryNameAndPageNo(cname, pageNo, step);
			if(alwpi.getArticles() == null) 
				return "notfound";
			return "info";
		}
	}
	/**
	 * 
	* @Title: jumpToArticle 
	* @Description: 跳转到文章页面，url为aritlce,参数为id
	* @return String 
	* @throws
	 */
	@Action(value = "article",results = {
			@Result(name = "info",location = "/guest/article.jsp"),
			@Result(name = "2nd",location = "/guest/page.jsp"),
			@Result(name = "notfound",location = "/404.jsp")}
			,interceptorRefs={@InterceptorRef(value = "defaultStack")})  
	public String jumpToArticle(){
		article = as.getArticleById(id);
		boolean is2ndPage = article.getCategory().isIs2ndPage();
		System.out.println(article.getId()+"  "+article.isOnIndex()+"  "+article.isOnTop()+" is2ndPage "+is2ndPage);
		if(article == null) 
			return "notfound";
		if(is2ndPage){
			return "2nd";
		}else{
			return "info";
		}
	}
		
	@Action(value = "login",results = {
			@Result(name = "login",location = "login.jsp"),
			@Result(name = "success",location = "/admin/i.jsp")})  
	public String jumpToLogin(){
		User user = us.getUserByUsernameAndPassword(u.getUsername(), u.getPassword());
		if(user == null) return "login";
		else{
			session.put("admin",user);
			return "success";
		}
	}
	/**
	 * 
	* @Title: jumpToIndex 
	* @Description: 跳转到主页
	* @return String 
	* @throws
	 */
	@Action(value = "",results = {
			@Result(name = "success",location = "/guest/i.jsp")})  
	public String jumpToIndex(){
		categories = ts.getAllCategory();
		onIndexAritcles = as.getOnIndexArticles();
		indexArticleList = new ArrayList<>();
		String [] arc =new  String[]{"新闻", "公告", "学术"};
		for(String cn : arc){
			indexArticleList.add(as.getAllArticleByCategoryNameAndPageNo(cn, 1, 8));
		}
		for(ArticleListWithPageInfoDto dto : indexArticleList){
			for(ArticleListItemDto d : dto.getArticles()){
				System.out.println(d.getId()+"__"+d.getLastModifyTime());
			}
			System.out.println("2222222222222");
		}
		return "success";
	}
	/**
	 * 
	* @Title: jumpToLeader 
	* @Description: 跳转到领导页面,url为leader
	* @return String 
	* @throws
	 */
	@Action(value = "leader",results = {
			@Result(name = "success",location = "/guest/leader.jsp")})  
	public String jumpToLeader(){
		categories = ts.getAllCategory();
		jwtl = ts.getJobWithTeachersByJob();
		return "success";
	}
	
}
