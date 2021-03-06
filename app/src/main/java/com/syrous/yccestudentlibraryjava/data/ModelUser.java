package com.syrous.yccestudentlibraryjava.data;


import java.io.Serializable;

/**
 * Created By : Syrous
 * date : 22/2/20
 */

public class ModelUser implements Serializable {

    private String userName;
    private String userId;
    private String emailId;

    public ModelUser(String userId, String userName, String emailId){
        this.userId = userId;
        this.userName = userName;
        this.emailId = emailId;
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
