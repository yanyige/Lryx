package cn.edu.nenu.lryx.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

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
import cn.edu.nenu.lryx.model.Article;
import cn.edu.nenu.lryx.model.Teacher;
import cn.nenu.edu.lryx.service.ArticleService;
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
	private TeacherService ts;

	private int id;
	private String name;
	private Teacher teacher;
	private File upload;            //文件
	private String uploadFileName;  //文件名
	private String filePath;      //文件路径
	private Map<String,Object> jsonMap;
	private Map<Integer,Integer> itemNoMap;

	public Map<Integer, Integer> getItemNoMap() {
		return itemNoMap;
	}
	public void setItemNoMap(Map<Integer, Integer> itemNoMap) {
		this.itemNoMap = itemNoMap;
	}
	public Map<String, Object> getJsonMap() {
		return jsonMap;
	}
	public void setJsonMap(Map<String, Object> jsonMap) {
		this.jsonMap = jsonMap;
	}
	public TeacherService getTs() {
		return ts;
	}
	public void setTs(TeacherService ts) {
		this.ts = ts;
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
			@Result(name = "success", type="json",params = {"root", "jsonMap"})})  
	public String addJob(){
		boolean can = ts.addTeacherJob(name);
		jsonMap = new HashMap<>();
		if(!can ){
			ActionUtil.setJsonMap(jsonMap, false, "添加失败！");
		}else {
			ActionUtil.setJsonMap(jsonMap, true, "finish = ture表示操作成功，"
					+ "跳转到Admin/manageTeacherTitle，如果finish=false,alert(errorMsg)");
		}
		return "success";
	}
	/**
	 * 
	 * @Title: delJob 
	 * @Description: 删除教师职务，ulr路径为Admin/delTeacherJob，参数id(职务id)
	 * @return String 
	 * @throws
	 */
	@Action(value = "delTeacherJob",results =  {
			@Result(name = "success", type="json",params = {"root", "jsonMap"})})  
	public String delJob(){
		boolean can = ts.deleteTeacherJob(id);
		jsonMap = new HashMap<>();
		if(!can ){
			ActionUtil.setJsonMap(jsonMap, false, "删除失败！");
		}else {
			ActionUtil.setJsonMap(jsonMap, true, "finish = ture表示操作成功，"
					+ "跳转到Admin/manageTeacherTitle，如果finish=false,alert(errorMsg)");
		}
		return "success";
	}
	/**
	 * @Title: saveTitle
	 * @Description: 添加TeacherTitle，ulr路径为Admin/addTeacherTitle，参数name
	 * @return String 
	 * @throws
	 */
	@Action(value = "addTeacherTitle",results = {
			@Result(name = "success", type="json",params = {"root", "jsonMap"})}) 
	public String addTitle(){
		boolean can = ts.addTeacherTitle(name);
		jsonMap = new HashMap<>();
		if(!can ){
			ActionUtil.setJsonMap(jsonMap, false, "添加失败！");
		}else {
			ActionUtil.setJsonMap(jsonMap, true, "finish = ture表示操作成功，"
					+ "跳转到Admin/manageTeacherTitle，如果finish=false,alert(errorMsg)");
		}
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
			@Result(name = "success", type="json",params = {"root", "jsonMap"})}) 
	public String delTitle(){
		boolean can = ts.deleteTeacherTitle(id);
		jsonMap = new HashMap<>();
		if(!can ){
			ActionUtil.setJsonMap(jsonMap, false, "删除失败！");
		}else {
			ActionUtil.setJsonMap(jsonMap, true, "finish = ture表示操作成功，"
					+ "跳转到Admin/manageTeacherTitle，如果finish=false,alert(errorMsg)");
		}
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
			@Result(name = "success", type="json",params = {"root", "jsonMap"})}) 
	public String delCategory(){
		boolean can = ts.deleteTeacherCategory(id);
		jsonMap = new HashMap<>();
		if(!can ){
			ActionUtil.setJsonMap(jsonMap, false, "删除失败！");
		}else {
			ActionUtil.setJsonMap(jsonMap, true, "finish = ture表示操作成功，"
					+ "跳转到Admin/manageTeacherTitle，如果finish=false,alert(errorMsg)");
		}
		return "success";
	}

	/**
	 * @Title: saveCategory
	 * @Description: 添加TeacherCategory，ulr路径为Admin/addTeacherCategory，参数name
	 * @return String 
	 * @throws
	 */
	@Action(value = "addTeacherCategory",results = {
			@Result(name = "success", type="json",params = {"root", "jsonMap"})})  
	public String saveJob(){
		boolean can = ts.addTeacherCategory(name);
		jsonMap = new HashMap<>();
		if(!can ){
			ActionUtil.setJsonMap(jsonMap, false, "添加失败！");
		}else {
			ActionUtil.setJsonMap(jsonMap, true, "finish = ture表示操作成功，"
					+ "跳转到Admin/manageTeacherTitle，如果finish=false,alert(errorMsg)");
		}
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
			@Result(name = "success", type="json",params = {"root", "jsonMap"})})
	public String saveTeacher(){
		jsonMap = new HashMap<>();
		try{

			filePath =  ActionUtil.upload("/t-h-img", upload, uploadFileName);
			teacher.setImgUrl(filePath);
			id = ts.addTeacher(teacher);
			jsonMap = new HashMap<>();
			ActionUtil.setJsonMap(jsonMap, true, "finish = ture表示操作成功，"
					+ "跳转到teacher?id=id，如果finish=false,alert(errorMsg)");
			jsonMap.put("id", id);
		}catch(Exception e){
			ActionUtil.setJsonMap(jsonMap, false, "添加失败！");
		}
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
			@Result(name = "success", type="json",params = {"root", "jsonMap"}),
			@Result(name = "notfound",location = "/404.jsp")})  
	public String  deleteTeacher(){
//		(name = "success",type = "redirect",location = "/Admin/modifyTeacherNo")
		boolean can = ts.deleteTeacherById(id);
		jsonMap = new HashMap<>();
		if(!can) return "notfound";
		else{
			jsonMap = new HashMap<>();
			ActionUtil.setJsonMap(jsonMap, true, "成功，跳转到/Admin/modifyTeacherNo");
			return "success";
		}
	}
	/**
	 * 
	 * @Title: modifyTeacher 
	 * @Description: 修改教师信息,url为Admin/modifyTeacherDone，参数参考页面modify_teacher.jsp
	 * @return String 
	 * @throws
	 */
	@Action(value = "modifyTeacherDone",results = {
			@Result(name = "success", type="json",params = {"root", "jsonMap"}),
			@Result(name = "notfound",location = "/404.jsp")})  
	public String modifyTeacher(){
		filePath = ActionUtil.upload("t-h-img", upload, uploadFileName);
		jsonMap = new HashMap<>();
		if(!filePath.equals("")) {
			teacher.setImgUrl(filePath);
		}
		boolean can = ts.modifyTeacher(teacher);
		if(can ) {
			jsonMap = new HashMap<>();
			ActionUtil.setJsonMap(jsonMap, true, "跳转到teacher?id=id");
			jsonMap.put("id", teacher.getId());
			return "success";
		}
		else return "notfound";
	}
	/**
	* @Title: mdifyJobNo 
	* @Description: 修改teahcer job的顺序 ，url是 Admin/modifyJobNo，参数为一个Map<Integer,Integer>表示顺序
	* @return String 
	* @throws
	 */
	@Action(value = "modifyJobNo",results = {
			@Result(name = "success", type="json",params = {"root", "jsonMap"})})  
	public String mdifyJobNo(){
		boolean can  = ts.modifyJobsNo(itemNoMap);
		if(can){
			ActionUtil.setJsonMap(jsonMap, true,"修改职务顺序成功！");
		}else {
			ActionUtil.setJsonMap(jsonMap, false, "修改职务顺序失败！");
		}
		return "success";
	}
	/**
	 * 
	* @Title: mdifyCategoryNo 
	* @Description:修改教师类别的顺序 url是Admin/modifyCategoryNo,参数是一个Map
	* @return String 
	* @throws
	 */
	@Action(value = "modifyCategoryNo",results = {
			@Result(name = "success", type="json",params = {"root", "jsonMap"})})  
	public String mdifyCategoryNo(){
		boolean can  = ts.modifyCategory(itemNoMap);
		if(can){
			ActionUtil.setJsonMap(jsonMap, true,"修改类别顺序成功！");
		}else {
			ActionUtil.setJsonMap(jsonMap, false, "修改类别顺序失败！");
		}
		return "success";
	}
	/**
	 * 
	* @Title: mdifyTeacherNo 
	* @Description: 修改教师类别的顺序 url是Admin/modifyTeacherNo,参数是一个教师类别的id和一个Map
	* @return String 
	* @throws
	 */
	@Action(value = "modifyTeacherNo",results = {
			@Result(name = "success", type="json",params = {"root", "jsonMap"})})  
	public String mdifyTeacherNo(){
		boolean can  = ts.modifyTeacherNo(id, itemNoMap);
		if(can){
			ActionUtil.setJsonMap(jsonMap, true,"修改职务顺序成功！");
		}else {
			ActionUtil.setJsonMap(jsonMap, false, "修改职务顺序失败！");
		}
		return "success";
	}
}
