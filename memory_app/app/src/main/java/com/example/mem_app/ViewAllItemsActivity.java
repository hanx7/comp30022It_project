package com.example.mem_app;

import android.util.Base64;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.BitmapFactory;
import java.io.InputStream;
import java.io.ByteArrayInputStream;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import android.graphics.Bitmap;
public class ViewAllItemsActivity extends AppCompatActivity {
    private ImageView image1;
    final TextView text_username = (TextView) MainActivity.text_username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Show login page
        setContentView(R.layout.viewitem);
        Intent intent = getIntent();
        init();
    }

    private void init(){
        HashMap<String, String []> items;
        items = MainActivity.processor.fetchItems(text_username.getText().toString());
        image1 = findViewById(R.id.imageButton1);
        String name = String.valueOf((items.size()-1));
        Log.v("iamge is ", items.get("tt")[1].split("D8nRo2iHyjhg")[0]);
        Log.v("iamge is ", items.get("tt")[1]);
        //Log.v("test is ", AddItemActivity.test);
        decodeBase64AndSetImage(items.get("tt")[1].split("D8nRo2iHyjhg")[0], image1);
    }

    private void decodeBase64AndSetImage(String completeImageData, ImageView imageView) {

        // Incase you're storing into aws or other places where we have extension stored in the starting.
        String imageDataBytes = completeImageData.substring(completeImageData.indexOf(",")+1);

        InputStream stream = new ByteArrayInputStream(Base64.decode(imageDataBytes.getBytes(), Base64.DEFAULT));
        //Log.v("ERROR:", stream.toString());
        Bitmap bitmap = BitmapFactory.decodeStream(stream);

        imageView.setImageBitmap(bitmap);
    }

    public void onLogoutButtonClick(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void onHomeButtonClick(View view) {
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
    }

}
