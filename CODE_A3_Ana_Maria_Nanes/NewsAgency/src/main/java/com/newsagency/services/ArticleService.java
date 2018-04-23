package com.newsagency.services;

import com.newsagency.data.entities.Article;
import com.newsagency.data.entities.Writer;
import com.newsagency.data.repositories.ArticleRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
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

    public List<Article> getRelated(String title)
    {
        List<Article> foundArticles = new ArrayList<>();
        List<Article> allArticles  = getAll();
        String[] title_words = title.split(" ");
        for(Article article: allArticles)
        {
            for(String str: title_words)
            {
                if(article.getTitle().toLowerCase().contains(str.toLowerCase()))
                {
                    foundArticles.add(article);
                    break;
                }
            }
        }
        return foundArticles;
    }

}
