package com.example.mem_app.Utils;

import android.util.Log;

import com.example.mem_app.MainActivity;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class SingleItem {
    private String item_name;
    private String image_string;
    private String item_description;
    private String upload_username;
    private String itemID;
    private LinkedHashMap<String, Event> eventHmap;


    public SingleItem(String item_name, String image_string, String item_description,String upload_username, String itemID) {
        this.item_name = item_name;
        this.image_string = image_string;
        this.item_description = item_description;
        this.upload_username = upload_username;
        this.itemID = itemID;
        this.eventHmap = new LinkedHashMap<String, Event>();
    }

    public String getItem_name(){return this.item_name;}
    public String getImage_string(){return this.image_string;}
    public String getItem_description(){return this.item_description;}
    public String getUpload_username(){return this.upload_username;}
    public String getItemID(){ return this.itemID;}

    public HashMap<String, Event> getEventHmap() {
        String resp = MainActivity.processor.viewEventHttpSend(MainActivity.user_profile.user_name, MainActivity.user_profile.user_pwd);

        String events[] = resp.split("%%IMAGE_SPLITOR%%");

        for(String eventString: events){

            String[] eventElements = eventString.split("%%INFO_SPLITOR%%");

            String eventTitle = eventElements[0];
            String eventImage = eventElements[1];
            String eventTime = eventElements[2];
            String eventContent = eventElements[3];
            String itemName = eventElements[4];
            String itemID = eventElements[5];

//            Log.d("debug-item", itemName);

            Event event = new Event(eventTitle, eventImage, eventTime, eventContent, itemName, itemID);
            eventHmap.put(itemID, event);
        }
        return eventHmap;
    }

    public HashMap<String, Event> getItems(){ return eventHmap;}
}
