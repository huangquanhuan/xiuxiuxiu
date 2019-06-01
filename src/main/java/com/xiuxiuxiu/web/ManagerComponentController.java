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
	public String update(Model model,HttpSession session,@RequestParam("id") String id,
			@RequestParam("name") String name,@RequestParam("price") String price
    ,@RequestParam("type") String type,@RequestParam("quantity") String quantity){
    	System.out.println("name => "+name);
    	System.out.println("price =>"+price);
    	System.out.println("type => "+type);
    	System.out.println("quantity => "+quantity);
    	Double doublePrice ;
    	int intQuantity;
    	try {
    		doublePrice = Double.parseDouble(price);
    	}catch (Exception e) {
			model.addAttribute("err", "请输入正确的价格！！");
			return list(model,session);
		}
    	try {
    		intQuantity = Integer.parseInt(quantity);
    	}catch (Exception e) {
    		model.addAttribute("err", "请输入正确的数量！！");
    		return list(model,session);
    	}
    	
    	
        Component component=componentService.findComponentById(Integer.parseInt(id));

        component.setName(name);
        component.setPrice(doublePrice);
        component.setQuantity(intQuantity);
        component.setType(type);
    	componentService.edit(component);
    	return list(model,session);
	}
    
    @RequestMapping("/manager/AddComponent")
   	public String Add(Model model,
   			@RequestParam("name") String name,@RequestParam("price") String price
   		    ,@RequestParam("type") String type,@RequestParam("quantity") String quantity,HttpSession session){
       	
    	System.out.println("name => "+name);
    	System.out.println("price => "+price);
    	System.out.println("type => "+type);
    	System.out.println("quantity => "+quantity);	
    	
    	Double doublePrice ;
    	int intQuantity;
    	try {
    		doublePrice = Double.parseDouble(price);
    	}catch (Exception e) {
			model.addAttribute("err", "请输入正确的价格！！");
			return list(model,session);
		}
    	try {
    		intQuantity = Integer.parseInt(quantity);
    	}catch (Exception e) {
    		model.addAttribute("err", "请输入正确的数量！！");
    		return list(model,session);
    	}
    	

    	Component component=new Component();
       
    	component.setName(name);
    	component.setPrice(doublePrice);
    	component.setQuantity(intQuantity);
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

