package com.xiuxiuxiu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.xiuxiuxiu.model.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Integer> {

    Manager findById(int id);

    void deleteById(int id);
}