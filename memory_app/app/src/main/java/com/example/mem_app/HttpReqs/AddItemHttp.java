package com.example.mem_app.HttpReqs;

import com.example.mem_app.Utils.Http;

public class AddItemHttp extends Http {
    private String item_name;
    private String image_string;
    private String description;
    private String response;
    private String username;
    private String password;

    public AddItemHttp( String item_name,
                        String description,
                        String image_string,
                        String username,
                        String password) {
        super();
        this.item_name = item_name;
        this.description = description;
        this.response = "";
        this.image_string = image_string;
        this.username = username;
        this.password = password;
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
        String url =    "/addItem?" +
                        "user_name=" + username +
                        "&user_pwd=" + password +
                        "&item_name=" + item_name +
                        "&description=" + description;
        this.response = postHttp(url, image_string);
    }



}
