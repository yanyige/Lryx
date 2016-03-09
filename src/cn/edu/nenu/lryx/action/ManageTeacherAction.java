package cn.edu.nenu.lryx.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

import cn.edu.nenu.lryx.config.ErrorMsg;
import cn.edu.nenu.lryx.model.Teacher;
import cn.nenu.edu.lryx.service.TeacherService;

/**   
 * @Title: ManageTeacherAction.java 
 * @Package cn.edu.nenu.lryx.action 
 * @Description: 教师信息管理
 * @author py pengyang813@foxmail.com
 * @date 2016年3月3日 上午11:38:52 
 * @version V1.0   
 */
@Component
@Scope(value = "prototype")
@ParentPackage("json-default")
@Namespace(value="/Admin")
public class ManageTeacherAction {
	@Resource
	private TeacherService as;
	private int id;
	private String name;
	private Teacher teacher;
	private String errorMsg;
	private File upload;            //文件
	private String uploadFileName;  //文件名
	private String filePath;      //文件路径

	public TeacherService getAs() {
		return as;
	}

	public void setAs(TeacherService as) {
		this.as = as;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @Title: saveJob 
	 * @Description: 添加教师职务，ulr路径为Admin/addTeacherJob，参数name(新添加职务的名称)
	 * @return String 
	 * @throws
	 */
	@Action(value = "addTeacherJob",results = {
			@Result(name = "success",type = "redirect",location = "/Admin/manageTeacherTitle")}
	,interceptorRefs={@InterceptorRef(value = "defaultStack")})  
	public String addJob(){
		boolean can = as.addTeacherJob(name);
		if(!can) errorMsg = ErrorMsg._sameTeacherJobNameError_;
		return "success";
	}
	/**
	 * 
	 * @Title: delJob 
	 * @Description: 删除教师职务，ulr路径为Admin/delTeacherJob，参数id(职务id)
	 * @return String 
	 * @throws
	 */
	@Action(value = "delTeacherJob",results = {
			@Result(name = "success",type = "redirect",location = "/Admin/manageTeacherTitle")}
	,interceptorRefs={@InterceptorRef(value = "defaultStack")})  
	public String delJob(){
		boolean can = as.deleteTeacherJob(id);
		if(!can) errorMsg = ErrorMsg._deleteTeacherJobEroor_;
		return "success";
	}
	/**
	 * @Title: saveTitle
	 * @Description: 添加TeacherTitle，ulr路径为Admin/addTeacherTitle，参数name
	 * @return String 
	 * @throws
	 */
	@Action(value = "addTeacherTitle",results = {
			@Result(name = "success",type = "redirect",location = "/Admin/manageTeacherTitle")}
	,interceptorRefs={@InterceptorRef(value = "defaultStack")})  
	public String addTitle(){
		boolean can = as.addTeacherTitle(name);
		if(!can) errorMsg = ErrorMsg._sameTeacherTitleNameError_;
		return "success";
	}
	/**
	 * 
	 * @Title: delTitle 
	 * @Description: 删除职称，ulr路径为Admin/delTeacherTitle，参数id
	 * @return String 
	 * @throws
	 */
	@Action(value = "delTeacherTitle",results = {
			@Result(name = "success",type = "redirect",location = "/Admin/manageTeacherTitle")}
	,interceptorRefs={@InterceptorRef(value = "defaultStack")})  
	public String delTitle(){
		boolean can = as.deleteTeacherTitle(id);
		if(!can) errorMsg = ErrorMsg._deleteTeacherTitleEroor_;
		return "success";
	}
	/**
	 * 
	 * @Title: delCategory 
	 * @Description: 删除类型，ulr路径为Admin/delTeacherCategory，参数id
	 * @return String 
	 * @throws
	 */
	@Action(value = "delTeacherCategory",results = {
			@Result(name = "success",type = "redirect",location = "/Admin/manageTeacherTitle")}
	,interceptorRefs={@InterceptorRef(value = "defaultStack")})  
	public String delCategory(){
		boolean can = as.deleteTeacherCategory(id);
		if(!can) errorMsg = ErrorMsg._deleteTeacherCategoryEroor_;
		return "success";
	}

	/**
	 * @Title: saveCategory
	 * @Description: 添加TeacherCategory，ulr路径为Admin/addTeacherCategory，参数name
	 * @return String 
	 * @throws
	 */
	@Action(value = "addTeacherCategory",results = {
			@Result(name = "success",type = "redirect",location = "/Admin/manageTeacherTitle")}
	,interceptorRefs={@InterceptorRef(value = "defaultStack")})  
	public String saveJob(){
		boolean can = as.addTeacherCategory(name);
		if(!can) errorMsg = ErrorMsg._sameTeacherCategoryNameError_;
		return "success";
	}
	/**
	 * 
	 * @Title: saveTeacher 
	 * @Description: 添加教师，ulr路径为Admin/saveTeacher，参数见示例页面add_teacher.jsp
	 * @return String 
	 * @throws
	 */
	@Action(value = "saveTeacher",results = {
			@Result(name = "success",type = "redirect",location = "/Admin/addTeacher")}
	,interceptorRefs={@InterceptorRef(value = "defaultStack")})  
	public String saveTeacher(){
		filePath = upload();
		teacher.setImgUrl(filePath);
		id = as.addTeacher(teacher);
		return "success";
	}
	/**
	 * 
	 * @Title: deleteTeacher 
	 * @Description: 删除教师，ulr路径为Admin/deleteTeacher，参数id
	 * @return String 
	 * @throws
	 */
	@Action(value = "deleteTeacher",results = {
			@Result(name = "success",type = "redirect",location = "/Admin/modifyTeacherNo"),
			@Result(name = "notfound",location = "/404.jsp")}
	,interceptorRefs={@InterceptorRef(value = "defaultStack")})  
	public String  deleteTeacher(){
		boolean can = as.deleteTeacherById(id);
		if(!can) return "notfound";
		return "success";
	}
	/**
	 * 
	 * @Title: modifyTeacher 
	 * @Description: 修改教师信息,url为Admin/modifyTeacher，参数参考页面modify_teacher.jsp
	 * @return String 
	 * @throws
	 */
	@Action(value = "modifyTeacherDone",results = {
			@Result(name = "success",type = "redirect",location = "/Admin/index"),
			@Result(name = "notfound",type = "redirect",location = "/404.jsp")}
	,interceptorRefs={@InterceptorRef(value = "defaultStack")})  
	public String modifyTeacher(){
		filePath = upload();
		if(!filePath.equals("")) {
			teacher.setImgUrl(filePath);
		}
		System.out.println("###"+filePath);
		boolean can = as.modifyTeacher(teacher);
		if(can ) return "success";
		else return "notfound";
	}
	private String upload(){
		System.out.println("90909090909090");
		String path = ServletActionContext.getServletContext().getRealPath("/t-h-img");
		String rp = "";
		try {
			if (this.upload != null) {
				File f = upload;
				String fileName = java.util.UUID.randomUUID().toString(); // 采用时间+UUID的方式随即命名
				String name = fileName+ uploadFileName.substring(uploadFileName.lastIndexOf(".")); // 保存在硬盘中的文件名

				FileInputStream inputStream = new FileInputStream(f);
				FileOutputStream outputStream = new FileOutputStream(path+ "\\" + name);
				byte[] buf = new byte[1024];
				int length = 0;
				while ((length = inputStream.read(buf)) != -1) {
					outputStream.write(buf, 0, length);
				}
				inputStream.close();
				outputStream.flush();
				rp = "t-h-img"+"/"+name;
				outputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rp;
	}
}
