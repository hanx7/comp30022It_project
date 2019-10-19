package com.example.mem_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UserInformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Show user information associated to logged in user
        setContentView(R.layout.user_info);
        final TextView profile_user_name = (TextView) super.findViewById(R.id.profile_user_name);
        profile_user_name.setText(MainActivity.user_profile.user_name);

        final TextView profileFirstName = (TextView) super.findViewById(R.id.profile_first_name);
        profileFirstName.setText(MainActivity.user_profile.firstName);

        final TextView profilelastName = (TextView) super.findViewById(R.id.profile_last_name);
        profilelastName.setText(MainActivity.user_profile.lastName);
    }

    public void onProfileMyItemsClick(View view) {
        Intent i = new Intent(this, MyCategoryActivity.class);
        startActivity(i);
    }

    public void onMainButtonCLick(View view){
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);

    }


}
