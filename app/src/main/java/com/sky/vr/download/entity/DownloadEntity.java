package com.sky.vr.download.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

/**
 * Created by sky on 16-10-30.
 */
@Entity
public class DownloadEntity {

    @Id(autoincrement = true)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String tempName;

    @NotNull
    private String url;

    @NotNull
    private String dir;

    private int progress;
    private long length;
    private long finished;

    /**
     * 是否支持分块下载
     */
    private boolean acceptRanges;
    private int state;
    private long time;

    @Generated(hash = 1203823663)
    public DownloadEntity(Long id, @NotNull String name, @NotNull String tempName,
            @NotNull String url, @NotNull String dir, int progress, long length,
            long finished, boolean acceptRanges, int state, long time) {
        this.id = id;
        this.name = name;
        this.tempName = tempName;
        this.url = url;
        this.dir = dir;
        this.progress = progress;
        this.length = length;
        this.finished = finished;
        this.acceptRanges = acceptRanges;
        this.state = state;
        this.time = time;
    }

    @Generated(hash = 1671715506)
    public DownloadEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTempName() {
        return tempName;
    }

    public void setTempName(String tempName) {
        this.tempName = tempName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public long getFinished() {
        return finished;
    }

    public void setFinished(long finished) {
        this.finished = finished;
    }

    public boolean isAcceptRanges() {
        return acceptRanges;
    }

    public void setAcceptRanges(boolean acceptRanges) {
        this.acceptRanges = acceptRanges;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public boolean getAcceptRanges() {
        return this.acceptRanges;
    }
}
