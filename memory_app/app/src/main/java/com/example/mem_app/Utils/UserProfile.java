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
    public static String firstName;
    public static String lastName;
    private ArrayList<String> all_images;
    private HashMap<String, SingleItem> item_hmap;
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


    public  HashMap<String, SingleItem> getItemHmap() {
        item_hmap = new HashMap<>();
        String resp = MainActivity.processor.viewItemHttpSend(MainActivity.user_profile.user_name, MainActivity.user_profile.user_pwd);
        if (!resp.equals("")) {
            String all_items[] = resp.split("%%IMAGE_SPLITOR%%");

            for (String item : all_items) {

                String[] itemElements = item.split("%%INFO_SPLITOR%%");
                String itemName = itemElements[0];
                String itemImage = itemElements[1];
                String description = itemElements[2];
                String userName = itemElements[3];
                String itemID = itemElements[4];
                Log.d("debug-item", itemName);
                SingleItem singleItem = new SingleItem(itemName, itemImage, description, userName, itemID);
                item_hmap.put(itemID, singleItem);
            }
        }
        return item_hmap;
    }

    public HashMap<String, SingleItem> getItems(){ return item_hmap;}
//    public  ArrayList<SingleItem> getItems() {
//        return items;
//    }

    public String getUserName() {
        return user_name;
    }

    public String getUserPwd() {
        return user_pwd;
    }

}
