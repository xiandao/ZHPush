package com.zhpush.client.core;

import android.content.Context;
import android.os.Build;

import com.cjz.clog.CLog;
import com.zhpush.client.db.MsgDbManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MixPushClient {
    private static final String tag = MixPushClient.class.getSimpleName();

    private static Map<String, IMixPushManager> sPushManagerMap = new HashMap<>();
    public static DefaultProvider defaultProvider;


    public static void setPushIntentService(Class<? extends MixPushIntentService> mixPushIntentServiceClass) {
        defaultProvider = new DefaultProvider(mixPushIntentServiceClass);

    }


    public static void init(boolean brand, List<String> platforms) {
        try {
            String phoneMaker = Build.MANUFACTURER;

            if (brand) {//rom决定,选用那种push平台
                IMixPushManager manager = null;
                Class<?> cls = null;
                if (phoneMaker.equalsIgnoreCase("xiaomi")) {
                    cls = Class.forName("com.zhpush.client.mipush.MiPushManager");
                    manager = (IMixPushManager) cls.newInstance();
                    addPushManager(manager);
                } else if (phoneMaker.equalsIgnoreCase("huawei")) {
                    cls = Class.forName("com.zhpush.client.huawei.HuaweiPushManager");
                    manager = (IMixPushManager) cls.newInstance();
                    addPushManager(manager);
                } else {
                    cls = Class.forName("com.zhpush.client.umeng.UmengPushManager");
                    manager = (IMixPushManager) cls.newInstance();

                    Class<?> cls2 = Class.forName("com.zhpush.client.sogopush.SogoPushManager");
                    IMixPushManager manager2 = (IMixPushManager) cls2.newInstance();

                    addPushManager(manager);
                    addPushManager(manager2);
                }
            } else {
                if (platforms != null && platforms.size() > 0) {
                    IMixPushManager manager = null;
                    Class<?> cls = null;
                    for (String platform : platforms) {
                        if (platform.equalsIgnoreCase("xiaomi")) {
                            cls = Class.forName("com.zhpush.client.mipush.MiPushManager");
                        } else if (platform.equalsIgnoreCase("huawei")) {
                            cls = Class.forName("com.zhpush.client.huawei.HuaweiPushManager");
                        } else if (platform.equalsIgnoreCase("umeng")) {
                            cls = Class.forName("com.zhpush.client.umeng.UmengPushManager");
                        } else if (platform.equalsIgnoreCase("sogo")) {
                            cls = Class.forName("com.zhpush.client.sogopush.SogoPushManager");
                        }
                        if (cls != null) {
                            manager = (IMixPushManager) cls.newInstance();
                            addPushManager(manager);
                        }
                    }
                }
            }
        } catch (Exception e) {
            CLog.e(tag, e);
        }
    }


    public static String getUsePushName() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, IMixPushManager> entry : sPushManagerMap.entrySet()) {
            IMixPushManager iMixPushManager = entry.getValue();
            if (!iMixPushManager.isDisable()) {
                sb.append(entry.getKey());
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    public static void addPushManager(IMixPushManager pushAdapter) {
        sPushManagerMap.put(pushAdapter.getName(), pushAdapter);
    }

    public static void registerPush(Context context, ITokenListener listener) {
        if (!MsgDbManager.isInit) {
            MsgDbManager.getInstance().init(context);
        }

        for (Map.Entry<String, IMixPushManager> entry : sPushManagerMap.entrySet()) {
            IMixPushManager iMixPushManager = entry.getValue();
            iMixPushManager.registerPush(context, listener);
            iMixPushManager.setMessageProvider(defaultProvider);
        }
    }

    public static void registerPushByName(Context context, String name, ITokenListener listener) {
        try {
            IMixPushManager manager = null;
            Class<?> cls = null;
            if (name.equalsIgnoreCase("meizu")) {
                cls = Class.forName("com.zhpush.client.meizu.MeizuPushManager");
            } else if (name.equalsIgnoreCase("xiaomi")) {
                cls = Class.forName("com.zhpush.client.mipush.MiPushManager");
            } else if (name.equalsIgnoreCase("huawei")) {
                cls = Class.forName("com.zhpush.client.huawei.HuaweiPushManager");
            } else if (name.equalsIgnoreCase("sogo")) {
                cls = Class.forName("com.zhpush.client.sogopush.SogoPushManager");
            } else if (name.equalsIgnoreCase("umeng")) {
                cls = Class.forName("com.zhpush.client.umeng.UmengPushManager");
            }
            manager = (IMixPushManager) cls.newInstance();
            manager.registerPush(context, listener);
            manager.setMessageProvider(defaultProvider);
            addPushManager(manager);
        } catch (Exception e) {
            CLog.e(tag, e);

        }

    }

    public static void activityCreate(Context context) {
        for (Map.Entry<String, IMixPushManager> entry : sPushManagerMap.entrySet()) {
            IMixPushManager value = entry.getValue();
            value.activityCreate(context);
        }
    }

    public static void unRegisterPush(Context context) {
        for (Map.Entry<String, IMixPushManager> entry : sPushManagerMap.entrySet()) {
            IMixPushManager value = entry.getValue();
            value.unRegisterPush(context);
        }
    }


    public static HashMap<String, String> platformsToken(Context context) {
        HashMap<String, String> map = new HashMap<>();
        for (Map.Entry<String, IMixPushManager> entry : sPushManagerMap.entrySet()) {
            IMixPushManager value = entry.getValue();
            map.put(value.getName(), value.getToken(context));
        }
        return map;
    }

    public static void cleanCache() {
        if (MsgDbManager.isInit) {
            MsgDbManager.getInstance().cleanCache();
        }
    }


}
