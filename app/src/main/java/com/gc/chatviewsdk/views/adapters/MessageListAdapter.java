package com.gc.chatviewsdk.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.gc.chatviewsdk.R;
import com.gc.chatviewsdk.databinding.RowReceiverMessageBinding;
import com.gc.chatviewsdk.databinding.RowSenderMessageBinding;
import com.gc.chatviewsdk.model.MessageData;
import com.gc.chatviewsdk.views.viewHolders.ReceivedMessageHolder;
import com.gc.chatviewsdk.views.viewHolders.SenderMessageHolder;

import java.util.ArrayList;

public class MessageListAdapter extends RecyclerView.Adapter {
    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;

    private Context mContext;
    private ArrayList<MessageData> mMessageList;
    private final String  userId;

    public MessageListAdapter(Context context, ArrayList<MessageData> messageList, String userId) {
        mContext = context;
        mMessageList = messageList;
        this.userId = userId;
    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }

    // Determines the appropriate ViewType according to the sender of the message.
    @Override
    public int getItemViewType(int position) {
        MessageData message = mMessageList.get(position);

        if (message.getFromUserId().equals(userId)) {
            // If the current user is the sender of the message
            return VIEW_TYPE_MESSAGE_SENT;
        } else {
            // If some other user sent the message
            return VIEW_TYPE_MESSAGE_RECEIVED;
        }
    }

    // Inflates the appropriate layout according to the ViewType.
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            RowSenderMessageBinding rowSenderMessageBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                    R.layout.row_sender_message, parent, false);
            return new SenderMessageHolder(rowSenderMessageBinding);
        } else if (viewType == VIEW_TYPE_MESSAGE_RECEIVED) {
            RowReceiverMessageBinding receiveBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                    R.layout.row_receiver_message, parent, false);
            return new ReceivedMessageHolder(receiveBinding);
        }
        return null;
    }

    // Passes the message object to a ViewHolder so that the contents can be bound to UI.
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (holder.getItemViewType()) {
            case VIEW_TYPE_MESSAGE_SENT:
                ((SenderMessageHolder) holder).senderMessageItemBinding.setMessage(mMessageList.get(position));
                break;
            case VIEW_TYPE_MESSAGE_RECEIVED:
                ((ReceivedMessageHolder) holder).receivedMessageItemBinding.setMessage(mMessageList.get(position));
        }
    }
}
