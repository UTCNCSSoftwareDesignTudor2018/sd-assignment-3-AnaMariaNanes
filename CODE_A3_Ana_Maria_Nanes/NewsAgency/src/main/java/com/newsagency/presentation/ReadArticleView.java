package com.newsagency.presentation;

import com.newsagency.data.entities.Article;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;

public class ReadArticleView extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JButton btnBack = new JButton("BACK");
    private JTextArea textArea = new JTextArea();

    private Article articleModel;

    public ReadArticleView()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300, 300, 550, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        btnBack.setBounds(423, 315, 97, 25);
        contentPane.add(btnBack);

        JLabel lblChosenArticle = new JLabel("Chosen Article:");
        lblChosenArticle.setBounds(38, 28, 97, 25);
        contentPane.add(lblChosenArticle);

        textField = new JTextField();
        textField.setBounds(147, 29, 116, 22);
        textField.setEditable(false);
        contentPane.add(textField);
        textField.setColumns(10);


        textArea.setBounds(38, 67, 452, 231);
        contentPane.add(textArea);

    }

    public Article getArticleModel() {
        return articleModel;
    }

    public void setArticleModel(Article articleModel) {
        this.articleModel = articleModel;
    }

    public void setArticleTitle(String title)
    {
        textField.setText(title);
    }

    public void setArticleData()
    {
        textField.setText(articleModel.getTitle());
        textArea.setText(articleModel.getBody());

    }

    public void addBackActionListener(ActionListener al)
    {
        this.btnBack.addActionListener(al);
    }

    public void clear()
    {
        textField.setText("");
        textArea.setText("");
    }
}
