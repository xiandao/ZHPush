package com.zhpush.client;


import android.content.Intent;

import com.cjz.clog.CLog;
import com.zhpush.client.core.MixPushIntentService;
import com.zhpush.client.core.MixPushMessageReceiver;
import com.zhpush.client.pojo.MixPushMessage;
import com.zhpush.client.pojo.PushAction;

/**
 * 需要定义一个receiver 或 Service 来接收透传和通知栏点击的信息，建议使用Service，更加简单
 */

public class PushIntentService extends MixPushIntentService {
    private static final String tag = PushIntentService.class.getSimpleName();

    @Override
    public void onReceivePassThroughMessage(MixPushMessage message) {
        CLog.e(tag, "收到透传消息 -> " + message.getPlatform());
        PushAction pushAction = message.getContent();

        if (pushAction == null) {
            return;
        }

        Intent intent = new Intent(MixPushMessageReceiver.RECEIVE_THROUGH_MESSAGE);
        intent.putExtra(MixPushMessageReceiver.MESSAGE, message);
        sendBroadcast(intent);




    }

    @Override
    public void onNotificationMessageClicked(MixPushMessage message) {
        CLog.e(tag, "通知栏消息点击 -> " + message.getPlatform());
        CLog.e(tag, "通知栏消息点击 -> " + message.getContent());


    }

    /**
     * 建议使用普通通知栏来实现打开URL，因为这样可以实现打开内部浏览器
     */
    private void openWebView(String url) {
        CLog.e(tag, "打开浏览器 -> " + url);
        // 请自行实现WebViewActivity
    }


}
