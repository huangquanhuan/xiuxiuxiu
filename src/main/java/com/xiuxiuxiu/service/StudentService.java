package com.xiuxiuxiu.service;

import com.xiuxiuxiu.model.Student;

import java.util.List;

import org.springframework.data.domain.Page;

public interface StudentService {

    public List<Student> getStudentList();

    public Student findStudentById(int id);
    
    public Student findStudentByPhoneNumber(String phoneNumber);

    public void save(Student student);

    public void edit(Student student);

    public void delete(int id);

    public Page<Student> findAll(int pageNum,int pageSize);
}
