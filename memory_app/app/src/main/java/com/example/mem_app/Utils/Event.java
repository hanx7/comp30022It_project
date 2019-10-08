package com.example.mem_app.Utils;
import java.util.Date;

public class Event {
    private String eventTime;
    private String eventContent;
    private String eventTitle;
    private String itemName;
    private String itemID;
    private String eventImage;
    private String eventID;

    public Event(String eventTitle, String eventContent, String eventTime, String eventImage, String itemName, String itemID, String eventID){
        this.eventContent = eventContent;
        this.eventTime = eventTime;
        this.eventTitle = eventTitle;
        this.eventImage = eventImage;
        this.itemName = itemName;
        this.itemID = itemID;
        this.eventID = eventID;
    }

    public String getEventContent(){ return this.eventContent;}
    public String getEventTitle(){ return this.eventTitle;}
    public String getEventImage(){ return this.eventImage;}
    public String getItemName(){ return itemName;}
    public String getEventTime(){ return eventTime;}
    public String getItemID(){ return itemID;}
    public String getEventID() {return eventID;}
}
