package com.newsagency.presentation;

import com.newsagency.entities.Writer;

import javax.swing.*;

public class WriterView extends JFrame {

    private Writer writerModel;

    public WriterView()
    {

    }

    public void setWriterModel(Writer writer)
    {
        this.writerModel = writerModel;
    }

    public void setTheTitle()
    {
        setTitle("Welcome writer " + writerModel.getName());
    }
}
