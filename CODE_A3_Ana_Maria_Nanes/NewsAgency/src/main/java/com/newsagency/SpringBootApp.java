package com.newsagency;

import com.newsagency.controllers.HomePageController;
import com.newsagency.presentation.HomePageView;
import com.newsagency.services.ArticleService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootApp {

    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringBootApp.class)
                .headless(false)
                .run(args);
    }

    @Bean
    public HomePageView createHomePageView() {
        return new HomePageView();
    }

    @Bean
    public HomePageController createHomePageController() {
        return new HomePageController();
    }

    @Bean
    public WriterValidator createWriterValidator()
    {
        return new WriterValidator();
    }

    @Bean
    public ArticleService createArticleService()
    {
        return new ArticleService();
    }

}
