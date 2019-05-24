package com.xiuxiuxiu.web;

import com.xiuxiuxiu.model.Activity;

import com.xiuxiuxiu.service.ActivityService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class RepairActivityController {

	@Resource
	   ActivityService activityService;

    @RequestMapping("/activity")
    public String index() {
        return "redirect:/manager/activity";
    }

    @RequestMapping("/manager/activity")
    public String list(Model model) {
    	List<Activity> activityList=activityService.getActivityList();
    	model.addAttribute("activityList", activityList);
        
        return "/manager/RepairActivity";
    }

   
}

