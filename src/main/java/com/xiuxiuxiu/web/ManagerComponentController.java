package com.xiuxiuxiu.web;

<<<<<<< HEAD
import org.springframework.data.domain.Page;
=======

>>>>>>> branch 'master' of https://github.com/huangquanhuan/xiuxiuxiu
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.xiuxiuxiu.model.Component;
import com.xiuxiuxiu.model.Component;
import com.xiuxiuxiu.model.ReturnData;
=======

import com.xiuxiuxiu.model.Component;

>>>>>>> branch 'master' of https://github.com/huangquanhuan/xiuxiuxiu
import com.xiuxiuxiu.service.ComponentService;


import javax.annotation.Resource;

import java.util.List;

@Controller
//@RestController
public class ManagerComponentController {

	@Resource
	ComponentService componentService;
<<<<<<< HEAD
	  
=======
>>>>>>> branch 'master' of https://github.com/huangquanhuan/xiuxiuxiu
	
    @RequestMapping("/Mcomponent")
    public String index() {
<<<<<<< HEAD
=======
    	System.out.println("fansile");
>>>>>>> branch 'master' of https://github.com/huangquanhuan/xiuxiuxiu
        return "redirect:/manager/Mcomponent";
    }

    @RequestMapping("/manager/Mcomponent")
    public String list(Model model) {
<<<<<<< HEAD
    	/*List<Component> componentList=componentService.getComponentList();
    	model.addAttribute("componentList", componentList);
        System.out.println("dsaf s ");
        return "/manager/Mcomponent";*/
    	return "/manager/Mcomponent2";
=======
    	List<Component> componentList=componentService.getComponentList();
    	model.addAttribute("componentList", componentList);
        
        return "/manager/Mcomponent";
>>>>>>> branch 'master' of https://github.com/huangquanhuan/xiuxiuxiu
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
<<<<<<< HEAD
    	System.out.println("id => "+id);
    	System.out.println("name => "+name);
    	System.out.println("price => "+price);
    	System.out.println("type => "+type);
    	System.out.println("quantity => "+quantity);
        
    	Component component=componentService.findComponentById(id);
=======
    	System.out.println("name => "+name);
    	System.out.println("type => "+type);
    	System.out.println("quantity => "+quantity);
    
        System.out.println("price =>"+price);
        Component component=componentService.findComponentById(id);
>>>>>>> branch 'master' of https://github.com/huangquanhuan/xiuxiuxiu

<<<<<<< HEAD
    	component.setName(name);
    	component.setPrice(price);
    	component.setQuantity(quantity);
    	component.setType(type);
=======
        component.setName(name);
        component.setPrice(price);
        component.setQuantity(quantity);
        component.setType(type);
>>>>>>> branch 'master' of https://github.com/huangquanhuan/xiuxiuxiu
    	componentService.edit(component);
		return "redirect:/manager/Mcomponent";
	}
    
    @RequestMapping("/manager/AddComponent")
   	public String Add(Model model,
   			@RequestParam("name") String name,@RequestParam("price") Double price
   		    ,@RequestParam("type") String type,@RequestParam("quantity") int quantity){
       	
<<<<<<< HEAD
    	/*System.out.println("name => "+name);
    	System.out.println("price => "+price);
    	System.out.println("type => "+type);
    	System.out.println("quantity => "+quantity);*/
        
    	Component component=new Component();

    	component.setName(name);
    	component.setPrice(price);
    	component.setQuantity(quantity);
    	component.setType(type);
    	componentService.save(component);
   		return "redirect:/manager/Mcomponent";
=======
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
>>>>>>> branch 'master' of https://github.com/huangquanhuan/xiuxiuxiu
   	}
    
<<<<<<< HEAD
    /**
	 * 后端分页
	 * */
	@RequestMapping("/manager/getAllComponent")
	@ResponseBody
    public ReturnData<Component> findAllNoQuery(Mode mode,@RequestParam(value="offset",defaultValue="0") Integer offset,
    		@RequestParam(value="limit",defaultValue="5") Integer limit) {
		int sum	= componentService.findAll().size();
		Page<Component> datas = componentService.findAll(offset, limit);
		List<Component> listDatas = datas.getContent(); 
		return new ReturnData<Component>(sum,listDatas);
    }
}
=======
   
}

>>>>>>> branch 'master' of https://github.com/huangquanhuan/xiuxiuxiu
