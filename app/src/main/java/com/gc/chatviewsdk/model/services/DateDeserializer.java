package com.gc.chatviewsdk.model.services;

import com.gc.chatviewsdk.model.Message;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;

import timber.log.Timber;

public class DateDeserializer implements JsonDeserializer<Message> {
@Override
public Message deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        json.getAsJsonObject()
        .addProperty("updated_in_epoch", isoToEpoch(json.getAsJsonObject().get("updated_at").getAsString()));

        Gson gson = new GsonBuilder().create();

        return gson.fromJson(json.toString(),
        Message.class);
        }

private long isoToEpoch(String dateString) {
        try {
        Timber.e( dateString);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd'T'HH:mm:ss'Z'");
        Date date = format.parse(dateString);
        long epoch = date.getTime();
        Timber.e( epoch + "");
        System.out.println(epoch);
        return epoch;
        } catch (Exception e) {
        e.printStackTrace();
        return 0;
        }
        }
        }