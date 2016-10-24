package com.sky.vr.data.mapper;

import com.sky.vr.data.model.ResListModel;
import com.sky.vr.data.mojing.Result;
import com.sky.vr.data.mojing.ResList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by starrysky on 16-10-10.
 */

public class ResListMapper extends BaseMapper {

    public ResListModel transform(Result<ResList> result) {
        return transform(getData(result));
    }

    public ResListModel transform(ResList resList) {

        if (resList == null) return null;

        ResListModel model = new ResListModel();

        model.setTitle(resList.getTitle());
        model.setTotal(resList.getTotal());
        model.setResources(transform(resList.getList()));

        return model;
    }

    public List<ResListModel.Resource> transform(List<ResList.Resource> resources) {

        if (resources == null) return null;

        List<ResListModel.Resource> tResources = new ArrayList<>();

        for (ResList.Resource resource : resources) {

            ResListModel.Resource tResource = transform(resource);
            if (tResource != null) tResources.add(tResource);
        }

        return tResources;
    }

    public ResListModel.Resource transform(ResList.Resource resource) {

        if (resource == null) return null;

        ResListModel.Resource tResource = new ResListModel.Resource();

        tResource.setTitle(resource.getTitle());
        tResource.setCategoryType(resource.getCategory_type());
        tResource.setOperationType(resource.getOperation_type());
        tResource.setPicUrl(resource.getPic_url());
        tResource.setResId(resource.getRes_id());
        tResource.setSubtitle(resource.getSubtitle());
        tResource.setType(resource.getType());
        tResource.setSubtype(resource.getSubtype());
        tResource.setUrl(resource.getUrl());

        return tResource;
    }
}
