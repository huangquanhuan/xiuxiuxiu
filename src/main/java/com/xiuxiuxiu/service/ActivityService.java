package com.xiuxiuxiu.service;

import com.xiuxiuxiu.model.Activity;

import java.util.List;

public interface ActivityService {

    public List<Activity> getActivityList();

    public Activity findActivityById(int id);

    public void save(Activity student);

    public void edit(Activity student);

    public void delete(int id);


}
