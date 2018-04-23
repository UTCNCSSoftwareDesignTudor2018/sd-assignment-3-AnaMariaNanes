package com.newsagency.observer;

import com.newsagency.entities.Article;

import java.util.List;

public interface Observer
{
    public void update(List<Article> articles);
}
