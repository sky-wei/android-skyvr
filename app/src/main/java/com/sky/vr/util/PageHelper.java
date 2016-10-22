package com.sky.vr.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sky on 16-10-21.
 */

public class PageHelper<T> {

    public static final int PAGE_SIZE = 20;

    private int mCurPage;
    private int mTotal;
    private int mTotalPage;
    private List<T> mDatas;

    public PageHelper() {
        mDatas = new ArrayList<>();
    }

    public PageHelper(List<T> mDatas) {
        this.mDatas = mDatas;
    }

    public void setData(List<T> datas) {
        setData(datas != null ? datas.size() : 0, datas);
    }

    public void setData(int total, List<T> datas) {

        reset();
        mTotal = total;

        if (mTotal > PAGE_SIZE) {
            mTotalPage = mTotal / PAGE_SIZE;
            if (mTotal % PAGE_SIZE >= 1) mTotalPage++;
        } else {
            mTotalPage = 1;
        }

        if (datas != null && !datas.isEmpty()) {
            mDatas.addAll(datas);
        }
    }

    public List<T> getData() {
        return mDatas;
    }

    public T getDataItem(int index) {
        return mDatas.get(index);
    }

    public boolean appendData(List<T> datas) {

        if (datas == null
                || datas.isEmpty()
                || !isNextPage()) {
            return false;
        }

        mCurPage++;
        return mDatas.addAll(datas);
    }

    public boolean isNextPage() {
        return mCurPage + 1 < mTotalPage;
    }

    public int getCurPage() {
        return mCurPage;
    }

    private void reset() {

        mCurPage = 0;
        mTotal = 0;
        mTotalPage = 0;
        mDatas.clear();
    }
}
