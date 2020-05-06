package com.gc.chatviewsdk.views.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.gc.chatviewsdk.R;
import com.gc.chatviewsdk.databinding.ActivityChatRoomBinding;
import com.gc.chatviewsdk.model.ImageMessageData;
import com.gc.chatviewsdk.model.MessageData;
import com.gc.chatviewsdk.utils.GifSizeFilter;
import com.gc.chatviewsdk.viewmodel.ChatViewModel;
import com.gc.chatviewsdk.views.adapters.MessageListAdapter;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.filter.Filter;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import cn.xm.weidongjian.popuphelper.PopupWindowHelper;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ChatRoomActivity extends AppCompatActivity
        implements View.OnClickListener {
    private ActivityChatRoomBinding chatRoomBinding;
    private ChatViewModel chatViewModel;
    private String userName = "";
    private String senderName = "";
    private MessageListAdapter messageListAdapter;
    private PopupWindowHelper popupWindowHelper;
    private View popupviewUp;
    private String[] PERMISSIONS = {android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.CAMERA};
    private int PERMISSION_ALL = 1478;
    private static final int REQUEST_CODE_CHOOSE = 23;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chatRoomBinding = DataBindingUtil.setContentView(ChatRoomActivity.this,
                R.layout.activity_chat_room);
        RoomClickHandlers handlers = new RoomClickHandlers(this);
        chatRoomBinding.setHandlers(handlers);
        chatRoomBinding.setActivity(this);
        chatRoomBinding.rvChat.setLayoutManager(new LinearLayoutManager(this));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ChatRoomActivity.this);
        chatRoomBinding.rvChat.setLayoutManager(linearLayoutManager);

        ChatViewModel.Factory factory = new ChatViewModel.Factory(
                Objects.requireNonNull(getApplication()));
        chatViewModel =new ViewModelProvider(this,factory).get(ChatViewModel.class);
        messageListAdapter = new MessageListAdapter(this, chatViewModel.getMessageDataArrayList(),
                "fromUser");
        chatRoomBinding.rvChat.setAdapter(messageListAdapter);
        chatRoomBinding.rvChat.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft,
                                       int oldTop, int oldRight, int oldBottom) {
                if (bottom < oldBottom) scrollToBottom();
            }
        });

        popupviewUp = LayoutInflater.from(this).inflate(R.layout.media_attach_menu, null);
        popupviewUp.findViewById(R.id.menu_attachment_gallery).setOnClickListener(this);

        // getIntent Value
        chatRoomBinding.tvTitle.setText(getIntent().getStringExtra("userName"));



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
        }
        public void onSendClicked(View view){
            if (chatRoomBinding.edittextChatMessage.getText().length()>1){
                MessageData messageData = new MessageData(chatRoomBinding.edittextChatMessage.getText().toString(),
                        new Date().getTime(),
                        chatRoomBinding.tvSender.getText().toString(),
                        chatRoomBinding.tvSender.getText().toString(),
                        false,
                        MessageData.messageDataType.Text);
                sendMessage(messageData);
            }else Toast.makeText(ChatRoomActivity.this,"type message to send",Toast.LENGTH_SHORT).show();

        }
    }

    private void sendMessage(MessageData messageDataToSend) {
        final int newPos = chatViewModel.getMessageDataArrayList().size();
        chatViewModel.getMessageDataArrayList().add(messageDataToSend);
        chatRoomBinding.rvChat.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            chatRoomBinding.rvChat.getAdapter().notifyItemInserted(newPos);
                                            chatRoomBinding.rvChat.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    new Handler().postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            messageDataToSend.setBoolDelivered(true);
                                                            messageListAdapter.notifyDataSetChanged();
                                                        }
                                                    },4000);
                                                    chatRoomBinding.rvChat.smoothScrollToPosition(newPos);
                                                    chatRoomBinding.edittextChatMessage.setText("");
                                                }
                                            }, 100);
                                        }
                                    }
        );

    }

    private void showBottomSheetDialog(){
        // show Attachment Dialog
        popupWindowHelper = new PopupWindowHelper(popupviewUp);
        popupWindowHelper.initPopupWindow(1);
        popupWindowHelper.showAsPopUp(chatRoomBinding.attachImage);

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.menu_attachment_gallery:
                openGalleryView();
                break;
            case R.id.menu_attachment_document:
                break;
        }

    }

    private void openGalleryView() {
        popupWindowHelper.dismiss();
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (aBoolean) {
                            Matisse.from(ChatRoomActivity.this)
                                    .choose(MimeType.of(MimeType.JPEG, MimeType.PNG, MimeType.GIF))
                                    .countable(true)
                                    .maxSelectable(1)
                                    .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                                    .gridExpectedSize(
                                            getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                                    .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                                    .thumbnailScale(0.85f)
                                    .imageEngine(new GlideEngine())
                                    .forResult(REQUEST_CODE_CHOOSE);

                            //mAdapter.setData(null);
                        } else {
                            Toast.makeText(ChatRoomActivity.this, R.string.permission_request_denied, Toast.LENGTH_LONG)
                                    .show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            assert data != null;
            List<Uri> results = Matisse.obtainResult(data);
            if (results!=null&&results.size()>0){
                ImageMessageData messageData = new ImageMessageData(results.get(0),"",
                        new Date().getTime(),
                        chatRoomBinding.tvSender.getText().toString(),
                        chatRoomBinding.tvSender.getText().toString(),
                        false,
                        MessageData.messageDataType.Image);
                        sendMessage(messageData);
            }
        }
    }

    private void scrollToBottom() {
        chatRoomBinding.rvChat.smoothScrollToPosition(messageListAdapter.getItemCount());
    }

}
