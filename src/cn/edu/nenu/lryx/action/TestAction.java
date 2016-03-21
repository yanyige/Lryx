package cn.edu.nenu.lryx.action;

import java.util.Map;
import java.util.Map.Entry;

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
	private Map<Integer,Integer> itemNoMap;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public Map<Integer, Integer> getItemNoMap() {
		return itemNoMap;
	}


	public void setItemNoMap(Map<Integer, Integer> itemNoMap) {
		this.itemNoMap = itemNoMap;
	}


	@Action(value = "t",results = {
			@Result(name = "success",type = "redirect",location = "/test.jsp"),
			@Result(name = "notfound",type = "redirect",location = "/test.jsp")})  
	public String testError(){
		System.out.println("id = "+id);
		for(Entry<Integer,Integer> e : itemNoMap.entrySet()){
			System.out.println(e.getKey()+"  "+e.getValue());
		}
		return "success";
	}
}
