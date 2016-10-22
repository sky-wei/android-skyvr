package com.sky.vr.data.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sky on 16-10-22.
 */

public class ResRetailsModel implements Serializable {

    private int resId;
    private String title;
    private int type;
    private int subtype;
    private String subtitle;
    private String desc;

    private String source;
    private String downloadUrl;
    private String size;
    private int downloadCount;

    private List<VideoAttrs> videoAttrs;
    private List<String> screenshot;
    private List<String> thumbPicUrl;

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSubtype() {
        return subtype;
    }

    public void setSubtype(int subtype) {
        this.subtype = subtype;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }

    public List<VideoAttrs> getVideoAttrs() {
        return videoAttrs;
    }

    public void setVideoAttrs(List<VideoAttrs> videoAttrs) {
        this.videoAttrs = videoAttrs;
    }

    public List<String> getScreenshot() {
        return screenshot;
    }

    public void setScreenshot(List<String> screenshot) {
        this.screenshot = screenshot;
    }

    public List<String> getThumbPicUrl() {
        return thumbPicUrl;
    }

    public void setThumbPicUrl(List<String> thumbPicUrl) {
        this.thumbPicUrl = thumbPicUrl;
    }

    public static class VideoAttrs implements Serializable {

        private int definitionId;
        private String definitionName;
        private String size;
        private String playUrl;
        private String downloadUrl;

        public int getDefinitionId() {
            return definitionId;
        }

        public void setDefinitionId(int definitionId) {
            this.definitionId = definitionId;
        }

        public String getDefinitionName() {
            return definitionName;
        }

        public void setDefinitionName(String definitionName) {
            this.definitionName = definitionName;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getPlayUrl() {
            return playUrl;
        }

        public void setPlayUrl(String playUrl) {
            this.playUrl = playUrl;
        }

        public String getDownloadUrl() {
            return downloadUrl;
        }

        public void setDownloadUrl(String downloadUrl) {
            this.downloadUrl = downloadUrl;
        }
    }
}
