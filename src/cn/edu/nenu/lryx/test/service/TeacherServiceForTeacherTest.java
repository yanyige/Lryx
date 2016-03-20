package cn.edu.nenu.lryx.test.service;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sun.org.apache.xerces.internal.util.Status;

import cn.edu.nenu.lryx.dto.CategoryWithTeachersDto;
import cn.edu.nenu.lryx.dto.JobWithTeachersDto;
import cn.edu.nenu.lryx.model.Teacher;
import cn.edu.nenu.lryx.model.TeacherCategory;
import cn.edu.nenu.lryx.model.TeacherJob;
import cn.edu.nenu.lryx.model.TeacherTitle;
import cn.nenu.edu.lryx.service.TeacherService;

/**   
 * @Title: AdminServiceTeacherTest.java 
 * @Package cn.edu.nenu.lryx.test.service 
 * @Description: TODO
 * @author py pengyang813@foxmail.com
 * @date 2016年3月3日 下午6:33:59 
 * @version V1.0   
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/cn/edu/nenu/lryx/resource/applicationContext.xml"})
public class TeacherServiceForTeacherTest {
	@Autowired
	TeacherService as;
	@Test
	public void addTeacherTest() {
		List<TeacherTitle> tts = as.getAllTitles();
		List<TeacherCategory> cas = as.getAllCategory();
		List<TeacherJob> jobs = as.getAllJob();
		for(int  i = 0 ; i < tts.size() * 10 ; ++ i){
			Teacher t = new Teacher();
			//随机选择一个类别
			TeacherCategory ca = null;
			Collections.shuffle(cas);
			if(!cas.isEmpty()) ca = cas.get(0);
			t.setCategory(ca);
			//随机选择一个职务
			TeacherJob job = null;
			Collections.shuffle(jobs);
			if(!jobs.isEmpty()) job = jobs.get(0);
			t.setJob(job);
			//随机选择一个职称
			TeacherTitle ti = null;
			Collections.shuffle(tts);
			if(!tts.isEmpty()) ti = tts.get(0);
			t.setTitle(ti);
			//设置其他信息
			t.setMan(i%2== 0);
			t.setName(i+"姓名");
			t.setNation(i+"名族");
			t.setPhone(i+"18243003897");
			t.setBirth(i+"1993-12-09");
			t.setEducationbg(i+"教育背景");
			t.setEmail(i+"@nenu.edu.cn");
			t.setFamous(i % 2 == 0);
			t.setImgUrl(i+"url");
			t.setIntroduction(i+"介绍");

			as.addTeacher(t);

		}

		for(int i = 0 ; i < 3 ; ++ i){
			Teacher t = new Teacher();
			//随机选择一个类别
			TeacherCategory ca = new TeacherCategory();
			ca.setId(-1);
			t.setCategory(ca);
			//随机选择一个职务
			TeacherJob job = new TeacherJob();
			job.setId(-1);
			t.setJob(job);
			//随机选择一个职称
			TeacherTitle ti =new TeacherTitle();
			ti.setId(-1);
			t.setTitle(ti);
			//设置其他信息
			t.setMan(i%2== 0);
			t.setName(i+"姓名");
			t.setNation(i+"名族");
			t.setPhone(i+"18243003897");
			t.setBirth(i+"1993-12-09");
			t.setEducationbg(i+"教育背景");
			t.setEmail(i+"@nenu.edu.cn");
			t.setFamous(i % 2 == 0);
			t.setImgUrl(i+"url");
			t.setIntroduction(i+"介绍");
		}
		StatusCheckingUtil.statusCheckForTeacherInfo(as);
	}

	@Test
	public void findTeachersByJobIdTest(){
		List<TeacherJob> js = as.getAllJob();
		for(TeacherJob j : js){
			List<Teacher> ts = as.getTeachersByJobId(j.getId());

			for(Teacher t : ts){
				assertEquals(t.getJob().getId(), j.getId());
			}
		}
		StatusCheckingUtil.statusCheckForTeacherInfo(as);
	}
	@Test
	public void findTeachersByCategoryIdTest(){
		List<TeacherCategory> cs = as.getAllCategory();
		for(TeacherCategory c : cs){
			List<Teacher> ts = as.getTeachersByCategoryId(c.getId());

			int lasN = -1;
			for(Teacher t : ts){
				assertEquals(t.getCategory().getId(), c.getId());
				assertFalse(lasN >= t.getNo());
				lasN = t.getNo();
			}
		}
		StatusCheckingUtil.statusCheckForTeacherInfo(as);
	}
	@Test
	public void findTeachersByCategoryId_Test(){
		List<Teacher> ts = as.getTeachersByCategoryId(-1);
		for(Teacher t : ts){
			assertEquals(t.getCategory(), null);
		}
		StatusCheckingUtil.statusCheckForTeacherInfo(as);
	}
	@Test
	public void findTeachersByJobId_Test(){
		List<Teacher> ts = as.getTeachersByJobId(-1);
		for(Teacher t : ts){
			assertEquals(t.getJob(), null);
		}
		StatusCheckingUtil.statusCheckForTeacherInfo(as);
	}
	@Test
	public void deleteTest() {
		List<TeacherCategory> cas = as.getAllCategory();
		Collections.shuffle(cas);
		int cid = -1;
		if(cas.size() != 0)  cid = cas.get(0).getId();
		else return ;
		List<Teacher> ts = as.getTeachersByCategoryId(cid);
		Collections.shuffle(ts);
		int tid = -1;
		if(ts.size() != 0) tid = ts.get(0).getId();
		else return ;
		as.deleteTeacherById(tid);
		List<Teacher> ts_  = as.getTeachersByCategoryId(cid);
		int no = 1;
		assertEquals(ts.size(), ts_.size() + 1);
		for(Teacher t : ts_){
			assertEquals(t.getNo(), no);
			no++;
		}
		StatusCheckingUtil.statusCheckForTeacherInfo(as);
	}


	@Test
	public void modifyTeachertest(){
		//不存在的教师
		Teacher t = new Teacher();
		t.setId(-999);
		assertEquals(false,as.modifyTeacher(t));
		StatusCheckingUtil.statusCheckForTeacherInfo(as);

		//更改普通信息(非类别信息)
		List<Teacher> ts = as.getTeachersByCategoryId(-1);
		modifyNomalInfo(ts);
		StatusCheckingUtil.statusCheckForTeacherInfo(as);

		List<TeacherCategory> categories = as.getAllCategory();
		for(TeacherCategory ca :categories){
			ts = as.getTeachersByCategoryId(ca.getId());
			modifyNomalInfo(ts);
		}
		StatusCheckingUtil.statusCheckForTeacherInfo(as);

		//更改类别
		List<TeacherCategory> cas = as.getAllCategory();
		//无类别->有类别
		ts = as.getTeachersByCategoryId(-1);
		modifyCategory(ts, randomChooseCa(cas));
		StatusCheckingUtil.statusCheckForTeacherInfo(as);

		//有类别->有类别
		for(TeacherCategory ca :categories){
			ts = as.getTeachersByCategoryId(ca.getId());
			modifyCategory(ts, randomChooseCa(cas));
			StatusCheckingUtil.statusCheckForTeacherInfo(as);
		}
		//有类别->无类别
		for(TeacherCategory ca :categories){
			ts = as.getTeachersByCategoryId(ca.getId());
			TeacherCategory tc_ = new TeacherCategory();
			tc_ .setId(-1);
			modifyCategory(ts, tc_);
			StatusCheckingUtil.statusCheckForTeacherInfo(as);
		}
	}

	public void modifyCategory(List<Teacher> ts,TeacherCategory ca){
		if(ts.size() == 0) return ;
		Collections.shuffle(ts);
		Teacher t = ts.get(0);
		t.setCategory(ca);
		assertEquals(true,as.modifyTeacher(t));
		Teacher t_ = as.getTeacherById(t.getId());
		if(t_.getCategory() == null ){
			assertEquals(ca.getId(), -1);			
		}else 
			assertEquals(ca.getId(), t_.getCategory().getId());
	}
	public TeacherCategory randomChooseCa(List<TeacherCategory> cas){
		Collections.shuffle(cas);
		TeacherCategory ca = new TeacherCategory();
		ca.setId(cas.get(0).getId());
		return ca;
	}
	public void modifyNomalInfo(List<Teacher> ts){
		Collections.shuffle(ts);
		if(ts.size() != 0){
			Teacher t = ts.get(0);
			String str ="修改后的姓名";
			t.setName(str);			
			assertEquals(true,as.modifyTeacher(t));
			Teacher t_ = as.getTeacherById(t.getId());
			assertEquals(t_.getName(),str);
		}
	}
	@Test
	public void modifyTnTest() {
		List<TeacherCategory> cas = as.getAllCategory();
		for(TeacherCategory ca : cas){
			List<Teacher> ts = as.getTeachersByCategoryId(ca.getId()); 
			Collections.shuffle(ts);
			Map<Integer,Integer> nom = new HashMap<>();
			int i = 1;
			for(Teacher t : ts){
				nom.put(Integer.valueOf(t.getId()), Integer.valueOf(i++));
			}
			boolean can = as.modifyTeacherNo(ca.getId(), nom);
			List<Teacher> ts_ = as.getTeachersByCategoryId(ca.getId());
			for(Teacher t : ts_){
				assertEquals(t.getNo(), nom.get(t.getId()).intValue());
			}
		}
		StatusCheckingUtil.statusCheckForTeacherInfo(as);
	}
	@Test
	public void orderByCategoryTest() {
		List<CategoryWithTeachersDto> ctdtoList = as.getAllTeachersOrderByCategory();
		List<TeacherCategory> cas =as.getAllCategory();
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
		StatusCheckingUtil.statusCheckForTeacherInfo(as);
	}
	@Test
	public void  getByCategoryIdTest(){
		List<TeacherCategory> cas = as.getAllCategory();
		for(TeacherCategory ca : cas){
			CategoryWithTeachersDto dto = as.getCategoryWithTeacherByCategoryId(ca.getId());
			int ccid = -1;
			if(dto.getCategory() != null) ccid =dto.getCategory().getId();
			assertSame(ca.getId() ,ccid);
			int tno = 1;
			for(Teacher t : dto.getTeachers()){
				assertEquals(tno ++, t.getNo());
				assertEquals(dto.getCategory(), t.getCategory());
			}
		}
		StatusCheckingUtil.statusCheckForTeacherInfo(as);
	}
	@Test
	public void orderByJobTest(){
		List<JobWithTeachersDto> jwtDto = as.getJobWithTeachersByJob();
		List<TeacherJob> jobs = as.getAllJob();
		assertEquals(jobs.size(), jwtDto.size());
		int jno = 1;
		for(JobWithTeachersDto dto : jwtDto){
			assertEquals(jno ++, dto.getJob().getNo());
			for(Teacher t : dto.getTeachers()){
				assertEquals(dto.getJob(), t.getJob());
			}
		}
		StatusCheckingUtil.statusCheckForTeacherInfo(as);
	}
}
