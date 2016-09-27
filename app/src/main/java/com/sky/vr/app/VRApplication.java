package com.sky.vr.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by sky on 16-9-26.
 */

public class VRApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initImageLoader(getBaseContext());
    }

    private void initImageLoader(Context context) {


    }
}
