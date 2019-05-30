package com.xiuxiuxiu.service.impl;

import com.xiuxiuxiu.model.Equipment;
import com.xiuxiuxiu.model.Student;
import com.xiuxiuxiu.model.Article;
import com.xiuxiuxiu.repository.EquipmentRepository;
import com.xiuxiuxiu.service.EquipmentService;

import org.hibernate.annotations.Source;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.CoreMatchers.is;

import java.util.List;

@Service
public class TestEquipmentServiceImpl{

	@Autowired
	private EquipmentServiceImpl service;
	@Autowired
	private StudentServiceImpl studentService;
	@Autowired
	private Equipment equipment;
	
	@Test
    public void getStudentTest() {
		List<Student> students =studentService.getStudentList();
    	for(Student student :students )
    		System.out.println(student.getName());
    }
	
    @Test
    public void getEquipmentListTest() {
    	int id	= 0;
    	System.out.println(service.getEquipmentList().get(0).toString());
        Assert.assertThat(service.getEquipmentList().get(0).getId(),is(id) );
        int i = 0;
        i++;
    }

    @Test
    public void findArticleByIdTest() {
    	equipment = service.getEquipmentList().get(0);
    	int id = equipment.getId();
    	Assert.assertThat(equipment , is(service.findEquipmentById(id))); 
    }

    @Test
    public void saveTest() {
    	equipment = new Equipment();
    	equipment.setId(10086);
    	equipment.setEquipmentName("未知");
    	List<Student> students =studentService.getStudentList();
    	for(Student student :students )
    		System.out.println(student.getName());
    	equipment.setStudent(students.get(1));
    	service.save(equipment);
    	Assert.assertThat(equipment.getId(), is(service.findEquipmentById(10086).getId()));
    }

    @Test
    @Transactional
    public void editTest() {
    	equipment = service.getEquipmentList().get(0);
    	int id = equipment.getId();
    	equipment.setEquipmentName("未知");
    	service.edit(equipment);
    	Assert.assertThat(equipment.getEquipmentName(), is(service.findEquipmentById(id).getEquipmentName()));
    }

    @Test
    @Transactional
    public void deleteTest() {
    	equipment = service.getEquipmentList().get(0);
    	int id = equipment.getId();
    	service.delete(id);
    	Assert.assertNotEquals(null, service.getEquipmentList().get(0));
    }
}


