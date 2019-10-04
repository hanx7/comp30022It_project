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

import com.example.mem_app.Utils.SingleItem;

public class ViewSingleItemActivity extends AppCompatActivity {

    ImageView itemImage;
    TextView itemName;
    TextView itemDescription;
    public static String currentItemID;
    public static String currentItemName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Show login page
        setContentView(R.layout.item_info);
        itemImage = (ImageView)findViewById(R.id.itemInfoImage);
        itemName = (TextView)findViewById(R.id.itemInfoName);
        itemDescription = (TextView)findViewById(R.id.itemInfoStory);
        String itemID = getIntent().getExtras().getString("itemID");

        SingleItem item = MainActivity.user_profile.getItems().get(itemID);
        itemImage.setImageBitmap(StringToBitMap(item.getImage_string()));
        itemName.setText(item.getItem_name());
        itemDescription.setText(item.getItem_description());

        currentItemID = itemID;
        currentItemName = item.getItem_name();
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

    public void onGoToEventButtonClick(View view){
        Intent i = new Intent(this, EventCategoryActivity.class);
        startActivity(i);
    }



}
