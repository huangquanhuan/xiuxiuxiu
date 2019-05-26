package com.xiuxiuxiu.service.impl;

import com.xiuxiuxiu.model.Manager;

import com.xiuxiuxiu.repository.ManagerRepository;
import com.xiuxiuxiu.service.ManagerService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ManagerServiceImpl implements ManagerService{

	@Autowired
    private ManagerRepository managerRepository;

    @Override
    public List<Manager> getManagerList() {
        return managerRepository.findAll();
    }

    @Override
    public Manager findManagerById(int id) {
        return managerRepository.findById(id);
    }

    @Override
    public Manager findManagerByPhoneNumber(String phoneNumber) {
    	return managerRepository.findByPhoneNumber(phoneNumber);
    }
    
    @Override
    public void save(Manager manager) {
        managerRepository.save(manager);
    }

    @Override
    public void edit(Manager manager) {
        managerRepository.save(manager);
    }

    @Override
    public void delete(int id) {
        managerRepository.deleteById(id);
    }
}


