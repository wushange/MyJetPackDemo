package com.wsg.base.keyvalue;

import android.app.Application;

import com.tencent.mmkv.MMKV;

public class KeyValueHelper {
    private KeyValueClient keyValueClient;

    private KeyValueHelper() {
    }

    private static class SingletonInstance {
        private static final KeyValueHelper INSTANCE = new KeyValueHelper();
    }

    public static KeyValueHelper init(Application application, KeyValueClient client) {
        //这里使用得MMKV框架，所以需要初始化一下mmkv得配置，也可以在Application中单独配置  这里为了application得简洁统一放在这里， 更换其他框架可替换其他初始化
        MMKV.initialize(application);
        KeyValueHelper keyValueHelper = SingletonInstance.INSTANCE;
        keyValueHelper.keyValueClient = (client);
        return keyValueHelper;
    }

    public static KeyValueHelper getInstance() {
        return SingletonInstance.INSTANCE;
    }

    public KeyValueClient getClient() {
        if (keyValueClient == null) {
            throw new IllegalArgumentException("KeyValueHelper初出错: KeyValueClient不能为null");
        }
        return keyValueClient;
    }

}