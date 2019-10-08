package com.example.mem_app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EditEventActivity extends AppCompatActivity {

    private ImageView item_image;
    private String image_string;
    public static Context context;
    ImageView itemImage;
    TextView itemName;
    TextView itemDescription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Show add_item page
        setContentView(R.layout.edit_item);

        Intent intent = getIntent();
        context = getApplicationContext();
        itemImage = (ImageView) findViewById(R.id.editItemImage);
        itemName = (TextView) findViewById(R.id.editItemName);
        itemDescription = (TextView) findViewById(R.id.editItemDescription);

        itemImage.setImageBitmap(ViewSingleItemActivity.imageBitmap);
        itemName.setText(ViewSingleItemActivity.itemNameText);
        itemDescription.setText(ViewSingleItemActivity.itemDescriptionText);
    }
}