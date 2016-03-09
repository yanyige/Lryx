package cn.nenu.edu.lryx.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.edu.nenu.lryx.dao.TeacherDao;
import cn.edu.nenu.lryx.dto.CategoryWithTeachersDto;
import cn.edu.nenu.lryx.dto.JobWithTeachersDto;
import cn.edu.nenu.lryx.model.Teacher;
import cn.edu.nenu.lryx.model.TeacherCategory;
import cn.edu.nenu.lryx.model.TeacherJob;
import cn.edu.nenu.lryx.model.TeacherTitle;

/**   
* @Title: AdminService.java 
* @Package cn.nenu.edu.lryx.service 
* @Description: TODO
* @author py pengyang813@foxmail.com
* @date 2016年3月3日 上午11:12:50 
* @version V1.0   
*/
@Component("TeacherService")
public class TeacherService {
	@Resource
	private TeacherDao tdao;

	public TeacherService() {
		super();
	}
	public TeacherDao getTdao() {
		return tdao;
	}
	public void setTdao(TeacherDao tdao) {
		this.tdao = tdao;
	}
	/**
	 * 
	* @Title: addTeacherTitle 
	* @Description: 添加教师职称
	* @param titleName
	* @return boolean 
	* @throws
	 */
	public boolean  addTeacherTitle(String titleName){
		TeacherTitle title = new TeacherTitle();
		title.setName(titleName);
		int rst = tdao.addTeacherTitle(title);
		if(rst == -1) return false;
		return true;
	}
	/**
	 * 
	* @Title: deleteTeacherTitle 
	* @Description: 删除职称
	* @param id
	* @return boolean 
	* @throws
	 */
	public boolean deleteTeacherTitle(int id){
		return tdao.deleteTeacherTitle(id);
	}
	/**
	 * 
	* @Title: getAllTitles 
	* @Description: 得到所有的职称
	* @return List<TeacherTitle> 
	* @throws
	 */
	public List<TeacherTitle> getAllTitles(){
		return tdao.findAllTitle();
	}
	/**
	 * 
	* @Title: addTeacherJob 
	* @Description: 添加教师职务
	* @param jobName
	* @return boolean 
	* @throws
	 */
	public boolean  addTeacherJob(String jobName){
		TeacherJob job = new TeacherJob();
		job.setName(jobName);
		int rst = tdao.addTeacherJob(job);
		if(rst == -1) return false;
		return true;
	}
	/**
	 * 
	* @Title: deleteTeacherJob 
	* @Description: 删除教师职务
	* @param id
	* @return boolean 
	* @throws
	 */
	public boolean deleteTeacherJob(int id){
		return tdao.deleteTeacherJob(id);
	}
	/**
	* @Title: getAllJob 
	* @Description: 得到所有的职务
	* @return List<TeacherJob> 
	* @throws
	 */
	public List<TeacherJob> getAllJob(){
		return tdao.findAllJob();
	}
	/**
	 * 
	* @Title: modifyJobsNo 
	* @Description: 修改职务的顺序
	* @param jobNoMap
	* @return boolean 
	* @throws
	 */
	public boolean modifyJobsNo(Map<Integer,Integer> jobNoMap){
		return tdao.modifyJobsNo(jobNoMap);
	}
	/**
	 * 
	* @Title: addTeacherCategory 
	* @Description: 添加教师类别
	* @param caName
	* @return boolean 
	* @throws
	 */
	public boolean  addTeacherCategory(String caName){
		TeacherCategory ca = new TeacherCategory();
		ca.setName(caName);
		int rst = tdao.addTeacherCategory(ca);
		if(rst == -1) return false;
		return true;
	}
	/**
	 * 
	* @Title: deleteTeacherCategory 
	* @Description: 删除类别
	* @param id
	* @return boolean 
	* @throws
	 */
	public boolean deleteTeacherCategory(int id){
		return tdao.deleteTeacherCategory(id);
	}
	/**
	 * 
	* @Title: getAllCategory 
	* @Description:得到所有的类别
	* @return List<TeacherCategory> 
	* @throws
	 */
	public List<TeacherCategory> getAllCategory(){
		return tdao.findAllCategory();
	}
	/**
	 * 
	* @Title: modifyCategory 
	* @Description: 修改类别的顺序
	* @param categorysNoMap
	* @return boolean 
	* @throws
	 */
	public boolean  modifyCategory(Map<Integer,Integer> categorysNoMap){
		
		List<TeacherCategory> cas = tdao.findAllCategory();
		if(categorysNoMap.size() != cas.size()) return false;
		
		return tdao.modifyCategorysNo(categorysNoMap);
	}
	/**
	 * 
	* @Title: addTeacher 
	* @Description: 添加教师
	* @param teacher
	* @return int 
	* @throws
	 */
	public int addTeacher(Teacher teacher){
		return tdao.addTeacher(teacher);
	}
	/**
	 * 
	* @Title: getTeachersByJobId 
	* @Description: 得到相应职务的所有教师
	* @param id
	* @return List<Teacher> 
	* @throws
	 */
	public List<Teacher> getTeachersByJobId(int id){
		return tdao.findTeachersByJobId(id);
	}
	/**
	 * 
	* @Title: getTeachersByCategoryId 
	* @Description: 得到相应类别的所有教师
	* @param id
	* @return List<Teacher> 
	* @throws
	 */
	public List<Teacher> getTeachersByCategoryId(int id){
		return tdao.findTeachersByCategoryId(id);
	}
	/**
	 * 
	* @Title: deleteTeacherById 
	* @Description: 删除对应id的教师
	* @param id
	* @return boolean 
	* @throws
	 */
	public boolean deleteTeacherById(int id){
		return tdao.deleteTeacherById(id);
	}
	/**
	* @Title: modifyTeacherNo 
	* @Description: 修改对应类别的所有教师的顺序
	* @param cid
	* @param nom
	* @return boolean 
	* @throws
	 */
	public boolean modifyTeacherNo(int cid, Map<Integer,Integer> nom){
		List<Teacher> ts = tdao.findTeachersByCategoryId(cid);
		if(ts.size() != nom.size()) return false;
		return tdao.modifyTeacherNo(nom);
	}
	/**
	 * 
	* @Title: modifyTeacher 
	* @Description:修改教师信息
	* @param te
	* @return boolean 
	* @throws
	 */
	public boolean modifyTeacher(Teacher te){
		return tdao.modifyTeacher(te);
	}
	/**
	 * 
	* @Title: getTeacherById 
	* @Description: 根据id得到教师信息
	* @param id
	* @return Teacher 
	* @throws
	 */
	public Teacher getTeacherById(int id){
		return tdao.findTeacherById(id);
	}
	
	/**
	 * 
	* @Title: getAllTeachersOrderByCategory
	* @Description: 得到所有教师，按分类排列，分类和教师都已经按照顺序号排列
	* @return List<CategoryWithTeachersDto> 
	* @throws
	 */
	public List<CategoryWithTeachersDto> getAllTeachersOrderByCategory(){
		List<TeacherCategory> cas = tdao.findAllCategory();
		List<CategoryWithTeachersDto> cwtDtoList = new ArrayList<>();
		for(TeacherCategory ca : cas){
			CategoryWithTeachersDto dto = new CategoryWithTeachersDto();
			dto.setCategory(ca);
			dto.setTeachers(tdao.findTeachersByCategoryId(ca.getId()));
			cwtDtoList.add(dto);
		}
		return cwtDtoList;
	}
	/**
	 * 
	* @Title: getCategoryWithTeachersByCategoryId 
	* @Description: 得到指定类别的教师，教师已经按照序号排序
	* @param id
	* @return CategoryWithTeachersDto 
	* @throws
	 */
	public CategoryWithTeachersDto getCategoryWithTeacherByCategoryId(int id){
		TeacherCategory ca = tdao.findCategoryById(id);
		if(ca == null) return null;
		return new CategoryWithTeachersDto(ca, tdao.findTeachersByCategoryId(id));
	}
	/**
	 * 
	* @Title: getJobWithTeachersByJob 
	* @Description: 得到所有教师，按职务排列，职务已经按照顺序号排列
	* @return List<JobWithTeachersDto> 
	* @throws
	 */
	public List<JobWithTeachersDto> getJobWithTeachersByJob(){
		List<TeacherJob> jobs = tdao.findAllJob();
		List<JobWithTeachersDto> jwtDtoList = new ArrayList<>();
		for(TeacherJob job : jobs){
			JobWithTeachersDto dto = new JobWithTeachersDto();
			dto.setTeachers(tdao.findTeachersByJobId(job.getId()));
			dto.setJob(job);
			jwtDtoList.add(dto);
		}
		return jwtDtoList;
	}
	
}
