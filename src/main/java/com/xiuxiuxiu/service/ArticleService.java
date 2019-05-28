package com.xiuxiuxiu.service;

import com.xiuxiuxiu.model.Article;
import com.xiuxiuxiu.model.Manager;

import java.util.List;

public interface ArticleService {

    public List<Article> getArticleList();

    public Article findArticleById(int id);
    
    public Article findArticleByTitle(String title);

    public List<Article> findByManager(Manager manager);
    
    public void save(Article article);

    public void edit(Article article);

    public void delete(int id);


    public Article findByTitleLink(String title);
}
