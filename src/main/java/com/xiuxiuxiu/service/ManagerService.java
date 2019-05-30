package com.xiuxiuxiu.service;

import com.xiuxiuxiu.model.Equipment;
import com.xiuxiuxiu.model.Manager;
import com.xiuxiuxiu.model.Student;

import java.util.List;

import org.springframework.data.domain.Page;

public interface ManagerService {

    public List<Manager> getManagerList();

    public Manager findManagerById(int id);
    
    public Manager findManagerByPhoneNumber(String phoneNumber);

    public void save(Manager manager);

    public void edit(Manager manager);

    public void delete(int id);

    public Page<Manager> findAll(int pageNum,int pageSize);
    
    public List<Manager> findAll();
}
