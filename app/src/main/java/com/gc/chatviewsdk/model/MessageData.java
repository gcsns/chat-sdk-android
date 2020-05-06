package com.gc.chatviewsdk.model;

public class MessageData {
    public enum messageDataType {
        Text,
        Image,
        Pdf
    }
    private String messageText = "";
    private long time;
    private String fromUserName = "";
    private String fromUserId = "";
    private boolean boolDelivered = true;
    private messageDataType messageType;


    public MessageData(String messageText, long time, String fromUserName, String fromUserId, boolean boolDelivered, messageDataType messageType) {
        this.messageText = messageText;
        this.time = time;
        this.fromUserName = fromUserName;
        this.fromUserId = fromUserId;
        this.boolDelivered = boolDelivered;
        this.messageType = messageType;
    }

    public String getmessageText() {
        return messageText;
    }

    public void setmessageText(String messageText) {
        this.messageText = messageText;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }



    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public boolean isBoolDelivered() {
        return boolDelivered;
    }

    public void setBoolDelivered(boolean boolDelivered) {
        this.boolDelivered = boolDelivered;
    }

    public MessageData.messageDataType getMessageType() {
        return messageType;
    }

}
