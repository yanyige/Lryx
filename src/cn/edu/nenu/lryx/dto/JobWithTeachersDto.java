package cn.edu.nenu.lryx.dto;

import java.util.List;

import cn.edu.nenu.lryx.model.Teacher;
import cn.edu.nenu.lryx.model.TeacherJob;

/**   
* @Title: JobWithTeachersDto.java 
* @Package cn.edu.nenu.lryx.dto 
* @Description: TODO
* @author py pengyang813@foxmail.com
* @date 2016年3月5日 上午11:15:01 
* @version V1.0   
*/
public class JobWithTeachersDto {
	private TeacherJob job;
	private List<Teacher> teachers;
	public TeacherJob getJob() {
		return job;
	}
	public void setJob(TeacherJob job) {
		this.job = job;
	}
	public List<Teacher> getTeachers() {
		return teachers;
	}
	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}
	public JobWithTeachersDto(TeacherJob job, List<Teacher> teachers) {
		super();
		this.job = job;
		this.teachers = teachers;
	}
	public JobWithTeachersDto() {
		super();
		// TODO Auto-generated constructor stub
	} 
	
}
