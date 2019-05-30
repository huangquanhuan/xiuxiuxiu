package com.xiuxiuxiu.service;

import com.xiuxiuxiu.model.Activity;
import com.xiuxiuxiu.model.Student;

import java.util.List;

import org.springframework.data.domain.Page;

public interface ActivityService {

    public List<Activity> getActivityList();

    public Activity findActivityById(int id);
    
    public void save(Activity student);

    public void edit(Activity student);

    public void delete(int id);

    public Page<Activity> findAll(int pageNum,int pageSize);
    
    public List<Activity> findAll();
}
