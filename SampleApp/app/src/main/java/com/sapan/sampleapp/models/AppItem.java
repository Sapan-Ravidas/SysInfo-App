package com.sapan.sampleapp;

import android.graphics.drawable.Drawable;

public class AppItem {
    private String packageName;
    private String appName;
    private Drawable appIcon;
    private Boolean enable;


    public AppItem(String packageName, String appName, Drawable appIcon, Boolean enable) {
        this.packageName = packageName;
        this.appName = appName;
        this.appIcon = appIcon;
        this.enable = enable;
    }

    public String getPkgName() {
        return packageName;
    }

    public String getAppName() {
        return appName;
    }

    public Drawable getAppIcon() {
        return appIcon;
    }

    public Boolean isDualAppEnable() {
        return enable;
    }
}
