package com.xiuxiuxiu.service.impl;

import com.xiuxiuxiu.model.Component;
import com.xiuxiuxiu.model.Component;
import com.xiuxiuxiu.model.Student;
import com.xiuxiuxiu.repository.ComponentRepository;
import com.xiuxiuxiu.service.ComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    
    @Override
    public Page<Component> findAll(int pageNum, int pageSize) {
		Sort sort = new Sort(Sort.Direction.DESC, "id");  //降序
	    Pageable pageable = PageRequest.of(pageNum,pageSize,sort); 
	    Page<Component> pages = componentRepository.findAll(pageable);
	    return pages;
	}

	@Override
	public List<Component> findAll() {
		return componentRepository.findAll();
	}
}


