package com.example.mem_app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mem_app.Utils.SingleItem;

public class ViewSingleEventActivity extends AppCompatActivity {

    ImageView eventImage;
    TextView eventName;
    TextView eventStory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.events);

        eventImage = (ImageView)findViewById(R.id.eventImageView);
        eventName = (TextView)findViewById(R.id.eventName);
        eventStory = (TextView)findViewById(R.id.eventStory);

        String itemID = getIntent().getExtras().getString("itemID");
        SingleItem item = MainActivity.user_profile.getItems().get(itemID);

        eventImage.setImageBitmap(StringToBitMap(item.getImage_string()));
        eventName.setText(item.getItem_name());
        eventStory.setText(item.getItem_description());
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

    public void onGoBackButtonClick(View view) {
        Intent i = new Intent(this, EventCategoryActivity.class);
        startActivity(i);
    }
}
