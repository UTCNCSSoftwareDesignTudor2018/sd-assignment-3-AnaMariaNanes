package com.newsagency.presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class AllWritersView extends JFrame {

    private JPanel contentPane;

    public AllWritersView()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300, 300, 550, 400);
        contentPane = new JPanel();
        setTitle("All writers");
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
    }
}
