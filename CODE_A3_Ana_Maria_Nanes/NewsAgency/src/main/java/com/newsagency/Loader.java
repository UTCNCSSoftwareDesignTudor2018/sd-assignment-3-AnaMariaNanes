package com.newsagency;

import com.newsagency.controllers.HomePageController;
import com.newsagency.entities.Article;
import com.newsagency.entities.Writer;
import com.newsagency.presentation.HomePageView;
import com.newsagency.services.ArticleService;
import com.newsagency.services.WriterService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Component
public class Loader implements ApplicationListener<ContextRefreshedEvent>{

    @Inject
    private ArticleService articleService;

    @Inject
    private WriterService writerService;

    @Inject
    HomePageController homePageController;

    @Inject
    HomePageView homePageView;


    public void onApplicationEvent(ContextRefreshedEvent event) {
       homePageController.init(homePageView);
    }
}


