package com.example.mem_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mem_app.HttpReqs.LoginHttp;
import com.example.mem_app.Exceptions.*;
import com.example.mem_app.HttpReqs.SignupHttp;

public class MainActivity extends AppCompatActivity {

//    private TextView text_username;
//    private TextView text_password;
//    private Button button_login;
//    private Button
//
//    private Button main_button_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // following is code starting
        showLoginPage();
    }

    private void showLoginPage(){
        // show login page
        setContentView(R.layout.login);
        final TextView text_username = (TextView)super.findViewById(R.id.login_text_username);
        final TextView text_password = (TextView)super.findViewById(R.id.login_text_password);
        Button button_login = (Button)super.findViewById(R.id.login_buttion_login);

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // starting process on-click
                String resp = loginHttpSend(text_username, text_password);
                // test flask's response
                if (resp.equals("###USER_LOGIN_SUCCESS###")){
                    popAlert("user login success");
                    showMainPage();
                }
                else if (resp.equals("###USER_LOGIN_FAILED###")){
                    popAlert("user login failed");
                    showSignupPage();
                }
            }
        });
    }


    private void showSignupPage(){
        // show login page
        setContentView(R.layout.sign_up);
        final TextView text_username = (TextView)super.findViewById(R.id.signup_text_username);
        final TextView text_password = (TextView)super.findViewById(R.id.signup_text_password);
        Button button_login = (Button)super.findViewById(R.id.signup_test_button);

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // starting process on-click
                String resp = signupHttpSend(text_username, text_password);
                // test flask's response
                if (resp.equals("###USER_REG_SUCCESS###")){
                    popAlert("user register success");
                    showLoginPage();
                }
                else if (resp.equals("###USER_REG_FAILED###")){
                    popAlert("user register failed");
                    showSignupPage();
                }
            }
        });
    }

    private void showMainPage(){
        setContentView(R.layout.main);
        Button main_button_logout = (Button)super.findViewById(R.id.main_button_logout);
        main_button_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLoginPage();
            }
        });
    }

    private String loginHttpSend(TextView text_username, TextView text_password){
        LoginHttp login_http = new LoginHttp(text_username.getText().toString(), text_password.getText().toString());
        return login_http.send();
    }

    private String signupHttpSend(TextView text_username, TextView text_password){
        SignupHttp signupHttp = new SignupHttp(text_username.getText().toString(), text_password.getText().toString());
        return signupHttp.send();
    }

    private void popAlert(String text){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        AlertDialog ad = alertDialogBuilder.create();
        ad.setMessage(text);
        ad.show();
    }
}
