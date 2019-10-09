package com.example.mem_app;

import android.widget.EditText;
import android.widget.TextView;

import com.example.mem_app.HttpReqs.AddEventHttp;
import com.example.mem_app.HttpReqs.AddItemHttp;
import com.example.mem_app.HttpReqs.DeleteItemHttp;
import com.example.mem_app.HttpReqs.EditEventHttp;
import com.example.mem_app.HttpReqs.EditItemHttp;
import com.example.mem_app.HttpReqs.GetUserInfoHttp;
import com.example.mem_app.HttpReqs.LoginHttp;
import com.example.mem_app.HttpReqs.SignupHttp;
import com.example.mem_app.HttpReqs.ViewEventHttp;
import com.example.mem_app.HttpReqs.ViewItemHttp;
import com.example.mem_app.HttpReqs.DeleteEventHttp;

import java.util.Date;

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


    public String editItemHttpSend(  String text_item_name,
                                    String text_description,
                                    String image_string,
                                    String user_name,
                                    String user_pwd,
                                    String item_ID){

        EditItemHttp editItemHttp = new EditItemHttp(  text_item_name,
                text_description,
                image_string,
                user_name,
                user_pwd,
                item_ID);
        return editItemHttp.send();
    }


    // call view item http method which will invoke the method in view item http
    public String viewItemHttpSend(String user_name, String user_password){

        ViewItemHttp viewItemHttp = new ViewItemHttp(user_name,user_password);
        return viewItemHttp.send();
    }

    public String viewEventHttpSend(String user_name, String user_password, String item_ID){

        ViewEventHttp viewEventHttp = new ViewEventHttp(user_name,user_password, item_ID);
        return viewEventHttp.send();
    }

    public String getUserInfoHttpSend(String user_name, String user_password) {
        GetUserInfoHttp getUserInfoHttp = new GetUserInfoHttp(user_name,user_password);
        return getUserInfoHttp.send();
    }

    public String addEventHttpSend(String eventTitle, String eventContent, String eventTime,
                                   String eventImage, String itemName, String itemID,
                                   String username, String userPassword){

        AddEventHttp addEventHttp = new AddEventHttp(eventTitle,
                eventContent,
                eventTime,
                eventImage,
                itemName,
                itemID,
                username,
                userPassword);
        return addEventHttp.send();
    }

    public String deleteItemHttpSend(String text_username, String text_password, String item_ID){
        DeleteItemHttp deleteItemHttp = new DeleteItemHttp(text_username, text_password,item_ID);
        return deleteItemHttp.send();
    }

    public String deleteEventHttpSend(String text_username, String text_password, String item_ID, String event_ID){
        DeleteEventHttp deleteEventHttp = new DeleteEventHttp(text_username, text_password,item_ID, event_ID);
        return deleteEventHttp.send();
    }

    public String editEventHttpSend(String image_string,
                                    String username,
                                    String password,
                                    String item_ID,
                                    String item_name,
                                    String event_ID,
                                    String event_title,
                                    String event_content,
                                    String event_date){

        EditEventHttp editEventHttp = new EditEventHttp(image_string,
                username,
                password,
                item_ID,
                item_name,
                event_ID,
                event_title,
                event_content,
                event_date);
        return editEventHttp.send();
    }


}
