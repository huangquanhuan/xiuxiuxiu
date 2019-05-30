package com.xiuxiuxiu.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.xiuxiuxiu.model.Article;
import com.xiuxiuxiu.model.Manager;
import com.xiuxiuxiu.model.Reservation;

public interface ArticleRepository extends JpaRepository<Article, Integer> {

    Article findById(int id);
    
    List<Article> findByTitleLike(String title);
    
    List<Article> findByTextLike(String text);

    void deleteById(int id);

	Article findBytitle(String title);
	
	public Page<Article> findAll(Pageable pageable);

	List<Article> findByManager(Manager manager);


}