package cn.edu.nenu.lryx.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.edu.nenu.lryx.dto.CategoryWithTeachersDto;
import cn.edu.nenu.lryx.model.Teacher;
import cn.edu.nenu.lryx.model.TeacherCategory;
import cn.nenu.edu.lryx.service.TeacherService;

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
public class GuestJumpAction {
	@Resource
	private TeacherService ts;
	private List<CategoryWithTeachersDto> cwtl;
	private List<TeacherCategory> categories;
	private Teacher teacher;
	private int id = - 1;
	
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
}
