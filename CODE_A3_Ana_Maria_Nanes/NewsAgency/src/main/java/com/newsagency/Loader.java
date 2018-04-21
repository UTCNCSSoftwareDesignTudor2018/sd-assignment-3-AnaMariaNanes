package com.newsagency;

import com.newsagency.entities.Writer;
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


    public void onApplicationEvent(ContextRefreshedEvent event) {
       List<Writer> allWriters = new ArrayList<Writer>();
       allWriters = writerService.getAll();
       allWriters.stream().forEach(e -> System.out.println(e.getName()));
    }
}

