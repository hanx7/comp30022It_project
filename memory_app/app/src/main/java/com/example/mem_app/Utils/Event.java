package com.example.mem_app.Utils;
import java.util.Date;

public class Event {
    private String eventTime;
    private String eventContent;
    private String eventTitle;
    private String itemName;
    private String itemID;
    private String eventImage;

    public Event(String eventTitle, String eventContent, String eventTime, String eventImage, String itemName, String itemID){
        this.eventContent = eventContent;
        this.eventTime = eventTime;
        this.eventTitle = eventTitle;
        this.eventImage = eventImage;
        this.itemName = itemName;
        this.itemID = itemID;
    }

    public String getEventContent(){ return this.eventContent;}
    public String getEventTitle(){ return this.eventTitle;}
    public String getEventImage(){ return this.eventImage;}
    public String getItemName(){ return itemName;}
    public String getEventTime(){ return eventTime;}
    public String getItemID(){ return itemID;}
}
