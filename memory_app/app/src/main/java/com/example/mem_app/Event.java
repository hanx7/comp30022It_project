package com.example.mem_app;
import java.util.Date;

public class Event {
    private Date eventTime;
    private String eventContent;
    private String eventTitle;
    private String itemName;

    public Event(String eventTitle, String eventContent, String itemName, Date eventTime){
        this.eventContent = eventContent;
        this.eventTime = eventTime;
        this.eventTitle = eventTitle;
        this.itemName = itemName;
    }

    public String getEventContent(){ return eventContent;}
    public String getEventTitle(){ return eventTitle;}
    public String getItemName(){ return itemName;}
    public Date getEventTime(){ return eventTime;}

}
