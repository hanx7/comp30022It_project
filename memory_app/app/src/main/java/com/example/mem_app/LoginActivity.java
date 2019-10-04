package com.example.mem_app;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Show login page
        setContentView(R.layout.login);
    }

    public void onLoginButtonClick(View view) {
        final TextView text_username = (TextView) super.findViewById(R.id.loginUsername);
        final TextView text_password = (TextView) super.findViewById(R.id.loginPassword);

        // starting process on-click
        String resp = MainActivity.processor.loginHttpSend(text_username, text_password);
        // test flask's response
        if (resp.equals("###USER_LOGIN_SUCCESS###")) {
            try {
                popAlert("user login success");
                MainActivity.user_profile.user_name = text_username.getText().toString();
                MainActivity.user_profile.user_pwd = text_password.getText().toString();
                String fullName = MainActivity.processor.getUserInfoHttpSend(
                        MainActivity.user_profile.user_name,
                        MainActivity.user_profile.user_pwd);

                MainActivity.user_profile.firstName = fullName.split(" ")[0];
                MainActivity.user_profile.lastName = fullName.split(" ")[1];

                Intent i = new Intent(this, HomeActivity.class);
                startActivity(i);
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }

            //showMainPage();
        }
        else if (resp.equals("###USER_LOGIN_FAILED###")) {
            try{
                popAlert("user login failed\nwrong password\nclick anywhere to sign up!");
                Intent i = new Intent(this, SignUpActivity.class);
                startActivity(i);
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    public void onSignUpButtonClick(View view){
        Intent i = new Intent(this,SignUpActivity.class);
        startActivity(i);
    }

    private void popAlert(String text){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        AlertDialog ad = alertDialogBuilder.create();
        ad.setMessage(text);
        ad.show();
    }
}
