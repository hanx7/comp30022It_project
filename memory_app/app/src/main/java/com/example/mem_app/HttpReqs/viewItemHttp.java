package com.example.mem_app.HttpReqs;

import android.util.Log;

import androidx.core.content.res.TypedArrayUtils;

import com.example.mem_app.Utils.Http;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class viewItemHttp extends Http {


    //HashMap is to store all image in string format from mongoDB
    private HashMap<String, String []> items = new HashMap<String, String[]>();
    private String username;
    private String response = "";


    public viewItemHttp(String username){
        this.username = username;
    }

    public HashMap<String, String[]> send(){
        // main thread
        start();
        try{
            join();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return items;
    }

    public void run(){
        String url = "/getItems?" + "user_name=" + username;
        this.response = getHttp(url);
        JSONObject item = null;
        String itemInfo [];
        try {

            item = new JSONObject(response);
            String name = "";
            for(int i=0; i<item.length(); i++){
                name = item.getString(String.valueOf(i));
                itemInfo = name.split(";");
                items.put(itemInfo[0], itemInfo);
                Log.v("here", itemInfo[0]);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
