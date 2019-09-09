package com.example.mem_app.HttpReqs;

import com.example.mem_app.Utils.Http;
import com.example.mem_app.ui.login.Login;

public class LoginHttp extends Http {
    private String username;
    private String password;

    public LoginHttp(String username, String password){
        super();
        this.username = username;
        this.password = password;
    }

    public void send(){
        start();
    }

    public void run(){
        String url = "/login?" + "user_name=" + username + "&user_pwd=" + password;
        String res = getHttp(url);
        System.out.println(res);
    }
}
