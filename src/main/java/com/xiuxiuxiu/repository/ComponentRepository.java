package com.xiuxiuxiu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.xiuxiuxiu.model.Component;

public interface ComponentRepository extends JpaRepository<Component, Integer> {

    Component findById(int id);

    void deleteById(int id);
}