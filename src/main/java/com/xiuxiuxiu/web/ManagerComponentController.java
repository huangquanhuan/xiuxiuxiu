package com.xiuxiuxiu.web;

/*
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
	ArticleService compontentService;
	
	@Resource
	ManagerService managerService;
	
    Date day=new Date();    

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	
    @RequestMapping("/Mcompontent")
    public String index() {
        return "redirect:/manager/Mcompontent";
    }

    @RequestMapping("/manager/Mcompontent")
    public String list(Model model) {
    	List<Article> compontentList=compontentService.getArticleList();
    	model.addAttribute("compontentList", compontentList);
        
        return "/manager/Mcompontent";
    }

    @RequestMapping("/manager/DeleteComponent")
	public String delete(Model model,@RequestParam("id") int id) {
    	System.out.println("id => "+id);
    	compontentService.delete(id);
		return "redirect:/manager/Mcompontent";
	}
    
    @RequestMapping("/manager/UpdateComponent")
	public String update(Model model,@RequestParam("id") int id,
			@RequestParam("name") String name,@RequestParam("price") Double price
    ,@RequestParam("type") String type,@RequestParam("quantity") int quantity){
    	System.out.println("id => "+id);
    	System.out.println("title => "+title);
    	System.out.println("text => "+text);
        String time=df.format(day);
        System.out.println("time =>"+time);
    	Article compontent=compontentService.findArticleById(id);

    	compontent.setText(text);
    	compontent.setTitle(title);
    	compontent.setTime(time);
    	compontentService.edit(compontent);
		return "redirect:/manager/Mcompontent";
	}
    
    @RequestMapping("/manager/AddComponent")
   	public String Add(Model model,
   			@RequestParam("name") String name,@RequestParam("price") Double price
   		    ,@RequestParam("type") String type,@RequestParam("quantity") int quantity){
       	
       	System.out.println("title => "+title);
       	System.out.println("text => "+text);
        String time=df.format(day);
        System.out.println("time =>"+time);
        Article compontent=new Article();
       
    	Manager manager=(Manager)session.getAttribute("administrator");
    	System.out.println(manager.getName());
    	manager.setName(manager.getName());
    	compontent.setManager(manager);
    	compontent.setText(text);
    	compontent.setTitle(title);
    	compontent.setTime(time);
    	compontentService.save(compontent);
   		return "redirect:/manager/Mcompontent";
   	}
    
    @RequestMapping("/manager/MyArticle")
   	public String Add(Model model,HttpSession session
   			) {      	

        String time=df.format(day);
        System.out.println("time =>"+time);
        Article compontent=new Article();
       
    	Manager manager=(Manager)session.getAttribute("administrator");
    	List<Article> mylist=compontentService.findByManager(manager);
    	model.addAttribute("compontentList", mylist);
   		return "/manager/Mcompontent";
   	}
}
*/
