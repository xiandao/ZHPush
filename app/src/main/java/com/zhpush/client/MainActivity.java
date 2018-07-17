package com.zhpush.client;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zhpush.client.core.ITokenListener;
import com.zhpush.client.core.MixPushClient;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ITokenListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        List<String> platforms = new ArrayList<>();
//        platforms.add("xiaomi");
//        platforms.add("huawei");
//        platforms.add("sogo");
//        platforms.add("umeng");
        MixPushClient.init(false, platforms);

        // 配置接收推送消息的服务类
        MixPushClient.setPushIntentService(PushIntentService.class);
        // 注册推送
        MixPushClient.registerPush(this, this);
    }


    @Override
    public void tokenListener(String platform, String token) {

    }


}
