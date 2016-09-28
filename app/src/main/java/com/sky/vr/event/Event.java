package com.sky.vr.event;

import android.os.Bundle;

import java.io.Serializable;
import java.lang.reflect.Constructor;

/**
 * Created by sky on 16-9-28.
 */

public abstract class Event {

    private int mEventId;
    private Object mData;
    private Bundle mExtras;

    Event() {}

    public int getEventId() {
        return mEventId;
    }

    public Object getData() {
        return mData;
    }

    public Bundle getBundle() {
        return mExtras;
    }

    public boolean getBooleanExtra(String name, boolean defaultValue) {
        return mExtras == null ? defaultValue :
                mExtras.getBoolean(name, defaultValue);
    }

    public int getIntExtra(String name, int defaultValue) {
        return mExtras == null ? defaultValue :
                mExtras.getInt(name, defaultValue);
    }

    public long getLongExtra(String name, long defaultValue) {
        return mExtras == null ? defaultValue :
                mExtras.getLong(name, defaultValue);
    }

    public String getStringExtra(String name) {
        return mExtras == null ? null : mExtras.getString(name);
    }

    public Serializable getSerializableExtra(String name) {
        return mExtras == null ? null : mExtras.getSerializable(name);
    }

    public static class Build {

        private int mEventId;
        private Object mData;
        private Bundle mExtras;

        public Build(int eventId) {

        }

        public Build setEventId(int eventId) {
            mEventId = eventId;
            return this;
        }

        public Build setData(Object data) {
            mData = data;
            return this;
        }

        public Build setBundle(Bundle bundle) {
            mExtras = bundle;
            return this;
        }

        public Build putExtra(String name, boolean value) {
            if (mExtras == null) {
                mExtras = new Bundle();
            }
            mExtras.putBoolean(name, value);
            return this;
        }

        public Build putExtra(String name, int value) {
            if (mExtras == null) {
                mExtras = new Bundle();
            }
            mExtras.putInt(name, value);
            return this;
        }

        public Build putExtra(String name, long value) {
            if (mExtras == null) {
                mExtras = new Bundle();
            }
            mExtras.putLong(name, value);
            return this;
        }

        public Build putExtra(String name, String value) {
            if (mExtras == null) {
                mExtras = new Bundle();
            }
            mExtras.putString(name, value);
            return this;
        }

        public Build putExtra(String name, Serializable value) {
            if (mExtras == null) {
                mExtras = new Bundle();
            }
            mExtras.putSerializable(name, value);
            return this;
        }

        public <T extends Event> T create(Class<T> tClass) {

            try {
                // 创建对象
                Constructor<T> constructor = tClass.getConstructor();
                constructor.setAccessible(true);
                Event event = constructor.newInstance();

                // 设置值
                event.mEventId = mEventId;
                event.mData = mData;
                event.mExtras = mExtras;

                return (T) event;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
