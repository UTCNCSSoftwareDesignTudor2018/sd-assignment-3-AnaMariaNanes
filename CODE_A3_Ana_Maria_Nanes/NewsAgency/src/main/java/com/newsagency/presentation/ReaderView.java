package com.newsagency.presentation;

import com.newsagency.entities.Article;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.List;

public class ReaderView extends JFrame {

    private JPanel contentPane;
    private JTextField title_field;
    private JTextField author_field;
    private JTextField abstract_field;
    private JTextField body_field;

    private JButton btnBack = new JButton("BACK");
    private JButton btnViewArticle = new JButton("CHOOSE ARTICLE");
    private JButton btnAllAuthors = new JButton("All authors");

    private JList list;
    private DefaultListModel<String> listModel;

    public ReaderView()
    {
        setBounds(400, 400, 784, 600);
        contentPane = new JPanel();
        setTitle("Welcome dear reader!");
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        btnBack.setBounds(631, 515, 97, 25);
        contentPane.add(btnBack);

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

        title_field = new JTextField();
        title_field.setBounds(426, 63, 116, 22);
        contentPane.add(title_field);
        title_field.setColumns(10);

        JLabel lblNewLabel = new JLabel("Title: ");
        lblNewLabel.setBounds(350, 63, 56, 16);
        contentPane.add(lblNewLabel);

        JLabel lblAuthor = new JLabel("Author:");
        lblAuthor.setBounds(350, 103, 56, 16);
        contentPane.add(lblAuthor);

        author_field = new JTextField();
        author_field.setBounds(426, 100, 116, 22);
        contentPane.add(author_field);
        author_field.setColumns(10);

        JLabel lblAbstract = new JLabel("Abstract:");
        lblAbstract.setBounds(350, 142, 56, 25);
        contentPane.add(lblAbstract);

        abstract_field = new JTextField();
        abstract_field.setBounds(426, 143, 285, 55);
        contentPane.add(abstract_field);
        abstract_field.setColumns(10);

        body_field = new JTextField();
        body_field.setBounds(339, 210, 372, 210);
        contentPane.add(body_field);
        body_field.setColumns(10);

        btnAllAuthors.setBounds(31, 503, 159, 37);
        contentPane.add(btnAllAuthors);
    }

    public void initializeList(List<Article> article) {
        for (Article art : article) {
            listModel.addElement(art.getTitle());
        }
        list = new JList<>(listModel);

    }

    public void addTheList() {

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

    public void addBackListener(ActionListener al)
    {
        this.btnBack.addActionListener(al);
    }

    public void addViewAuthorsListener(ActionListener al)
    {
        this.btnAllAuthors.addActionListener(al);
    }

    public JList getList()
    {
        return this.list;
    }
}
