package com.xiuxiuxiu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.xiuxiuxiu.model.Manager;
import com.xiuxiuxiu.model.Student;

public interface ManagerRepository extends JpaRepository<Manager, Integer> {

    Manager findById(int id);

    void deleteById(int id);

	Manager findByPhoneNumber(String phoneNumber);

}