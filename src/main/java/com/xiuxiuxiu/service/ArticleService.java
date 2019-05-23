package com.xiuxiuxiu.service;

import com.xiuxiuxiu.model.Article;

import java.util.List;

public interface ArticleService {

    public List<Article> getArticleList();

    public Article findArticleById(int id);

    public void save(Article article);

    public void edit(Article article);

    public void delete(int id);


}
