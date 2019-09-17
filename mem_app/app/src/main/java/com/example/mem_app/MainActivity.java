package com.example.mem_app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mem_app.HttpReqs.LoginHttp;

public class MainActivity extends AppCompatActivity {

    private TextView text_username;
    private TextView text_password;
    private TextView text_email;
    private Button button_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        login_page = new Login();
        setContentView(R.layout.login);
        this.text_username = (TextView)super.findViewById(R.id.login_text_username);
        this.text_password = (TextView)super.findViewById(R.id.login_text_password);
        this.text_email=(TextView)super.findViewById(R.id.login_text_email);

        this.button_login = (Button)super.findViewById(R.id.login_buttion_login);

        this.button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginHttpSend();
            }
        });
    }

    private void loginHttpSend(){
        LoginHttp login_http = new LoginHttp(this.text_username.getText().toString(), this.text_password.getText().toString(),this.text_email.getText().toString());
        login_http.send();
    }

}
