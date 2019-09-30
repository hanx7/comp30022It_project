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


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.lang.String;

import androidx.appcompat.app.AppCompatActivity;

public class ViewAllItemsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Show login page
        setContentView(R.layout.viewitem);

        String resp = MainActivity.processor.viewItemHttpSend(MainActivity.user_profile.user_name, MainActivity.user_profile.user_pwd);
        System.out.println(resp);
        String all_images[] = resp.split("%%IMAGE_SPLITOR%%");
        System.out.println(resp);

        MainActivity.user_profile.all_images = new ArrayList<>();

        for (String image: all_images) {
            MainActivity.user_profile.all_images.add(image);
        }

        String one_image_str = MainActivity.user_profile.all_images.get(0).split("%%INFO_SPLITOR%%")[1];
        final ImageView image_1 = (ImageView) super.findViewById(R.id.image1);
        image_1.setImageBitmap(StringToBitMap(one_image_str));


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
