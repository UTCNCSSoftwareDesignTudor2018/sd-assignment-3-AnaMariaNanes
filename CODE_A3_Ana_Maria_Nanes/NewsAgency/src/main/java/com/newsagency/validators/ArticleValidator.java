package com.newsagency.validators;

import com.newsagency.entities.Article;
import com.newsagency.services.ArticleService;

import javax.inject.Inject;
import java.util.List;

public class ArticleValidator {

    @Inject
    ArticleService articleService;

    public ArticleValidator() {
    }

    public boolean validateArticle(Article article)
    {
        if(isTitleUnique(article.getTitle()) ==  false)
            return false;
        if(article.getArticleAbstract().equals(""))
            return false;
        if(article.getArticleAbstract().equals(""))
            return false;
        if(article.getBody().equals(""))
            return false;
        if(article.getAuthor() == null)
            return false;
        return true;
    }

    private boolean isTitleUnique(String title) {
        List<Article> allArticles = articleService.getAll();
        for(Article art : allArticles)
        {
            if(art.getTitle().equals(title))
            {
                return false;
            }
        }
        return true;
    }
}
