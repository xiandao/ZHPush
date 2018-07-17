package com.zhpush.client.core;

import android.content.Context;

public interface IMixPushManager {


    void registerPush(Context context, ITokenListener listener);

    void unRegisterPush(Context context);

    String getToken(Context context);

    String getName();

    void setMessageProvider(IMixMessageProvider provider);

    void activityCreate(Context context);

    /**
     * 有些平台没有unRegister 方法，所以用一个boole 值来来标识，是否失效
     * 默认 为 true
     */
    boolean isDisable();

}
