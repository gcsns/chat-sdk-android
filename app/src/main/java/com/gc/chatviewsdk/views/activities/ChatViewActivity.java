package com.gc.chatviewsdk.views.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.gc.chatviewsdk.R;
import com.gc.chatviewsdk.fragments.UserConversationFrag;

public class ChatViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_view);
        if (savedInstanceState == null) {
            UserConversationFrag userConversationFrag = new UserConversationFrag();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fl_container, userConversationFrag, UserConversationFrag.newInstance().getTag())
                    .addToBackStack(null)
                    .commit();
        }
    }
    @Override
    public void onBackPressed() {
        FragmentManager fm = this.getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 1) {
            fm.popBackStack();
        } else {
            this.finish();
        }
    }
}
