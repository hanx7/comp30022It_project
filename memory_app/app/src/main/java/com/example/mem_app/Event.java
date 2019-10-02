package com.example.mem_app;
import java.util.Date;

public class Event {
    private Date eventTime;
    private String eventContent;
    private String eventTitle;
    private String itemName;
    private String itemID;
    public Event(String eventTitle, String eventContent, String itemName, Date eventTime, String itemID){
        this.eventContent = eventContent;
        this.eventTime = eventTime;
        this.eventTitle = eventTitle;
        this.itemName = itemName;
        this.itemID = itemID;
    }

    public String getEventContent(){ return eventContent;}
    public String getEventTitle(){ return eventTitle;}
    public String getItemName(){ return itemName;}
    public Date getEventTime(){ return eventTime;}
    public String getItemID(){ return itemID;}
}
