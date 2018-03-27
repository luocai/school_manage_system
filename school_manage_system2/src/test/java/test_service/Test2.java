package test_service;

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
		List<College> list = collegeService.findAll();
		
		for(College c : list){
			System.out.println(c);
		}
	}

}
