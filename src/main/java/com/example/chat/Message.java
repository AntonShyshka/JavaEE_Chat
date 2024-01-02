package com.example.chat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

public class Message implements Serializable {
    private Date date = new Date();
    private String from;
    private String to;
    private String text;

    public String toJSON() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
    }

    public static Message fromJSON(String s) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(s, Message.class);
    }

    public int send(String url) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

        conn.setRequestMethod("post");
        conn.setDoOutput(true);

        OutputStream os = conn.getOutputStream();

        try {
            String json = toJSON();
            os.write(json.getBytes());

            return conn.getResponseCode();
        } finally {
            os.close();
        }
    }
}
