package com.example.mem_app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;

import java.lang.String;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;


import android.widget.TextView;

import java.util.HashMap;

import com.example.mem_app.Utils.SingleItem;

public class ViewAllItemsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Show login page
        setContentView(R.layout.viewitem);
        String resp = MainActivity.processor.viewItemHttpSend(MainActivity.user_profile.user_name, MainActivity.user_profile.user_pwd);
        String all_images[] = resp.split("%%IMAGE_SPLITOR%%");

        MainActivity.user_profile.all_images = new ArrayList<>();
        MainActivity.user_profile.items = new ArrayList<>();
        MainActivity.user_profile.item_hmap = new HashMap<>();


        for (String image: all_images)
            if (!image.equals(""))
                MainActivity.user_profile.all_images.add(image);

        for(String item: all_images){
            SingleItem singleItem = new SingleItem();
            singleItem.setItem_attributes(item.split("%%INFO_SPLITOR%%")[0],
                item.split("%%INFO_SPLITOR%%")[1],
                item.split("%%INFO_SPLITOR%%")[2], item.split("%%INFO_SPLITOR%%")[3]);

            MainActivity.user_profile.items.add(singleItem);
            MainActivity.user_profile.item_hmap.put(singleItem.item_name,singleItem);
        }

        SingleItem singleItem = MainActivity.user_profile.item_hmap.get(MainActivity.user_profile.items.get(0).item_name);
        String one_image_str = singleItem.image_string;

        final ImageView image_1 = (ImageView) super.findViewById(R.id.viewitemImage1);
        image_1.setImageBitmap(StringToBitMap(one_image_str));

        final TextView upload_username_1 = (TextView) super.findViewById(R.id.viewitemUsername1);
        upload_username_1.setText(singleItem.upload_username);


    }





    public void onImgButton1Click(View view){
        Intent i = new Intent(this, ViewSingleItemActivity.class);
        startActivity(i);

    }


    private Bitmap StringToBitMap(String encodedString){
        try{
            byte [] encodeByte = Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        }
        catch(Exception e){
            e.getMessage();
            return null;
        }
    }
}
