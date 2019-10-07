package com.example.mem_app;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Show login page
        setContentView(R.layout.sign_up);
    }

    public void onGoBackButtonClick(View view) {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

    public void onSignUpButtonClick(View view) {
        final TextView text_username = (TextView) super.findViewById(R.id.signupUsername);
        final TextView text_password = (TextView) super.findViewById(R.id.signupPassword);
        final TextView text_first_name = (TextView) super.findViewById(R.id.signupFirstName);
        final TextView text_last_name = (TextView) super.findViewById(R.id.signupLastName);
        final TextView text_email = (TextView) super.findViewById(R.id.signupEmail);
        final TextView text_dob = (TextView) super.findViewById(R.id.signupDOB);

        String resp = MainActivity.processor.signupHttpSend(text_username, text_password,
                text_first_name, text_last_name, text_email, text_dob);

        // test flask's response
        if (resp.equals("###USER_REG_SUCCESS###")) {
            popAlert("user register success");
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);


        } else if (resp.equals("###NON_VALID_EMAIL###")) {
            popAlert("Non valid email.\nTry again.");
            Intent i = new Intent(this, SignUpActivity.class);
            startActivity(i);

        } else if (resp.equals("###USER_REG_FAILED_2###")) {
            popAlert("Please fulfill all information.\nTry again.");
            Intent i = new Intent(this, SignUpActivity.class);
            startActivity(i);

        } else if (resp.equals("###USER_REG_FAILED###")) {
            popAlert("User name already in use.\nTry again.");
            Intent i = new Intent(this, SignUpActivity.class);
            startActivity(i);

        } else if (resp.equals("###NON_VALID_DOB###")) {
            popAlert("Non valid dob.\nTry again.");
            Intent i = new Intent(this, SignUpActivity.class);
            startActivity(i);
        }
    }

    private void popAlert(String text){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setPositiveButton("ok",null);
            AlertDialog ad = alertDialogBuilder.create();
            ad.setMessage(text);
            //alertDialogBuilder.setPositiveButton("ok",null);
            ad.show();
    }
}
