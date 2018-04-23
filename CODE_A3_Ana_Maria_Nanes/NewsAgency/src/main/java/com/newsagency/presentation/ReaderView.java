package com.newsagency.presentation;

import com.newsagency.entities.Article;
import com.newsagency.observer.Observer;
import com.newsagency.resources.ArticleStorage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.util.List;


public class ReaderView extends JFrame implements Observer {

    private JPanel contentPane;
    private JTextArea title_field;
    private JTextArea author_field;
    private JTextArea abstract_field;
    private JTextArea body_field;

    private JButton btnViewArticle = new JButton("CHOOSE ARTICLE");

    private JList list;
    private DefaultListModel<String> listModel;

    private ArticleStorage articleStorage;

    public ReaderView()
    {
        setBounds(400, 400, 784, 600);
        contentPane = new JPanel();
        setTitle("Welcome dear reader!");
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        list = new JList();
        listModel = new DefaultListModel<>();

        JLabel lblAllArticles = new JLabel("All articles:");
        lblAllArticles.setBounds(38, 13, 88, 27);
        contentPane.add(lblAllArticles);

        btnViewArticle.setBounds(31, 433, 157, 37);
        contentPane.add(btnViewArticle);

        JLabel lblReadTheArticlr = new JLabel("Read the article:");
        lblReadTheArticlr.setBounds(374, 13, 146, 37);
        contentPane.add(lblReadTheArticlr);

        title_field = new JTextArea();
        title_field.setBounds(426, 63, 116, 22);
        title_field.setEditable(false);
        contentPane.add(title_field);

        JLabel lblNewLabel = new JLabel("Title: ");
        lblNewLabel.setBounds(350, 63, 56, 16);
        contentPane.add(lblNewLabel);

        JLabel lblAuthor = new JLabel("Author:");
        lblAuthor.setBounds(350, 103, 56, 16);
        contentPane.add(lblAuthor);

        author_field = new JTextArea();
        author_field.setBounds(426, 100, 116, 22);
        author_field.setEditable(false);
        contentPane.add(author_field);

        JLabel lblAbstract = new JLabel("Abstract:");
        lblAbstract.setBounds(350, 142, 56, 25);
        contentPane.add(lblAbstract);

        abstract_field = new JTextArea();
        abstract_field.setBounds(426, 143, 285, 55);
        abstract_field.setEditable(false);
        contentPane.add(abstract_field);

        body_field = new JTextArea();
        body_field.setBounds(339, 210, 372, 210);
        body_field.setEditable(false);
        contentPane.add(body_field);
    }

    @Override
    public void update(List<Article> allArticles)
    {

        listModel.removeAllElements();
        initializeList(allArticles);
    }

    public void initializeList(List<Article> article) {
        for (Article art : article) {
            listModel.addElement(art.getTitle());
        }
    }

    public void addTheList() {

        //moved here
        list = new JList<>(listModel);
        list.setBounds(36, 53, 272, 367);
        list.setVisible(true);
        contentPane.add(list);
    }

    public void clear()
    {
       title_field.setText("");
       author_field.setText("");
       abstract_field.setText("");
       body_field.setText("");
    }

    public void setArticle(Article article)
    {
        title_field.setText(article.getTitle());
        author_field.setText(article.getAuthor().getName());
        abstract_field.setText(article.getArticleAbstract());
        body_field.setText(article.getBody());
    }

    //add action listeners
    public void addViewArticleListener(ActionListener al)
    {
        this.btnViewArticle.addActionListener(al);
    }

    public JList getList()
    {
        return this.list;
    }

    public ArticleStorage getArticleStorage() {
        return articleStorage;
    }

    public void setArticleStorage(ArticleStorage articleStorage) {
        this.articleStorage = articleStorage;
    }
}
