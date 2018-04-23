package com.newsagency.presentation;

import com.newsagency.data.entities.Writer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.util.List;

public class AllWritersView extends JFrame {

    private JPanel contentPane;
    private JList list;
    private DefaultListModel<String> listModel;
    private JButton btnBack = new JButton("BACK");


    public AllWritersView()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300, 300, 550, 400);
        contentPane = new JPanel();
        setTitle("All writers");
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTheWriters = new JLabel("The writers:");
        lblTheWriters.setBounds(37, 13, 89, 28);
        contentPane.add(lblTheWriters);

        btnBack.setBounds(423, 315, 97, 25);
        contentPane.add(btnBack);

        list = new JList();
        listModel = new DefaultListModel<>();

    }

    public void addBackListener(ActionListener al)
    {
        this.btnBack.addActionListener(al);
    }

    public void initializeList(List<Writer> writers) {
        listModel.removeAllElements();
        for (Writer wr: writers) {
            listModel.addElement(wr.getName());
        }
    }

    public void addTheList() {

        list = new JList<>(listModel);
        list.setBounds(37, 54, 323, 241);
        list.setVisible(true);
        contentPane.add(list);
    }

    public JList getList()
    {
        return this.list;
    }
}
