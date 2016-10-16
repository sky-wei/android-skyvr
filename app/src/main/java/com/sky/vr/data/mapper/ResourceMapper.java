package com.sky.vr.data.mapper;

import com.sky.vr.data.model.ResourceModel;
import com.sky.vr.data.mojing.Result;
import com.sky.vr.data.mojing.TagsResource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by starrysky on 16-10-10.
 */

public class ResourceMapper extends BaseMapper {

    public ResourceModel transform(Result<TagsResource> result) {
        return transform(getData(result));
    }

    public ResourceModel transform(TagsResource tagsResource) {

        if (tagsResource == null) return null;

        ResourceModel model = new ResourceModel();

        model.setTitle(tagsResource.getTitle());
        model.setTotal(tagsResource.getTotal());
        model.setResources(transform(tagsResource.getList()));

        return model;
    }

    public List<ResourceModel.Resource> transform(List<TagsResource.Resource> resources) {

        if (resources == null) return null;

        List<ResourceModel.Resource> tResources = new ArrayList<>();

        for (TagsResource.Resource resource : resources) {

            ResourceModel.Resource tResource = transform(resource);
            if (tResource != null) tResources.add(tResource);
        }

        return tResources;
    }

    public ResourceModel.Resource transform(TagsResource.Resource resource) {

        if (resource == null) return null;

        ResourceModel.Resource tResource = new ResourceModel.Resource();

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
