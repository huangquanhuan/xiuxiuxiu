package com.xiuxiuxiu.service;

import com.xiuxiuxiu.model.Equipment;
import com.xiuxiuxiu.model.Student;

import java.util.List;

import org.springframework.data.domain.Page;

public interface EquipmentService {

    public List<Equipment> getEquipmentList();

    public Equipment findEquipmentById(int id);

    public void save(Equipment equipment);

    public void edit(Equipment equipment);

    public void delete(int id);

    public Page<Equipment> findAll(int pageNum,int pageSize);
    
    public List<Equipment> findAll();
}
