package test_mybatis;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cai.mapper.CollegeMapper;
import com.cai.mapper.StudentMapper;
import com.cai.po.College;
import com.cai.po.CollegeExample;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-mybatis.xml")
public class Test1 {
	
	@Autowired
	private StudentMapper studentMapper;
	@Autowired
	private CollegeMapper collegeMapper;
	
	@Test
	public void test1(){
		CollegeExample example = new CollegeExample();
		CollegeExample.Criteria criteria= example.createCriteria();
		
		criteria.andCollegeidIsNotNull();
		
		List<College> list = collegeMapper.selectByExample(example);
		
		for (College l : list){
			System.out.println(l);
		}
	}



}
