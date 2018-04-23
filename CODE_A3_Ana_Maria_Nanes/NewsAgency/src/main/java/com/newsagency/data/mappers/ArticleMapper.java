package com.newsagency.data.mappers;

import com.newsagency.data.entities.Article;

public class ArticleMapper {

    private int articleid;
    private String title;
    private String author;
    private String articleAbstract;
    private String body;

    public ArticleMapper(Article article) {
        this.articleid = article.getArticleid();
        this.title = article.getTitle();
        this.author = article.getAuthor().getName();
        this.articleAbstract = article.getArticleAbstract();
        this.body = article.getBody();
    }

    public int getArticleid() {
        return articleid;
    }

    public void setArticleid(int articleid) {
        this.articleid = articleid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getArticleAbstract() {
        return articleAbstract;
    }

    public void setArticleAbstract(String articleAbstract) {
        this.articleAbstract = articleAbstract;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
