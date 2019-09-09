package com.example.mem_app.Utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class Http extends Thread{
    private final String IP_ADDRESS;
    private final int PORT_NUM;
    private final String HOST_URL;

    public Http(){
        IP_ADDRESS = "10.0.2.2";
        PORT_NUM = 5000;
        HOST_URL = "http://" + IP_ADDRESS + ":" + PORT_NUM;
    }

    protected String getHttp(String remote_url)
    {
        try
        {
            URL obj = new URL(HOST_URL + remote_url); //http://127.0.0.1:5000/login?user_name=wajdhajwhd&user_pwd=376432764
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));//用来从buffer里读flask 传来的东西
            String inputLine;
            StringBuffer response = new StringBuffer();

            //String response ="";
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        }
        catch (Exception e){
            return e.getMessage();
        }

    }
}
