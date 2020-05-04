package com.gc.chatviewsdk.views.viewHolders;

import androidx.recyclerview.widget.RecyclerView;

import com.gc.chatviewsdk.databinding.RowReceiverMessageBinding;

public class ReceivedMessageHolder extends RecyclerView.ViewHolder {
    public RowReceiverMessageBinding receivedMessageItemBinding;
    public ReceivedMessageHolder(RowReceiverMessageBinding receivedMessageItemBinding) {
        super(receivedMessageItemBinding.getRoot());
        this.receivedMessageItemBinding = receivedMessageItemBinding;
    }
}
