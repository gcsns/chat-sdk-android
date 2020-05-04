package com.gc.chatviewsdk.model;

public class MessageData {
    public static final int TYPE_TEXT = 0;
    public static final int TYPE_IMAGE = 1;
    public static final int TYPE_PDF = 2;
    private String messageText = "";
    private long time;
    private String fromUserName = "";
    private String fromUserId = "";
    private boolean boolDelivered = true;
    private int[] Type = {TYPE_TEXT,TYPE_IMAGE,TYPE_PDF};

    public MessageData(String messageText, long time, String fromUserName, String fromUserId, boolean boolDelivered, int type) {
        this.messageText = messageText;
        this.time = time;
        this.fromUserName = fromUserName;
        this.fromUserId = fromUserId;
        this.boolDelivered = boolDelivered;
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


    public static int getTypeText() {
        return TYPE_TEXT;
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

    public int[] getType() {
        return Type;
    }

    public void setType(int[] type) {
        Type = type;
    }

    public boolean isBoolDelivered() {
        return boolDelivered;
    }

    public void setBoolDelivered(boolean boolDelivered) {
        this.boolDelivered = boolDelivered;
    }
}
