package com.example.mem_app.HttpReqs;

import android.util.Log;

import com.example.mem_app.Utils.Http;

public class AddItemHttp extends Http {
    private String item_name;
    private String image_string;
    private String description;
    private String response;
    private String username;

    public AddItemHttp(String item_name, String description, String image_string, String username) {
        super();
        this.item_name = item_name;
        //this.image_view = image_view;
        this.description = description;
        this.response = "";
        this.image_string = image_string;
        this.username = username;
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
        String url = "/upload?" + "user_name=" + username + "&item_name=" + item_name
                      + "&description=" + description + "&image_string=" + image_string;
        Log.v("image instered is ", image_string);
        this.response = getHttp(url);
    }



}
