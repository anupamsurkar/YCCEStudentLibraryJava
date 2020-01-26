package com.syrous.yccestudentlibraryjava.DI;

import android.content.Context;

import com.syrous.yccestudentlibraryjava.DI.Model.User;

import java.util.List;

/**
 * Created By : Syrous
 * date : 26/1/20
 */
public class DataStore {

    private static DataStore sDataStore;

    private List<User> userList;

    private Context mContext;

    private DataStore (Context context){
        mContext = context;
    }

    public DataStore get(Context context){

        if(sDataStore == null) {
            sDataStore = new DataStore(context);
        }
        return sDataStore;
    }

    public void addUser (User user){

    }
}
