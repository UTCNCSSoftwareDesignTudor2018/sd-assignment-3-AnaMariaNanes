package com.newsagency.presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;

public class WriterLogInView extends JFrame {

    private JPanel contentPane;
    private JTextField username_field;
    private JPasswordField password_field;

    private JButton btnBack = new JButton("BACk");
    private JButton btnLogIn = new JButton("LOG IN");

    public WriterLogInView()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300, 300, 550, 400);
        contentPane = new JPanel();
        setTitle("Writer LOG IN");
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        btnBack.setBounds(391, 299, 97, 25);
        contentPane.add(btnBack);

        username_field = new JTextField();
        username_field.setBounds(164, 81, 116, 22);
        contentPane.add(username_field);
        username_field.setColumns(10);

        password_field = new JPasswordField();
        password_field.setBounds(164, 116, 116, 22);
        contentPane.add(password_field);
        password_field.setColumns(10);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setBounds(62, 84, 76, 16);
        contentPane.add(lblUsername);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(62, 119, 76, 16);
        contentPane.add(lblPassword);

        btnLogIn.setBounds(130, 184, 97, 25);
        contentPane.add(btnLogIn);
    }

    //add action listeners

    public void addLogInListener(ActionListener al)
    {
        this.btnLogIn.addActionListener(al);
    }

    public void addBackListener(ActionListener al)
    {
        this.btnBack.addActionListener(al);
    }


    //get user input

    public String getUsername()
    {
        return username_field.getText();
    }

    public String getPassword()
    {
        String password;
        char[] pass = password_field.getPassword();
        password = String.valueOf(pass);
        return password;
    }

    public void clear()
    {
        username_field.setText("");
        password_field.setText("");
    }
}
