package com.newsagency.data.observer;

import com.newsagency.data.entities.Article;

import java.util.List;

public interface Observer
{
    public void update(List<Article> articles);
}
