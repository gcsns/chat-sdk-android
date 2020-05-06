package com.gc.chatviewsdk.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.gc.chatviewsdk.R;
import com.gc.chatviewsdk.databinding.UserConversationFragmentBinding;
import com.gc.chatviewsdk.model.Message;
import com.gc.chatviewsdk.model.callback.ListItemClickCallback;
import com.gc.chatviewsdk.viewmodel.UserConversationViewModel;
import com.gc.chatviewsdk.views.activities.ChatRoomActivity;
import com.gc.chatviewsdk.views.adapters.ConversationListAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserConversationFrag extends Fragment {
    private ConversationListAdapter conversationListAdapter;
    private UserConversationFragmentBinding conversationBinding;
    public static UserConversationFrag newInstance() {
        return new UserConversationFrag();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        conversationBinding = DataBindingUtil.inflate(inflater, R.layout.user_conversation_fragment, container, false);
        conversationListAdapter = new ConversationListAdapter(new ArrayList<>(), listItemClickCallback);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        conversationBinding.rvConversations.setLayoutManager(linearLayoutManager);
        conversationBinding.rvConversations.setAdapter(conversationListAdapter);
        return conversationBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        conversationBinding.pbLoader.setVisibility(View.VISIBLE);
        conversationBinding.rvConversations.setVisibility(View.GONE);

        UserConversationViewModel.Factory factory = new UserConversationViewModel.Factory(
                Objects.requireNonNull(getActivity()).getApplication());

        UserConversationViewModel issuesListViewModel = new ViewModelProvider(this,factory).get(UserConversationViewModel.class);
        issuesListViewModel.getConversationList().observe(getViewLifecycleOwner(), new Observer<List<Message>>() {
            @Override
            public void onChanged(List<Message> messageList) {

                showConversationList(messageList);
            }
        });

    }

    //setting up data on to the view
    private void showConversationList(List<Message> messageList) {
        conversationBinding.pbLoader.setVisibility(View.GONE);
        conversationBinding.rvConversations.setVisibility(View.VISIBLE);
        conversationListAdapter.refreshData(messageList);
    }

    private final ListItemClickCallback listItemClickCallback = messages -> {
        Intent openChatRoom = new Intent(this.getContext(), ChatRoomActivity.class);
        openChatRoom.putExtra("userName",messages.getStrUserName());
        openChatRoom.putExtra("userIcon",messages.getStrUserIcon());
        startActivity(openChatRoom);
    };

}
