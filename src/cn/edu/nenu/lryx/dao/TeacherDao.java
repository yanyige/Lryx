package cn.edu.nenu.lryx.dao;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import cn.edu.nenu.lryx.model.Teacher;
import cn.edu.nenu.lryx.model.TeacherCategory;
import cn.edu.nenu.lryx.model.TeacherJob;
import cn.edu.nenu.lryx.model.TeacherTitle;


/**
 * 
 * @Title: TeacherDao.java 
 * @Package cn.edu.nenu.lryx.dao 
 * @Description: Teacher DAO
 * @author py pengyang813@foxmail.com
 * @date 2016年2月24日 下午3:41:33 
 * @version V1.0
 */
@Component
public class TeacherDao {
	@Resource
	private SessionFactory sf;
	public TeacherDao(SessionFactory sf) {
		super();
		this.sf = sf;
	}
	public TeacherDao() {
		super();
	}
	private Session getCurrentSession() {
		return sf.getCurrentSession();
	}
	/**
	 * @Title: addTeacher 
	 * @Description: 添加新的教师
	 * @param teacher 新的教师
	 * @return int 
	 * @throws
	 */
	public int addTeacher(Teacher teacher){

		TeacherTitle t = findTitleById(teacher.getTitle().getId()); 
		TeacherJob j = findJobById(teacher.getJob().getId());
		TeacherCategory c =findCategoryById(teacher.getCategory().getId());
		if(t != null) teacher.setTitle(t);
		else teacher.setTitle(null);
		if(j != null) teacher.setJob(j);
		else teacher.setJob(null);
		if(c != null) teacher.setCategory(c);
		else teacher.setCategory(null);
		
		List<Teacher> ts = findTeachersByCategoryId(teacher.getCategory().getId());
		int nl = ts.size();
		teacher.setNo(nl + 1);
		
		return (int) getCurrentSession().save(teacher);
	}
	/**
	 * @Title: modifyTeacher 
	 * @Description: 修改教师信息
	 * @param teacher
	 * @return int 
	 * @throws
	 */
	public boolean modifyTeacher(Teacher teacher){
		System.out.println(teacher.getId()+"  "+teacher.getName()+"  "+teacher.getImgUrl());
		Teacher teacherInDb = findTeacherById(teacher.getId());
		if(teacherInDb == null) return false;
		TeacherJob njob = null;
		if(teacher.getBirth() != null)  njob = findJobById(teacher.getJob().getId());
		teacher.setJob(njob);
		TeacherTitle nti = null;
		if(teacher.getTitle() != null) nti = findTitleById(teacher.getTitle().getId());
		teacher.setTitle(nti);

		TeacherCategory oca = teacherInDb.getCategory();
		TeacherCategory nca  = null;
		if(teacher.getCategory() != null && teacher.getCategory().getId() != -1) 
			nca = findCategoryById(teacher.getCategory().getId());
		System.out.print(oca); if(oca != null) System.out.print(" "+oca.getId());
		System.out.print("   "+nca); if(nca != null) System.out.println(" "+nca.getId());
		
//		if(oca == null && nca ==null){//无类别->无类别
//			
//		}else if( oca != null){
//			if(nca == null){//有类别->无类别
//				subTeacherNoAfter(teacherInDb.getNo(), oca.getId());
//			}else if( nca.getId() != oca.getId()){//有类别->有类别，类别不同
//				subTeacherNoAfter(teacherInDb.getNo(), oca.getId());
//				List<Teacher> ts = findTeachersByCategoryId(nca.getId());
//				int nl = ts.size() + 1;
//				teacher.setNo(nl);
//			}	
//		}else{//无类别->有类别
//		List<Teacher> ts = findTeachersByCategoryId(nca.getId());
//		int nl = ts.size() + 1;
//		teacher.setNo(nl);
//		}
		teacher.setNo(teacherInDb.getNo());
		if(oca != null && oca != nca){
			subTeacherNoAfter(teacherInDb.getNo(), oca.getId());
		}
		if(nca != null && oca != nca){
			List<Teacher> ts = findTeachersByCategoryId(nca.getId());
			int nl = ts.size() + 1;
			teacher.setNo(nl);
		}
		
		teacher.setCategory(nca);
		teacherInDb.update(teacher);
		System.out.println(teacherInDb.getName() +"  "+teacherInDb.getImgUrl());

		getCurrentSession().update(teacherInDb);
		return true;
	}
	/**
	 * 
	 * @Title: findTeachersByCategoryId 
	 * @Description:根据类别id得到教师列表
	 * @param id
	 * @return List<Teacher> 
	 * @throws
	 */
	public List<Teacher> findTeachersByCategoryId(int id){
		if(id == -1){
			return getCurrentSession().createCriteria(Teacher.class).add(Restrictions.isNull("category")).list();
		}else
			return getCurrentSession()
					.createCriteria(Teacher.class)
					.add(Restrictions.eq("category.id", id))
					.addOrder(Order.asc("no")).list();
	}
	/**
	 * 
	 * @Title: findTeachersByJobId 
	 * @Description: 根据职务id得到教师列表
	 * @param id
	 * @return List<Teacher> 
	 * @throws
	 */
	public List<Teacher> findTeachersByJobId(int id){
		if(id == -1)
			return getCurrentSession().createCriteria(Teacher.class).add(Restrictions.isNull("job")).list();
		else
			return getCurrentSession()
					.createCriteria(Teacher.class)
					.add(Restrictions.eq("job.id", id))
					.addOrder(Order.asc("no")).list();
	}
	/**
	 * 
	 * @Title: deleteTeacherById 
	 * @Description: 删除指定id的教师，所有同类别且排名在他之后的教师的序号都减1
	 * @param id
	 * @return boolean 
	 * @throws
	 */
	public boolean deleteTeacherById(int id){
		Teacher t = findTeacherById(id);
		if(t != null){
			//使同一类别的排名在要删除的teacher之后的序号减一
			if(t.getCategory() != null)
				subTeacherNoAfter(t.getNo(), t.getCategory().getId());
			//删除teacher
			getCurrentSession().delete(t);
			return true;
		}else return false;
	}
	/**
	 * 
	* @Title: addTeacherNoAfter 
	* @Description: 使于指定类别的位于指定位置后面的教师的顺序号减1
	* @param no
	* @param cid void 
	* @throws
	 */
	public void subTeacherNoAfter(int no, int cid){
		getCurrentSession()
		.createQuery("update Teacher t set t.no = t.no - 1 where t.no > "+no+" and category.id="+cid)
		.executeUpdate();
	}
	/**
	 * @Title: findAllTitle 
	 * @Description:得到所有的职称
	 * @return List<TeacherTitle> 
	 * @throws
	 */
	public List<TeacherTitle> findAllTitle(){
		return getCurrentSession().createCriteria(TeacherTitle.class).list();
	}
	/**
	 * @Title: addTeacherTtile 
	 * @Description: 添加教师职称
	 * @param teacherTtitle
	 * @return int 
	 * @throws
	 */
	public int addTeacherTitle(TeacherTitle teacherTtitle){
		Object obj =getCurrentSession()
				.createCriteria(TeacherTitle.class)
				.add(Restrictions.eq("name", teacherTtitle.getName())).uniqueResult();
		if(obj != null) return  -1;
		return (int) getCurrentSession().save(teacherTtitle);
	}
	/**
	 * @Title: modifyTeacherTitle 
	 * @Description: 修改教师职称
	 * @param teacherTitle
	 * @return int 
	 * @throws
	 */
	public int modifyTeacherTitle(TeacherTitle teacherTitle){
		getCurrentSession().update(teacherTitle);
		return teacherTitle.getId();
	}
	/**
	 * @Title: deleteTeacherTitle 
	 * @Description: 删除指定id的教师职称，需要删除该职称和所有教师的关联
	 * @param id
	 * @return boolean 
	 * @throws
	 */
	public boolean deleteTeacherTitle(int id){
		Object obj =getCurrentSession()
				.createCriteria(TeacherTitle.class)
				.add(Restrictions.eq("id", id)).uniqueResult();
		if(obj == null){
			return false;
		}else {
			//删除与教师的关联
			getCurrentSession()
			.createQuery("update Teacher t set t.title=null where t.title.id="+((TeacherTitle)obj).getId())
			.executeUpdate();
			getCurrentSession().delete(obj);
			return true;
		}
	}
	/**
	 * @Title: findAllJob
	 * @Description:得到所有的职务
	 * @return List<TeacherJob> 
	 * @throws
	 */
	public List<TeacherJob> findAllJob(){
		return getCurrentSession()
				.createCriteria(TeacherJob.class)
				.addOrder(Order.asc("no"))
				.list();
	}
	/**
	 * @Title: addTeacherJob 
	 * @Description: 添加新的教师职务
	 * @param teacherJob
	 * @return int 
	 * @throws
	 */
	public int addTeacherJob(TeacherJob teacherJob){
		Object obj =getCurrentSession()
				.createCriteria(TeacherJob.class)
				.add(Restrictions.eq("name", teacherJob.getName())).uniqueResult();
		if(obj != null) return  -1;
		//为新添加的职务标记顺序
		List<TeacherJob> lastJob = (List<TeacherJob>) getCurrentSession()
				.createCriteria(TeacherJob.class)
				.addOrder(Order.desc("no")).list();
		int ln = 0;
		if(lastJob != null && lastJob.size() != 0)  ln =  lastJob.get(0).getNo();
		teacherJob.setNo(ln + 1);
		return (int) getCurrentSession().save(teacherJob);
	}
	/**
	 * @Title: modifyTeacherJob 
	 * @Description: 修改教师职务
	 * @param teacherJob
	 * @return int 
	 * @throws
	 */
	public int modifyTeacherJob(TeacherJob teacherJob){
		getCurrentSession().update(teacherJob);
		return teacherJob.getId();
	}	
	/**
	 * @Title: deleteTeacherJob 
	 * @Description: 删除指定id的教师职务，需要删除该职务和所有教师的关联
	 * @param id
	 * @return boolean 
	 * @throws
	 */
	public boolean deleteTeacherJob(int id){
		Object obj =getCurrentSession()
				.createCriteria(TeacherJob.class)
				.add(Restrictions.eq("id", id)).uniqueResult();
		if(obj == null){
			return false;
		}else {
			//删除与教师的关联
			getCurrentSession()
			.createQuery("update Teacher t set t.job=null where t.job.id="+((TeacherJob)obj).getId())
			.executeUpdate();
			//修改职务的顺序
			getCurrentSession()
			.createQuery("update TeacherJob tj set tj.no = tj.no - 1 where tj.no >"
					+Integer.toString(((TeacherJob)obj)
							.getNo())).executeUpdate();
			//删除职务
			getCurrentSession().delete(obj);
			return true;
		}
	}
	/**
	 * 
	 * @Title: modifyJobsNo 
	 * @Description: 修改各职务的序号
	 * @param jobNoMap
	 * @return boolean 
	 * @throws
	 */
	public boolean modifyJobsNo(Map<Integer,Integer> jobNoMap){
		for(Entry<Integer,Integer>e : jobNoMap.entrySet()){
			getCurrentSession()
			.createQuery("update TeacherJob tj set tj.no = "+
					e.getValue()+" where tj.id="+e.getKey())
			.executeUpdate();
		}
		return true;
	}
	/**
	 * @Title: findAllCategory
	 * @Description:得到所有的类别
	 * @return List<TeacherCategory> 
	 * @throws
	 */
	public List<TeacherCategory> findAllCategory(){
		return getCurrentSession()
				.createCriteria(TeacherCategory.class)
				.addOrder(Order.asc("no"))
				.list();
	}
	/**
	 * @Title: addTeacherCategory
	 * @Description: 添加新的教师类别
	 * @param teacherCa
	 * @return int 
	 * @throws
	 */
	public int addTeacherCategory(TeacherCategory teacherCa){
		Object obj =getCurrentSession()
				.createCriteria(TeacherCategory.class)
				.add(Restrictions.eq("name", teacherCa.getName())).uniqueResult();
		if(obj != null) return  -1;
		//为新添加的职务标记顺序
		List<TeacherCategory> lastCategory = (List<TeacherCategory>) getCurrentSession()
				.createCriteria(TeacherCategory.class)
				.addOrder(Order.desc("no")).list();
		int ln = 0;
		if(lastCategory != null && lastCategory.size() != 0)  ln =  lastCategory.get(0).getNo();
		teacherCa.setNo(ln + 1);
		return (int) getCurrentSession().save(teacherCa);
	}
	/**
	 * @Title: modifyTeacherCategory
	 * @Description: 修改教师类别
	 * @param teacherCa
	 * @return int 
	 * @throws
	 */
	public int modifyTeacherCategory(TeacherCategory teacherCa){
		getCurrentSession().update(teacherCa);
		return teacherCa.getId();
	}	
	/**
	 * @Title: deleteTeacherCategory
	 * @Description: 删除指定id的教师类别，需要删除该类别和所有教师的关联
	 * @param id
	 * @return boolean 
	 * @throws
	 */
	public boolean deleteTeacherCategory(int id){
		Object obj =getCurrentSession()
				.createCriteria(TeacherCategory.class)
				.add(Restrictions.eq("id", id)).uniqueResult();
		if(obj == null){
			return false;
		}else {
			//删除与教师的关联
			getCurrentSession()
			.createQuery("update Teacher t set t.category=null where t.category.id="+((TeacherCategory)obj).getId())
			.executeUpdate();
			//修改类别的顺序
			getCurrentSession()
			.createQuery("update TeacherCategory tc set tc.no= tc.no - 1 where tc.no >"
					+Integer.toString(((TeacherCategory)obj)
							.getNo())).executeUpdate();
			//删除类别
			getCurrentSession().delete(obj);
			return true;
		}
	}
	/**
	 * 
	 * @Title: modifyCategorysNo 
	 * @Description: 修改类别的序号
	 * @param categorysNoMap
	 * @return boolean 
	 * @throws
	 */
	public boolean modifyCategorysNo(Map<Integer,Integer> categorysNoMap){
		for(Entry<Integer,Integer>e : categorysNoMap.entrySet()){
			getCurrentSession()
			.createQuery("update TeacherCategory tc set tc.no = "+
					e.getValue()+" where tc.id="+e.getKey())
			.executeUpdate();
		}
		return true;
	}

	public boolean modifyTeacherNo(Map<Integer,Integer> teacherNoMap){
		for(Entry<Integer,Integer> e : teacherNoMap.entrySet()){
			getCurrentSession()
			.createQuery("update Teacher t set t.no = "+e.getValue()+" where t.id="+e.getKey())
			.executeUpdate();
		}
		return true;
	}
	public Teacher findTeacherById(int id){
		return (Teacher) getCurrentSession()
				.createCriteria(Teacher.class)
				.add(Restrictions.idEq(id))
				.uniqueResult();
	}
	public TeacherJob findJobById(int id){
		return  (TeacherJob) getCurrentSession()
				.createCriteria(TeacherJob.class)
				.add(Restrictions.idEq(id))
				.uniqueResult();
	}
	public TeacherTitle findTitleById(int id){
		return (TeacherTitle) getCurrentSession()
				.createCriteria(TeacherTitle.class)
				.add(Restrictions.idEq(id))
				.uniqueResult();
	}
	public TeacherCategory findCategoryById(int id){
		return (TeacherCategory) getCurrentSession()
				.createCriteria(TeacherCategory.class)
				.add(Restrictions.idEq(id))
				.uniqueResult();
	}
}
