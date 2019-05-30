package com.xiuxiuxiu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.xiuxiuxiu.model.Article;
import com.xiuxiuxiu.model.Manager;

public interface ArticleRepository extends JpaRepository<Article, Integer> {

    Article findById(int id);
    
    List<Article> findByTitleLike(String title);
    
    List<Article> findByTextLike(String text);

    void deleteById(int id);

	Article findBytitle(String title);

	List<Article> findByManager(Manager manager);


}