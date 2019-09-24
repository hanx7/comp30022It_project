package com.example.mem_app.HttpReqs;

import com.example.mem_app.Utils.Http;

public class AddItemHttp extends Http {
    private String item_name;
    //private ImageView image_view;
    private String description;
    private String response;
    private String username;

    public AddItemHttp(String item_name, String description,String username) {
        super();
        this.item_name = item_name;
        //this.image_view = image_view;
        this.description = description;
        this.response = "";
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
        String url = "/Upload?" + "user_name=" + username + "&item_name=" + item_name
                      + "&description=" + description;
        this.response = getHttp(url);
    }



}
