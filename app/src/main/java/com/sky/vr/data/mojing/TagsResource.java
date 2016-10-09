package com.sky.vr.data.mojing;

import java.util.List;

/**
 * Created by starrysky on 16-10-9.
 */

public class TagsResource {

    private int status;
    private String status_msg;
    private int version;
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

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
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

        private String title;
        private String layout_type;
        private int total;
        private String category_url;
        private List<Resource> list;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLayout_type() {
            return layout_type;
        }

        public void setLayout_type(String layout_type) {
            this.layout_type = layout_type;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public String getCategory_url() {
            return category_url;
        }

        public void setCategory_url(String category_url) {
            this.category_url = category_url;
        }

        public List<Resource> getList() {
            return list;
        }

        public void setList(List<Resource> list) {
            this.list = list;
        }
    }

    public static class Resource {

        private int res_id;
        private int operation_type;
        private int category_type;
        private String pic_url;
        private String title;
        private String subtitle;
        private String payment_type;
        private String payment_count;
        private int type;
        private int subtype;
        private String headwear;
        private int is_pay;
        private String url;

        public int getRes_id() {
            return res_id;
        }

        public void setRes_id(int res_id) {
            this.res_id = res_id;
        }

        public int getOperation_type() {
            return operation_type;
        }

        public void setOperation_type(int operation_type) {
            this.operation_type = operation_type;
        }

        public int getCategory_type() {
            return category_type;
        }

        public void setCategory_type(int category_type) {
            this.category_type = category_type;
        }

        public String getPic_url() {
            return pic_url;
        }

        public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
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

        public String getPayment_type() {
            return payment_type;
        }

        public void setPayment_type(String payment_type) {
            this.payment_type = payment_type;
        }

        public String getPayment_count() {
            return payment_count;
        }

        public void setPayment_count(String payment_count) {
            this.payment_count = payment_count;
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

        public String getHeadwear() {
            return headwear;
        }

        public void setHeadwear(String headwear) {
            this.headwear = headwear;
        }

        public int getIs_pay() {
            return is_pay;
        }

        public void setIs_pay(int is_pay) {
            this.is_pay = is_pay;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
