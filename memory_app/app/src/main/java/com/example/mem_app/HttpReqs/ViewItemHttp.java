package com.example.mem_app.HttpReqs;

import com.example.mem_app.Utils.Http;

public class ViewItemHttp extends Http {

    private String user_name;
    private String user_password;
    private String response;


    public ViewItemHttp(String user_name, String user_password){
        super();

        this.user_name = user_name;
        this.user_password = user_password;

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

    // send url from android to flask
    public void run(){
        // child thread
        String url = "/viewItem?"
                + "user_name=" + user_name
                + "&user_pwd=" + user_password;
        this.response = getHttp(url);
    }
}
