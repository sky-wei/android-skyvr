package com.sky.vr.download.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by sky on 16-10-30.
 */
@Entity
public class ThreadEntity {

    @Id(autoincrement = true)
    private Long id;

    /**
     * 任务id
     */
    private long tid;

    @NotNull
    private String url;

    private long start;
    private long end;
    private long finished;

    @Generated(hash = 894116799)
    public ThreadEntity(Long id, long tid, @NotNull String url, long start,
            long end, long finished) {
        this.id = id;
        this.tid = tid;
        this.url = url;
        this.start = start;
        this.end = end;
        this.finished = finished;
    }

    @Generated(hash = 968277741)
    public ThreadEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getTid() {
        return tid;
    }

    public void setTid(long tid) {
        this.tid = tid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public long getFinished() {
        return finished;
    }

    public void setFinished(long finished) {
        this.finished = finished;
    }
}
