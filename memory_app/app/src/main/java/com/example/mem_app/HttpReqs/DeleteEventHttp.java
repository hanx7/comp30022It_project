package com.example.mem_app.HttpReqs;

import com.example.mem_app.Utils.Http;

public class DeleteEventHttp extends Http {
    private String username;
    private String password;
    private String response;
    private String item_ID;
    private String event_ID;


    public DeleteEventHttp(String username, String password, String item_ID, String event_ID){
        super();
        this.username = username;
        this.password = password;
        this.item_ID = item_ID;
        this.event_ID = event_ID;
        this.response = "";
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
        String url = "/delete_event?" +
                "user_name=" + username +
                "&user_pwd=" + password +
                "&item_ID=" + item_ID +
                "&event_ID=" + event_ID;
        this.response = getHttp(url);
    }
}