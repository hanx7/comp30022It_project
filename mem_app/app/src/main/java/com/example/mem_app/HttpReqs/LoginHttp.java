package com.example.mem_app.HttpReqs;

import com.example.mem_app.Utils.Http;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginHttp extends Http {
    private String username;
    private String password;
    private String email;

    public LoginHttp(String username, String password,String email){
        super();
        this.username = username;
        this.password = password;
        this.email = email;
    }
    //建立一个新的线程 ， 把login作用的Http请求发给flask， 并打印出flask的reply
    public void send(){
        start();
    }

    public void run(){
        String url = "/login?" + "user_name=" + username + "&user_pwd=" + password+"&user_email=" + email;
        String res = getHttp(url);
        System.out.println(res);
        /*try
        {
            URL obj = new URL("http://10.0.2.2:5000/login?user_name=awdejawhdjuwahdj&user_pwd=28173812732313123");
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println(response.toString());
        }
        catch (Exception e){
            System.out.println(e.toString());
        }*/

    }
}
