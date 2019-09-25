package com.example.mem_app;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ViewAllItemsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Show login page
        setContentView(R.layout.viewitem);
        Intent intent = getIntent();

    }
}
