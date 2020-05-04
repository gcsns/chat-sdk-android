package com.gc.chatviewsdk.model.services;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gc.chatviewsdk.model.Message;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceRepository {
    private ApiService apiService;
    private static ServiceRepository serviceRepository;
    private List<Message> messageList;
    private ServiceRepository(Context context) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create(initGSONSerializers()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public synchronized static ServiceRepository getInstance(Context context) {
        if (serviceRepository == null) {
            if (serviceRepository == null) {
                serviceRepository = new ServiceRepository(context);
            }
        }
        return serviceRepository;
    }

    private Gson initGSONSerializers() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Message.class, new DateDeserializer());
        return gsonBuilder.create();
    }

    public LiveData<List<Message>> fetchMessages() {
        MutableLiveData<List<Message>> issuesMutableLiveData = new MutableLiveData<>();
        apiService.fetchOpenIssues()
                .map(issuesList -> {
                    List<Message> conversationSortedList = new ArrayList<>(issuesList);
                    //Collections.sort(issuesSortedList, (o1, o2) -> Long.compare(o1.updated_in_epoch, o2.updated_in_epoch));
                    return conversationSortedList;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<List<Message>>() {
                    @Override
                    public void onNext(List<Message> issues) {
                        messageList = new ArrayList<>();
                        messageList.add(new Message("Dummy 1","Lets connect with the Lead Here",
                                "",new Date().getTime()));
                        messageList.add(new Message("Dummy 2","I have an issue in loading documents",
                                "",new Date().getTime()));
                        messageList.add(new Message("Dummy 3","Hi How are u",
                                "",new Date().getTime()));
                        messageList.add(new Message("Dummy 4","I am thinking about someone",
                                "",new Date().getTime()));
                        issuesMutableLiveData.setValue(messageList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        issuesMutableLiveData.setValue(null);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return issuesMutableLiveData;
    }

}