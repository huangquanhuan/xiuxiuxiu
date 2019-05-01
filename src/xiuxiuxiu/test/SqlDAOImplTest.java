package xiuxiuxiu.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import xiuxiuxiu.dao.SqlDAOImpl;
import xiuxiuxiu.pojo.Student;

public class SqlDAOImplTest {
	
	String password = "23216516";
	String name = "测试";
	String phoneNumber = "13123191992";
	int accessLevel = 0;
	String address = "天堂";
	String email = "1669598494@qq.com";
	String studentID = "221600303";
	List<Integer> equipment = null;
	SqlDAOImpl dao;
	
	int totalStudent;
	
	public SqlDAOImplTest(){
		dao = new SqlDAOImpl();
	}
	
//	@Before
	@Test
	public void testGetTotalStudent(){
		totalStudent = dao.getTotalStudent();
	}

	@Test
	public void testAddStudent() {
		Student bean = new Student( password, name, phoneNumber, accessLevel, address, email, studentID, equipment);
		totalStudent = dao.getTotalStudent();
		dao.addStudent(bean);
		assertEquals(totalStudent+1, dao.getTotalStudent());
		System.out.println(totalStudent+1);
	}
	
	
//
//	@Test
//	public void testDeleteStudent() {
//		Student bean = new Student( password, name, phoneNumber, accessLevel, address, Email, studentID, equipment);
//		for(int i=3 ;i<=20;i++)
//		{
//			dao.deleteStudent(i);
//			assertEquals(totalStudent-1, dao.getTotalStudent());
//			totalStudent = dao.getTotalStudent();
//			System.out.println(totalStudent+1);
//		}
//	}

//
//	@Test
//	public void testUpdateStudent() {
//		fail("尚未实现");
//	}
//
//	@Test
//	public void testGetStudentInt() {
//		
//		fail("尚未实现");
//	}
//
//	@Test
//	public void testGetStudentIntString() {
//		fail("尚未实现");
//	}
//
//	@Test
//	public void testSearchStudent() {
//		fail("尚未实现");
//	}
//
//	@Test
//	public void testGetTotalStudent() {
//		fail("尚未实现");
//	}
//
//	@Test
//	public void testIsStudentExist() {
//		fail("尚未实现");
//	}
	
}
