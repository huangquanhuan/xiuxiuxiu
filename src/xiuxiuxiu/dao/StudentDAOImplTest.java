package xiuxiuxiu.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import xiuxiuxiu.pojo.Student;

class StudentDAOImplTest {

	String password = "23216516";
	String name = "测试";
	String phoneNumber = "13123191992";
	int accessLevel = 0;
	String address = "天堂";
	String Email = "1669598494@qq.com";
	String studentID = "221600303";
	List<Integer> equipment = null;
	StudentDAOImpl dao = new StudentDAOImpl();
	Student student;
	int totalStudent;

	@Test
	void testGetTotal() {
		totalStudent = dao.getTotal();
	}

	@Test
	public void testAdd() {
		totalStudent = dao.getTotal();
		student = new Student(password, name, phoneNumber, accessLevel, address, Email, studentID, equipment);
		dao.add(student);
		assertEquals(totalStudent + 1, dao.getTotal());
		System.out.println(totalStudent + 1);
	}

	@Test
	public void testDeleteInt() {
		student = new Student(password, name, "13123191933", accessLevel, address, null, "226100331", equipment);
		dao.add(student);
		totalStudent = dao.getTotal();
		dao.delete(student.getID());
		assertEquals(totalStudent - 1, dao.getTotal());
		System.out.println(totalStudent + 1);
	}

	@Test
	void testDeleteStudent() {
		student = new Student(password, name, "13123191934", accessLevel, address, null, "226100332", equipment);
		dao.add(student);
		totalStudent = dao.getTotal();
		dao.delete(student);
		assertEquals(totalStudent - 1, dao.getTotal());
	}

	@Test
	void testUpdateAndGet() {
		student = new Student(password, "xp", "13123191934", accessLevel, address, null, "226100332", equipment);
		dao.add(student);
		student.setName("whq");
		student.setStudentID("221600000");
		dao.update(student);
		Student student2 = dao.get(student.getID());
		System.out.println(student2.getStudentID());
		assertEquals("221600000", student2.getStudentID());
		assertEquals("whq", student2.getName());
		dao.delete(student2);
	}

}
