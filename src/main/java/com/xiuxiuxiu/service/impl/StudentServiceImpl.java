package com.xiuxiuxiu.service.impl;

import com.xiuxiuxiu.model.Student;
import com.xiuxiuxiu.repository.StudentRepository;
import com.xiuxiuxiu.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> getStudentList() {
        return studentRepository.findAll();
    }

    @Override
    public Student findStudentById(int id) {
        return studentRepository.findById(id);
    }
    
    @Override
    public Student findStudentByPhoneNumber(int phoneNumber) {
    	return studentRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void edit(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void delete(int id) {
        studentRepository.deleteById(id);
    }
}


