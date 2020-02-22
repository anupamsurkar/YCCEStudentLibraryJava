package com.syrous.yccestudentlibraryjava.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.SyncStateContract;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.syrous.yccestudentlibraryjava.Constants.GlobalConstants;

/**
 * Created By : Syrous
 * date : 22/2/20
 */

public class User {

    private static User sUser;
    private ModelUser user;
    private Context context;
    private String serverAuthCode;
    private GoogleSignInAccount account;
    private boolean isRegistered = false;

    private User(Context context){
        this.context = context.getApplicationContext();
    }

    public void setAccount(GoogleSignInAccount account) {
        this.account = account;
    }

    public GoogleSignInAccount getAccount() {
        return account;
    }

    public static User get(Context context){
        if(sUser == null){
            sUser = new User(context);
        }
        return sUser;
    }

    public void registerUser(ModelUser user){
        this.user = user;
        this.isRegistered = true;
        SharedPreferences prefs = context.getSharedPreferences(GlobalConstants.LOGGED_USER, 0);
        prefs.edit().putBoolean(GlobalConstants.LOGGED_USER, isRegistered).apply();
    }

    public ModelUser getUser() {
        return user;
    }

    public boolean isRegistered() {
        return isRegistered;
    }
}
