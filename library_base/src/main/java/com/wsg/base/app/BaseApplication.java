package com.wsg.base.app;

import android.app.Activity;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;

public class BaseApplication extends Application implements ViewModelStoreOwner {
    //TODO tip：可借助 Application 来管理一个应用级 的 SharedViewModel，
    // 实现全应用范围内的 生命周期安全 且 事件源可追溯的 视图控制器 事件通知。

    private ViewModelStore mAppViewModelStore;

    private ViewModelProvider.Factory mFactory;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppViewModelStore = new ViewModelStore();

    }

    @NonNull
    @Override
    public ViewModelStore getViewModelStore() {
        return mAppViewModelStore;
    }

    public ViewModelProvider getAppViewModelProvider(Activity activity) {
        return new ViewModelProvider((BaseApplication) activity.getApplicationContext(),
                ((BaseApplication) activity.getApplicationContext()).getAppFactory(activity));
    }

    private ViewModelProvider.Factory getAppFactory(Activity activity) {
        Application application = checkApplication(activity);
        if (mFactory == null) {
            mFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(application);
        }
        return mFactory;
    }

    private Application checkApplication(Activity activity) {
        Application application = activity.getApplication();
        if (application == null) {
            throw new IllegalStateException("Your activity/fragment is not yet attached to "
                    + "Application. You can't request ViewModel before onCreate call.");
        }
        return application;
    }
}
