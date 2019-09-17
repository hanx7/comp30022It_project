package com.example.mem_app.ui.login;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.mem_app.MainActivity;
import com.example.mem_app.R;

public class Login extends MainActivity {
    private TextView text_username;
    private TextView text_password;
    private Button button_login;
    private int id;

    public Login(){
        this.id = R.layout.login;
        this.text_username = (TextView)super.findViewById(R.id.login_text_username);
        this.text_password = (TextView)super.findViewById(R.id.login_text_password);
        this.button_login = (Button)super.findViewById(R.id.login_buttion_login);

        this.button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                loginButtonClicked();
                setContentView(R.layout.sign_up);
            }
        });
    }

    private Boolean loginButtonClicked(){

        return true;
    }
}
