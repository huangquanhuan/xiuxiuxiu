package com.xiuxiuxiu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.xiuxiuxiu.model.Article;

public interface ArticleRepository extends JpaRepository<Article, Integer> {

    Article findById(int id);

    void deleteById(int id);

	Article findBytitle(String title);


}