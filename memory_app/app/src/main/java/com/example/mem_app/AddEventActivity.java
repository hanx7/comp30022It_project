package com.example.mem_app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.content.Context;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mem_app.Utils.SingleItem;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddEventActivity extends AppCompatActivity {

    private ImageView eventUploadImage;
    private String imageString;
    public static Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // show add event page
        setContentView(R.layout.add_event);
        TextView item_name = (TextView) super.findViewById(R.id.addEventItemName);
        item_name.setText(ViewSingleItemActivity.currentItemName);
        Intent intent = getIntent();
        context = getApplicationContext();
    }

    // when add button is clicked, add user input data into database
    public void onAddEventButtonClick(View view){
        final String eventTitle = ((TextView) super.findViewById(
                R.id.addEventTitle)).getText().toString();
        final String eventContent = ((TextView) super.findViewById(
                R.id.addEventContent)).getText().toString();
        final String eventTime = ((TextView) super.findViewById(
                R.id.addEventDate)).getText().toString();
        final String eventImage = imageString;
        final String username = MainActivity.user_profile.user_name;
        final String userPassword = MainActivity.user_profile.user_pwd;
        final String itemID = ViewSingleItemActivity.currentItemID;
        final String itemName = ViewSingleItemActivity.currentItemName;

        String resp = MainActivity.processor.addEventHttpSend(
                eventTitle,
                eventContent,
                eventTime,
                eventImage,
                itemName,
                itemID,
                username,
                userPassword);
        if (resp.equals("###ADD_EVENT_SUCCESS###")) {
            final CharSequence[] options = {"OK"};
            AlertDialog.Builder builder = new AlertDialog.Builder(AddEventActivity.this);
            builder.setTitle("Success!");
            builder.setItems(options, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int item) {
                    if (options[item].equals("OK")) {
                        dialog.dismiss();
                        Intent i = new Intent(context, EventCategoryActivity.class);
                        startActivity(i);
                    }
                }
            });
            builder.show();
        } else if (resp.equals("###ADD_EVENT_FAILED###")) {
            final CharSequence[] options = {"Cancel"};
            AlertDialog.Builder builder = new AlertDialog.Builder(AddEventActivity.this);
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

    // select image from system album then shown on the app
    public void onUploadEventImageButtonClick(View view) {
        final CharSequence[] options = { "Choose from Gallery","Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(AddEventActivity.this);
        builder.setTitle("Choose Your Photo");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Choose from Gallery"))
                {
                    Intent intent = new Intent(
                 Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 1);
                }
                else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();

    }

    // set selected image on screen
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));
                BitMapToString(bitmap);
                eventUploadImage= findViewById(R.id.addEventImage);
                eventUploadImage.setImageBitmap(bitmap);
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
        imageString = Base64.encodeToString(b, Base64.DEFAULT);
        return imageString;
    }

    // pop up text
    private void popAlert(String text){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setPositiveButton("ok",null);
        AlertDialog ad = alertDialogBuilder.create();
        ad.setMessage(text);
        ad.show();
    }
}
