package com.hoyup.rniconbadge;

import android.content.Context;
import android.content.SharedPreferences;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import me.leolin.shortcutbadger.ShortcutBadger;

public class RNIconBadgeModule extends ReactContextBaseJavaModule {

    ReactApplicationContext reactContext;

    public RNIconBadgeModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    private static final String KEY = "RNIconBadge";

    @Override
    public String getName() {
        return "RNIconBadge";
    }

    @ReactMethod
    public void setIconBadge(int badgeNumber) {
        assert badgeNumber > 0;
        saveBadge(badgeNumber);
        ShortcutBadger.applyCount(reactContext, badgeNumber);
    }

    @ReactMethod
    public void badgeAdd(int badgeNumber) {
        assert badgeNumber > 0;
        int newBadgeNumber = getBadge() + badgeNumber;
        saveBadge(newBadgeNumber);
        ShortcutBadger.applyCount(reactContext, newBadgeNumber);
    }

    @ReactMethod
    public void badgeMinus(int badgeNumber) {
        assert badgeNumber > 0;
        int newBadgeNumber = getBadge() - badgeNumber > 0 ? getBadge() - badgeNumber : 0;
        saveBadge(newBadgeNumber);
        ShortcutBadger.applyCount(reactContext, newBadgeNumber);
    }

    @ReactMethod
    public void clearBadge() {
        saveBadge(0);
        ShortcutBadger.removeCount(reactContext);
    }

    @ReactMethod
    public void getBadgeNumber(Promise promise) {
        try {
            promise.resolve(getBadge());
        } catch (Exception e) {
            e.printStackTrace();
            promise.reject("can not get badge number due to an exception occurs", e);
        }
    }

    private int getBadge() {
        SharedPreferences sharedPreferences = getSharedPreferences(reactContext);
        return sharedPreferences.getInt(KEY, 0);
    }

    private void saveBadge(int badgeNumber) {
        SharedPreferences.Editor editor = getSharedPreferences(reactContext).edit();
        editor.putInt(KEY, badgeNumber);
        editor.apply();
    }

    private SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(KEY, Context.MODE_PRIVATE);
    }
}
