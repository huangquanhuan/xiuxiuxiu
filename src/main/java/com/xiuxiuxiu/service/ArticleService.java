package com.xiuxiuxiu.service;

import com.xiuxiuxiu.model.Article;
import com.xiuxiuxiu.model.Manager;
import com.xiuxiuxiu.model.Student;

import java.util.List;

import org.springframework.data.domain.Page;

public interface ArticleService {

    public List<Article> getArticleList();

    public Article findArticleById(int id);
    
    public Article findArticleByTitle(String title);
    
    public void save(Article article);

    public void edit(Article article);

    public void delete(int id);

	public List<Article> findByManager(Manager manager);

<<<<<<< HEAD
    public Article findByTitleLink(String title);

	public Page<Article> findAll(int pageNum, int pageSize);
	
	public List<Article> findAll();
=======

    public List<Article> findByTitleLike(String title);

    List<Article> findByTextLike(String text);
>>>>>>> branch 'master' of https://github.com/huangquanhuan/xiuxiuxiu
}
