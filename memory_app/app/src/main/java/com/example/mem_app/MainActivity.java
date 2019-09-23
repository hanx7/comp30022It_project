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


//    private void showSignupPage(){
//        // show login page
//        setContentView(R.layout.sign_up);
//        final TextView text_username = (TextView)super.findViewById(R.id.signup_text_username);
//        final TextView text_password = (TextView)super.findViewById(R.id.signup_text_password);
//        final TextView text_first_name = (TextView)super.findViewById(R.id.signup_text_first_name);
//        final TextView text_last_name = (TextView)super.findViewById(R.id.signup_text_last_name);
//        final TextView text_email = (TextView)super.findViewById(R.id.signup_text_email);
//        final TextView text_dob = (TextView)super.findViewById(R.id.signup_text_dob);
//
//        Button button_go_back = (Button)super.findViewById(R.id.goback_test_button);
//
//        button_go_back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // starting process on-click
//                showLoginPage();
//            }
//        });
//
//
//        Button button_signup = (Button)super.findViewById(R.id.signup_test_button);
//
//        button_signup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // starting process on-click
//                String resp = signupHttpSend(text_username, text_password, text_first_name,
//                                            text_last_name, text_email , text_dob);
//                // test flask's response
//                if (resp.equals("###USER_REG_SUCCESS###")){
//                    popAlert("user register success");
//                    showLoginPage();
//                }
//                else if (resp.equals("###NON_VALID_EMAIL###")){
//                    popAlert("User register failed.\nNon valid email.\nTry again.");
//                    showSignupPage();
//                }
//                else if (resp.equals("###USER_REG_FAILED_2###")){
//                    popAlert("User register failed.\nPlease fulfill your username, password and email.\nTry again.");
//                    showSignupPage();
//                }
//
//                else if(resp.equals("###USER_REG_FAILED###")){
//                    popAlert("User register failed.\nUser name already in use.\nTry again.");
//                    showSignupPage();
//                }
//            }
//        });
//    }

//    private void showMainPage(){
//        setContentView(R.layout.main);
//        Button main_button_logout = (Button)super.findViewById(R.id.main_button_logout);
//        main_button_logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showLoginPage();
//            }
//        });
//
//        Button categoryButton = (Button)super.findViewById(R.id.categoryButton);
//        categoryButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showCategoryPage();
//            }
//        });

//        Button userInfoButton = (Button)super.findViewById(R.id.myInfoButton);
//        userInfoButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showUserInfoPage();
//            }
//        });

//    }

//    private void showCategoryPage(){
//        setContentView(R.layout.category);
//
//        Button previousButton = (Button)super.findViewById(R.id.previousButton);
//        previousButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showMainPage();
//            }
//        });
//
//        Button logoutButton = (Button)super.findViewById(R.id.logoutButton);
//        logoutButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showLoginPage();
//            }
//        });
//
//        Button homeButton = (Button)super.findViewById(R.id.homeButton);
//        homeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showMainPage();
//            }
//        });
//    }

    private void popAlert(String text){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        AlertDialog ad = alertDialogBuilder.create();
        ad.setMessage(text);
        ad.show();
    }

}
