package com.newsagency.presentation;

import com.newsagency.entities.Article;
import com.newsagency.entities.Writer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.util.List;

public class WriterView extends JFrame {

    private JPanel contentPane;
    private JTextField title_field;
    private JTextArea abstract_field;
    private JTextArea body_field;
    private JTextField textField;
    private JLabel lblArticleToOpen = new JLabel("Article to open: ");

    private JButton btnAddArticle = new JButton("ADD ARTICLE");
    private JButton btnRelatedArticles = new JButton("SHOW RELATED ARTICLES");
    private JButton btnOpenArticle = new JButton("OPEN ARTICLE");
    private JButton btnModifyPersonalArticles = new JButton("MODIFY PERSONAL ARTICLES");
    private JButton btnLogOut = new JButton("LOG OUT");
    private JButton btnDeleteAccount = new JButton("DELETE ACCOUNT");

    private JList list;
    private DefaultListModel<String> listModel;


    private Writer writerModel;

    public WriterView()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300, 300, 785, 575);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblWriteANew = new JLabel("Write a new article:");
        lblWriteANew.setBounds(95, 27, 139, 27);
        contentPane.add(lblWriteANew);

        JLabel lblTitle = new JLabel("Title:");
        lblTitle.setBounds(35, 67, 62, 16);
        contentPane.add(lblTitle);

        title_field = new JTextField();
        title_field.setBounds(105, 67, 198, 22);
        contentPane.add(title_field);
        title_field.setColumns(10);

        abstract_field = new JTextArea();
        abstract_field.setBounds(101, 96, 202, 68);
        contentPane.add(abstract_field);

        JLabel lblAbstract = new JLabel("Abstract:");
        lblAbstract.setBounds(33, 99, 56, 16);
        contentPane.add(lblAbstract);

        body_field = new JTextArea();
        body_field.setBounds(35, 177, 268, 189);
        contentPane.add(body_field);

        btnAddArticle.setBounds(35, 379, 121, 33);
        contentPane.add(btnAddArticle);

        btnLogOut.setBounds(658, 490, 97, 25);
        contentPane.add(btnLogOut);

        list = new JList();
        listModel = new DefaultListModel<>();

        btnRelatedArticles.setBounds(35, 424, 213, 25);
        contentPane.add(btnRelatedArticles);

        btnOpenArticle.setBounds(563, 411, 139, 27);
        contentPane.add(btnOpenArticle);

        btnModifyPersonalArticles.setBounds(35, 473, 213, 25);
        contentPane.add(btnModifyPersonalArticles);

        JLabel lblRelatedArticles = new JLabel("Related articles: ");
        lblRelatedArticles.setBounds(363, 32, 121, 16);
        contentPane.add(lblRelatedArticles);

        btnDeleteAccount.setBounds(502, 490, 139, 25);
        contentPane.add(btnDeleteAccount);

        textField = new JTextField();
        textField.setVisible(true);
        textField.setBounds(525, 384, 219, 22);
        textField.setColumns(10);
        contentPane.add(textField);

        lblArticleToOpen.setBounds(401, 387, 97, 16);
        contentPane.add(lblArticleToOpen);
    }

    public void setWriterModel(Writer writerModel)
    {
        this.writerModel = writerModel;
    }

    public void setTheTitle(String name)
    {

        this.setTitle("Welcome writer " + name);
    }

    public void clearInput()
    {
        title_field.setText("");
        abstract_field.setText("");
        body_field.setText("");
    }

    public Article getInputArticle()
    {
        Article article;
        String title = title_field.getText();
        String abstractArt = abstract_field.getText();
        String body = body_field.getText();

        article = new Article(title,abstractArt,body,writerModel);
        return article;
    }

    public String getTitle()
    {
        return title_field.getText();
    }

    public String getChosenTitle()
    {
        return textField.getText();
    }

    public void addLogOutListener(ActionListener al)
    {
        this.btnLogOut.addActionListener(al);
    }

    public void addInsertArticleListener(ActionListener al)
    {
        this.btnAddArticle.addActionListener(al);
    }

    public void addOpenArticleListener(ActionListener al)
    {
        this.btnOpenArticle.addActionListener(al);
    }

    public void addModifyArticleListener(ActionListener al)
    {
        this.btnModifyPersonalArticles.addActionListener(al);
    }

    public void addRelatedArticleListener(ActionListener al)
    {
        this.btnRelatedArticles.addActionListener(al);
    }

    public void addDeleteAccountListener(ActionListener al)
    {
        this.btnDeleteAccount.addActionListener(al);
    }

    public Writer getWriterModel()
    {
        return this.writerModel;
    }


    public void initializeList(List<Article> article) {
        listModel.removeAllElements();
        if(article !=null)
        for (Article art : article) {
            listModel.addElement(art.getTitle());
        }
    }

    public void addTheList() {

        list = new JList<>(listModel);
        list.setBounds(363, 66, 278, 301);
        list.setVisible(true);
        contentPane.add(list);

    }

    public JList getList()
    {
        return this.list;
    }

    public String getArticleToOpen()
    {
        return textField.getText();
    }

    public void clear()
    {
        textField.setText("");
    }


}
