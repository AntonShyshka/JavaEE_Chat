package com.example.chat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class MessageList {
    private static final MessageList msgList = new MessageList();

    private final List<Message> list = new ArrayList<>();

    public static MessageList getInstance() {
        return msgList;
    }

    public synchronized void add(Message m) {
        list.add(m);
    }

    public synchronized String toJSON(int n) {
        List<Message> res = new ArrayList<>();

        for (int i = n; i < list.size(); i++)
            res.add(list.get(i));

        if (res.size() > 0) {
            Gson gson = new GsonBuilder().create();
            return gson.toJson(res.toArray());
        } else
            return null;
    }
}