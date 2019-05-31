package com.xiuxiuxiu.web;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.xiuxiuxiu.model.Component;

import com.xiuxiuxiu.service.ComponentService;


import javax.annotation.Resource;

import java.util.List;

@Controller
public class ManagerComponentController {

	@Resource
	ComponentService componentService;
	
    @RequestMapping("/Mcomponent")
    public String index() {
    	System.out.println("fansile");
        return "redirect:/manager/Mcomponent";
    }

    @RequestMapping("/manager/Mcomponent")
    public String list(Model model) {
    	List<Component> componentList=componentService.getComponentList();
    	model.addAttribute("componentList", componentList);
        
        return "/manager/Mcomponent";
    }

    @RequestMapping("/manager/DeleteComponent")
	public String delete(Model model,@RequestParam("id") String id) {
    	System.out.println("id => "+id);
    	componentService.delete(Integer.parseInt(id));
		return "redirect:/manager/Mcomponent";
	}
    
    @RequestMapping("/manager/UpdateComponent")
	public String update(Model model,@RequestParam("id") String id,
			@RequestParam("name") String name,@RequestParam("price") Double price
    ,@RequestParam("type") String type,@RequestParam("quantity") int quantity){
    	System.out.println("name => "+name);
    	System.out.println("type => "+type);
    	System.out.println("quantity => "+quantity);
    
        System.out.println("price =>"+price);
        Component component=componentService.findComponentById(Integer.parseInt(id));

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

