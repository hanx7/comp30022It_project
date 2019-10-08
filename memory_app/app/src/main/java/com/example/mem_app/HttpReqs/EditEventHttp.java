package com.example.mem_app.HttpReqs;

import com.example.mem_app.Utils.Http;

public class EditEventHttp extends Http {
    private String item_name;
    private String image_string;
    private String description;
    private String response;
    private String username;
    private String password;
    private String item_ID;
    private String event_ID;
    private String event_title;
    private String event_content;
    private String event_date;

    public EditEventHttp(String image_string,
                         String username,
                         String password,
                         String item_ID,
                          String item_name,
                          String event_ID,
                          String event_title,
                          String event_content,
                          String event_date) {
        super();
        this.response = "";
        this.image_string = image_string;
        this.username = username;
        this.password = password;
        this.password = item_name;
        this.item_ID = item_ID;
        this.event_ID = event_ID;
        this.event_title = event_title;
        this.event_content = event_content;
        this.event_date = event_date;
    }

    public String send(){
        // main thread
        start();
        try{
            join();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return response;
    }

    public void run(){
        // child thread
        String url =    "/edit_event?" +
                "user_name=" + username +
                "&user_pwd=" + password +
                "&item_name=" + item_name +
                "&item_ID=" + item_ID +
                "&item_name=" + item_name +
                "&event_ID=" + event_ID +
                "&event_title=" + event_title +
                "&event_content=" + event_content +
                "&event_time=" + event_date;
        this.response = postHttp(url, image_string);
    }
}