package test_service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cai.po.College;
import com.cai.po.StudentCustom;
import com.cai.service.CollegeService;
import com.cai.service.StudentService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml","classpath:spring-service.xml"})
public class Test2 {

	@Autowired
	private StudentService studentService;
	@Autowired
	private CollegeService collegeService;
	
	
//	@Test
//	public void test1() throws Exception{
//		
//		List<StudentCustom> list = studentService.findByName("小");
//		
//		for (StudentCustom s : list){
//			System.out.println(s);
//		}
//	}
//	
	@Test
	public void test2() throws Exception{
	
		SimpleDateFormat simpleDateFormate = new SimpleDateFormat("yyyy-mm-dd");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2006);
		cal.set(Calendar.MONTH, 8);
		cal.set(Calendar.DAY_OF_MONTH, 3);
		Date date = cal.getTime();
		
//		StudentCustom studentCustom = new StudentCustom();
//		studentCustom.setBirthyear(date);
//		studentCustom.setCollegeid(1);
//		studentCustom.setCollegeName("设计系");
//		studentCustom.setGrade(date);
//		studentCustom.setSelectedCourseList(selectedCourseList);
	}

}
