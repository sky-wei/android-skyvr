package com.sky.vr.event;

import android.os.Bundle;

import com.sky.android.common.utils.Alog;

import java.io.Serializable;
import java.lang.reflect.Constructor;

/**
 * Created by sky on 16-9-28.
 */

public class Event {

    private int mEventId;
    private Object mData;
    private Bundle mExtras;

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

    public static  <T extends Event> T buildEvent(Class<T> tClass, int eventId) {
        return buildEvent(tClass, eventId, null);
    }

    public static  <T extends Event> T buildEvent(Class<T> tClass, int eventId, Object data) {
        return new Build(eventId).setData(data).create(tClass);
    }

    public static class Build {

        private int mEventId;
        private Object mData;
        private Bundle mExtras;

        public Build() {}

        public Build(int eventId) {
            mEventId = eventId;
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

            if (tClass == null) return null;

            try {
                // 创建对象
                Event event = tClass.newInstance();

                // 设置值
                event.mEventId = mEventId;
                event.mData = mData;
                event.mExtras = mExtras;

                return (T) event;
            } catch (Exception e) {
                Alog.e("Event", "Create Exception!", e);
            }
            return null;
        }
    }
}
