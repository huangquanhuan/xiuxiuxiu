package com.xiuxiuxiu.web;

import com.xiuxiuxiu.model.Article;
import com.xiuxiuxiu.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class ArticleController {

    @Resource
    ArticleService articleService;

    @RequestMapping("/article")
    public String index() {
        return "redirect:article/articleList";
    }

    @RequestMapping("/article/articleList")
    public String list(Model model) {
        List<Article> articles = articleService.getArticleList();
        model.addAttribute("articles", articles);
        System.out.println("articles => " + articles);
        return "article/articleList";
    }

    @RequestMapping("/article/articleSearch")
    public String search(Model model , @RequestParam("searchInfo") String searchInfo) {
    	try {
    		Article article = articleService.findArticleByTitle(searchInfo);
    		model.addAttribute("article", article);
//    		System.out.println("找到文章：article.id => " + article.getId());
			if(article==null)
				model.addAttribute("message","未找到相关文章！");
		} catch (Exception e) {
			model.addAttribute("err", "抱歉，查找文章失败！");
			e.printStackTrace();
		}
        return "article/ArticleSearch";
    }
    
    @RequestMapping("/article/articleDetail")
    public String getDetail(@RequestParam Integer id, Model model){
        System.out.println("id => "+id);
        Article article = articleService.findArticleById(id);
        System.out.println(article);
        model.addAttribute("article",article);
        return "article/articleDetail";
    }
}

