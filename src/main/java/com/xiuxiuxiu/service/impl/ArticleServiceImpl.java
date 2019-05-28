package com.xiuxiuxiu.service.impl;

import com.xiuxiuxiu.model.Article;
import com.xiuxiuxiu.model.Manager;
import com.xiuxiuxiu.repository.ArticleRepository;
import com.xiuxiuxiu.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService{

	@Autowired
    private ArticleRepository articleRepository;

    @Override
    public List<Article> getArticleList() {
        return articleRepository.findAll();
    }

    @Override
    public Article findArticleById(int id) {
        return articleRepository.findById(id);
    }

    @Override
    public Article findArticleByTitle(String title) {
        return articleRepository.findBytitle(title);
    }
    
    @Override
    public List<Article> findByManager(Manager manager) {
        return articleRepository.findByManager(manager);
    }
    
    @Override
    public void save(Article article) {
        articleRepository.save(article);
    }

    @Override
    public void edit(Article article) {
        articleRepository.save(article);
    }

    @Override
    public void delete(int id) {
        articleRepository.deleteById(id);
    }
    
    
}


