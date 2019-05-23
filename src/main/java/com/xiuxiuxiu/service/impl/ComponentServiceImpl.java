package com.xiuxiuxiu.service.impl;

import com.xiuxiuxiu.model.Component;
import com.xiuxiuxiu.repository.ComponentRepository;
import com.xiuxiuxiu.service.ComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComponentServiceImpl implements ComponentService{

	@Autowired
    private ComponentRepository componentRepository;

    @Override
    public List<Component> getComponentList() {
        return componentRepository.findAll();
    }

    @Override
    public Component findComponentById(int id) {
        return componentRepository.findById(id);
    }

    @Override
    public void save(Component component) {
        componentRepository.save(component);
    }

    @Override
    public void edit(Component component) {
        componentRepository.save(component);
    }

    @Override
    public void delete(int id) {
        componentRepository.deleteById(id);
    }
}


