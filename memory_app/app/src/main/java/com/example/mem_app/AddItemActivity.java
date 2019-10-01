package com.example.mem_app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.app.AlertDialog;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


public class AddItemActivity extends AppCompatActivity{

    private ImageView item_image;
    private String image_string;
    public static Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Show add_item page
        setContentView(R.layout.add_item);
        Intent intent = getIntent();
        context = getApplicationContext();
    }

    public void onAddButtonClick(View view){
        final TextView text_item_name = (TextView) super.findViewById(R.id.addItemName);
        final TextView text_description = (TextView) super.findViewById(R.id.addItemDescription);
        final String text_image_string = image_string;
        final String user_name = MainActivity.user_profile.user_name;
        final String user_pwd = MainActivity.user_profile.user_pwd;

        System.out.println("USER_NAME = " + user_name);
        System.out.println("USER_PWD = " + user_pwd);
        System.out.println("Image String = " + text_image_string);

        String resp = MainActivity.processor.addItemHttpSend(   text_item_name,
                                                                text_description,
                                                                text_image_string,
                                                                user_name,
                                                                user_pwd);
        if (resp.equals("###ADD_ITEM_SUCCESS###")) {
            final CharSequence[] options = { "OK" };
            AlertDialog.Builder builder = new AlertDialog.Builder(AddItemActivity.this);
            builder.setTitle("Success!");
            builder.setItems(options, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("OK")) {
                    dialog.dismiss();
                    Intent i = new Intent(context, HomeActivity.class);
                    startActivity(i);
                }
                }
            });
            builder.show();
        }
        else if (resp.equals("###ADD_ITEM_FAILED###")){
            final CharSequence[] options = { "Cancel" };
            AlertDialog.Builder builder = new AlertDialog.Builder(AddItemActivity.this);
            builder.setTitle("Failure!");
            builder.setItems(options, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
                }
            });
            builder.show();
        }

    }
    public void onLogoutButtonClick(View view) {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

    public void onHomeButtonClick(View view) {
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
    }


    public void onUploadButtonClick(View view) {
        final CharSequence[] options = { "Choose from Gallery","Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(AddItemActivity.this);
        builder.setTitle("Choose Your Photo");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Choose from Gallery"))
                {
                    Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 1);
                }
                else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));
                BitMapToString(bitmap);
                item_image= findViewById(R.id.addItemImage);
                item_image.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String BitMapToString(Bitmap userImage1) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        userImage1.compress(Bitmap.CompressFormat.PNG, 60, baos);
        byte[] b = baos.toByteArray();
        image_string = Base64.encodeToString(b, Base64.DEFAULT);
        return image_string;
    }

    private void popAlert(String text){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setPositiveButton("ok",null);
        AlertDialog ad = alertDialogBuilder.create();
        ad.setMessage(text);
        //alertDialogBuilder.setPositiveButton("ok",null);
        ad.show();
    }



}
