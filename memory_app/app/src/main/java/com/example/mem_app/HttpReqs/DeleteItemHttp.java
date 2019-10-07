package com.example.mem_app.HttpReqs;

import com.example.mem_app.Utils.Http;

public class DeleteItemHttp extends Http {
    private String username;
    private String password;
    private String response;
    private String item_ID;


    public DeleteItemHttp(String username, String password, String item_ID){
        super();
        this.username = username;
        this.password = password;
        this.item_ID = item_ID;
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
        String url = "/login?" + "user_name=" + username + "&user_pwd=" + password + "&item_ID=" + item_ID;
        this.response = getHttp(url);
    }
}