package com.newsagency.resources;

import com.newsagency.entities.Article;
import com.newsagency.observer.Observable;
import com.newsagency.observer.Observer;
import com.newsagency.services.ArticleService;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;


@Component
public class ArticleStorage implements Observable{

    private List<Observer> readers ;

    private List<Article> allArticles;

    @Inject
    ArticleService articleService;

    public void init()
    {
        readers = new ArrayList<>();
        allArticles = new ArrayList<>();
    }

    @Override
    public void addObserver(Observer o)
    {
            readers.add(o);
    }

    @Override
    public void removeObserver(Observer o)
    {
            readers.remove(o);
    }

    public List<Article> getAllArticles() {
        return allArticles;
    }

    public void setAllArticles(List<Article> allAricles) {
        this.allArticles = allAricles;
    }

    public void notifyObservers()
    {
        setAllArticles(articleService.getAll());
        for(Observer obs: readers)
            obs.update(allArticles);
    };

}
