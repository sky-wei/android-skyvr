package com.sky.vr.util;

import com.google.gson.Gson;

/**
 * Created by sky on 16-10-30.
 */

public class GsonUtil {

    public static String toJson(Object object) {

        Gson gson = new Gson();

        return gson.toJson(object);
    }
}
