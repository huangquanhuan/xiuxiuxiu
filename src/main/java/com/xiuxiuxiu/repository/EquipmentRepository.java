package com.xiuxiuxiu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.xiuxiuxiu.model.Equipment;

public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {

    Equipment findById(int id);

    void deleteById(int id);
}