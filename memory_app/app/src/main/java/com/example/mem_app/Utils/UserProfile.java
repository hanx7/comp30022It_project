package com.example.mem_app.Utils;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import com.example.mem_app.MainActivity;
import com.example.mem_app.Utils.SingleItem;

public class UserProfile {
    public static String user_name;
    public static String user_pwd;
    public static String user_email;
    private ArrayList<String> all_images;
    private LinkedHashMap<String, SingleItem> item_hmap;
    private ArrayList<SingleItem> items;

    public UserProfile(){
        this.all_images = new ArrayList<>();
        this.item_hmap = new LinkedHashMap<>();
        this.items = new ArrayList<>();
    }

    public void add_image(String[] images){
//        System.out.println("XXXXXXX" + images[0]);
        all_images = new ArrayList<>();
        for (String image: images) {
            if (image != null)
                all_images.add(image);
        }
    }
    public void transfer_hmap(){

        for(String item: all_images){

            SingleItem singleItem = new SingleItem(item.split("%%INFO_SPLITOR%%")[0] ,
                               item.split("%%INFO_SPLITOR%%")[1] ,
                               item.split("%%INFO_SPLITOR%%")[2] ,
                               item.split("%%INFO_SPLITOR%%")[3]);

            items.add(singleItem);

            item_hmap.put(singleItem.getItem_name(), singleItem);
        }
    }

    public  HashMap<String, SingleItem> getItemHmap() {
        String resp = MainActivity.processor.viewItemHttpSend(MainActivity.user_profile.user_name, MainActivity.user_profile.user_pwd);
        String all_items[] = resp.split("%%IMAGE_SPLITOR%%");

        for(String item: all_items){

            String [] item_elements = item.split("%%INFO_SPLITOR%%");
            String item_name = item_elements[0];
            String item_image = item_elements[1];
            String description = item_elements[2];
            String userName = item_elements[3];
            Log.d("debug-item", item_name);
            SingleItem singleItem = new SingleItem(item_name, item_image, description, userName);
            item_hmap.put(item_name, singleItem);
        }
        return item_hmap;
    }

    public  ArrayList<SingleItem> getItems() {
        return items;
    }

}
