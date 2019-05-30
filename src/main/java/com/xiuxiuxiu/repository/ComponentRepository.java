package com.xiuxiuxiu.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.xiuxiuxiu.model.Component;
import com.xiuxiuxiu.model.Reservation;

public interface ComponentRepository extends JpaRepository<Component, Integer> {

    Component findById(int id);

    void deleteById(int id);
    
    public Page<Component> findAll(Pageable pageable);
}