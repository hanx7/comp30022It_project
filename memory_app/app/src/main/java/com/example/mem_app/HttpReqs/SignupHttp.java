package com.example.mem_app.HttpReqs;

import com.example.mem_app.Utils.Http;

public class SignupHttp extends Http {

    private String username;
    private String password;
    private String response;
    private String first_name;
    private String last_name;
    private String email;
    private String dob;


    public SignupHttp(String username, String password, String first_name ,String last_name, String email , String dob){
        super();
        this.username = username;
        this.password = password;
        this.response = "";
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.dob = dob;
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
        String url = "/register?" + "user_name=" + username + "&user_pwd=" + password
                +"&user_first_name=" + first_name + "&user_last_name=" + last_name
                + "&user_email=" + email + "&user_dob=" + dob;
        this.response = getHttp(url);
    }


}
