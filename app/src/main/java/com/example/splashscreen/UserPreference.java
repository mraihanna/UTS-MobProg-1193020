package com.example.splashscreen;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.splashscreen.UserModel;

public class UserPreference {
    private static final String PREFS_NAME = "user_pref";
    private static final String USERNAME = "username";
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String JENKEL = "jenkel";
    private static final String PASS = "pass";
    private static final String PHONE_NUMBER = "telpon";
    private static final String ALAMAT = "alamat";
    private static final String HOBI = "hobi";

    private final SharedPreferences preferences;

    UserPreference(Context context) {
        preferences = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
    }
    public void setUser(UserModel value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(USERNAME, value.username);
        editor.putString(NAME, value.name);
        editor.putString(EMAIL, value.email);
        editor.putString(PASS, value.pass);
        editor.putString(ALAMAT, value.alamat);
        editor.putString(PHONE_NUMBER, value.telpon);
        editor.putBoolean(JENKEL, value.jenkel);
        editor.putString(HOBI, value.hobi);
        editor.apply();
    }
    UserModel getUser() {
        UserModel model = new UserModel();
        model.setName(preferences.getString(NAME, ""));
        model.setUsername(preferences.getString(USERNAME, ""));
        model.setEmail(preferences.getString(EMAIL, ""));
        model.setPass(preferences.getString(PASS, ""));
        model.setTelpon(preferences.getString(PHONE_NUMBER, ""));
        model.setAlamat(preferences.getString(ALAMAT, ""));
        model.setJenkel(preferences.getBoolean(JENKEL, false));
        model.setHobi(preferences.getString(HOBI, ""));
        return model;
    }
}