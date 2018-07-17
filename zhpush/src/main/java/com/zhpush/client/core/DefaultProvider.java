package com.zhpush.client.core;

import android.content.Context;
import android.content.Intent;

import com.cjz.clog.CLog;
import com.zhpush.client.db.MsgDbManager;
import com.zhpush.client.pojo.MixPushMessage;

public class DefaultProvider implements IMixMessageProvider {
    private static final String tag = DefaultProvider.class.getSimpleName();

    private Class<? extends MixPushIntentService> sMixPushIntentServiceClass;

    public DefaultProvider(Class<? extends MixPushIntentService> sMixPushIntentServiceClass) {
        this.sMixPushIntentServiceClass = sMixPushIntentServiceClass;
    }

    @Override
    public void onReceivePassThroughMessage(Context context, MixPushMessage message) {
        if (message == null) {
            return;
        }
        message.setNotify(0);
        Intent intent = new Intent(MixPushMessageReceiver.RECEIVE_THROUGH_MESSAGE);
        intent.putExtra(MixPushMessageReceiver.MESSAGE, message);


        if (sMixPushIntentServiceClass != null) {
            boolean msgExist = MsgDbManager.getInstance().isMsgExist(message);
            if (!msgExist) {
                intent.setClass(context, sMixPushIntentServiceClass);
                context.startService(intent);
                MsgDbManager.getInstance().saveMsg(message);
            } else {
                CLog.e(tag, "已有重复的消息");
            }


        }
    }

    @Override
    public void onNotificationMessageClicked(Context context, MixPushMessage message) {
        if (message == null) {
            return;
        }
//        message.setNotify(1);
//        Intent intent = new Intent(MixPushMessageReceiver.NOTIFICATION_CLICKED);
//        intent.putExtra(MixPushMessageReceiver.MESSAGE, message);
//        context.sendBroadcast(intent);
//        CLog.e(tag, message.getContent());
//
//        if (sMixPushIntentServiceClass != null) {
//            intent.setClass(context, sMixPushIntentServiceClass);
//            context.startService(intent);
//        }
    }

    @Override
    public void onNotificationMessageArrived(Context context, MixPushMessage message) {
        if (message == null) {
            return;
        }
//        Intent intent = new Intent(MixPushMessageReceiver.NOTIFICATION_ARRIVED);
//        intent.putExtra("message", message);
//        context.sendBroadcast(intent);
//        CLog.e(tag, message.getContent());
//
//        if (sMixPushIntentServiceClass != null) {
//            intent.setClass(context, sMixPushIntentServiceClass);
//            context.startService(intent);
//        }
    }
}
