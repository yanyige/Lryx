package cn.edu.nenu.lryx.test.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.edu.nenu.lryx.dto.CategoryWithTeachersDto;
import cn.edu.nenu.lryx.dto.JobWithTeachersDto;
import cn.edu.nenu.lryx.model.Teacher;
import cn.edu.nenu.lryx.model.TeacherCategory;
import cn.edu.nenu.lryx.model.TeacherJob;
import cn.nenu.edu.lryx.service.TeacherService;

/**   
* @Title: GuestVisistTeacherServiceTest.java 
* @Package cn.edu.nenu.lryx.test.service 
* @Description: TODO
* @author py pengyang813@foxmail.com
* @date 2016年3月5日 上午11:33:44 
* @version V1.0   
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/cn/edu/nenu/lryx/resource/applicationContext.xml"})
public class TeacherServiceVisistTest {
	
	@Autowired
	private TeacherService ts;
	@Test
	public void orderByCategoryTest() {
		List<CategoryWithTeachersDto> ctdtoList = ts.getAllTeachersOrderByCategory();
		List<TeacherCategory> cas = ts.getAllCategory();
		assertEquals(cas.size(), ctdtoList.size());
		int cno = 1;
		for(CategoryWithTeachersDto dto : ctdtoList){
			assertEquals(cno ++, dto.getCategory().getNo());
			int tno = 1;
			for(Teacher t : dto.getTeachers()){
				assertEquals(tno ++, t.getNo());
				assertEquals(dto.getCategory(), t.getCategory());
			}
		}
		StatusCheckingUtil.statusCheckForTeacherInfo(ts);
	}
	@Test
	public void  getByCategoryIdTest(){
		List<TeacherCategory> cas = ts.getAllCategory();
		for(TeacherCategory ca : cas){
			CategoryWithTeachersDto dto = ts.getCategoryWithTeacherByCategoryId(ca.getId());
			int ccid = -1;
			if(dto.getCategory() != null) ccid =dto.getCategory().getId();
			assertSame(ca.getId() ,ccid);
			int tno = 1;
			for(Teacher t : dto.getTeachers()){
				assertEquals(tno ++, t.getNo());
				assertEquals(dto.getCategory(), t.getCategory());
			}
		}
		StatusCheckingUtil.statusCheckForTeacherInfo(ts);
	}
	@Test
	public void orderByJobTest(){
		List<JobWithTeachersDto> jwtDto = ts.getJobWithTeachersByJob();
		List<TeacherJob> jobs = ts.getAllJob();
		assertEquals(jobs.size(), jwtDto.size());
		int jno = 1;
		for(JobWithTeachersDto dto : jwtDto){
			assertEquals(jno ++, dto.getJob().getNo());
			for(Teacher t : dto.getTeachers()){
				assertEquals(dto.getJob(), t.getJob());
			}
		}
		StatusCheckingUtil.statusCheckForTeacherInfo(ts);
	}
}
