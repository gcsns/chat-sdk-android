package com.gc.chatviewsdk.views.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.gc.chatviewsdk.R;
import com.gc.chatviewsdk.databinding.ConversationListItemBinding;
import com.gc.chatviewsdk.model.Message;
import com.gc.chatviewsdk.model.callback.ListItemClickCallback;
import com.gc.chatviewsdk.views.viewHolders.ConversationListViewHolder;

import java.util.List;

public class ConversationListAdapter extends RecyclerView.Adapter<ConversationListViewHolder> {
    private List<Message> messageList;
    private ListItemClickCallback listItemClickCallback;
    public ConversationListAdapter(List<Message> messageList, ListItemClickCallback listItemClickCallback) {
        this.messageList = messageList;
        this.listItemClickCallback = listItemClickCallback;
    }
    @NonNull
    @Override
    public ConversationListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ConversationListItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.conversation_list_item, parent, false);
        return new ConversationListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ConversationListViewHolder holder, int position) {
        holder.conversationListItemBinding.setMessage(messageList.get(position));
        holder.conversationListItemBinding.setCallback(listItemClickCallback);
    }

    @Override
    public int getItemCount() {
        return messageList != null ? messageList.size() : 0;
    }

    public void refreshData(List<Message> messageList) {
        this.messageList = messageList;
        notifyDataSetChanged();
    }
}