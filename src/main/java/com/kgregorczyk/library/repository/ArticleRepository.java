package com.kgregorczyk.library.repository;

import com.kgregorczyk.library.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ArticleRepository extends JpaRepository<Article, Long> {

}
