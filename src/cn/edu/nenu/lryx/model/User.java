package cn.edu.nenu.lryx.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**   
* @Title: User.java 
* @Package cn.edu.nenu.lryx.model 
* @Description: TODO
* @author py pengyang813@foxmail.com
* @date 2016年3月6日 上午11:20:16 
* @version V1.0   
*/
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames="name")}	)
public class User {
	private int id;
	private String name;
	private String username;
	private String password;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
