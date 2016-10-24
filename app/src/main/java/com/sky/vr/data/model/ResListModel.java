package com.sky.vr.data.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by starrysky on 16-10-10.
 */

public class ResListModel implements Serializable {

    private String title;
    private int total;
    private List<ResListModel.Resource> resources;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    public static class Resource implements Serializable {

        private int resId;
        private int operationType;
        private int categoryType;
        private String picUrl;
        private String title;
        private String subtitle;
        private int type;
        private int subtype;
        private String url;

        public int getResId() {
            return resId;
        }

        public void setResId(int resId) {
            this.resId = resId;
        }

        public int getOperationType() {
            return operationType;
        }

        public void setOperationType(int operationType) {
            this.operationType = operationType;
        }

        public int getCategoryType() {
            return categoryType;
        }

        public void setCategoryType(int categoryType) {
            this.categoryType = categoryType;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
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

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
