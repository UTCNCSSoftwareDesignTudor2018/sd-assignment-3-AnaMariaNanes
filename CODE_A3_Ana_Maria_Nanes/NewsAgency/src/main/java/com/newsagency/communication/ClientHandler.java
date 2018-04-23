package com.newsagency.communication;

import com.newsagency.data.entities.Article;
import com.newsagency.data.mappers.ArticleMapper;
import com.newsagency.services.ArticleService;
import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class ClientHandler extends Thread {

    ArticleService articleService;

    final DataInputStream in;
    final DataOutputStream out;
    final Socket socket;


    public ClientHandler(Socket socket, DataInputStream in, DataOutputStream out)
    {
        this.socket =socket;
        this.in = in;
        this.out = out;
    }

    @Override
    public void run() {
        String received = "";

        while (!received.equals("END")) {
            try {
                received = in.readUTF();
                System.out.println(received);

                Article article = articleService.getByTitle(received);
                ArticleMapper articleMapper = new ArticleMapper(article);

                JSONObject articleJson = new JSONObject(articleMapper);
                out.writeUTF(articleJson.toString());

            } catch (IOException i) {
                System.out.println(i);
            }
        }
        try {
            this.socket.close();
            this.in.close();
        }catch(IOException ee){
            ee.printStackTrace();
        }
    }

    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }
}
