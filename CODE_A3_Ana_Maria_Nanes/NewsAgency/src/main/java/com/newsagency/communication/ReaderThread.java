package com.newsagency.communication;

import com.newsagency.entities.Article;
import com.newsagency.entities.Writer;
import com.newsagency.presentation.HomePageView;
import com.newsagency.presentation.ReaderView;
import com.newsagency.services.WriterService;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.Socket;

public class ReaderThread extends Thread{

    private ReaderView readerView;
    private WriterService writerService;
    private HomePageView homePageView;
    private Socket socketClient;
    private InputStream is;
    private DataInputStream dis;
    private BufferedReader readerBf;
    private DataOutputStream out;


    public void run() {
        try {
            socketClient = new Socket("127.0.0.1", 5000);
            readerView.addViewArticleListener(new ReadAricleListener());
            readerView.addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent we)
                {
                    homePageView.removeClient();
                    System.out.println("Current number of clients:  " + homePageView.getTotalClients());
                }
            });
        } catch (IOException i) {
            System.out.println(i);
        }

    }

    class ReadAricleListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            Object selectedArticle = readerView.getList().getSelectedValue();
            String articleTitle = (String) selectedArticle;

            if(selectedArticle == null)
            {
                JOptionPane.showMessageDialog(null,
                        "No article has been chosen.",
                        "ERROR OPENING THE ARTICLE",
                        JOptionPane.ERROR_MESSAGE);
            }
            else {

                try {

                    is = new ByteArrayInputStream(articleTitle.getBytes("UTF-8"));
                    readerBf = new BufferedReader(new InputStreamReader(is));
                    String line = readerBf.readLine();

                    dis = new DataInputStream(socketClient.getInputStream());
                    out = new DataOutputStream(socketClient.getOutputStream());
                    out.writeUTF(line);

                    String serverArticle = dis.readUTF();
                    JSONObject jsonObject = new JSONObject(serverArticle);
                    Article receivedArticle;

                    int articleid = jsonObject.getInt("articleid");
                    String title = jsonObject.getString("title");
                    String author_name = jsonObject.getString("author");
                    Writer author = writerService.getByName(author_name);
                    String articleAbstract = jsonObject.getString("articleAbstract");
                    String body = jsonObject.getString("body");

                    receivedArticle = new Article(title, articleAbstract, body, author);
                    receivedArticle.setArticleid(articleid);

                    readerView.setArticle(receivedArticle);


                } catch (IOException i) {
                    System.out.println(i);
                }
            }
        }
    }
    public void setReaderView(ReaderView readerView){
        this.readerView = readerView;
    }

    public void setHomePageView(HomePageView homePageView)
    {
        this.homePageView = homePageView;
    }

    public HomePageView getHomePageView()
    {
        return this.homePageView;
    }

    public void setWriterService(WriterService writerService)
    {
        this.writerService = writerService;
    }



}
