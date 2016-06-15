package com.dieam.reactnativepushnotification.modules;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;

/**
 * Created by yas on 6/13/16.
 */

interface JSEventSender {
    public void sendEvent(String eventName, Object params);
}

public class TokenReceiver extends BroadcastReceiver {

    private JSEventSender mJSEventSender;
    private String token = "";

    TokenReceiver(JSEventSender sender) {
        mJSEventSender = sender;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        token = intent.getStringExtra("token");
        WritableMap params = Arguments.createMap();
        params.putString("deviceToken", token);
        mJSEventSender.sendEvent("remoteNotificationsRegistered", params);
    }

    public String getToken() {
        return token;
    }

}