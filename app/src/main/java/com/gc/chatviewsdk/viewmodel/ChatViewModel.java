package com.gc.chatviewsdk.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.gc.chatviewsdk.model.MessageData;

import java.util.ArrayList;

public class ChatViewModel extends AndroidViewModel {
    private ArrayList<MessageData> messageDataArrayList;
    private ChatViewModel(@NonNull Application application) {
        super(application);
    }
    public ArrayList<MessageData> getMessageDataArrayList() {
        if (messageDataArrayList==null){
            messageDataArrayList = new ArrayList<>();
            messageDataArrayList.add(new MessageData(
                    "Hi, Arun I just forget my bank statement",
                    1588216067,
                    "Danish",
                    "12345",
                    true,
                    MessageData.TYPE_TEXT));
            messageDataArrayList.add(new MessageData(
                    "Hello! Can you please send my bank statement?",
                    1588216097,
                    "Danish",
                    "12345",
                    true,
                    MessageData.TYPE_TEXT));
            messageDataArrayList.add(new MessageData(
                    "Sorry for late reply",
                    1588288167,
                    "fromUser",
                    "fromUser",
                    true,
                    MessageData.TYPE_TEXT));
            messageDataArrayList.add(new MessageData(
                    "Sending you asap on your mail. Please check and revert me back",
                    1588288169,
                    "fromUser",
                    "fromUser",
                    true,
                    MessageData.TYPE_TEXT));
        }
        return messageDataArrayList;
    }
    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application application;

        public Factory(@NonNull Application application) {
            this.application = application;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            return (T) new ChatViewModel(application);
        }
    }

}
