package com.xiuxiuxiu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xiuxiuxiu.model.Activity;

public interface ActivityRepository extends JpaRepository<Activity, Integer> {

	Activity findById(int id);

    void deleteById(int id);
}