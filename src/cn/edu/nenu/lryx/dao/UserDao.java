package cn.edu.nenu.lryx.dao;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import cn.edu.nenu.lryx.model.User;

/**   
 * @Title: UserDao.java 
 * @Package cn.edu.nenu.lryx.dao 
 * @Description: TODO
 * @author py pengyang813@foxmail.com
 * @date 2016年3月7日 下午7:14:36 
 * @version V1.0   
 */
@Component
public class UserDao {
	@Resource
	private SessionFactory sf;


	public UserDao(SessionFactory sf) {
		super();
		this.sf = sf;
	}

	public UserDao() {
		super();
	}

	private Session getCurrentSession() {
		return sf.getCurrentSession();
	}
	public int addUser(User u){
		return (int) getCurrentSession().save(u);
	}
	public User findUserByUserName(String username){
		return (User) getCurrentSession().createCriteria(User.class).add(Restrictions.eq("username", username)).uniqueResult();
	}
	public User findUserByUsernameAndPassword(String username,String password){
		return (User) getCurrentSession()
				.createCriteria(User.class)
				.add(Restrictions.eq("username", username))
				.add(Restrictions.eq("password", password))
				.uniqueResult();

	}
	public User findUserById(int id){
		return (User) getCurrentSession()
				.createCriteria(User.class)
				.add(Restrictions.idEq(id))
				.uniqueResult();
	}
}
