package com.xiuxiuxiu.service.impl;

import com.xiuxiuxiu.model.Activity;
import com.xiuxiuxiu.repository.ActivityRepository;
import com.xiuxiuxiu.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService{

	@Autowired
    private ActivityRepository activityRepository;

    @Override
    public List<Activity> getActivityList() {
        return activityRepository.findAll();
    }

    @Override
    public Activity findActivityById(int id) {
        return activityRepository.findById(id);
    }

    @Override
    public void save(Activity activity) {
        activityRepository.save(activity);
    }

    @Override
    public void edit(Activity activity) {
        activityRepository.save(activity);
    }

    @Override
    public void delete(int id) {
        activityRepository.deleteById(id);
    }
}


