package com.gc.chatviewsdk.model.services;

import com.gc.chatviewsdk.model.Message;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    @GET("repos/firebase/firebase-ios-sdk/issues")
    Observable<List<Message>> fetchOpenIssues();
}
