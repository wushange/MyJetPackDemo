package com.wsg.base;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.wsg.base.keyvalue.KeyValueHelper;
import com.wsg.base.keyvalue.MMKVClient;
import com.wsg.library_fragmentation.core.Fragmentation;
import com.wsg.library_fragmentation.core.helper.ExceptionHandler;

/**
 * Antiy工具类初始化
 */
public class AntiyUtils {

    /**
     * 统一处理应用初始化加载插件
     *
     * @param application app
     */
    public static void init(Application application) {
        //常用工具操作类
        Utils.init(application);
        //持久化配置存储
        KeyValueHelper.init(application, new MMKVClient());
        //Fragment管理框架初始化
        Fragmentation.builder()
                .stackViewMode(Fragmentation.NONE)
                .debug(BuildConfig.DEBUG)
                .handleException(new ExceptionHandler() {
                    @Override
                    public void onException(Exception e) {
                    }
                })
                .install();
    }
}
