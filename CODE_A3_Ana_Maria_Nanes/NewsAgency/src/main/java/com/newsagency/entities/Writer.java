package com.newsagency.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
@Table(name = "writers")
public class Writer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int writerid;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "popularity", nullable = false)
    private int popularity;

    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Article> articles;

    public Writer(){}

    public Writer(String name, String username, String password ){
        this.name = name;
        this.username = username;
        this.password = password;
        this.popularity = new Random().nextInt(10) + 1;
        articles = new ArrayList<Article>();
    }

    public int getWriterid() {
        return writerid;
    }

    public void setWriterid(int writerid) {
        this.writerid = writerid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
