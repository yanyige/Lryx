package cn.edu.nenu.lryx.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**   
* @Title: TeacherCategory.java 
* @Package cn.edu.nenu.lryx.model 
* @Description: 教师分类，用于显示
* @author py pengyang813@foxmail.com
* @date 2016年3月3日 上午11:29:14 
* @version V1.0   
*/

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames="name")})
public class TeacherCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private int no;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
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
}
