package com.example.mem_app.Utils;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.mem_app.MainActivity;
import com.example.mem_app.Utils.SingleItem;

public class UserProfile {
    public static String user_name;
    public static String user_pwd;
    public static String user_email;
    public static ArrayList<String> all_images = new ArrayList<>();
    public static HashMap<String, SingleItem> item_hmap = new HashMap<>();
    public static ArrayList<SingleItem> items = new ArrayList<>();


    public void add_image(String[] images){
//        System.out.println("XXXXXXX" + images[0]);
        all_images = new ArrayList<>();
        for (String image: images) {
            if (image != null)
                all_images.add(image);
        }
    }
    public void transfer_hmap(){
        item_hmap = new HashMap<>();
        for(String item: all_images){
            SingleItem singleItem = new SingleItem();
            //singleItem.item_name =  item.split("%%INFO_SPLITOR%%")[0];


            singleItem.setItem_attributes(item.split("%%INFO_SPLITOR%%")[0] ,
                               item.split("%%INFO_SPLITOR%%")[1] ,
                               item.split("%%INFO_SPLITOR%%")[2] ,
                               item.split("%%INFO_SPLITOR%%")[3]);

            items.add(singleItem);

            item_hmap.put(singleItem.item_name, singleItem);
        }
    }

    public  HashMap<String, SingleItem> getItem_hmap() {
        return item_hmap;
    }

    public  ArrayList<SingleItem> getItems() {
        return items;
    }

}
