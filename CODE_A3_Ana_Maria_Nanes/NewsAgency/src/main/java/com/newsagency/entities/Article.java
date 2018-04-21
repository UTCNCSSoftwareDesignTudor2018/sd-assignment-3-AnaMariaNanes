package com.newsagency.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int articleid;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "articleAbstract", nullable = false)
    private String articleAbstract;

    @Column(name = "body", nullable = false)
    private String Body;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "writerid",nullable = false)
    private Writer author;

    public Article()
    {

    }

    public Article(String title, String articleAbstract, String body, Writer author) {
        this.title = title;
        this.articleAbstract = articleAbstract;
        Body = body;
        this.author = author;
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

    public String getArticleAbstract() {
        return articleAbstract;
    }

    public void setArticleAbstract(String articleAbstract) {
        this.articleAbstract = articleAbstract;
    }

    public String getBody() {
        return Body;
    }

    public void setBody(String body) {
        Body = body;
    }

    public Writer getAuthor() {
        return author;
    }

    public void setAuthor(Writer author) {
        this.author = author;
    }
}
