package com.xiuxiuxiu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.xiuxiuxiu.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    Student findById(int id);

    void deleteById(int id);
}