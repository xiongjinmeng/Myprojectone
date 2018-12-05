package bwie.com.mydimensionmall;

import android.app.Application;

import java.util.HashMap;

import bwie.com.mydimensionmall.uile.LogUtil;
import bwie.com.mydimensionmall.uile.OkHttpUile;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.init();
        initHttpHead();
    }

    private void initHttpHead() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token","this is token");
        hashMap.put("version","version1.0");
        hashMap.put("platform","android");
        OkHttpUile.init(hashMap);
    }
}
