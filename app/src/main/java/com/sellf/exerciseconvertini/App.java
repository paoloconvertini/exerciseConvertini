package com.sellf.exerciseconvertini;

import android.app.Application;
import android.content.Context;

import java.lang.ref.WeakReference;

/**
 * Created by ovosodo on 19/06/2017.
 */

public class App extends Application {
    static WeakReference<Context> mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = new WeakReference<Context>(this);
    }

    public static Context getContext() {
        return mContext.get();
    }
}
