package com.gc.chatviewsdk.views.viewHolders;

import androidx.recyclerview.widget.RecyclerView;

import com.gc.chatviewsdk.databinding.ConversationListItemBinding;

public class ConversationListViewHolder extends RecyclerView.ViewHolder {
    public ConversationListItemBinding conversationListItemBinding;
    public ConversationListViewHolder(ConversationListItemBinding conversationBinding) {
        super(conversationBinding.getRoot());
        this.conversationListItemBinding = conversationBinding;
    }
}
