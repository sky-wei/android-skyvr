package com.sky.vr.data.mapper;

import com.sky.vr.data.DataException;
import com.sky.vr.data.mojing.Result;

/**
 * Created by sky on 16-10-16.
 */

public abstract class BaseMapper {

    public <T> T getData(Result<T> result) {

        if (result == null) {
            throw new DataException("后台数据为NULL", DataException.CODE_CUSTOM);
        }

        if (result.getStatus() != 0) {
            // 后台异常
            throw new DataException(result.getStatus_msg(), result.getStatus());
        }

        return result.getData();
    }
}
