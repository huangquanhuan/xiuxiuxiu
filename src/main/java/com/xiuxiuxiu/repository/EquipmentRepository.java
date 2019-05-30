package com.xiuxiuxiu.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.xiuxiuxiu.model.Equipment;
import com.xiuxiuxiu.model.Reservation;

public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {

    Equipment findById(int id);

    void deleteById(int id);
    
    public Page<Equipment> findAll(Pageable pageable);
}