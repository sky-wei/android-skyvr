package com.sky.vr.data.mojing;

import java.util.List;

/**
 * Created by sky on 16-9-29.
 */

public class Tags {

    List<Category> list;
    private String url;
    private String type;
    private String subtype;

    public List<Category> getList() {
        return list;
    }

    public void setList(List<Category> list) {
        this.list = list;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }


    public static class Category {

        private String bg;
        private int res_id;
        private String keyname;
        private String title;
        private List<Classify> list;
        private String type;
        private String subtype;

        public String getBg() {
            return bg;
        }

        public void setBg(String bg) {
            this.bg = bg;
        }

        public int getRes_id() {
            return res_id;
        }

        public void setRes_id(int res_id) {
            this.res_id = res_id;
        }

        public String getKeyname() {
            return keyname;
        }

        public void setKeyname(String keyname) {
            this.keyname = keyname;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<Classify> getList() {
            return list;
        }

        public void setList(List<Classify> list) {
            this.list = list;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getSubtype() {
            return subtype;
        }

        public void setSubtype(String subtype) {
            this.subtype = subtype;
        }
    }

    public static class Classify {

        private String bg;
        private String keyname;
        private String title;
        private List<SubClassify> list;
        private String type;
        private String subtype;

        public String getBg() {
            return bg;
        }

        public void setBg(String bg) {
            this.bg = bg;
        }

        public String getKeyname() {
            return keyname;
        }

        public void setKeyname(String keyname) {
            this.keyname = keyname;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<SubClassify> getList() {
            return list;
        }

        public void setList(List<SubClassify> list) {
            this.list = list;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getSubtype() {
            return subtype;
        }

        public void setSubtype(String subtype) {
            this.subtype = subtype;
        }
    }

    public static class SubClassify {

        private int id;
        private String title;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
