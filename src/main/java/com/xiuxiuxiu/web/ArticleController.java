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
		return "/article/articleList";
	}

	@RequestMapping("/article/articleSearch")
	public String search(Model model, @RequestParam("searchInfo") String searchInfo) {
		
		model.addAttribute("searchInfo",searchInfo);
		try {
			System.out.println("查找"+searchInfo+"相关的文章：");
			List<Article> articles = articleService.findByTitleLike("%"+searchInfo+"%");
			model.addAttribute("articles", articles);

			if (articles.size() < 1) {
				model.addAttribute("message", "未找到"+searchInfo+"的相关文章！");
				System.out.println("未找到"+searchInfo+"的相关文章！");
			}
			else {
				for (Article article : articles) {
					System.out.println("找到文章："+article.getTitle());
				}
			}

		} catch (Exception e) {
			model.addAttribute("err", "抱歉，查找文章失败！");
			e.printStackTrace();
		}
		return "/article/articleList";
	}

	@RequestMapping("/article/articleDetail")
	public String getDetail(@RequestParam Integer id, Model model) {
		System.out.println("查看id为" + id + "的文章");
		Article article = articleService.findArticleById(id);
		model.addAttribute("article", article);
		return "/article/articleDetail";
	}
}
