package com.example.mem_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Context;
import android.content.Intent;

import com.example.mem_app.HttpReqs.LoginHttp;
import com.example.mem_app.Exceptions.*;
import com.example.mem_app.HttpReqs.SignupHttp;
public class HomeActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Show login page
        setContentView(R.layout.main);
        Intent intent = getIntent();

    }

    public void onCategoryButtonClick(View view) {
        Intent i = new Intent(this, CategoryActivity.class);
        startActivity(i);
    }

    public void onViewAllItemsButtonClick(View view) {
        Intent i = new Intent(this, ViewAllItemsActivity.class);
        startActivity(i);
    }


    public void onUserInformationButtonClick(View view) {
        Intent i = new Intent(this, UserInformationActivity.class);
        startActivity(i);
    }

    public void onLogoutButtonClick(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void onAddItemButtonClick(View view) {
        Intent i = new Intent(this, AddItemActivity.class);
        startActivity(i);
    }


}
