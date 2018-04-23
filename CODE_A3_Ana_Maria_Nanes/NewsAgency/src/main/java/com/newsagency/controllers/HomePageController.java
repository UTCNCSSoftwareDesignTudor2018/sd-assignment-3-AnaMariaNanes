package com.newsagency.controllers;

import com.newsagency.validators.ArticleValidator;
import com.newsagency.validators.WriterValidator;
import com.newsagency.communication.ReaderThread;
import com.newsagency.communication.ServerThread;
import com.newsagency.entities.Article;
import com.newsagency.entities.Writer;
import com.newsagency.presentation.*;
import com.newsagency.resources.ArticleStorage;
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
    private WriterService writerService;

    @Inject
    private ArticleService articleService;

    @Inject
    private ArticleStorage articleStorage;

    @Inject
    private ArticleValidator articleValidator;

    @Inject
    private WriterValidator  writerValidator;

    private HomePageView homePageView;
    private ReaderView readerView;
    private WriterView writerView;
    private WriterLogInView writerLoginView;
    private WriterRegistrationView writerRegistrationView;
    private AllWritersView allWritersView;
    private ReadArticleView readArticleView;
    private EditArticleView editArticleView;

    private Writer writerModel;

    public void init(HomePageView homePageView)
    {
        ServerThread serverThread = new ServerThread();
        serverThread.setArticleService(articleService);
        serverThread.start();

        this.writerModel = new Writer();

        articleStorage.init();
        articleStorage.setAllArticles(articleService.getAll());

        this.homePageView = homePageView;
        this.homePageView.setVisible(true);

        this.readerView = new ReaderView();
        this.writerLoginView = new WriterLogInView();
        this.writerView = new WriterView();
        this.writerRegistrationView = new WriterRegistrationView();
        this.writerRegistrationView = new WriterRegistrationView();
        this.allWritersView = new AllWritersView();
        this.readArticleView = new ReadArticleView();
        this.editArticleView = new EditArticleView();

        homePageView.addReaderBtnListener(new ReaderViewListener());
        homePageView.addWriterBtnListener(new WriterViewListener());
        homePageView.addNewWriterBtnListener(new NewWriterViewListener());
        homePageView.addAllWritersListener(new AllWritersListener());

        writerRegistrationView.addBackListener(new BackFromRegistrationListener());
        writerRegistrationView.addRegisterListener(new RegisterListener());

        writerLoginView.addBackListener(new BackFromLogInListener());
        writerLoginView.addLogInListener(new LogInListener());

        readerView.addViewArticleListener(new ViewArticleListener());

        writerView.addInsertArticleListener(new CreateArticleListener());
        writerView.addLogOutListener(new LogOutListener());
        writerView.addModifyArticleListener(new ModifyArticleListener());
        writerView.addOpenArticleListener(new OpenArticleListener());
        writerView.addRelatedArticleListener(new RelatedArticlesListener());
        writerView.addDeleteAccountListener(new DeleteAccountListener());

        allWritersView.addBackListener(new BackFromAllWritersListener());

        readArticleView.addBackActionListener(new BackFromReadArticleListener());

        editArticleView.addBackListener(new BackFromEditArticleListener());
        editArticleView.addDeleteListener(new DeleteArticleListener());
        editArticleView.addUpdateListener(new UpdateArticleListener());
        editArticleView.addChooseListener(new ChooseArticleListener());

    }

    class ReaderViewListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

                ReaderView readerView = new ReaderView();
                List<Article> allArticles = articleService.getAll();
                readerView.initializeList(allArticles);
                readerView.setArticleStorage(articleStorage);
                articleStorage.addObserver(readerView);
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

    class AllWritersListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            homePageView.setVisible(false);

            List<Writer> allWriters = writerService.getAll();
            allWritersView.initializeList(allWriters);
            allWritersView.addTheList();
            allWritersView.setVisible(true);
        }
    }


    class NewWriterViewListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
                homePageView.setVisible(false);
                writerRegistrationView.setVisible(true);
        }
    }

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
            writerRegistrationView.setVisible(false);
            homePageView.setVisible(true);
        }
    }

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
                        writerModel= foundWriter;

                        writerLoginView.clear();
                        writerLoginView.setVisible(false);
                        writerView.setWriterModel(foundWriter);
                        writerView.setTheTitle(foundWriter.getName());
                        writerView.initializeList(null);
                        writerView.addTheList();
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

    class ViewArticleListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {

        }
    }

    class CreateArticleListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
                Article newArticle = writerView.getInputArticle();
                if(articleValidator.validateArticle(newArticle) == false)
                {
                    JOptionPane.showMessageDialog(null,
                            "The values of input fields are not valid.",
                            "NEW ARTICLE ERROR",
                            JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    articleService.insert(newArticle);
                    writerView.clearInput();
                    articleStorage.notifyObservers();
                    JOptionPane.showMessageDialog(null,
                            "The article was created.",
                            "NEW ARTICLE",
                            JOptionPane.INFORMATION_MESSAGE);


                }
        }
    }

    class LogOutListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
              writerLoginView.setVisible(true);
              writerView.setVisible(false);
        }
    }

    class ModifyArticleListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
                writerView.setVisible(false);

                List<Article > allArticles = articleService.getByAuthor(writerModel);

                 editArticleView.initializeList(allArticles);
                 editArticleView.setWriterModel(writerModel);
                 editArticleView.addTheList();
                 editArticleView.setVisible(true);
        }
    }

    class OpenArticleListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            String title = writerView.getChosenTitle();

            if(title== null || title.equals(""))
            {
                JOptionPane.showMessageDialog(null,
                        "No article selected.",
                        "ERROR AT OPENING THE ARTICLE",
                        JOptionPane.ERROR_MESSAGE);

            }
            else
            {

                Article foundArticle = articleService.getByTitle(title);
                if(foundArticle == null)
                {
                    JOptionPane.showMessageDialog(null,
                            "No valid article selected.",
                            "ERROR AT OPENING THE ARTICLE",
                            JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    writerView.setVisible(false);
                    readArticleView.setVisible(true);
                    readArticleView.setArticleModel(foundArticle);
                    readArticleView.setArticleData();
                }

            }


        }
    }

    class RelatedArticlesListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
                String title = writerView.getTitle();
                if(title == null || title.equals(""))
                {
                    JOptionPane.showMessageDialog(null,
                            "No title has been provided",
                            "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                }
                else {

                    List<Article> articles = articleService.getRelated(title);
                    writerView.initializeList(articles);
                    writerView.addTheList();
                }
        }
    }

    class DeleteAccountListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
                writerLoginView.setVisible(true);
                writerView.setVisible(false);
                writerService.delete(writerView.getWriterModel());

               JOptionPane.showMessageDialog(null,
                    "The account was deleted.",
                    "DELETE ACCOUNT",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    class BackFromAllWritersListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            allWritersView.setVisible(false);
            homePageView.setVisible(true);
        }
    }

    class BackFromReadArticleListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            writerView.clear();
            readArticleView.clear();
            readArticleView.setVisible(false);
            writerView.setVisible(true);
        }
    }

    class BackFromEditArticleListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
                    editArticleView.setVisible(false);
                    writerView.setVisible(true);
        }
    }

    class UpdateArticleListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
                try {
                    Article newArticle = editArticleView.getArticleChosen();

                    if (newArticle.getTitle().equals("") || newArticle.getArticleAbstract().equals("") || newArticle.getBody().equals("")) {

                        JOptionPane.showMessageDialog(null,
                                "Invalid article input data.",
                                "UPDATE ARTICLE ERRROR ",
                                JOptionPane.ERROR_MESSAGE);
                    } else
                    {
                    articleService.insert(newArticle);
                    editArticleView.clear();
                    articleStorage.notifyObservers();

                    JOptionPane.showMessageDialog(null,
                            "The article has been updated.",
                            "UPDATE ARTICLE ",
                            JOptionPane.INFORMATION_MESSAGE);
                     }
                }
                catch(Exception ee)
                {
                    JOptionPane.showMessageDialog(null,
                            ee.getMessage(),
                            "UPDATE ARTICLE ",
                            JOptionPane.ERROR_MESSAGE);
                }
        }
    }

    class DeleteArticleListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Object selectedArticle = editArticleView.getList().getSelectedValue();
            String articleName = (String) selectedArticle;

            if(selectedArticle == null)
            {
                JOptionPane.showMessageDialog(null,
                        "No article has been selected.",
                        "DELETE ARTICLE ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                Article foundArticle = articleService.getByTitle(articleName);
                articleService.delete(foundArticle);
                articleStorage.notifyObservers();
                List<Article> newArticles = articleService.getByAuthor(writerModel);
                editArticleView.initializeList(newArticles);

                JOptionPane.showMessageDialog(null,
                        "The article has been deleted.",
                        "DELETED ARTICLE",
                        JOptionPane.INFORMATION_MESSAGE);


            }
        }
    }

    class ChooseArticleListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Object selectedArticle = editArticleView.getList().getSelectedValue();
            String articleName = (String) selectedArticle;

            if(selectedArticle == null)
            {
                JOptionPane.showMessageDialog(null,
                        "No article has been selected.",
                        "EDIT ARTICLE ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                Article foundArticle = articleService.getByTitle(articleName);
                editArticleView.setArticleModel(foundArticle);
                editArticleView.setArticle(foundArticle);

            }
        }
    }
}
