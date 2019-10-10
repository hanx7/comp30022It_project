package com.example.mem_app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.mem_app.Utils.Event;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mem_app.Utils.SingleItem;

public class ViewSingleEventActivity extends AppCompatActivity {

    ImageView eventImage;
    TextView eventName;
    TextView eventStory;
    public static Event currentEvent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Show corresponding xml page
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events);

        // Get text view and image view from xml page
        eventImage = (ImageView)findViewById(R.id.eventImageView);
        eventName = (TextView)findViewById(R.id.eventName);
        eventStory = (TextView)findViewById(R.id.eventStory);

        // Get event data from hashmap
        String eventIdentifier = getIntent().getExtras().getString("eventName");
        String itemID = ViewSingleItemActivity.currentItemID;
        Event event = MainActivity.user_profile.getItemHmap().get(itemID).getEventHmap().get(eventIdentifier);
        currentEvent = event;

        // Convert image string to bitmap
        String imageString = event.getEventImage();
        Bitmap temp = StringToBitMap(event.getEventImage());

        // Display text and image on screen
        eventImage.setImageBitmap(StringToBitMap(event.getEventImage()));
        eventName.setText(event.getEventTitle());
        eventStory.setText(event.getEventContent());
    }

    // Method to convert an image String back to bitmap
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

    // Return to EventCategoryActivity
    public void onGoBackButtonClick(View view) {
        Intent i = new Intent(this, EventCategoryActivity.class);
        startActivity(i);
    }

    // Go to EditEventButtonActivity
    public void onEditButtonClick(View view) {
        Intent i = new Intent(this, EditEventActivity.class);
        startActivity(i);
    }


    // Method to delete an event
    public void onDeleteButtonClick(View view) {
        final CharSequence[] options = { "Confirm","Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(ViewSingleEventActivity.this);
        builder.setTitle("Do you want to delete this event?");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Confirm"))
                {
                    // Collect event information
                    final String user_name = MainActivity.user_profile.user_name;
                    final String user_pwd = MainActivity.user_profile.user_pwd;
                    final String item_ID = ViewSingleItemActivity.currentItemID;
                    final String event_ID = currentEvent.getEventID();

                    // Send request to delete event via http and get response message
                    String resp = MainActivity.processor.deleteEventHttpSend(
                            user_name,
                            user_pwd,
                            item_ID,
                            event_ID);

                    Log.v("see res", resp);

                    // If delete success
                    if (resp.equals("###DELETE_EVENT_SUCCESS###")) {
                        final CharSequence[] options = { "OK" };
                        AlertDialog.Builder builder = new AlertDialog.Builder(ViewSingleEventActivity.this);
                        builder.setTitle("Success!");
                        builder.setItems(options, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int item) {
                                if (options[item].equals("OK")) {
                                    dialog.dismiss();

                                    // Return to home page
//                                    Intent i = new Intent(getApplicationContext(), EventCategoryActivity.class);
                                    Intent i = new Intent(HomeActivity.context, HomeActivity.class);
                                    startActivity(i);
                                }
                            }
                        });
                        builder.show();
                    }
                    // If delete fails
                    else if (resp.equals("###DELETE_ITEM_FAILED###")){
                        final CharSequence[] options = { "Cancel" };
                        AlertDialog.Builder builder = new AlertDialog.Builder(ViewSingleEventActivity.this);
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

                // If cancel delete, stay at View Single Event page
                else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
}
