package com.xiuxiuxiu.service;

import com.xiuxiuxiu.model.Equipment;

import java.util.List;

public interface EquipmentService {

    public List<Equipment> getEquipmentList();

    public Equipment findEquipmentById(int id);

    public void save(Equipment equipment);

    public void edit(Equipment equipment);

    public void delete(int id);


}
