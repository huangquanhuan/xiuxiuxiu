package com.xiuxiuxiu.service.impl;

import static org.hamcrest.CoreMatchers.is;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.xiuxiuxiu.model.Student;

//@RunWith(SpringRunner.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class TestStudentServicesImpl {
	
	@Autowired
    private StudentServiceImpl studentService;
	@Autowired
	private Student student;
	
	@Test
    public void getStudentListTest() {
		student = studentService.getStudentList().get(0);
		Assert.assertThat("吴可强",is( student.getName()));
    }

    @Test
    public void findStudentByIdTest() {
    	student = studentService.getStudentList().get(0);
    	int id = student.getId();
    	Assert.assertThat(student, is(studentService.findStudentById(id)));
    }
    
    @Test
    public void findStudentByPhoneNumberTest() {
    	String phoneNumber="13123191994";
    	Assert.assertThat(studentService.findStudentByPhoneNumber(phoneNumber).getPhoneNumber(),is(phoneNumber));
    }

   /* @Test
    public void saveTest(Student student) {
        studentRepository.save(student);
    }*/

    @Test
    @Transactional
    public void editTest(Student student) {
    	student = studentService.getStudentList().get(0);
    	int id = student.getId();
    	student.setAddress("？未知？");
    	studentService.edit(student);
    	Assert.assertThat(student.getAddress(), is(studentService.findStudentById(id).getAddress()));
    }

    @Test
    @Transactional
    public void deleteTest() {
    	student = studentService.getStudentList().get(0);
    	int id = student.getId();
    	studentService.delete(id);
    	Assert.assertNotEquals(student, studentService.getStudentList().get(0));
    }
}