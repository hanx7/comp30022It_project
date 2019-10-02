package com.example.mem_app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.lang.String;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;


import android.widget.TextView;

import java.util.HashMap;

import com.example.mem_app.Utils.SingleItem;

public class ViewAllItemsActivity extends AppCompatActivity {
    HashMap<String, SingleItem> items;
    ImageView image1;
    ImageView image2;
    ImageView image3;
    ImageView image4;
    int numOfItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // Show login page
        setContentView(R.layout.viewitem);
        image1 = (ImageView)findViewById(R.id.viewitemImage1);
        image2 = (ImageView)findViewById(R.id.viewitemImage2);
        image3 = (ImageView)findViewById(R.id.viewitemImage3);
        image4 = (ImageView)findViewById(R.id.viewitemImage4);
        items = MainActivity.user_profile.getItemHmap();
        init();
//        SingleItem singleItem = MainActivity.user_profile.item_hmap.get(MainActivity.user_profile.items.get(0).item_name);
//        String one_image_str = singleItem.image_string;
//
//
//
//        final ImageView image_1 = (ImageView) super.findViewById(R.id.viewitemImage1);
//        image_1.setImageBitmap(StringToBitMap(one_image_str));
//
//        final TextView upload_username_1 = (TextView) super.findViewById(R.id.viewitemUsername1);
//        upload_username_1.setText(singleItem.upload_username);
    }

    private void init(){
        numOfItem = items.size();
        if(numOfItem >= 1){
            String imageString1 = (new ArrayList<SingleItem>(items.values())).get(0).getImage_string();
            image1.setImageBitmap(StringToBitMap(imageString1));
        }
        if(numOfItem >= 2){
            String imageString2 = (new ArrayList<SingleItem>(items.values())).get(1).getImage_string();
            image2.setImageBitmap(StringToBitMap(imageString2));
        }
        if(numOfItem >= 3){
            String imageString3 = (new ArrayList<SingleItem>(items.values())).get(1).getImage_string();
            image3.setImageBitmap(StringToBitMap(imageString3));
        }
        if(numOfItem >= 4){
            String imageString4 = (new ArrayList<SingleItem>(items.values())).get(1).getImage_string();
            image4.setImageBitmap(StringToBitMap(imageString4));
        }
    }




    public void onImgButton1Click(View view){
        Intent i = new Intent(this, ViewSingleItemActivity.class);
        startActivity(i);
    }


    public void onImgButton2Click(View view){
        Intent i = new Intent(this, ViewSingleItemActivity.class);
        startActivity(i);
    }

    public void onImgButton3Click(View view){
        Intent i = new Intent(this, ViewSingleItemActivity.class);
        startActivity(i);
    }

    public void onImgButton4Click(View view){
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
