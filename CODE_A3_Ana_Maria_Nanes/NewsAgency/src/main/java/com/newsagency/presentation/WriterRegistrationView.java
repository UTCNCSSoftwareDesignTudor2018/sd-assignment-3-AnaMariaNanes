package com.newsagency.presentation;

import com.newsagency.entities.Writer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;

public class WriterRegistrationView extends JFrame{

    private JPanel contentPane;

    private JTextField firstNameField;
    private JTextField lastName_field;
    private JTextField username_field;
    private JPasswordField password_field;


    private JButton btnBack = new JButton("BACK");
    private JButton btnRegister = new JButton("REGISTER");

    public WriterRegistrationView()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300, 300, 550, 400);
        contentPane = new JPanel();
        setTitle("Writer Registration.");
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        btnBack.setBounds(407, 315, 97, 25);
        contentPane.add(btnBack);

        JLabel lblCreateAWriter = new JLabel("Create a writer account:");
        lblCreateAWriter.setBounds(80, 32, 210, 41);
        contentPane.add(lblCreateAWriter);

        JLabel lblFirstName = new JLabel("First Name:  ");
        lblFirstName.setBounds(80, 103, 97, 16);
        contentPane.add(lblFirstName);

        JLabel lblLastName = new JLabel("Last Name:  ");
        lblLastName.setBounds(80, 149, 73, 16);
        contentPane.add(lblLastName);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setBounds(80, 201, 73, 16);
        contentPane.add(lblUsername);

        JLabel lblPassword = new JLabel("Password: ");
        lblPassword.setBounds(80, 247, 73, 16);
        contentPane.add(lblPassword);

        firstNameField = new JTextField();
        firstNameField.setBounds(162, 100, 116, 22);
        contentPane.add(firstNameField);
        firstNameField.setColumns(10);

        lastName_field = new JTextField();
        lastName_field.setBounds(165, 146, 116, 22);
        contentPane.add(lastName_field);
        lastName_field.setColumns(10);

        username_field = new JTextField();
        username_field.setBounds(165, 198, 116, 22);
        contentPane.add(username_field);
        username_field.setColumns(10);

        password_field = new JPasswordField();
        password_field.setBounds(165, 244, 116, 22);
        contentPane.add(password_field);
        password_field.setColumns(10);

        btnRegister.setBounds(126, 289, 116, 38);
        contentPane.add(btnRegister);

    }

    // action listeners
    public void addBackListener(ActionListener al)
    {
            this.btnBack.addActionListener(al);
    }

    public void addRegisterListener(ActionListener al)
    {
        this.btnRegister.addActionListener(al);
    }

    public Writer getInputWriter()
    {
        Writer newWriter;
        String firstName = firstNameField.getText();
        String lastName = lastName_field.getText();
        String name = firstName + " " + lastName;
        String username = username_field.getText();
        char[] pass = password_field.getPassword();
        String password = String.valueOf(pass);

        newWriter = new Writer(name,username,password);
        return newWriter;
    }

    public void clearInput()
    {
            firstNameField.setText("");
            lastName_field.setText("");
            username_field.setText("");
            password_field.setText("");
    }
}
