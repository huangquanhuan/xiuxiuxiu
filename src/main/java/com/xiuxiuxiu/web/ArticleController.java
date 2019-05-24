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
        return "/article/articleList";
    }

    @RequestMapping("/article/articleDetail")
    public String getDetail(@RequestParam Integer id, Model model){
        System.out.println("id => "+id);
        Article article = articleService.findArticleById(id);
        System.out.println(article);
        model.addAttribute("article",article);
        return "/article/articleDetail";
    }
}

