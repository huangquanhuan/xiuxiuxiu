package com.xiuxiuxiu.service.impl;

import com.xiuxiuxiu.model.Article;
import com.xiuxiuxiu.repository.ArticleRepository;
import com.xiuxiuxiu.service.ArticleService;

import org.aspectj.lang.annotation.Before;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.not;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertArrayEquals;

import java.util.List;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestArticleServiceImpl {
	
	@Autowired
    private ArticleServiceImpl articleServiceImpl;
	@Autowired
	private Article article;
	
    @Test
    public void getArticleListTest() {
    	String title = "哪家的固态硬盘比较好？固态硬盘选择全攻略！哪家的固态硬盘比较好？固态硬盘选择全攻略！";
    	Assert.assertThat(title, is(articleServiceImpl.getArticleList().get(0).getTitle())); 
    }

    @Test
    public void findArticleByIdTest() {
    	int id = 0;
    	String title = "哪家的固态硬盘比较好？固态硬盘选择全攻略！";
    	Assert.assertThat(title, is(articleServiceImpl.findArticleById(id).getTitle())); 
    }

    @Test
    public void saveTest() {
    	ManagerServiceImpl managerService = new ManagerServiceImpl();
    	article = new Article();
    	article.setTitle("testArticle");
    	article.setId(10086);
    	article.setText("test内容1");
    	article.setTime("未知");
    	article.setManager(managerService.findManagerById(0));
    	articleServiceImpl.save(article);
    	//Assert.assertSame(article.getTitle(), is(articleServiceImpl.findArticleById(10086).getTitle()));
    	Assert.assertThat(article.getTitle(), is(articleServiceImpl.findArticleById(10086).getTitle()));
    }

    @Test
    @Transactional
    public void editTest() {
    	article = articleServiceImpl.getArticleList().get(0);
    	int id = article.getId();
    	article.setTitle("?test?");
    	Assert.assertThat(article.getTitle(), is(articleServiceImpl.findArticleById(id).getTitle()));
    }

    @Test
    @Transactional
    public void deleteTest() {
    	article = articleServiceImpl.getArticleList().get(0);
    	int id = article.getId();
    	articleServiceImpl.delete(id);
    	Assert.assertNotEquals(null, articleServiceImpl.getArticleList().get(0));
    }
}