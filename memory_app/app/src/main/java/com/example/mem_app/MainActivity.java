package com.example.mem_app;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import android.content.Context;
import com.example.mem_app.HttpReqs.LoginHttp;
import com.example.mem_app.Exceptions.*;
import com.example.mem_app.HttpReqs.SignupHttp;

public class MainActivity extends AppCompatActivity {

    public static Processor processor = new Processor();
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();

        // Show login page
        Intent i = new Intent(context ,LoginActivity.class);
        startActivity(i);


    }


}
