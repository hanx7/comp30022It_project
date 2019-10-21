package com.example.mem_app;

import android.app.AlertDialog;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class EditEventActivity extends AppCompatActivity {

    private ImageView event_image;
    private String image_string;
    public static Context context;

    ImageView eventImage;
    TextView eventTitle;
    TextView eventStory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Show edit event page
        setContentView(R.layout.edit_event);

        Intent intent = getIntent();
        context = getApplicationContext();

        // Display current information of event: title, date, image, description
        eventImage = (ImageView) findViewById(R.id.editEventImage);
        eventTitle = (TextView) findViewById(R.id.editEventTitle);
        eventStory = (TextView) findViewById(R.id.editEventStory);

        eventImage.setImageBitmap(StringToBitMap(
                ViewSingleEventActivity.currentEvent.getEventImage()));
        eventTitle.setText(ViewSingleEventActivity.currentEvent.getEventTitle());
        eventStory.setText(ViewSingleEventActivity.currentEvent.getEventContent());
    }

    // send new data to database
    public void onEventEditButtonClick(View view){

        final EditText text_event_name = (EditText) super.findViewById(R.id.editEventTitle);
        final EditText text_event_story = (EditText) super.findViewById(R.id.editEventStory);
        final String text_image_string = image_string;
        final String user_name = MainActivity.user_profile.user_name;
        final String user_pwd = MainActivity.user_profile.user_pwd;
        final String item_ID = ViewSingleItemActivity.currentItemID;
        final String event_ID = ViewSingleEventActivity.currentEvent.getEventID();

        String title = text_event_name.getText().toString();
        String story = text_event_story.getText().toString();

        //Send request to change corresponding entry in database via http, and get response message
        String resp = MainActivity.processor.editEventHttpSend(image_string,
                user_name,
                user_pwd,
                item_ID,
                ViewSingleItemActivity.currentItemName,
                event_ID,
                title,
                story,
                ViewSingleEventActivity.currentEvent.getEventTime());

        // Upon success, return to homepage
        if (resp.equals("###EDIT_EVENT_SUCCESS###")) {
            final CharSequence[] options = { "OK" };
            AlertDialog.Builder builder = new AlertDialog.Builder(EditEventActivity.this);
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
        // Failure
        else if (resp.equals("###EDIT_ITEM_FAILED###")){
            final CharSequence[] options = { "Cancel" };
            AlertDialog.Builder builder = new AlertDialog.Builder(EditEventActivity.this);
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

    // return to current event activity
    public void onEventCancelButtonClick(View view) {
        Intent i = new Intent(this, ViewSingleEventActivity.class);
        i.putExtra("itemID", ViewSingleEventActivity.currentEvent.getEventTitle());
        startActivity(i);
    }

    // select image from system album then shown on the app
    public void onEditEventImageButtonClick(View view) {
        final CharSequence[] options = { "Choose from Gallery","Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(EditEventActivity.this);
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
                BitMapToString(bitmap);
                event_image= findViewById(R.id.editEventImage);
                event_image.setImageBitmap(bitmap);
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