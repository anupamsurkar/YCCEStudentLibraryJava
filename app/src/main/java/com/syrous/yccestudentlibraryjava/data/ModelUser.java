package com.syrous.yccestudentlibraryjava.data;

import android.content.Context;

/**
 * Created By : Syrous
 * date : 22/2/20
 */

public class ModelUser {

    private String userName;
    private String userId;
    private String emailId;
    private String serverAuthCode;

    public ModelUser(String userId, String userName, String emailId, String serverAuthCode){
        this.userId = userId;
        this.userName = userName;
        this.emailId = emailId;
        this.serverAuthCode = serverAuthCode;
    }

    public String getServerAuthCode() {
        return serverAuthCode;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserId() {
        return userId;
    }

    public String getEmailId() {
        return emailId;
    }
}
