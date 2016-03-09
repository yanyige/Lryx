package cn.edu.nenu.lryx.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.edu.nenu.lryx.model.ArticleCategory;
import cn.edu.nenu.lryx.model.Teacher;
import cn.edu.nenu.lryx.model.TeacherCategory;
import cn.edu.nenu.lryx.model.TeacherJob;
import cn.edu.nenu.lryx.model.TeacherTitle;
import cn.nenu.edu.lryx.service.ArticleService;
import cn.nenu.edu.lryx.service.TeacherService;

/**   
* @Title: AdminJumpAction.java 
* @Package cn.edu.nenu.lryx.action 
* @Description: 管理员跳转
* @author py pengyang813@foxmail.com
* @date 2016年3月3日 下午12:13:14 
* @version V1.0   
*/
@Component
@Scope(value = "prototype")
@ParentPackage("json-default")
@Namespace(value="/Admin")
public class AdminJumpAction {
	@Resource
	private TeacherService ats;
	@Resource
	private ArticleService as;
	private int id = -2;
	private List<TeacherTitle> titles ;
	private List<TeacherJob> jobs;
	private List<TeacherCategory> categories;
	private List<Teacher> teachers;
	private Teacher teacher;
	private List<ArticleCategory> acategorise;
	
	


	public ArticleService getAs() {
		return as;
	}

	public void setAs(ArticleService as) {
		this.as = as;
	}

	public List<ArticleCategory> getAcategorise() {
		return acategorise;
	}

	public void setAcategorise(List<ArticleCategory> acategorise) {
		this.acategorise = acategorise;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public AdminJumpAction() {
		super();
		// TODO Auto-generated constructor stub
	}


	

	public TeacherService getAts() {
		return ats;
	}

	public void setAts(TeacherService ats) {
		this.ats = ats;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}

	public List<TeacherTitle> getTitles() {
		return titles;
	}

	public void setTitles(List<TeacherTitle> titles) {
		this.titles = titles;
	}

	public List<TeacherJob> getJobs() {
		return jobs;
	}

	public void setJobs(List<TeacherJob> jobs) {
		this.jobs = jobs;
	}

	public List<TeacherCategory> getCategories() {
		return categories;
	}

	public void setCategories(List<TeacherCategory> categories) {
		this.categories = categories;
	}
/**
* @Title: showTeacherTitles 
* @Description: 跳转到添加教师信息页面，ulr路径为Admin/manageTeacherTitle，页面返回3个list，分别为titles(职称列表),jobs(职务列表),categories(类别列表)
* @return String 
* @throws
 */
	@Action(value = "manageTeacherTitle",results = {
			@Result(name = "success",location = "/admin/teacher_pro_list.jsp")}
			,interceptorRefs={@InterceptorRef(value = "defaultStack")})  
	public String showTeacherTitles(){
		titles = ats.getAllTitles();
		jobs = ats.getAllJob();
		categories = ats.getAllCategory();
		return "success";
	}
	/**
	 * 
	* @Title: addTeacher 
	* @Description:跳转到添加教师信息页面，ulr路径为Admin/addTeacher，页面返回3个list，分别为titles(职称列表),jobs(职务列表),categories(类别列表)
	* @return String 
	* @throws
	 */
	@Action(value = "addTeacher",results = {
			@Result(name = "success",location = "/admin/add_teacher.jsp")}
			,interceptorRefs={@InterceptorRef(value = "defaultStack")})  
	public String jumpToAddTeacher(){
		titles = ats.getAllTitles();
		jobs = ats.getAllJob();
		categories = ats.getAllCategory();
		return "success";
	}
	/**
	* @Title: jumpToManageTeacherByCategoryId 
	* @Description: 跳转到教师列表的页面,参数为id(类别id)，ulr路径为Admin/modifyTeacherNo,页面返回一个列表(List<Teacher>) teachers(属于当前类别的教师)
	* 3个list，分别为titles(职称列表),jobs(职务列表),categories(类别列表)
	* @return String 
	* @throws
	 */
	@Action(value = "modifyTeacherNo",results = {
			@Result(name = "success",location = "/admin/teacher_list.jsp")}
			,interceptorRefs={@InterceptorRef(value = "defaultStack")})  
	public String jumpToManageTeacherByCategoryId(){
		categories = ats.getAllCategory();
		if(id == -2 && categories != null && categories.size() > 0) id = categories.get(0).getId();
		teachers = ats.getTeachersByCategoryId(id);
		return "success";
	}
	/**
	 * 
	* @Title: jumpToModifyTeacher 
	* @Description: 跳转到修改教师页面，ulr路径为Admin/modifyTeacher，参数id(要修改的教师id)，返回一个对象(Teacher) teacher
	* @return String 
	* @throws
	 */
	@Action(value = "modifyTeacher",results = {
			@Result(name = "success",location = "/admin/modify_teacher.jsp"),
			@Result(name = "notfound",location = "/404.jsp")}
			,interceptorRefs={@InterceptorRef(value = "defaultStack")})  
	public String jumpToModifyTeacher(){
		teacher = ats.getTeacherById(id);
		jobs = ats.getAllJob();
		categories = ats.getAllCategory();
		titles = ats.getAllTitles();
		if(teacher == null) return "notfound";
		return "success";
	}
	@Action(value = "index",results = {
			@Result(name = "success",location = "/admin/a_a.jsp")}
			,interceptorRefs={@InterceptorRef(value = "defaultStack")})  
	public String jumpToAddInformation(){
		acategorise = as.getAllInformationCategory();
		return "success";
	}
	/**
	 * 
	* @Title: jumpToAdmin 
	* @Description: 跳转到主页面，ulr路径为Admin/index
	* @return String 
	* @throws
	 */
	@Action(value = "index",results = {
			@Result(name = "success",location = "/admin/i.jsp")}
			,interceptorRefs={@InterceptorRef(value = "defaultStack")})  
	public String jumpToAdmin(){
		return "success";
	}
	
	
}
