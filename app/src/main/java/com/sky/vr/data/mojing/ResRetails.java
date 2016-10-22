package com.sky.vr.data.mojing;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sky on 16-10-22.
 */

public class ResRetails implements Serializable {

    private int res_id;
    private String title;
    private int type;
    private int subtype;
    private String subtitle;
    private String desc;
    private int payment_type;
    private String  payment_count;
    private int is_pay;
    private String score;
    private int score_count;
    private String source;

    private int pov_heading;
    private int video_dimension;
    private String subtitle_file;
    private int operation_type;
    private int video_is_live;
    private int duration;
    private int is_panorama;
    private String size;
    private String download_url;
    private int download_count;

    private List<VideoAttrs> video_attrs;
    private List<String> screenshot;
    private List<String> thumb_pic_url;

    public int getRes_id() {
        return res_id;
    }

    public void setRes_id(int res_id) {
        this.res_id = res_id;
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

    public int getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(int payment_type) {
        this.payment_type = payment_type;
    }

    public String getPayment_count() {
        return payment_count;
    }

    public void setPayment_count(String payment_count) {
        this.payment_count = payment_count;
    }

    public int getIs_pay() {
        return is_pay;
    }

    public void setIs_pay(int is_pay) {
        this.is_pay = is_pay;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public int getScore_count() {
        return score_count;
    }

    public void setScore_count(int score_count) {
        this.score_count = score_count;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getPov_heading() {
        return pov_heading;
    }

    public void setPov_heading(int pov_heading) {
        this.pov_heading = pov_heading;
    }

    public int getVideo_dimension() {
        return video_dimension;
    }

    public void setVideo_dimension(int video_dimension) {
        this.video_dimension = video_dimension;
    }

    public String getSubtitle_file() {
        return subtitle_file;
    }

    public void setSubtitle_file(String subtitle_file) {
        this.subtitle_file = subtitle_file;
    }

    public int getOperation_type() {
        return operation_type;
    }

    public void setOperation_type(int operation_type) {
        this.operation_type = operation_type;
    }

    public int getVideo_is_live() {
        return video_is_live;
    }

    public void setVideo_is_live(int video_is_live) {
        this.video_is_live = video_is_live;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getIs_panorama() {
        return is_panorama;
    }

    public void setIs_panorama(int is_panorama) {
        this.is_panorama = is_panorama;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDownload_url() {
        return download_url;
    }

    public void setDownload_url(String download_url) {
        this.download_url = download_url;
    }

    public int getDownload_count() {
        return download_count;
    }

    public void setDownload_count(int download_count) {
        this.download_count = download_count;
    }

    public List<VideoAttrs> getVideo_attrs() {
        return video_attrs;
    }

    public void setVideo_attrs(List<VideoAttrs> video_attrs) {
        this.video_attrs = video_attrs;
    }

    public List<String> getScreenshot() {
        return screenshot;
    }

    public void setScreenshot(List<String> screenshot) {
        this.screenshot = screenshot;
    }

    public List<String> getThumb_pic_url() {
        return thumb_pic_url;
    }

    public void setThumb_pic_url(List<String> thumb_pic_url) {
        this.thumb_pic_url = thumb_pic_url;
    }

    public static class VideoAttrs implements Serializable {

        private int definition_id;
        private String definition_name;
        private String size;
        private String play_url;
        private String download_url;
        private String slice_url;

        public int getDefinition_id() {
            return definition_id;
        }

        public void setDefinition_id(int definition_id) {
            this.definition_id = definition_id;
        }

        public String getDefinition_name() {
            return definition_name;
        }

        public void setDefinition_name(String definition_name) {
            this.definition_name = definition_name;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getPlay_url() {
            return play_url;
        }

        public void setPlay_url(String play_url) {
            this.play_url = play_url;
        }

        public String getDownload_url() {
            return download_url;
        }

        public void setDownload_url(String download_url) {
            this.download_url = download_url;
        }

        public String getSlice_url() {
            return slice_url;
        }

        public void setSlice_url(String slice_url) {
            this.slice_url = slice_url;
        }
    }
}
