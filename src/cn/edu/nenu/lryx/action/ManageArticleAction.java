package cn.edu.nenu.lryx.action;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.edu.nenu.lryx.model.Article;
import cn.edu.nenu.lryx.model.User;
import cn.edu.nenu.lryx.util.StringUtil;
import cn.nenu.edu.lryx.service.ArticleService;

/**   
 * @Title: ManageArticleAction.java 
 * @Package cn.edu.nenu.lryx.action 
 * @Description: TODO
 * @author py pengyang813@foxmail.com
 * @date 2016年3月17日 上午12:03:29 
 * @version V1.0   
 */
@Component
@Scope(value = "prototype")
@ParentPackage("json-default")
@Namespace(value="/Admin")
public class ManageArticleAction  implements SessionAware{
	@Resource
	private ArticleService as;
	private int id;
	private Article article;
	private File upload;            //文件
	private String uploadFileName;  //文件名
	private String filePath;      //文件路径
	private Map<String,Object> jsonMap;
	private Map<String,Object> session;
	
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	public Map<String, Object> getJsonMap() {
		return jsonMap;
	}
	public void setJsonMap(Map<String, Object> jsonMap) {
		this.jsonMap = jsonMap;
	}
	public ArticleService getAs() {
		return as;
	}
	public void setAs(ArticleService as) {
		this.as = as;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
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
	/**
	 * 
	 * @Title: saveArticle
	 * @Description: 保存新闻类文章,url为Admin/saveArticle,参数参加add_information.jsp
	 * @return String 
	 * @throws
	 */
	@Action(value = "saveArticle",
			results={@Result(name="success",type="json",
			params = { "root", "jsonMap" })})  
	public String saveInformation(){
		jsonMap = new HashMap<>();
		try{
			User u = (User) session.get("admin");
			System.out.println(u);
			article.setAuthor(u);
			article.setLastModifyUser(u);
			article.setPublishTime(StringUtil.getCurrentTimeStr());
			article.setLastModifyTime(StringUtil.getCurrentTimeStr());
			filePath = ActionUtil.upload("index_img", upload, uploadFileName);
			System.out.println(filePath);
			if(filePath != "")
				article.setImgUrl(filePath);
			if(article.getId() >0){
				as.modifyArticle(article);
				id = article.getId();
			}
			else id = as.addArticle(article, false);
			jsonMap.put("finish", Boolean.valueOf(true));
			jsonMap.put("id", id);
			jsonMap.put("errorMsg", "finish = ture表示操作成功，跳转到article?id=id，如果finish=false,alert(errorMsg)");
		}catch(Exception e){
			e.printStackTrace();
			jsonMap.put("finish", Boolean.valueOf(false));
			jsonMap.put("errorMsg", "操作失败！ ");
		}
		return "success";
	}
	/**
	 * 
	* @Title: deleteArticle 
	* @Description: 删除文章,url为Admin/deleteArticle参数为id
	* @return String 
	* @throws
	 */
	@Action(value = "deleteArticle",
			results={@Result(name="success",type="json",
			params = { "root", "jsonMap" })})  
	public String deleteArticle(){
		boolean can = as.deleteArticleById(id);
		jsonMap = new HashMap<>();
		if(can) {
			ActionUtil.setJsonMap(jsonMap, true, "删除成功，跳转到Admin/index");
		}else{
			ActionUtil.setJsonMap(jsonMap, false, "删除失败");
		}
		return "success";
	}
	/**
	 * 
	* @Title: save2ndPage 
	* @Description: 保存修改后的二级页面,url为Admin/save2ndPage ，参数参加modify_2ndpage.jsp
	* @return String 
	* @throws
	 */
	@Action(value = "save2ndPage",
			results={@Result(name="success",type="json",
			params = { "root", "jsonMap" })})  
	public  String save2ndPage(){
		jsonMap = new HashMap<>();
		try{
			User u = (User) session.get("admin");
			filePath = ActionUtil.upload("index_img", upload, uploadFileName);
			if(filePath != "")  article.setImgUrl(filePath);
			article.setLastModifyUser(u);
			article.setLastModifyTime(StringUtil.getCurrentTimeStr());
			boolean can = as.modifyArticle(article);
			if(can) {
				jsonMap.put("finish", Boolean.valueOf(true));
				jsonMap.put("id",article.getId());
				ActionUtil.setJsonMap(jsonMap, true, "修改成功，跳转到article?id=id");
			}else{
				ActionUtil.setJsonMap(jsonMap, false, "修改失败！");
			}
		}catch(Exception e){
			e.printStackTrace();
			ActionUtil.setJsonMap(jsonMap, false, "修改失败！");
		}
		return "success";
	}
}
