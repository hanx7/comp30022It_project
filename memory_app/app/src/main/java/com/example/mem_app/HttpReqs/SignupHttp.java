package com.example.mem_app.HttpReqs;

import com.example.mem_app.Utils.Http;

public class SignupHttp extends Http {

    private String username;
    private String password;
    private String response;


    public SignupHttp(String username, String password){
        super();
        this.username = username;
        this.password = password;
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
        String url = "/register?" + "user_name=" + username + "&user_pwd=" + password;
        this.response = getHttp(url);
    }


}
