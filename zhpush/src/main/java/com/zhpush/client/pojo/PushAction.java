package com.zhpush.client.pojo;

import java.io.Serializable;

/**
 * Created by fujianyi on 2016/2/23.
 */
public class PushAction implements Serializable {
    private String type;
    private String gameId;
    private String iconImg;
    private String title;
    private String subTitle;
    private String intentString;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getIconImg() {
        return iconImg;
    }

    public void setIconImg(String iconImg) {
        this.iconImg = iconImg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getIntentString() {
        return intentString;
    }

    public void setIntentString(String intentString) {
        this.intentString = intentString;
    }
}
