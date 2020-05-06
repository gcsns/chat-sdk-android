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
                    MessageData.messageDataType.Text));
            messageDataArrayList.add(new MessageData(
                    "Here is my Image which I have provided",
                    1588216067,
                    "Danish",
                    "12345",
                    true,
                    MessageData.messageDataType.Text));
            messageDataArrayList.add(new MessageData(
                    "",
                    1588226067,
                    "Danish",
                    "12345",
                    true,
                    MessageData.messageDataType.Image));
            messageDataArrayList.add(new MessageData(
                    "Sorry for late reply",
                    1588288167,
                    "fromUser",
                    "fromUser",
                    true,
                    MessageData.messageDataType.Text));
            messageDataArrayList.add(new MessageData(
                    "I am grateful to you",
                    1588571399,
                    "Avesh",
                    "4644687",
                    false,
                    MessageData.messageDataType.Text));
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
