package com.xiuxiuxiu.service;

import com.xiuxiuxiu.model.Manager;


import java.util.List;

public interface ManagerService {

    public List<Manager> getManagerList();

    public Manager findManagerById(int id);
    
    public Manager findManagerByPhoneNumber(String phoneNumber);

    public void save(Manager manager);

    public void edit(Manager manager);

    public void delete(int id);


}
