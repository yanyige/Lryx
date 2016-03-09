package cn.edu.nenu.lryx.action;

import org.apache.struts2.components.ActionError;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**   
* @Title: TestAction.java 
* @Package cn.edu.nenu.lryx.action 
* @Description: TODO
* @author py pengyang813@foxmail.com
* @date 2016年3月7日 下午9:43:33 
* @version V1.0   
*/
@Component
@Scope(value = "prototype")
@ParentPackage("json-default")
@Namespace(value="/")
public class TestAction {
	private int id ;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Action(value = "t",results = {
			@Result(name = "success",type = "redirect",location = "/test.jsp"),
			@Result(name = "notfound",type = "redirect",location = "/test.jsp")}
	,interceptorRefs={@InterceptorRef(value = "defaultStack")})  
	public String testError(){
		if(id == 1)
			return "success";
		else{
			
			return "notfound";
		}
	}
}
