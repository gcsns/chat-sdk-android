package com.gc.chatviewsdk.model;

public class Message {
    private String strLastMessage,strUserName,strUserIcon;
    private long lastMessageTime;
    public Message(String name,String message,String icon,long time){
        this.strLastMessage = message;
        this.strUserName = name;
        this.strUserIcon = icon;
        this.lastMessageTime = time;
    }

    public String getStrLastMessage() {
        return strLastMessage;
    }

    public String getStrUserName() {
        return strUserName;
    }

    public String getStrUserIcon() {
        return strUserIcon;
    }

    public long getLastMessageTime() {
        return lastMessageTime;
    }
}
