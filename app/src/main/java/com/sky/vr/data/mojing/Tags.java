package com.sky.vr.data.mojing;

import java.util.List;

/**
 * Created by sky on 16-9-29.
 */

public class Tags {

    private int status;
    private String status_msg;
    private int channel;
    private long date;
    private String language;
    private int data_type;
    private Data data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatus_msg() {
        return status_msg;
    }

    public void setStatus_msg(String status_msg) {
        this.status_msg = status_msg;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getData_type() {
        return data_type;
    }

    public void setData_type(int data_type) {
        this.data_type = data_type;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {

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
