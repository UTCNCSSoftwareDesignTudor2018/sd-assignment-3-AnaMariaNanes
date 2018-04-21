package com.newsagency.presentation;

import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;

@Component
public class HomePageView extends JFrame {

    private JPanel contentPane;
    private JButton btnNewWriter = new JButton("NEW WRITER ACCOUNT");
    private JButton btnWriter = new JButton("WRITER");
    private JButton btnReader = new JButton("READER");

    private int totalClients =0;

    public HomePageView()
    {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300, 300, 550, 400);
        contentPane = new JPanel();
        setTitle("NEWS AGENCY");
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        btnNewWriter.setBounds(312, 298, 184, 42);
        contentPane.add(btnNewWriter);

        JLabel lblWelcomeToThe = new JLabel("Welcome to the NEWS AGENCY!");
        lblWelcomeToThe.setBounds(182, 26, 190, 36);
        contentPane.add(lblWelcomeToThe);

        btnWriter.setBounds(89, 106, 126, 60);
        contentPane.add(btnWriter);

        btnReader.setBounds(332, 106, 119, 60);
        contentPane.add(btnReader);

    }

    //add action listener
    public void addWriterBtnListener(ActionListener al)
    {
        this.btnWriter.addActionListener(al);
    }

    public void addReaderBtnListener(ActionListener al)
    {
        this.btnReader.addActionListener(al);
    }

    public void addNewWriterBtnListener(ActionListener al)
    {
        this.btnNewWriter.addActionListener(al);
    }

    public void addClient()
    {
        this.totalClients ++;
    }

    public void removeClient()
    {
        this.totalClients --;
    }

    public int getTotalClients()
    {
        return this.totalClients;
    }

}
