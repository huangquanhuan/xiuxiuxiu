package com.xiuxiuxiu.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.xiuxiuxiu.model.DemoExcel;

public interface DemoExcelRepository extends JpaRepository<DemoExcel, Integer>{

	List<DemoExcel> findAll();

}
