package com.example.mem_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UserInformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Show login page
        setContentView(R.layout.user_info);
        final TextView profile_user_name = (TextView) super.findViewById(R.id.profile_user_name);
        profile_user_name.setText(MainActivity.user_profile.user_name);
    }

    public void onProfileMyItemsClick(View view) {
        Intent i = new Intent(this, MyCategoryActivity.class);
        startActivity(i);
    }



}
