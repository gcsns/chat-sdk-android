package com.gc.chatviewsdk.views.activities;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.gc.chatviewsdk.R;
import com.gc.chatviewsdk.databinding.ActivityChatRoomBinding;
import com.gc.chatviewsdk.model.MessageData;
import com.gc.chatviewsdk.viewmodel.ChatViewModel;
import com.gc.chatviewsdk.views.adapters.MessageListAdapter;

import java.util.Date;
import java.util.Objects;

public class ChatRoomActivity extends AppCompatActivity {
    private ActivityChatRoomBinding chatRoomBinding;
    private RoomClickHandlers handlers;
    private ChatViewModel chatViewModel;
    private String userName = "";
    private String senderName = "";
    private MessageListAdapter messageListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chatRoomBinding = DataBindingUtil.setContentView(ChatRoomActivity.this,
                R.layout.activity_chat_room);
        handlers = new RoomClickHandlers(this);
        chatRoomBinding.setHandlers(handlers);
        chatRoomBinding.setActivity(this);
        chatRoomBinding.rvChat.setLayoutManager(new LinearLayoutManager(this));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ChatRoomActivity.this);
        chatRoomBinding.rvChat.setLayoutManager(linearLayoutManager);

        ChatViewModel.Factory factory = new ChatViewModel.Factory(
                Objects.requireNonNull(getApplication()));
        chatViewModel =new ViewModelProvider(this,factory).get(ChatViewModel.class);
        messageListAdapter = new MessageListAdapter(this, chatViewModel.getMessageDataArrayList(),"fromUser");
        chatRoomBinding.rvChat.setAdapter(messageListAdapter);


    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    public class RoomClickHandlers{
        Context context;
        public RoomClickHandlers(Context context){
            this.context = context;
        }
        public void onAttachClicked(View view){
            showBottomSheetDialog();
            Toast.makeText(getApplicationContext(),"clicked attached",Toast.LENGTH_SHORT).show();
        }
        public void onSendClicked(View view){
            MessageData messageData = new MessageData(chatRoomBinding.edittextChatMessage.getText().toString(),
                    new Date().getTime(),
                    chatRoomBinding.tvSender.getText().toString(),
                    chatRoomBinding.tvSender.getText().toString(),
                    true,
                    MessageData.TYPE_TEXT);
            final int newPos = chatViewModel.getMessageDataArrayList().size();
            chatViewModel.getMessageDataArrayList().add(messageData);

            chatRoomBinding.rvChat.post(new Runnable() {
                                  @Override
                                  public void run() {
                                      chatRoomBinding.rvChat.getAdapter().notifyItemInserted(newPos);
                                      chatRoomBinding.rvChat.postDelayed(new Runnable() {
                                          @Override
                                          public void run() {
                                              chatRoomBinding.rvChat.smoothScrollToPosition(newPos);
                                              chatRoomBinding.edittextChatMessage.setText("");
                                          }
                                      }, 100);
                                  }
                              }
            );

        }
    }
    private void showBottomSheetDialog(){

    }

}
