package com.example.mem_app.HttpReqs;

import com.example.mem_app.Utils.Http;

import java.io.IOException;
import java.util.ArrayList;

public class viewItemHttp extends Http {


    //ArrayList is to store all image in string format from mongoDB
    private ArrayList<String> imageList = new ArrayList<>();
    private String response = "";
    public viewItemHttp(){}

    public String send(){
        // main thread
        start();
        try{
            join();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return response;
    }

    public void run(){

    }
}
