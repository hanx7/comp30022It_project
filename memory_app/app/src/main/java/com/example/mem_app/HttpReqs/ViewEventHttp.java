package com.example.mem_app.HttpReqs;

import com.example.mem_app.Utils.Http;

public class ViewEventHttp extends Http {

    private String user_name;
    private String user_password;
    private String item_ID;
    private String response;


    public ViewEventHttp(String user_name, String user_password, String item_ID){
        super();

        this.user_name = user_name;
        this.user_password = user_password;
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
        String url = "/viewEvent?"
                + "user_name=" + user_name
                + "&user_pwd=" + user_password
                + "&item_ID=" + item_ID;
        this.response = getHttp(url);
    }
}
