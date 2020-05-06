package com.gc.chatviewsdk.model;

import android.net.Uri;

public class ImageMessageData extends MessageData {
    private Uri imgUri;
    public ImageMessageData(Uri uri,String messageText, long time, String fromUserName, String fromUserId, boolean boolDelivered, messageDataType messageType) {
        super(messageText, time, fromUserName, fromUserId, boolDelivered, messageType);
        this.imgUri = uri;
    }

    public Uri getImgUri() {
        return imgUri;
    }

    public void setImgUri(Uri imgUri) {
        this.imgUri = imgUri;
    }
}
