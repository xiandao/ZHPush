package com.zhpush.client.pojo;

import com.cjz.clog.CLog;
import com.google.gson.Gson;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

import java.io.Serializable;

@Entity
public class MixPushMessage implements Serializable {
    private static final long serialVersionUID = 5368711111L;

    @Id(autoincrement = true)
    private Long id;
    private String messageId;//消息 id 用来 去重
    @Transient
    private PushAction content; //透传消息内容
    private String platform;// 消息来源平台
    private int notify;// 消息类型  0: 透传  1：通知（目前没有用到）
    private long createTime;


    @Generated(hash = 1223170179)
    public MixPushMessage(Long id, String messageId, String platform, int notify,
            long createTime) {
        this.id = id;
        this.messageId = messageId;
        this.platform = platform;
        this.notify = notify;
        this.createTime = createTime;
    }


    @Generated(hash = 1412056051)
    public MixPushMessage() {
    }


    public static MixPushMessage convert(String originalMsg, String platform) {
        MixPushMessage mixPushMessage = null;
        try {
            mixPushMessage = new Gson().fromJson(originalMsg, MixPushMessage.class);
            mixPushMessage.setPlatform(platform);
            mixPushMessage.setCreateTime(System.currentTimeMillis());
        } catch (Exception e) {
            CLog.e("MixPushMessage", e);
        }

        return mixPushMessage;
    }


    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public PushAction getContent() {
        return content;
    }

    public void setContent(PushAction content) {
        this.content = content;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public int getNotify() {
        return notify;
    }

    public void setNotify(int notify) {
        this.notify = notify;
    }




    public long getCreateTime() {
        return this.createTime;
    }


    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }


    public Long getId() {
        return this.id;
    }


    public void setId(Long id) {
        this.id = id;
    }
}
