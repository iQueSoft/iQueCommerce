package net.iquesoft.project.iQueCommerce.domain;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Pair;

import javax.inject.Inject;

public class PreferencesManager {

    public static final String CATEGORY_VIEW = "CATEGORY_VIEW";
    public static final String GRID_VIEW = "GRID_VIEW";
    public static final String LIST_VIEW = "LIST_VIEW";
    private static final String PREFERENCES = "USER_PREFERENCES";
    private static final String USER_EMAIL = "USER_EMAIL";
    private static final String USER_PASSWORD = "USER_PASSWORD";
    private static final String AUTO_LOGIN = "AUTO_LOGIN";
    private static final String IS_RELOGGING = "IS_RELOGGING";
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Inject
    public PreferencesManager() {
    }

    public String loadString(Context context, String key) {
        String value = "";
        initializeReadPreferences(context);
        if (key.equals(CATEGORY_VIEW)) {
            value = this.preferences.getString(key, GRID_VIEW);
        }
        return value;
    }

    @SuppressLint("CommitPrefEdits")
    private void initializeWritingPreferences(Context context) {
        this.preferences = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        this.editor = this.preferences.edit();
    }

    private void initializeReadPreferences(Context context) {
        this.preferences = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
    }

    public void saveString(Context context, String key, String value) {
        this.initializeWritingPreferences(context);
        this.editor.putString(key, value);
        this.editor.commit();
    }

    public void saveCredentials(Context context, String email, String password) {
        this.initializeWritingPreferences(context);
        this.editor.putString(USER_EMAIL, email);
        this.editor.putString(USER_PASSWORD, password);
        this.editor.putBoolean(AUTO_LOGIN, true);
        this.editor.commit();
    }

    public void setAutoLogin(Context context, boolean b) {
        this.initializeWritingPreferences(context);
        this.editor.putBoolean(AUTO_LOGIN, b);
        this.editor.commit();
    }

    public boolean isAutoLoginEnabled(Context context) {
        this.initializeReadPreferences(context);
//        return this.preferences.getBoolean(AUTO_LOGIN, false);
        return false; // TODO: 13-Feb-17 Change this if auto login is required
    }

    public Pair<String, String> loadUserCredentials(Context context) {
        this.initializeReadPreferences(context);
        return new Pair<>(this.preferences.getString(USER_EMAIL, ""), this.preferences.getString(USER_PASSWORD, ""));
    }

    public boolean isRelogging(Context context) {
        this.initializeReadPreferences(context);
        return this.preferences.getBoolean(IS_RELOGGING, false);
    }

    public void setRelogging(Context context, boolean b) {
        this.initializeWritingPreferences(context);
        this.editor.putBoolean(IS_RELOGGING, b);
        this.editor.commit();
    }
}
