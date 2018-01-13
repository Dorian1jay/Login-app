package com.example.jay.loginapp.Models;

/**
 * Created by jay on 10/01/18.
 */

public class Message {
    private String sender;
    private String reciever;
    private String messagecontent;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private String date;

    public Message(String s,String r, String mc, String d)
    {
        this.sender = s;
        this.reciever = r;
        this.messagecontent = mc;
        this.date = d;

    }

    public Message()
    {


    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReciever() {
        return reciever;
    }

    public void setReciever(String reciever) {
        this.reciever = reciever;
    }

    public String getMessagecontent() {
        return messagecontent;
    }

    public void setMessagecontent(String messagecontent) {
        this.messagecontent = messagecontent;
    }


}
