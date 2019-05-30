package com.xiuxiuxiu.service;

import com.xiuxiuxiu.model.Component;

import java.util.List;

import org.springframework.data.domain.Page;

public interface ComponentService {

    public List<Component> getComponentList();

    public Component findComponentById(int id);

    public void save(Component component);

    public void edit(Component component);

    public void delete(int id);

    public Page<Component> findAll(int pageNum,int pageSize);
    
    public List<Component> findAll();
}
