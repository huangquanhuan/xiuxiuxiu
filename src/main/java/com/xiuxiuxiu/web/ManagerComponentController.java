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
    public String list(Model model,HttpSession session) {
    	if(session.getAttribute("administrator")==null)
    		return "redirect:/manager";
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
   		    ,@RequestParam("type") String type,@RequestParam("quantity") int quantity,HttpSession session){
       	
    	System.out.println("name => "+name);
    	System.out.println("price => "+price);
    	System.out.println("type => "+type);
    	System.out.println("quantity => "+quantity);		         

    	Component component=new Component();
       
    	component.setName(name);
    	component.setPrice(price);
    	component.setQuantity(quantity);
    	component.setType(type);
    	try {
    		componentService.save(component);
			model.addAttribute("message", "新增成功!");
			System.out.println("新增成功");
		} catch (Exception e) {
			model.addAttribute("err", "抱歉，由于数据库原因，新增失败");
			System.out.println("抱歉，由于数据库原因，新增失败");
			e.printStackTrace();
		}
    	
    	return list(model,session);
   	}
    
   
}

