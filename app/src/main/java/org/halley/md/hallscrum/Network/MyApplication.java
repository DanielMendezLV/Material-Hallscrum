package org.halley.md.hallscrum.Network;

import android.app.Application;
import android.content.Context;

/**
 * Created by Mendez Diaz on 10/06/2015.
 */
public class MyApplication extends Application{
    private static MyApplication sInstance;
    public static final String TOKEN_KEY="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbmRyb2lkIiwiaWF0IjoxNDMyODcwNjcxLCJleHAiOjE0MzQ1OTg2NzF9.R4r3srlBmiAahfTlyQDWN5alYA9EtWNGYWuGoZ8Igu4";


    @Override
    public void onCreate(){
        super.onCreate();
        sInstance=this;
    }

    public static MyApplication getsInstance(){
        return sInstance;
    }

    public static Context getAppContext(){
        return sInstance.getApplicationContext();
    }

}
