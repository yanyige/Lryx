package cn.edu.nenu.lryx.test.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import cn.edu.nenu.lryx.model.Teacher;
import cn.edu.nenu.lryx.model.TeacherCategory;
import cn.edu.nenu.lryx.model.TeacherJob;
import cn.nenu.edu.lryx.service.TeacherService;

/**   
* @Title: StatusCheckingUtil.java 
* @Package cn.edu.nenu.lryx.test.service 
* @Description: TODO
* @author py pengyang813@foxmail.com
* @date 2016年3月4日 上午11:57:04 
* @version V1.0   
*/
public class StatusCheckingUtil {
	public static void statusCheckForTeacherInfo(TeacherService as){
		//title
		
		//job
		List<TeacherJob> jobs = as.getAllJob();
		int no = 1;
		for(TeacherJob job : jobs){
			assertEquals(no,job.getNo());
			no ++ ;
		}
		//category
		List<TeacherCategory> cas = as.getAllCategory();
		no  =  1;
		for(TeacherCategory ca : cas){
			assertEquals(no, ca.getNo());
			no ++ ;
		}
		//teacher
		for(TeacherCategory ca : cas){
			List<Teacher> ts = as.getTeachersByCategoryId(ca.getId());
			no = 1;
			for(Teacher t : ts){
				assertEquals(no, t.getNo());
				no++;
			}
		}
		
	}
}
