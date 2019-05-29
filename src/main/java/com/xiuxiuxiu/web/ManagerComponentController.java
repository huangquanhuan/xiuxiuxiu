package com.xiuxiuxiu.web;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xiuxiuxiu.model.Component;


import com.xiuxiuxiu.service.ComponentService;


import javax.annotation.Resource;
import javax.servlet.http.HttpSession;


import java.util.List;


public class ManagerComponentController {

	@Resource
	ComponentService componentService;
	  
	
    @RequestMapping("/Mcomponent")
    public String index() {
        return "redirect:/manager/Mcomponent";
    }

    @RequestMapping("/manager/Mcomponent")
    public String list(Model model) {
    	List<Component> componentList=componentService.getComponentList();
    	model.addAttribute("componentList", componentList);
        
        return "/manager/Mcomponent";
    }

    @RequestMapping("/manager/DeleteComponent")
	public String delete(Model model,@RequestParam("id") int id) {
    	System.out.println("id => "+id);
    	componentService.delete(id);
		return "redirect:/manager/Mcomponent";
	}
    
    @RequestMapping("/manager/UpdateComponent")
	public String update(Model model,@RequestParam("id") int id,
			@RequestParam("name") String name,@RequestParam("price") Double price
    ,@RequestParam("type") String type,@RequestParam("quantity") int quantity){
    	System.out.println("id => "+id);
    	System.out.println("name => "+name);
    	System.out.println("price => "+price);
    	System.out.println("type => "+type);
    	System.out.println("quantity => "+quantity);
        
    	Component component=componentService.findComponentById(id);

    	component.setName(name);
    	component.setPrice(price);
    	component.setQuantity(quantity);
    	component.setType(type);
    	componentService.edit(component);
		return "redirect:/manager/Mcomponent";
	}
    
    @RequestMapping("/manager/AddComponent")
   	public String Add(Model model,
   			@RequestParam("name") String name,@RequestParam("price") Double price
   		    ,@RequestParam("type") String type,@RequestParam("quantity") int quantity){
       	
    	System.out.println("name => "+name);
    	System.out.println("price => "+price);
    	System.out.println("type => "+type);
    	System.out.println("quantity => "+quantity);
        
    	Component component=new Component();

    	component.setName(name);
    	component.setPrice(price);
    	component.setQuantity(quantity);
    	component.setType(type);
    	componentService.save(component);
   		return "redirect:/manager/Mcomponent";
   	}
    
    
}

