package com.xiuxiuxiu.web;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.xiuxiuxiu.model.Article;
import com.xiuxiuxiu.model.Manager;
import com.xiuxiuxiu.service.ArticleService;
import com.xiuxiuxiu.service.ManagerService;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ManagerArticleController {

	@Resource
	ArticleService articleService;
	
	@Resource
	ManagerService managerService;
	
    Date day=new Date();    

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	
    @RequestMapping("/Marticle")
    public String index() {
        return "redirect:/manager/Marticle";
    }

    @RequestMapping("/manager/Marticle")
    public String list(Model model,HttpSession session) {
    	if(session.getAttribute("administrator")==null)
    		return "redirect:/manager";
    	List<Article> articleList=articleService.getArticleList();
    	model.addAttribute("articleList", articleList);
        
        return "/manager/Marticle";
    }

    @RequestMapping("/manager/DeleteArticle")
	public String delete(Model model,@RequestParam("id") String id) {
    	System.out.println("id => "+id);
    	articleService.delete(Integer.parseInt(id));
		return "redirect:/manager/Marticle";
	}
    
    @RequestMapping("/manager/UpdateArticle")
	public String update(Model model,@RequestParam("id") String id,@RequestParam("title") String title,
			@RequestParam("text") String text) {
    	System.out.println("id => "+id);
    	System.out.println("title => "+title);
    	System.out.println("text => "+text);
        String time=df.format(day);
        System.out.println("time =>"+time);
    	Article article=articleService.findArticleById(Integer.parseInt(id));

    	article.setText(text);
    	article.setTitle(title);
    	article.setTime(time);
    	articleService.edit(article);
		return "redirect:/manager/Marticle";
	}
    
    @RequestMapping("/manager/AddArticle")
   	public String Add(Model model,HttpSession session,
   			@RequestParam("title") String title,@RequestParam("text") String text) {
       
       	
       	System.out.println("title => "+title);
       	System.out.println("text => "+text);
        String time=df.format(day);
        System.out.println("time =>"+time);
        Article article=new Article();
       
    	Manager manager=(Manager)session.getAttribute("administrator");
    	System.out.println(manager.getName());
    	manager.setName(manager.getName());
    	article.setManager(manager);
    	article.setText(text);
    	article.setTitle(title);
    	article.setTime(time);
    	articleService.save(article);
   		return "redirect:/manager/Marticle";
   	}
    
    @RequestMapping("/manager/MyArticle")
   	public String myarticle(Model model,HttpSession session
   			) {      	
        String time=df.format(day);
        System.out.println("time =>"+time);      
    	Manager manager=(Manager)session.getAttribute("administrator");
    	List<Article> mylist=articleService.findByManager(manager);
    	if(mylist==null || mylist.size()==0)
    	{
    		model.addAttribute("message","该管理员未发表过文章");
    	}
    	model.addAttribute("articleList", mylist);
   		return list(model, session);
   	}
}

