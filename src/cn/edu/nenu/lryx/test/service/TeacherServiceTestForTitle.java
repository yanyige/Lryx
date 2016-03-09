package cn.edu.nenu.lryx.test.service;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.edu.nenu.lryx.model.Teacher;
import cn.edu.nenu.lryx.model.TeacherCategory;
import cn.edu.nenu.lryx.model.TeacherJob;
import cn.edu.nenu.lryx.model.TeacherTitle;
import cn.nenu.edu.lryx.service.TeacherService;

/**   
* @Title: AdminServiceTeacherTitleTest.java 
* @Package cn.edu.nenu.lryx.test.service 
* @Description: TODO
* @author py pengyang813@foxmail.com
* @date 2016年3月3日 下午1:01:18 
* @version V1.0   
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/cn/edu/nenu/lryx/resource/applicationContext.xml"})
public class TeacherServiceTestForTitle {
	@Autowired
	TeacherService as;
	String tn1 = "tn1";
	String  tn2 = "职称名字";
	String j1 = "j1";
	String  j2 = "职务名称";
	String c1 = "c1";
	String  c2 = "类别名称";
	String ntime = Long.toString(System.currentTimeMillis());
	@Test
	public void addTNameNowTimeTest(){
		boolean can = as.addTeacherTitle(ntime);
		assertEquals(can, true);
	}
	@Test
	public void addTn1Test(){
		boolean can = as.addTeacherTitle(tn1);
		assertEquals(can, true);
	}
	@Test
	public void addTn2Test(){
		boolean can = as.addTeacherTitle(tn2);
		assertEquals(can, true);
	}
	@Test
	public void addTn1ErrorTest(){
		boolean can = as.addTeacherTitle(tn1);
		assertEquals(can, false);
	}
	@Test
	public void addTn2ErrorTest(){
		boolean can = as.addTeacherTitle(tn2);
		assertEquals(can, false);
	}
	@Test
	public void addJNameNowTimeTest(){
		boolean can = as.addTeacherJob(ntime);
		assertEquals(can, true);
	}
	@Test
	public void addJ1Test(){
		boolean can = as.addTeacherJob(j1);
		assertEquals(can, true);
	}
	@Test
	public void addJ2Test(){
		boolean can = as.addTeacherJob(j2);
		assertEquals(can, true);
	}
	@Test
	public void addJ1ErrorTest(){
		boolean can = as.addTeacherJob(j1);
		assertEquals(can, false);
	}
	@Test
	public void addJ2ErrorTest(){
		boolean can = as.addTeacherJob(j2);
		assertEquals(can, false);
	}
	@Test
	public void addCNameNowTimeTest(){
		boolean can = as.addTeacherCategory(ntime);
		assertEquals(can, true);
	}
	@Test
	public void addC1Test(){
		boolean can = as.addTeacherCategory(c1);
		assertEquals(can, true);
	}
	@Test
	public void addC2Test(){
		boolean can = as.addTeacherCategory(c2);
		assertEquals(can, true);
	}
	@Test
	public void addC1ErrorTest(){
		boolean can = as.addTeacherCategory(c1);
		assertEquals(can, false);
	}
	@Test
	public void addC2ErrorTest(){
		boolean can = as.addTeacherCategory(c2);
		assertEquals(can, false);
	}
	@Test
	public void sc(){
		StatusCheckingUtil.statusCheckForTeacherInfo(as);
	}
	
	@Test
	public void deleteTTest(){
		List<TeacherTitle> tts = as.getAllTitles();
		Collections.shuffle(tts);
		int id;
		if(tts.size() != 0) id = tts.get(0).getId();
		else return ;
		boolean can = as.deleteTeacherTitle(id);
		StatusCheckingUtil.statusCheckForTeacherInfo(as);
//		List<Teacher> ts = as.get
	}
	@Test
	public void deleteJTest(){
		List<TeacherJob> tts = as.getAllJob();
		Collections.shuffle(tts);
		int id;
		if(tts.size() != 0) id = tts.get(0).getId();
		else return ;
		boolean can = as.deleteTeacherJob(id);
		List<Teacher> ts = as.getTeachersByJobId(id);
		assertEquals(0, ts.size());
		StatusCheckingUtil.statusCheckForTeacherInfo(as);
	}
	@Test
	public void deleteCTest(){
		List<TeacherCategory> tts = as.getAllCategory();
		Collections.shuffle(tts);
		int id;
		if(tts.size() != 0) id = tts.get(0).getId();
		else return ;
		boolean can = as.deleteTeacherCategory(id);
		List<Teacher> ts = as.getTeachersByCategoryId(id);
		assertEquals(0, ts.size());
		StatusCheckingUtil.statusCheckForTeacherInfo(as);
	}
	@Test
	public void jtest() {
		List<TeacherJob> jobs = as.getAllJob();
		Collections.shuffle(jobs);
		Map<Integer,Integer> nom = new HashMap<>();
		int i = 1;
		for(TeacherJob j : jobs){
			nom.put(Integer.valueOf(j.getId()), Integer.valueOf(i++));
		}
		
		as.modifyJobsNo(nom);
		
		List<TeacherJob> jobs_ = as.getAllJob();
		for(TeacherJob j : jobs_){
			assertEquals(j.getNo(), nom.get(j.getId()).intValue());
		}
		StatusCheckingUtil.statusCheckForTeacherInfo(as);
	}
	@Test
	public void ctest() {
		List<TeacherCategory> cas = as.getAllCategory();
		Collections.shuffle(cas);
		Map<Integer,Integer> nom = new HashMap<>();
		int i = 1;
		for(TeacherCategory c : cas){
			nom.put(Integer.valueOf(c.getId()), Integer.valueOf(i++));
		}
		
		as.modifyCategory(nom);
		
		List<TeacherCategory> cas_ = as.getAllCategory();
		for(TeacherCategory c : cas_){
			assertEquals(c.getNo(), nom.get(c.getId()).intValue());
		}
		StatusCheckingUtil.statusCheckForTeacherInfo(as);
	}
}
