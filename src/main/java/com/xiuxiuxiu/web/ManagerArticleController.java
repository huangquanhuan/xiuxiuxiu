package com.xiuxiuxiu.web;


import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.xiuxiuxiu.model.Article;
import com.xiuxiuxiu.model.Manager;
import com.xiuxiuxiu.model.ReturnData;
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
    public String list(Model model) {
    	List<Article> articleList=articleService.getArticleList();
    	model.addAttribute("articleList", articleList);
        
        return "/manager/Marticle";
    }

    @RequestMapping("/manager/DeleteArticle")
	public String delete(Model model,@RequestParam("id") int id) {
    	System.out.println("id => "+id);
    	articleService.delete(id);
		return "redirect:/manager/Marticle";
	}
    
    @RequestMapping("/manager/UpdateArticle")
	public String update(Model model,@RequestParam("id") int id,@RequestParam("title") String title,
			@RequestParam("text") String text) {
    	System.out.println("id => "+id);
    	System.out.println("title => "+title);
    	System.out.println("text => "+text);
        String time=df.format(day);
        System.out.println("time =>"+time);
    	Article article=articleService.findArticleById(id);

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
   	public String Add(Model model,HttpSession session
   			) {      	

        String time=df.format(day);
        System.out.println("time =>"+time);
       
    	Manager manager=(Manager)session.getAttribute("administrator");
    	List<Article> mylist=articleService.findByManager(manager);
    	model.addAttribute("articleList", mylist);
   		return "/manager/Marticle";
   	}
    
    /**
	 * 后端分页
	 * */
	@RequestMapping("/manager/getAllArticle")
	@ResponseBody
    public ReturnData<Article> findAllNoQuery(Mode mode,@RequestParam(value="offset",defaultValue="0") Integer offset,
    		@RequestParam(value="limit",defaultValue="5") Integer limit) {
		int sum	= articleService.findAll().size();
		Page<Article> datas = articleService.findAll(offset, limit);
		List<Article> listDatas = datas.getContent(); 
		return new ReturnData<Article>(sum,listDatas);
    }
}

