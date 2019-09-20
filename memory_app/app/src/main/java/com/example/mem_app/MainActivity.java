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
                    popAlert("user login failed\nwrong password\nclick anywhere to sign up!");
                    showSignupPage();
                }
            }
        });


        Button button_register = (Button)super.findViewById(R.id.sign_up_button);
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // starting process on-click
                showSignupPage();
            }
        });



    }


    private void showSignupPage(){
        // show login page
        setContentView(R.layout.sign_up);
        final TextView text_username = (TextView)super.findViewById(R.id.signup_text_username);
        final TextView text_password = (TextView)super.findViewById(R.id.signup_text_password);
        final TextView text_first_name = (TextView)super.findViewById(R.id.signup_text_first_name);
        final TextView text_last_name = (TextView)super.findViewById(R.id.signup_text_last_name);
        final TextView text_email = (TextView)super.findViewById(R.id.signup_text_email);
        final TextView text_dob = (TextView)super.findViewById(R.id.signup_text_dob);

        Button button_go_back = (Button)super.findViewById(R.id.goback_test_button);

        button_go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // starting process on-click
                showLoginPage();
            }
        });


        Button button_signup = (Button)super.findViewById(R.id.signup_test_button);

        button_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // starting process on-click
                String resp = signupHttpSend(text_username, text_password, text_first_name,
                                            text_last_name, text_email , text_dob);
                // test flask's response
                if (resp.equals("###USER_REG_SUCCESS###")){
                    popAlert("user register success");
                    showLoginPage();
                }
                else if (resp.equals("###NON_VALID_EMAIL###")){
                    popAlert("User register failed.\nNon valid email.\nTry again.");
                    showSignupPage();
                }
                else if (resp.equals("###USER_REG_FAILED_2###")){
                    popAlert("User register failed.\nPlease fulfill your username, password and email.\nTry again.");
                    showSignupPage();
                }

                else if(resp.equals("###USER_REG_FAILED###")){
                    popAlert("User register failed.\nUser name already in use.\nTry again.");
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

        Button categoryButton = (Button)super.findViewById(R.id.categoryButton);
        categoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCategoryPage();
            }
        });

    }

    private void showCategoryPage(){
        setContentView(R.layout.category);

        Button previousButton = (Button)super.findViewById(R.id.previousButton);
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMainPage();
            }
        });

        Button logoutButton = (Button)super.findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLoginPage();
            }
        });

        Button homeButton = (Button)super.findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMainPage();
            }
        });
    }







    private String loginHttpSend(TextView text_username, TextView text_password){
        LoginHttp login_http = new LoginHttp(text_username.getText().toString(), text_password.getText().toString());
        return login_http.send();
    }

    private String signupHttpSend(TextView text_username, TextView text_password ,
                                  TextView text_first_name, TextView text_last_name,
                                  TextView text_email, TextView text_dob){
        SignupHttp signupHttp = new SignupHttp(text_username.getText().toString(), text_password.getText().toString(),
                                                text_first_name.getText().toString(),
                                                text_last_name.getText().toString(),
                                                text_email.getText().toString(), text_dob.getText().toString());
        return signupHttp.send();
    }

    private void popAlert(String text){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        AlertDialog ad = alertDialogBuilder.create();
        ad.setMessage(text);
        ad.show();
    }
}
