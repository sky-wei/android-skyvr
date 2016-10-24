package com.sky.vr.data.mapper;

import com.sky.vr.data.model.ResRetailsModel;
import com.sky.vr.data.mojing.ResRetails;
import com.sky.vr.data.mojing.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sky on 16-10-22.
 */

public class ResRetailsMapper extends BaseMapper {

    public ResRetailsModel transform(Result<ResRetails> result) {
        return transform(getData(result));
    }

    public ResRetailsModel transform(ResRetails resRetails) {

        if (resRetails == null) return null;

        ResRetailsModel model = new ResRetailsModel();

        model.setResId(resRetails.getRes_id());
        model.setDesc(resRetails.getDesc());
        model.setDownloadCount(resRetails.getDownload_count());
        model.setScreenshot(resRetails.getScreenshot());
        model.setSize(resRetails.getSize());
        model.setSource(resRetails.getSource());
        model.setSubtitle(resRetails.getSubtitle());
        model.setSubtype(resRetails.getSubtype());
        model.setThumbPicUrl(resRetails.getThumb_pic_url());
        model.setTitle(resRetails.getTitle());
        model.setScore(resRetails.getScore());
        model.setType(resRetails.getType());
        model.setDownloadUrl(resRetails.getDownload_url());
        model.setVideoAttrs(transform(resRetails.getVideo_attrs()));

        return model;
    }

    public List<ResRetailsModel.VideoAttrs> transform(List<ResRetails.VideoAttrs> videoAttrs) {

        if (videoAttrs == null) return null;

        List<ResRetailsModel.VideoAttrs> attrses = new ArrayList<>();

        for (int i = 0; i < videoAttrs.size(); i++) {

            ResRetailsModel.VideoAttrs attrs = transform(videoAttrs.get(i));
            if (attrs != null) attrses.add(attrs);
        }

        return attrses;
    }

    public ResRetailsModel.VideoAttrs transform(ResRetails.VideoAttrs videoAttrs) {

        if (videoAttrs == null) return null;

        ResRetailsModel.VideoAttrs attrs = new ResRetailsModel.VideoAttrs();

        attrs.setDefinitionId(videoAttrs.getDefinition_id());
        attrs.setDefinitionName(videoAttrs.getDefinition_name());
        attrs.setDownloadUrl(videoAttrs.getDownload_url());
        attrs.setPlayUrl(videoAttrs.getPlay_url());
        attrs.setSize(videoAttrs.getSize());

        return attrs;
    }
}
