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

        // Show login page
        setContentView(R.layout.login);
        context = getApplicationContext();

    }

    public void onLoginButtonClick(View view) {
        final TextView text_username = (TextView) super.findViewById(R.id.login_text_username);
        final TextView text_password = (TextView) super.findViewById(R.id.login_text_password);

        // starting process on-click
        String resp = MainActivity.processor.loginHttpSend(text_username, text_password);
        // test flask's response
        if (resp.equals("###USER_LOGIN_SUCCESS###")) {
            popAlert("user login success");
            Intent i = new Intent(context, HomeActivity.class);
            startActivity(i);

            //showMainPage();
        } else if (resp.equals("###USER_LOGIN_FAILED###")) {
            popAlert("user login failed\nwrong password\nclick anywhere to sign up!");

            Intent i = new Intent(context, SignUpActivity.class);
            startActivity(i);
        }
    }

    public void onSignUpButtonClick(View view){
        Intent i = new Intent(context, SignUpActivity.class);
        startActivity(i);
    }


    private void popAlert(String text){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        AlertDialog ad = alertDialogBuilder.create();
        ad.setMessage(text);
        ad.show();
    }

}
