package com.wsg.base.keyvalue;

import android.os.Parcelable;

/**
 * KeyValue接口，可通过实现类， 实现不同框架加载
 */
public interface KeyValueClient {

    void putInt(String key, int value);

    int getInt(String key);

    int getInt(String key, int defValue);

    void putBool(String key, boolean value);

    boolean getBool(String key);

    boolean getBool(String key, boolean defValue);

    void putLong(String key, long value);

    long getLong(String key);

    long getLong(String key, long defValue);

    void putFloat(String key, float value);

    float getFloat(String key);

    float getFloat(String key, float defValue);

    void putDouble(String key, double value);

    double getDouble(String key);

    double getDouble(String key, double defValue);

    void putString(String key, String value);

    String getString(String key);

    String getString(String key, String defValue);

    void putBytes(String key, byte[] value);

    byte[] getBytes(String key);

    byte[] getBytes(String key, byte[] defValue);

    void put(String key, Parcelable value);

    <T extends Parcelable> T get(String key, Class<T> tClass);

}
