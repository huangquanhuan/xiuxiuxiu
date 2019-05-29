package com.xiuxiuxiu.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.xiuxiuxiu.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>, JpaSpecificationExecutor<Student> {

    Student findById(int id);

    void deleteById(int id);
    
    //分页
    public Page<Student> findAll(Pageable pageable);
    
	public Student findByPhoneNumber(String phoneNumber);
}