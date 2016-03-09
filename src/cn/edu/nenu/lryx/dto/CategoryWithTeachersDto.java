package cn.edu.nenu.lryx.dto;

import java.util.List;

import cn.edu.nenu.lryx.model.Teacher;
import cn.edu.nenu.lryx.model.TeacherCategory;

/**   
* @Title: CategoryWithTeachersDto.java 
* @Package cn.edu.nenu.lryx.dto 
* @Description: TODO
* @author py pengyang813@foxmail.com
* @date 2016年3月5日 上午11:06:31 
* @version V1.0   
*/
public class CategoryWithTeachersDto {
	private TeacherCategory category;
	private List<Teacher> teachers;
	public TeacherCategory getCategory() {
		return category;
	}
	public void setCategory(TeacherCategory category) {
		this.category = category;
	}
	public List<Teacher> getTeachers() {
		return teachers;
	}
	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}
	public CategoryWithTeachersDto(TeacherCategory category, List<Teacher> teachers) {
		super();
		this.category = category;
		this.teachers = teachers;
	}
	public CategoryWithTeachersDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
