package com.xiuxiuxiu.service.impl;

import com.xiuxiuxiu.model.Activity;
import com.xiuxiuxiu.model.Article;
import com.xiuxiuxiu.repository.ActivityRepository;
import com.xiuxiuxiu.service.ActivityService;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.CoreMatchers.is;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestActivityServiceImpl{

	@Autowired
    private ActivityServiceImpl activityService;
	@Autowired
	private Activity activity = new Activity();
	
    @Test
    public void getActivityListTest() {
    	ActivityServiceImpl activityService = new ActivityServiceImpl();
    	activity = activityService.getActivityList().get(0);
    	int id	= 0;
    	System.out.println(activityService.getActivityList().get(0).toString());
        Assert.assertThat(activityService.getActivityList().get(0).getId(),is(id) );
        int i = 0;
        i++;
    }

    @Test
    public void findArticleByIdTest() {
    	activity = activityService.getActivityList().get(0);
    	int id = activity.getId();
    	Assert.assertThat(activity , is(activityService.findActivityById(id))); 
    }

    @Test
    public void saveTest() {
    	ManagerServiceImpl managerService = new ManagerServiceImpl();
    	activity = new Activity();
    	activity.setId(10086);
    	activity.setPlace("未知");
    	activity.setTime("未知");
    	activity.setManager(managerService.findManagerById(0));
    	activityService.save(activity);
    	Assert.assertThat(activity.getId(), is(activityService.findActivityById(10086).getId()));
    }

    @Test
    @Transactional
    public void editTest() {
    	activity = activityService.getActivityList().get(0);
    	int id = activity.getId();
    	activity.setTime("未知");
    	activityService.edit(activity);
    	Assert.assertThat(activity.getTime(), is(activityService.findActivityById(id).getTime()));
    }

    @Test
    @Transactional
    public void deleteTest() {
    	activity = activityService.getActivityList().get(0);
    	int id = activity.getId();
    	activityService.delete(id);
    	Assert.assertNotEquals(null, activityService.getActivityList().get(0));
    }
}


