package com.gc.chatviewsdk.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.gc.chatviewsdk.model.Message;
import com.gc.chatviewsdk.model.services.ServiceRepository;

import java.util.List;

public class UserConversationViewModel extends AndroidViewModel {
    private LiveData<List<Message>> conversationLiveData;
    public UserConversationViewModel(@NonNull Application application) {
        super(application);
        conversationLiveData = ServiceRepository.getInstance(getApplication().getApplicationContext()).fetchMessages();
    }
    public LiveData<List<Message>> getConversationList() {
        return conversationLiveData;

    }
    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application application;

        public Factory(@NonNull Application application) {
            this.application = application;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            return (T) new UserConversationViewModel(application);
        }
    }
}