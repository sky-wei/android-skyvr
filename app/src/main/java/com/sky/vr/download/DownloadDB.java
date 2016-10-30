package com.sky.vr.download;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.sky.vr.download.entity.AppEntityDao;
import com.sky.vr.download.entity.DaoMaster;
import com.sky.vr.download.entity.DaoSession;
import com.sky.vr.download.entity.DownloadEntityDao;
import com.sky.vr.download.entity.ThreadEntityDao;
import com.sky.vr.download.entity.VideoEntityDao;

/**
 * Created by starrysky on 16-5-12.
 */
public class DownloadDB {

    private static final String DB_NAME = "download-db";

    private static DownloadDB mDownloadDB;

    public static DownloadDB getInstance(Context context) {

        if (mDownloadDB == null) {
            synchronized (DownloadDB.class) {
                if (mDownloadDB == null) {
                    mDownloadDB = new DownloadDB(context);
                }
            }
        }
        return mDownloadDB;
    }

    private Context mContext;
    private SQLiteDatabase mSqLiteDatabase;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    private DownloadDB(Context context) {
        mContext = context;
        initDBManager();
    }

    private void initDBManager() {

//        DaoMaster.OpenHelper openHelper;
//
//        if (Configure.isReleaseVersion) {
//            openHelper = new MOpenHelper(mContext, DB_NAME, null);
//        } else {
//            openHelper = new DaoMaster.DevOpenHelper(mContext, DB_NAME, null);
//        }

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(mContext, DB_NAME, null);
        mSqLiteDatabase = helper.getWritableDatabase();
        mDaoMaster = new DaoMaster(mSqLiteDatabase);
        mDaoSession = mDaoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public DownloadEntityDao getDownloadEntityDao() {
        return mDaoSession.getDownloadEntityDao();
    }

    public ThreadEntityDao getThreadEntityDao() {
        return mDaoSession.getThreadEntityDao();
    }

    public AppEntityDao getAppEntityDao() {
        return mDaoSession.getAppEntityDao();
    }

    public VideoEntityDao getVideoEntityDao() {
        return mDaoSession.getVideoEntityDao();
    }

    public void release() {

        if (mDaoMaster == null) return ;

        mDaoSession.clear();
        mSqLiteDatabase.close();
    }

    private final class MOpenHelper extends DaoMaster.OpenHelper {

        public MOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
            super(context, name, factory);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // 数据库升级，需要处理的方法
        }
    }
}
