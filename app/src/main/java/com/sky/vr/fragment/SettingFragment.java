package com.sky.vr.fragment;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import com.sky.vr.R;
import com.sky.vr.app.Constant;

/**
 * Created by sky on 16-10-9.
 */

public class SettingFragment extends PreferenceFragment implements
        Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {

    private ListPreference mDeviceListPreference;

    private Preference mImageCachePreference;
    private Preference mDataCachePreference;
    private Preference mDownloadCachePreference;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);

        // 设备设置属性
        mDeviceListPreference = (ListPreference) findPreference(Constant.Preference.DEVICE_SETTING);
        mDeviceListPreference.setOnPreferenceChangeListener(this);

        mImageCachePreference = findPreference(Constant.Preference.IMAGE_CACHE);
        mDataCachePreference = findPreference(Constant.Preference.DATA_CACHE);
        mDownloadCachePreference = findPreference(Constant.Preference.DOWNLOAD_CACHE);

        mImageCachePreference.setSummary("100MB");
        mDataCachePreference.setSummary("100MB");
        mDownloadCachePreference.setSummary("100MB");

        mImageCachePreference.setOnPreferenceClickListener(this);
        mDataCachePreference.setOnPreferenceClickListener(this);
        mDownloadCachePreference.setOnPreferenceClickListener(this);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {

        String key = preference.getKey();

        if (Constant.Preference.DEVICE_SETTING.equals(key)) {

            // 设备设置
            String curValue = mDeviceListPreference.getValue();
            String values = (String) newValue;

            if (values.equals(curValue)) return false;

            // 保存设备值
            mDeviceListPreference.setValue(values);
            return true;
        }

        return false;
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {

        String key = preference.getKey();

        if (Constant.Preference.IMAGE_CACHE.equals(key)) {
            // 清除图片缓存
            mImageCachePreference.setSummary("0KB");
        } else if (Constant.Preference.DATA_CACHE.equals(key)) {
            // 清除数据缓存
            mDataCachePreference.setSummary("0KB");
        } else if (Constant.Preference.DOWNLOAD_CACHE.equals(key)) {
            // 清除下载缓存
            mDownloadCachePreference.setSummary("0KB");
        }
        return true;
    }
}
