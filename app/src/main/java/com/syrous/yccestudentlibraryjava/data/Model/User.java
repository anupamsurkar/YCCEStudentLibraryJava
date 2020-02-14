package com.syrous.yccestudentlibraryjava.data.Model;

/**
 * Created By : Syrous
 * date : 26/1/20
 */
public class User {

    private String userName;
    private String token;
    private String userId;

    public User(String userName, String userId, String token){
        this.userName = userName;
        this.token = token;
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getToken() {
        return token;
    }

    public String getUserId() {
        return userId;
    }
}
