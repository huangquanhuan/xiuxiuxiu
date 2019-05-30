package com.xiuxiuxiu.service.impl;

import com.xiuxiuxiu.model.Article;
import com.xiuxiuxiu.model.Manager;
import com.xiuxiuxiu.model.Student;
import com.xiuxiuxiu.repository.ArticleRepository;
import com.xiuxiuxiu.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitterReturnValueHandler;

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
    public List<Article> findByTitleLike(String title){
    	return articleRepository.findByTitleLike(title);
    }
    
    @Override 
    public List<Article> findByTextLike(String text){
    	return articleRepository.findByTextLike(text);
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
    
    @Override
	public Page<Article> findAll(int pageNum, int pageSize) {
		Sort sort = new Sort(Sort.Direction.DESC, "id");  //降序
	    Pageable pageable = PageRequest.of(pageNum,pageSize,sort); 
	    Page<Article> Pages = articleRepository.findAll(pageable);
	    return Pages;
	}

	@Override
	public List<Article> findAll() {
		return articleRepository.findAll();
	}
	
}


