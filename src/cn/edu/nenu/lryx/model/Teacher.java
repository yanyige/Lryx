package cn.edu.nenu.lryx.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
//教师类
@Entity
public class Teacher {

	private int id;
	//姓名
	private String name;
	//是否是男性
	private boolean isMan;
	//民族
	private String nation;
	//学历
	private String educationbg;
	//出生日期
	private String birth;
	//联系电话
	private String phone;
	//联系邮箱
	private String email;
	//个人简介
	private String introduction;
	//照片路径
	private String imgUrl;
	//职称
	private TeacherTitle title;
	//职务
	private TeacherJob job;
	//排序位置
	private int no;
	//是否是名师
	private boolean isFamous;
	//分类
	private TeacherCategory category;
	//办公室
	private String room;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	

	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	@ManyToOne(fetch = FetchType.EAGER)  
	public TeacherTitle getTitle() {
		return title;
	}
	public void setTitle(TeacherTitle title) {
		this.title = title;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)  
	public TeacherJob getJob() {
		return job;
	}
	public void setJob(TeacherJob job) {
		this.job = job;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isMan() {
		return isMan;
	}
	public void setMan(boolean isMan) {
		this.isMan = isMan;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getEducationbg() {
		return educationbg;
	}
	public void setEducationbg(String educationbg) {
		this.educationbg = educationbg;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public boolean isFamous() {
		return isFamous;
	}
	public void setFamous(boolean isFamous) {
		this.isFamous = isFamous;
	}
	@ManyToOne(fetch = FetchType.EAGER)  
	public TeacherCategory getCategory() {
		return category;
	}
	public void setCategory(TeacherCategory category) {
		this.category = category;
	}
	public void update(Teacher t){
		this.setBirth(t.getBirth());
		this.setCategory(t.getCategory());
		this.setEducationbg(t.getEducationbg());
		this.setEmail(t.getEmail());
		this.setFamous(t.isFamous());
		this.setImgUrl(t.getImgUrl());
		this.setIntroduction(t.getIntroduction());
		this.setJob(t.getJob());
		this.setMan(t.isMan());
		this.setName(t.getName());
		this.setNation(t.getNation());
		this.setNo(t.getNo());
		this.setPhone(t.getPhone());
		this.setTitle(t.getTitle());
	}
}
