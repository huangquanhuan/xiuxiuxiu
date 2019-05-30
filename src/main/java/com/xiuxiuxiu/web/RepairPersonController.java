package com.xiuxiuxiu.web;


import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.xiuxiuxiu.model.Article;
import com.xiuxiuxiu.model.Manager;
import com.xiuxiuxiu.model.ReturnData;
import com.xiuxiuxiu.service.ArticleService;
import com.xiuxiuxiu.service.ManagerService;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import javax.annotation.Resource;

@Controller
public class RepairPersonController {

	@Resource
	ManagerService managerService;

	 @Resource
	    ArticleService articleService;
    @RequestMapping("/person")
    public String index() {
        return "redirect:/manager/person";
    }

    @RequestMapping("/manager/person")
    public String list(Model model) {
    	List<Manager> managerList=managerService.getManagerList();
    	model.addAttribute("managerList", managerList);
        
        return "/manager/RepairPerson";
    }

    @RequestMapping("/manager/AddPerson")
	public String delete(Model model,@RequestParam("name") String name,
			@RequestParam("password1") String password1,@RequestParam("password2") String password2,
			@RequestParam("accesslist") String access_level,@RequestParam("phone_number") String phone_number
			,@RequestParam("email") String email,@RequestParam("address") String address) {
    	
    	System.out.println("name => "+name);
    	System.out.println("password1 => "+password1);
    	System.out.println("password2 => "+password2);
    	System.out.println("address => "+address);
    	System.out.println("access_level => "+access_level);
    	System.out.println("phone_number => "+phone_number);
    	System.out.println("email => "+email);
    	Manager manager=new Manager();
    	manager.setName(name);
    	manager.setPassword(password2);
    	manager.setAccessLevel(Integer.parseInt(access_level));
    	manager.setAddress(address);
    	manager.setPhoneNumber(phone_number);
    	manager.setEmail(email);
    	managerService.save(manager);
		return "redirect:/manager/person";
	}
    
    @RequestMapping("/manager/UpdatePerson")
	public String update(Model model,@RequestParam("id") int id,@RequestParam("name") String name,
			@RequestParam("password1") String password1,@RequestParam("password2") String password2,
			@RequestParam("accesslist") String access_level,@RequestParam("phone_number") String phone_number
			,@RequestParam("email") String email,@RequestParam("address") String address){
    	System.out.println("id => "+id);
    	System.out.println("name => "+name);
    	System.out.println("password1 => "+password1);
    	System.out.println("password2 => "+password2);
    	System.out.println("address => "+address);
    	System.out.println("access_level => "+access_level);
    	System.out.println("phone_number => "+phone_number);
    	System.out.println("email => "+email);
    	Manager manager=managerService.findManagerById(id);
    	manager.setName(name);
    	manager.setPassword(password2);
    	manager.setAccessLevel(Integer.parseInt(access_level));
    	manager.setAddress(address);
    	manager.setPhoneNumber(phone_number);
    	manager.setEmail(email);
    	managerService.edit(manager);
		return "redirect:/manager/person";
	}
    
    @RequestMapping("/manager/DeletePerson")
	public String update(Model model,@RequestParam("id") int id){
    	System.out.println("id => "+id);
    //这里还需要判断改管理员是否发表过文章，否则不能删除
    	if(articleService.findArticleById(id)!=null);
    		model.addAttribute("err", "该管理发表过文章，不能删除，请先删除文章");
    	managerService.delete(id);
		return "redirect:/manager/person";
	}
    
    /**
	 * 后端分页
	 * */
	@RequestMapping("/manager/getAll")
	@ResponseBody
    public ReturnData<Article> findAllNoQuery(Mode mode,@RequestParam(value="offset",defaultValue="0") Integer offset,
    		@RequestParam(value="limit",defaultValue="5") Integer limit) {
		int sum	= articleService.findAll().size();
		Page<Article> datas = articleService.findAll(offset, limit);
		List<Article> listDatas = datas.getContent(); 
		return new ReturnData<Article>(sum,listDatas);
    }
}

