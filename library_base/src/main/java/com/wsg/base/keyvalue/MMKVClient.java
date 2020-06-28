package com.wsg.base.keyvalue;

import android.os.Parcelable;

import com.tencent.mmkv.MMKV;

/**
 * KeyValueClient接口实现类
 */
public class MMKVClient implements KeyValueClient {

    @Override
    public void putInt(String key, int value) {
        MMKV.defaultMMKV().encode(key, value);
    }

    @Override
    public int getInt(String key) {
        return MMKV.defaultMMKV().decodeInt(key);
    }

    @Override
    public int getInt(String key, int defValue) {
        return MMKV.defaultMMKV().decodeInt(key, defValue);
    }

    @Override
    public void putBool(String key, boolean value) {
        MMKV.defaultMMKV().encode(key, value);
    }

    @Override
    public boolean getBool(String key) {
        return MMKV.defaultMMKV().decodeBool(key);
    }

    @Override
    public boolean getBool(String key, boolean defValue) {
        return MMKV.defaultMMKV().decodeBool(key, defValue);
    }

    @Override
    public void putLong(String key, long value) {
        MMKV.defaultMMKV().encode(key, value);
    }

    @Override
    public long getLong(String key) {
        return MMKV.defaultMMKV().decodeLong(key);
    }

    @Override
    public long getLong(String key, long defValue) {
        return MMKV.defaultMMKV().decodeLong(key, defValue);
    }

    @Override
    public void putFloat(String key, float value) {
        MMKV.defaultMMKV().encode(key, value);
    }

    @Override
    public float getFloat(String key) {
        return MMKV.defaultMMKV().decodeFloat(key, 0F);
    }

    @Override
    public float getFloat(String key, float defValue) {
        return MMKV.defaultMMKV().decodeFloat(key, defValue);
    }

    @Override
    public void putDouble(String key, double value) {
        MMKV.defaultMMKV().encode(key, value);
    }

    @Override
    public double getDouble(String key) {
        return MMKV.defaultMMKV().decodeDouble(key);
    }

    @Override
    public double getDouble(String key, double defValue) {
        return MMKV.defaultMMKV().decodeDouble(key, defValue);
    }

    @Override
    public void putString(String key, String value) {
        MMKV.defaultMMKV().encode(key, value);
    }

    @Override
    public String getString(String key) {
        return MMKV.defaultMMKV().decodeString(key);
    }

    @Override
    public String getString(String key, String defValue) {
        return MMKV.defaultMMKV().decodeString(key, defValue);
    }

    @Override
    public void putBytes(String key, byte[] value) {
        MMKV.defaultMMKV().encode(key, value);
    }

    @Override
    public byte[] getBytes(String key) {
        return MMKV.defaultMMKV().decodeBytes(key);
    }

    @Override
    public byte[] getBytes(String key, byte[] defValue) {
        return MMKV.defaultMMKV().decodeBytes(key, defValue);
    }

    @Override
    public void put(String key, Parcelable value) {
        MMKV.defaultMMKV().encode(key, value);
    }

    @Override
    public <T extends Parcelable> T get(String key, Class<T> tClass) {
        return MMKV.defaultMMKV().decodeParcelable(key, tClass);
    }
}