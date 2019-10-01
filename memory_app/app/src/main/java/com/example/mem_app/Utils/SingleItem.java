package com.example.mem_app.Utils;

import com.example.mem_app.MainActivity;

public class SingleItem {
    public String item_name;
    public String image_string;
    public String item_description;
    public String upload_username;



    public void setItem_attributes(String item_name, String image_string, String item_description,String upload_username) {
        this.item_name = item_name;
        this.image_string = image_string;
        this.item_description = item_description;
        this.upload_username = upload_username;
    }
}
