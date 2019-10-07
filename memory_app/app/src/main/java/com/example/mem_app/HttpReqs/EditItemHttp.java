package com.example.mem_app.HttpReqs;

import com.example.mem_app.Utils.Http;

public class EditItemHttp extends Http {
    private String item_name;
    private String image_string;
    private String description;
    private String response;
    private String username;
    private String password;
    private String item_ID;

    public EditItemHttp( String item_name,
                        String description,
                        String image_string,
                        String username,
                        String password,
                        String item_ID) {
        super();
        this.item_name = item_name;
        this.description = description;
        this.response = "";
        this.image_string = image_string;
        this.username = username;
        this.password = password;
        this.item_ID = item_ID;
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
        String url =    "/EditItem?" +
                "user_name=" + username +
                "&user_pwd=" + password +
                "&item_name=" + item_name +
                "&description=" + description +
                "&item_ID=" + item_ID;
        this.response = postHttp(url, image_string);
    }



}