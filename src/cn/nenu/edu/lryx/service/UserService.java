package cn.nenu.edu.lryx.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.edu.nenu.lryx.dao.UserDao;
import cn.edu.nenu.lryx.model.User;

/**   
 * @Title: UserService.java 
 * @Package cn.nenu.edu.lryx.service 
 * @Description: TODO
 * @author py pengyang813@foxmail.com
 * @date 2016年3月7日 下午7:19:55 
 * @version V1.0   
 */
@Component
public class UserService {
	@Resource
	private UserDao ud;

	public UserService() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserDao getUd() {
		return ud;
	}

	public void setUd(UserDao ud) {
		this.ud = ud;
	}
	
	public boolean addUser(User user){
		System.out.println(user.getUsername() +"  "+user.getPassword());
		if(user == null || user.getUsername() == null || user.getPassword() == null) return false;
		User hu = ud.findUserByUserName(user.getUsername());
		if(hu != null) return false;
		ud.addUser(user);
		return true;
	}
	
	public User getUserByUsernameAndPassword(String username,String password){
		return ud.findUserByUsernameAndPassword(username, password);
	}
	
	public User getUserByUsername(String username){
		return ud.findUserByUserName(username);
	}
	
	public User getUserById(int id){
		return ud.findUserById(id);
	}
}
