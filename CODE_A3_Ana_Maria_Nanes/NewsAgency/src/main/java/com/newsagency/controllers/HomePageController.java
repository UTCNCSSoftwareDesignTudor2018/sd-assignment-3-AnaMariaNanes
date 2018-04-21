package com.newsagency.controllers;

import com.newsagency.WriterValidator;
import com.newsagency.communication.ReaderThread;
import com.newsagency.communication.ServerThread;
import com.newsagency.entities.Article;
import com.newsagency.entities.Writer;
import com.newsagency.presentation.*;
import com.newsagency.services.ArticleService;
import com.newsagency.services.WriterService;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

@Component
public class HomePageController {

    @Inject
    WriterValidator  writerValidator;

    @Inject
    WriterService writerService;

    @Inject
    ArticleService articleService;

    private HomePageView homePageView;
    private ReaderView readerView;
    private WriterView writerView;
    private WriterLogInView writerLoginView;
    private WriterRegistrationView writerRegistrationView;
    private AllWritersView allWritersView;


    public void init(HomePageView homePageView)
    {
        //start the server
        ServerThread serverThread = new ServerThread();
        serverThread.setArticleService(articleService);
        serverThread.start();

        this.homePageView = homePageView;
        this.homePageView.setVisible(true);

        this.readerView = new ReaderView();
        this.writerLoginView = new WriterLogInView();
        this.writerView = new WriterView();
        this.writerRegistrationView = new WriterRegistrationView();
        this.writerRegistrationView = new WriterRegistrationView();
        this.allWritersView = new AllWritersView();

        homePageView.addReaderBtnListener(new ReaderViewListener());
        homePageView.addWriterBtnListener(new WriterViewListener());
        homePageView.addNewWriterBtnListener(new NewWriterViewListener());

        writerRegistrationView.addBackListener(new BackFromRegistrationListener());
        writerRegistrationView.addRegisterListener(new RegisterListener());

        writerLoginView.addBackListener(new BackFromLogInListener());
        writerLoginView.addLogInListener(new LogInListener());

        readerView.addBackListener(new BackFromReaderListener());
        readerView.addViewArticleListener(new ViewArticleListener());
        readerView.addViewAuthorsListener(new ViewAllAuthors());

    }

    // ----------------------------- actions listeners for HomePageView

    class ReaderViewListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

                ReaderView readerView = new ReaderView();

                //set the articles
                List<Article> allArticles = articleService.getAll();
                readerView.initializeList(allArticles);
                readerView.addTheList();
                readerView.setVisible(true);

                //create the client
                ReaderThread readerThread = new ReaderThread();
                readerThread.setReaderView(readerView);
                readerThread.setWriterService(writerService);
                readerThread.setHomePageView(homePageView);
                readerThread.start();
                readerThread.getHomePageView().addClient();
                System.out.println("Current number of clients:  " + homePageView.getTotalClients() );
        }
    }

    class WriterViewListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
                homePageView.setVisible(false);
                writerLoginView.setVisible(true);
        }
    }

    class NewWriterViewListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
                homePageView.setVisible(false);
                writerRegistrationView.setVisible(true);
        }
    }

    // --------------------------- actions listeners for WriterRegistrationView

    class RegisterListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Writer newWriter = writerRegistrationView.getInputWriter();
            if(writerValidator.validateWriter(newWriter))
            {
                    writerService.insert(newWriter);
                    JOptionPane.showMessageDialog(null,
                        "You have been registered as a writer!",
                        "Registration performed",
                        JOptionPane.INFORMATION_MESSAGE);

                    homePageView.setVisible(true);
                    writerRegistrationView.clearInput();
                    writerRegistrationView.setVisible(false);
            }
            else
            {
                JOptionPane.showMessageDialog(null,
                        "Invalid input field values",
                        "Registration error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    class BackFromRegistrationListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            writerRegistrationView.setVisible(true);
            homePageView.setVisible(true);
        }
    }

    // --------------------------- actions listeners for WriterLogInView

    class BackFromLogInListener implements ActionListener {
          public void actionPerformed(ActionEvent e)
          {
              writerLoginView.clear();
              writerLoginView.setVisible(false);
              homePageView.setVisible(true);
          }
    }

    class LogInListener implements ActionListener {
        public void actionPerformed(ActionEvent e)
        {
            String username = writerLoginView.getUsername();
            String password = writerLoginView.getPassword();

            Writer foundWriter = writerService.getByUsername(username);

            if(foundWriter != null)
            {
                if(foundWriter.getPassword().equals(password))
                {
                        writerLoginView.clear();
                        writerLoginView.setVisible(false);
                        writerView.setWriterModel(foundWriter);
                        writerView.setTheTitle();
                        writerView.setVisible(true);

                }
                else
                {
                        JOptionPane.showMessageDialog(null,
                            "The provided password is not correct",
                            "LOG IN ERROR",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null,
                        "No writer with the provided username exists.",
                        "LOG IN ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }


        }
    }

    // ------------------------------------------- action listeners for ReaderView

    class BackFromReaderListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
                readerView.setVisible(false);
                homePageView.setVisible(true);
        }
    }

    class ViewArticleListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {

        }
    }

    class ViewAllAuthors implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            readerView.setVisible(false);
            allWritersView.setVisible(true);
        }
    }

}
