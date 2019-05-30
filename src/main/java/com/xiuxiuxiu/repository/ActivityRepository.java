package com.xiuxiuxiu.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.xiuxiuxiu.model.Activity;
import com.xiuxiuxiu.model.Reservation;

public interface ActivityRepository extends JpaRepository<Activity, Integer> {

	Activity findById(int id);

    void deleteById(int id);
    
    public Page<Activity> findAll(Pageable pageable);
}