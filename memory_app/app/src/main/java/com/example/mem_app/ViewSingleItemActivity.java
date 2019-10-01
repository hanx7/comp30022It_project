package com.example.mem_app;

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

import com.example.mem_app.Utils.UserProfile;

public class ViewSingleItemActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Show login page
        setContentView(R.layout.view_single_item);
//        Intent intent = getIntent();
        // send GET http to server, e.g. image, desc, imane
//        String item_ =



        String one_image_name = MainActivity.user_profile.all_images.get(0).split("%%INFO_SPLITOR%%")[0];
        String one_image_str = MainActivity.user_profile.all_images.get(0).split("%%INFO_SPLITOR%%")[1];
        String one_image_description = MainActivity.user_profile.all_images.get(0).split("%%INFO_SPLITOR%%")[2];

        final TextView viewpage_item_name = (TextView) super.findViewById(R.id.viewpage_item_name);
        viewpage_item_name.setText(one_image_name);

        final TextView viewpage_item_description = (TextView) super.findViewById(R.id.viewpage_item_description);
        viewpage_item_description.setText(one_image_description);

        final ImageView item_image = (ImageView) super.findViewById(R.id.item_image);
        item_image.setImageBitmap(StringToBitMap(one_image_str));
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
