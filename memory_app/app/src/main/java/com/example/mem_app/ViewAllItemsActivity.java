package com.example.mem_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ViewAllItemsActivity extends AppCompatActivity {
    private ImageView displayPhoto1;
    private ImageView displayPhoto2;
    private ImageView displayPhoto3;
    private ImageView displayPhoto4;
    private int[] photos = {R.drawable.cute1, R.drawable.cute2, R.drawable.cute3, R.drawable.cute4};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Show login page
        setContentView(R.layout.viewitem);
        Intent intent = getIntent();
        init();
        displayPhoto1.setImageResource(R.drawable.cute1);
        displayPhoto2.setImageResource(R.drawable.cute2);
        displayPhoto3.setImageResource(R.drawable.cute3);
        displayPhoto4.setImageResource(R.drawable.cute4);
    }

    private void init(){
        displayPhoto1 = findViewById(R.id.imageButton1);
        displayPhoto2 = findViewById(R.id.imageButton2);
        displayPhoto3 = findViewById(R.id.imageButton3);
        displayPhoto4 = findViewById(R.id.imageButton4);
    }
}
