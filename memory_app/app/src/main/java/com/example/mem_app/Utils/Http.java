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
        PORT_NUM = 7777;
        HOST_URL = "http://" + IP_ADDRESS + ":" + PORT_NUM;
    }

    protected String getHttp(String remote_url)
    {
        try {
            //http://10.0.0.2:7777/register?user_name=abc&user_pwd=111111
            URL obj = new URL(HOST_URL + remote_url);
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            String response = "";
            while ((inputLine = in.readLine()) != null) {
                response += inputLine;
            }
            in.close();
            return response;
        }
        catch (Exception e){
            //return getHttp(remote_url);
            return "getHttp conn error = " + e.getMessage();
        }
    }
}
