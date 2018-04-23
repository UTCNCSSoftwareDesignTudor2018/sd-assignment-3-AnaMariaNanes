package com.newsagency.presentation;

import com.newsagency.entities.Article;
import com.newsagency.entities.Writer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.util.List;

public class EditArticleView extends JFrame {

    private JPanel contentPane;
    private JButton btnBack = new JButton("BACK");

    private JTextArea body_area;
    private JTextArea abstract_area;
    private JTextField title_field;
    private JButton btnDeleteArticle = new JButton("Delete Article");
    private JButton btnUpdate = new JButton("Update");
    private JButton btnChooseToEdit = new JButton("Choose to edit");

    private JList list;
    private DefaultListModel<String> listModel;

    private Writer writerModel;
    private Article articleModel;

    public EditArticleView()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300, 300, 785, 575);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        btnBack.setBounds(658, 490, 97, 25);
        contentPane.add(btnBack);

        list = new JList();
        listModel = new DefaultListModel<>();

        JLabel lblTheseAreYour = new JLabel("These are your articles:");
        lblTheseAreYour.setBounds(27, 23, 179, 16);
        contentPane.add(lblTheseAreYour);

        btnDeleteArticle.setBounds(38, 436, 115, 25);
        contentPane.add(btnDeleteArticle);

        JLabel lblTitle = new JLabel("Title");
        lblTitle.setBounds(417, 53, 56, 16);
        contentPane.add(lblTitle);

        title_field = new JTextField();
        title_field.setBounds(499, 50, 231, 22);
        contentPane.add(title_field);
        title_field.setColumns(10);

        JLabel lblAbstract = new JLabel("Abstract");
        lblAbstract.setBounds(417, 96, 56, 16);
        contentPane.add(lblAbstract);

        abstract_area = new JTextArea();
        abstract_area.setBounds(499, 93, 231, 85);
        contentPane.add(abstract_area);

        body_area = new JTextArea();
        body_area.setBounds(417, 193, 317, 224);
        contentPane.add(body_area);

        list = new JList();
        listModel = new DefaultListModel<>();

        btnUpdate.setBounds(427, 436, 97, 25);
        contentPane.add(btnUpdate);

        btnChooseToEdit.setBounds(36, 474, 117, 25);
        contentPane.add(btnChooseToEdit);
    }

    public void addBackListener(ActionListener al)
    {
        this.btnBack.addActionListener(al);
    }

    public void addDeleteListener(ActionListener al)
    {
        this.btnDeleteArticle.addActionListener(al);
    }

    public void addUpdateListener(ActionListener al)
    {
        this.btnUpdate.addActionListener(al);
    }

    public void addChooseListener(ActionListener al)
    {
        this.btnChooseToEdit.addActionListener(al);
    }

    public void initializeList(List<Article> article) {
        listModel.removeAllElements();
        for (Article art : article) {
            listModel.addElement(art.getTitle());
        }
    }

    public void addTheList() {

        list = new JList<>(listModel);
        list.setBounds(27, 52, 317, 365);
        contentPane.add(list);

    }

    public JList getList() {
        return list;
    }

    public void setList(JList list) {
        this.list = list;
    }

    public Writer getWriterModel() {
        return writerModel;
    }

    public void setWriterModel(Writer writerModel) {
        this.writerModel = writerModel;
    }

    public void setArticle(Article article)
    {
        title_field.setText(article.getTitle());
        abstract_area.setText(article.getArticleAbstract());
        body_area.setText(article.getBody());
    }

    public void clear()
    {
        title_field.setText("");
        abstract_area.setText("");
        body_area.setText("");
    }

    public Article getArticleChosen()
    {
        Article article;
        String title = title_field.getText();
        String abstractArticle = abstract_area.getText();
        String body = body_area.getText();

        article = new Article(title,abstractArticle,body,writerModel);
        article.setArticleid(articleModel.getArticleid());

        return article;
    }

    public void setArticleModel(Article articleModel)
    {
        this.articleModel = articleModel;
    }


}
