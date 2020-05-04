package com.gc.chatviewsdk.core;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ChatViewApplication extends Application {
    private static ChatViewApplication chatViewApplication;
    public static ChatViewApplication getInstance ()
    {
        if (chatViewApplication == null)
        {
            chatViewApplication = new ChatViewApplication();
        }
        return chatViewApplication;
    }
    @Override
    public void onCreate ()
    {
        super.onCreate();

        chatViewApplication = this;
    }

    public boolean isNetworkAvailable ()
    {
        ConnectivityManager lConnectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo lNetworkInfo         = null;
        if (lConnectivityManager != null) {
            lNetworkInfo = lConnectivityManager.getActiveNetworkInfo();
        }
        return lNetworkInfo != null && lNetworkInfo.isConnected();
    }

}
