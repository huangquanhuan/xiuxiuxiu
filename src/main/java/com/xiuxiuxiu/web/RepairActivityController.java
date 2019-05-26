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

    @RequestMapping("/manager/DeleteActivity")
	public String delete(Model model,@RequestParam("id") int id) {
    	System.out.println("id => "+id);
    	activityService.delete(id);
		return "redirect:/manager/activity";
	}
    
    @RequestMapping("/manager/UpdateActivity")
	public String update(Model model,@RequestParam("id") int id,@RequestParam("begin_time") String begin_time,
			@RequestParam("end_time") String end_time,@RequestParam("place") String place) {
    	System.out.println("id => "+id);
    	System.out.println("begin_time => "+begin_time);
    	System.out.println("end_time => "+end_time);
    	System.out.println("place => "+place);
    	Activity activity=activityService.findActivityById(id);
    	activity.setTime(begin_time+"-"+end_time);
    	activity.setPlace(place);
    	activityService.edit(activity);
		return "redirect:/manager/activity";
	}
    
    @RequestMapping("/manager/AddActivity")
   	public String update(Model model,@RequestParam("begin_time") String begin_time,
   			@RequestParam("end_time") String end_time,@RequestParam("place") String place) {
       	
       	System.out.println("begin_time => "+begin_time);
       	System.out.println("end_time => "+end_time);
       	System.out.println("place => "+place);
       	Activity activity=new Activity();
       	activity.setTime(begin_time+"-"+end_time);
       	activity.setPlace(place);
       	activityService.save(activity);
   		return "redirect:/manager/activity";
   	}
}

