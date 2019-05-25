package com.xiuxiuxiu.service;

import com.xiuxiuxiu.model.Student;

import java.util.List;

public interface StudentService {

    public List<Student> getStudentList();

    public Student findStudentById(int id);
    
    public Student findStudentByPhoneNumber(String phoneNumber);

    public void save(Student student);

    public void edit(Student student);

    public void delete(int id);


}
