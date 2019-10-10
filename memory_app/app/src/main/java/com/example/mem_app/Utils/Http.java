package com.example.mem_app.Utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public abstract class Http extends Thread{
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
            // URL Concatenate
            URL obj = new URL(HOST_URL + remote_url);

            // GET Connection Initialization
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
            conn.setRequestMethod("GET");

            // GET Server Text Reply
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            String response = "";
            while ((inputLine = in.readLine()) != null)
                response += inputLine;
            in.close();
            return response;
        }
        catch (Exception e){
            //return getHttp(remote_url);
            return "GET Error = " + e.getMessage();
        }
    }

    protected String postHttp(String remote_url, String content){
        try{
            // URL Concatenate
            URL url = new URL(HOST_URL + remote_url);

            // POST Connection Initialization
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestMethod("POST");

            // Send Content to Server
            OutputStream os = conn.getOutputStream();
            //transfer the image string into binary code
            os.write(content.getBytes("UTF-8"));
            os.close();

            // GET Server Text Reply
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            String response = "";
            while ((inputLine = in.readLine()) != null)
                response += inputLine;
            in.close();
            return response;
        }
        catch (Exception e){
            return "POST Error = " + e.getMessage();
        }
    }

}
