package com.newsagency.services;

import com.newsagency.entities.Article;
import com.newsagency.entities.Writer;
import com.newsagency.repositories.ArticleRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service()
public class ArticleService {

    @Inject
    ArticleRepository articleRepository;

    public List<Article> getAll()
    {
        return  articleRepository.findAll();
    }

    public Article getById(int id)
    {
        return articleRepository.findByArticleid(id);
    }

    public Article getByTitle(String title)
    {
        return articleRepository.findByTitle(title);
    }

    public List<Article> getByAuthor(Writer author)
    {
        return articleRepository.findByAuthor(author);
    }

    public Article insert(Article article)
    {
        return articleRepository.save(article);
    }

    public void delete(Article article)
    {
        articleRepository.delete(article);
    }

}
