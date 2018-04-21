package com.newsagency.repositories;

import com.newsagency.entities.Article;
import com.newsagency.entities.Writer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Integer> {

    public List<Article> findAll();

    public Article findByArticleid(int id);

    public Article findByTitle(String title);

    public List<Article> findByAuthor(Writer author);

    public Article save(Article article);

    public void delete(Article article);

    //public void deleteByArticleId(int id);
}
