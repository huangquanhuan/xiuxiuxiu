package com.xiuxiuxiu.service;

import com.xiuxiuxiu.model.Component;

import java.util.List;

public interface ComponentService {

    public List<Component> getComponentList();

    public Component findComponentById(int id);

    public void save(Component component);

    public void edit(Component component);

    public void delete(int id);


}
