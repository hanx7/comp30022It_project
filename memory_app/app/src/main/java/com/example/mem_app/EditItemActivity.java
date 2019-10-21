package com.example.mem_app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.app.AlertDialog;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import com.example.mem_app.Utils.SingleItem;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


public class EditItemActivity extends AppCompatActivity{

    private ImageView item_image;
    private String image_string;
    public static Context context;
    ImageView itemImage;
    TextView itemName;
    TextView itemDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Show edit item page
        setContentView(R.layout.edit_item);
        Intent intent = getIntent();
        context = getApplicationContext();

        // Display current information of item: name, image, description
        itemImage = (ImageView)findViewById(R.id.editItemImage);
        itemName = (TextView)findViewById(R.id.editItemName);
        itemDescription = (TextView)findViewById(R.id.editItemDescription);
        itemImage.setImageBitmap(ViewSingleItemActivity.imageBitmap);
        itemName.setText(ViewSingleItemActivity.itemNameText);
        itemDescription.setText(ViewSingleItemActivity.itemDescriptionText);
    }

    public void onEditButtonClick(View view){

        final EditText text_item_name = (EditText) super.findViewById(R.id.editItemName);
        final EditText text_description = (EditText) super.findViewById(R.id.editItemDescription);
        final String text_image_string = image_string;
        final String user_name = MainActivity.user_profile.user_name;
        final String user_pwd = MainActivity.user_profile.user_pwd;
        final String item_ID = ViewSingleItemActivity.currentItemID;

        System.out.println("USER_NAME = " + user_name);
        System.out.println("USER_PWD = " + user_pwd);
        System.out.println("Image String = " + text_image_string);

        String name = text_item_name.getText().toString();
        String description = text_description.getText().toString();
        String resp = MainActivity.processor.editItemHttpSend(name, description,
                image_string, user_name, user_pwd, item_ID);

        if (resp.equals("###EDIT_ITEM_SUCCESS###")) {
            final CharSequence[] options = { "OK" };
            AlertDialog.Builder builder = new AlertDialog.Builder(EditItemActivity.this);
            builder.setTitle("Success!");
            builder.setItems(options, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int item) {
                    if (options[item].equals("OK")) {
                        dialog.dismiss();
                        Intent i = new Intent(HomeActivity.context, HomeActivity.class);
                        startActivity(i);
                    }
                }
            });
            builder.show();
        }
        else if (resp.equals("###EDIT_ITEM_FAILED###")){
            final CharSequence[] options = { "Cancel" };
            AlertDialog.Builder builder = new AlertDialog.Builder(EditItemActivity.this);
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

    // return to ViewSingleItemActivity
    public void onCancelButtonClick(View view) {
        Intent i = new Intent(this, ViewSingleItemActivity.class);
        i.putExtra("itemID", ViewSingleItemActivity.currentItemID);
        startActivity(i);
    }

    // select image from system album then shown on the app
    public void onEditImageButtonClick(View view) {
        final CharSequence[] options = { "Choose from Gallery","Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(EditItemActivity.this);
        builder.setTitle("Choose Your Photo");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Choose from Gallery"))
                {
                    Intent intent = new Intent(Intent.ACTION_PICK,
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 1);
                }
                else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    // set new selected image on screen
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));
                BitMapToString(bitmap);
                item_image= findViewById(R.id.editItemImage);
                item_image.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // convert bitmap to string
    public String BitMapToString(Bitmap userImage1) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        userImage1.compress(Bitmap.CompressFormat.JPEG, 60, baos);
        byte[] b = baos.toByteArray();
        image_string = Base64.encodeToString(b, Base64.DEFAULT);
        return image_string;
    }

    // pop up text
    private void popAlert(String text){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setPositiveButton("ok",null);
        AlertDialog ad = alertDialogBuilder.create();
        ad.setMessage(text);
        //alertDialogBuilder.setPositiveButton("ok",null);
        ad.show();
    }

    // convert string to bitmap
    private Bitmap StringToBitMap(String encodedString){
        try{
            byte [] encodeByte = Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        }
        catch(Exception e){
            e.getMessage();
            return null;
        }
    }
}
