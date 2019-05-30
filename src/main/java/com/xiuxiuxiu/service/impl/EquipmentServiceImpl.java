package com.xiuxiuxiu.service.impl;

import com.xiuxiuxiu.model.Equipment;
import com.xiuxiuxiu.model.Equipment;
import com.xiuxiuxiu.model.Student;
import com.xiuxiuxiu.repository.EquipmentRepository;
import com.xiuxiuxiu.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentServiceImpl implements EquipmentService{

	@Autowired
    private EquipmentRepository equipmentRepository;

    @Override
    public List<Equipment> getEquipmentList() {
        return equipmentRepository.findAll();
    }

    @Override
    public Equipment findEquipmentById(int id) {
        return equipmentRepository.findById(id);
    }

    @Override
    public void save(Equipment equipment) {
        equipmentRepository.save(equipment);
    }

    @Override
    public void edit(Equipment equipment) {
        equipmentRepository.save(equipment);
    }

    @Override
    public void delete(int id) {
        equipmentRepository.deleteById(id);
    }
    
    @Override
    public Page<Equipment> findAll(int pageNum, int pageSize) {
		Sort sort = new Sort(Sort.Direction.DESC, "id");  //降序
	    Pageable pageable = PageRequest.of(pageNum,pageSize,sort); 
	    Page<Equipment> pages = equipmentRepository.findAll(pageable);
	    return pages;
	}

	@Override
	public List<Equipment> findAll() {
		return equipmentRepository.findAll();
	}
}


