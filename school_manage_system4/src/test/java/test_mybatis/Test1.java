package test_mybatis;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cai.mapper.CollegeMapper;
import com.cai.mapper.StudentMapper;
import com.cai.mapper.UserloginMapper;
import com.cai.po.Student;
import com.cai.po.Userlogin;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-mybatis.xml")
public class Test1 {
	
	@Autowired
	private StudentMapper studentMapper;
	@Autowired
	private CollegeMapper collegeMapper;
	@Autowired
	private UserloginMapper userloginMapper;
	
//	@Test
//	public void test1(){
//		CollegeExample example = new CollegeExample();
//		CollegeExample.Criteria criteria= example.createCriteria();
//		
//		criteria.andCollegeidIsNotNull();
//		
//		List<College> list = collegeMapper.selectByExample(example);
//		
//		for (College l : list){
//			System.out.println(l);
//		}
//	}
	
	@Test
	public void test2(){
		
		Random random = new Random(3);
		SimpleDateFormat simpleDateFormate = new SimpleDateFormat("yyyy-mm-dd");
		
		
		for (int i = 0; i < 20; i++){
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, 2006+i);
			cal.set(Calendar.MONTH, 8);
			cal.set(Calendar.DAY_OF_MONTH, 3+i);
			Date date = cal.getTime();
			
			Integer a = 1000 + i;
			Integer b = random.nextInt();
			Student student = new Student(a, "caicai", "男", date, date, b); 
			studentMapper.insert(student);
			 //添加成功后，也添加到登录表
	        Userlogin userlogin = new Userlogin();
	        userlogin.setUsername(student.getUserid().toString());
	        userlogin.setPassword("123");
	        userlogin.setRole(2);
	        userloginMapper.insert(userlogin);
		}
	}	
	





}
