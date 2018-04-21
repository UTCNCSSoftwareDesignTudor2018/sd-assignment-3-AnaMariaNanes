package com.newsagency.communication;

import com.newsagency.services.ArticleService;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread extends Thread{

    private ArticleService articleService;

    public void run()
    {
        Socket socket;
        ServerSocket server = null;

        try {
            server = new ServerSocket(5000);
            System.out.println("Server started");
            System.out.println("Waiting for a clients ...");

            while(true) {
                socket = server.accept();

                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());

                ClientHandler t = new ClientHandler(socket, in, out);
                t.setArticleService(articleService);
                t.start();

            }
        } catch (IOException i) {
            try {
                server.close();
            }catch(IOException ee)
            {
                ee.printStackTrace();
            }
        }
    }

    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }
}
