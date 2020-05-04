package com.gc.chatviewsdk.views.viewHolders;

import androidx.recyclerview.widget.RecyclerView;

import com.gc.chatviewsdk.databinding.RowSenderMessageBinding;
import com.gc.chatviewsdk.model.MessageData;
import com.gc.chatviewsdk.utils.MyUtils;

public class SenderMessageHolder extends RecyclerView.ViewHolder {
    public RowSenderMessageBinding senderMessageItemBinding;
    public SenderMessageHolder(RowSenderMessageBinding senderBinding) {
        super(senderBinding.getRoot());
        this.senderMessageItemBinding = senderBinding;
    }
}