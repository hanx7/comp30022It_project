package com.example.mem_app;

import android.widget.TextView;

import com.example.mem_app.HttpReqs.AddItemHttp;
import com.example.mem_app.HttpReqs.GetUserInfoHttp;
import com.example.mem_app.HttpReqs.LoginHttp;
import com.example.mem_app.HttpReqs.SignupHttp;
import com.example.mem_app.HttpReqs.ViewItemHttp;

public class Processor {

    public Processor() {
    }

    public String loginHttpSend(TextView text_username, TextView text_password){
        LoginHttp login_http = new LoginHttp(text_username.getText().toString(), text_password.getText().toString());
        return login_http.send();
    }

    public String signupHttpSend(TextView text_username, TextView text_password ,
                                  TextView text_first_name, TextView text_last_name,
                                  TextView text_email, TextView text_dob){
        SignupHttp signupHttp = new SignupHttp(text_username.getText().toString(), text_password.getText().toString(),
                text_first_name.getText().toString(),
                text_last_name.getText().toString(),
                text_email.getText().toString(), text_dob.getText().toString());
        return signupHttp.send();
    }

    public String addItemHttpSend(  TextView text_item_name,
                                    TextView text_description,
                                    String image_string,
                                    String user_name,
                                    String user_pwd){

        AddItemHttp addItemHttp = new AddItemHttp(  text_item_name.getText().toString(),
                                                    text_description.getText().toString(),
                                                    image_string,
                                                    user_name,
                                                    user_pwd);
        return addItemHttp.send();
    }

    public String viewItemHttpSend(String user_name, String user_password){

        ViewItemHttp viewItemHttp = new ViewItemHttp(user_name,user_password);
        return viewItemHttp.send();
    }

    public String getUserInfoHttpSend(String user_name, String user_password) {
        GetUserInfoHttp getUserInfoHttp = new GetUserInfoHttp(user_name,user_password);
        return getUserInfoHttp.send();
    }

}
