package com.example.mem_app.HttpReqs;

import com.example.mem_app.Utils.Http;

import java.util.Date;

public class AddEventHttp extends Http {
    private String eventTitle;
    private String eventContent;
    private String eventTime;
    private String eventImage;
    private String itemName;
    private String itemID;
    private String response;
    private String username;
    private String password;

    public AddEventHttp( String eventTitle, String eventContent, String eventTime,
                         String eventImage, String itemName, String itemID,
                         String username, String userPassword) {
        super();
        this.eventTitle = eventTitle;
        this.eventContent = eventContent;
        this.eventTime = eventTime;
        this.eventImage = eventImage;
        this.itemName = itemName;
        this.itemID = itemID;
        this.username = username;
        this.password = userPassword;
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
        String url =    "/addEvent?" +
                        "user_name=" + username +
                        "&user_pwd=" + password +
                        "&item_name=" + itemName +
                        "&item_ID=" + itemID +
                        "&event_title=" + eventTitle +
                        "&event_content=" + eventContent +
                        "&event_time=" + eventTime;
        this.response = postHttp(url, eventImage);
    }
}
