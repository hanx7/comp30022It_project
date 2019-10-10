package com.example.mem_app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mem_app.Utils.Event;
import com.example.mem_app.Utils.SingleItem;

import java.util.ArrayList;
import java.util.HashMap;

public class EventCategoryActivity extends AppCompatActivity {
    // Events is a hash map which keeps event name as key and the event instance as value
    HashMap<String, Event> events;

    // textview 1 to 6 are to show item name
    TextView eventName1;
    TextView eventName2;
    TextView eventName3;
    TextView eventName4;
    TextView eventName5;
    TextView eventName6;

    // 4 items correspond to events are going to show in 4 image view
    Event event1;
    Event event2;
    Event event3;
    Event event4;
    Event event5;
    Event event6;

    int numOfEvents;
    int currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Show event_category page
        setContentView(R.layout.event_category);
        TextView item_name = (TextView) super.findViewById(R.id.eventCategoryItemName);
        item_name.setText(ViewSingleItemActivity.currentItemName);
        eventName1 = (TextView)findViewById(R.id.eventCategoryEvent1);
        eventName2 = (TextView)findViewById(R.id.eventCategoryEvent2);
        eventName3 = (TextView)findViewById(R.id.eventCategoryEvent3);
        eventName4 = (TextView)findViewById(R.id.eventCategoryEvent4);
        eventName5 = (TextView)findViewById(R.id.eventCategoryEvent5);
        eventName6 = (TextView)findViewById(R.id.eventCategoryEvent6);
        events =  MainActivity.user_profile.getItems().get(ViewSingleItemActivity.currentItemID).getEventHmap();
        init();
    }


    // initailize page, show events depending on how much events we have
    private void init(){
        currentPage = 1;
        numOfEvents = events.size();
        if(numOfEvents >= 1){
            event1 = (new ArrayList<Event>(events.values())).get(0);
            String eventName = event1.getEventTitle();
            eventName1.setText(eventName);
        }
        if(numOfEvents >= 2){
            event2 = (new ArrayList<Event>(events.values())).get(1);
            String eventName = event2.getEventTitle();
            eventName2.setText(eventName);
        }
        if(numOfEvents >= 3){
            event3 = (new ArrayList<Event>(events.values())).get(2);
            String eventName = event3.getEventTitle();
            eventName3.setText(eventName);
        }
        if(numOfEvents >= 4){
            event4 = (new ArrayList<Event>(events.values())).get(3);
            String eventName = event4.getEventTitle();
            eventName4.setText(eventName);
        }
        if(numOfEvents >= 5){
            event5 = (new ArrayList<Event>(events.values())).get(4);
            String eventName = event5.getEventTitle();
            eventName5.setText(eventName);
        }
        if(numOfEvents >= 6){
            event6 = (new ArrayList<Event>(events.values())).get(5);
            String eventName = event6.getEventTitle();
            eventName6.setText(eventName);
        }

    }

    // Go to AddEventActivity
    public void onGoToAddEventButtonClick(View view){
        Intent i = new Intent(this, AddEventActivity.class);
        startActivity(i);
    }

    // when button is clicked on, go to event info page
    public void oneventCategoryEvent1Click(View view){
        Intent i = new Intent(this, ViewSingleEventActivity.class);
        i.putExtra("eventName", eventName1.getText().toString());
        startActivity(i);
    }


    public void oneventCategoryEvent2Click(View view){
        Intent i = new Intent(this, ViewSingleEventActivity.class);
        i.putExtra("eventName", eventName2.getText().toString());
        startActivity(i);
    }

    public void oneventCategoryEvent3Click(View view){
        Intent i = new Intent(this, ViewSingleEventActivity.class);
        i.putExtra("eventName", eventName3.getText().toString());
        startActivity(i);
    }

    public void oneventCategoryEvent4Click(View view){
        Intent i = new Intent(this, ViewSingleEventActivity.class);
        i.putExtra("eventName", eventName4.getText().toString());
        startActivity(i);
    }

    public void oneventCategoryEvent5Click(View view){
        Intent i = new Intent(this, ViewSingleEventActivity.class);
        i.putExtra("eventName", eventName5.getText().toString());
        startActivity(i);
    }

    public void oneventCategoryEvent6Click(View view){
        Intent i = new Intent(this, ViewSingleEventActivity.class);
        i.putExtra("eventName", eventName6.getText().toString());
        startActivity(i);
    }
}
