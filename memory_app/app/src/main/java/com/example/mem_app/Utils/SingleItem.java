package com.example.mem_app.Utils;

import com.example.mem_app.MainActivity;

public class SingleItem {
    private String item_name;
    private String image_string;
    private String item_description;
    private String upload_username;
    private String itemID;

    public SingleItem(String item_name, String image_string, String item_description,String upload_username, String itemID) {
        this.item_name = item_name;
        this.image_string = image_string;
        this.item_description = item_description;
        this.upload_username = upload_username;
        this.itemID = itemID;
    }

    public String getItem_name(){return this.item_name;}
    public String getImage_string(){return this.image_string;}
    public String getItem_description(){return this.item_description;}
    public String getUpload_username(){return this.upload_username;}
    public String getItemID(){ return this.itemID;}
}
