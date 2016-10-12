package com.sky.vr.data.mapper;

import com.sky.vr.data.mojing.Tags;
import com.sky.vr.data.model.CategoryModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sky on 16-10-8.
 */

public class CategoryMapper {

    public CategoryModel transform(Tags tags) {

        if (tags == null || tags.getStatus() != 0) return null;

        CategoryModel model = new CategoryModel();

        model.setUrl(tags.getData().getUrl());
        model.setCategories(transform(tags.getData().getList()));

        return model;
    }

    public List<CategoryModel.Category> transform(List<Tags.Category> categories) {

        if (categories == null) return null;

        List<CategoryModel.Category> tCategories = new ArrayList<>();

        for (int i = 0; i < categories.size(); i++) {

            CategoryModel.Category tCategory = transform(categories.get(i));
            if (tCategory != null) tCategories.add(tCategory);
        }

        return tCategories;
    }

    public CategoryModel.Category transform(Tags.Category category) {

        if (category == null) return null;

        CategoryModel.Category tCategory = new CategoryModel.Category();

        tCategory.setResId(category.getRes_id());
        tCategory.setKeyName(category.getKeyname());
        tCategory.setTitle(category.getTitle());
        tCategory.setSubtype(category.getSubtype());
        tCategory.setType(category.getType());
        // 只获取第1个就可以了
        tCategory.setSubCategories(transform2(category.getList().get(0).getList()));

        return tCategory;
    }

    public List<CategoryModel.SubCategory> transform2(List<Tags.SubClassify> subClassifies) {

        if (subClassifies == null) return null;

        List<CategoryModel.SubCategory> subCategories = new ArrayList<>();

        for (int i = 0; i < subClassifies.size(); i++) {

            CategoryModel.SubCategory subCategory = transform(subClassifies.get(i));
            if (subCategories != null) subCategories.add(subCategory);
        }

        return subCategories;
    }

    public CategoryModel.SubCategory transform(Tags.SubClassify subClassify) {

        if (subClassify == null) return null;

        CategoryModel.SubCategory subCategory = new CategoryModel.SubCategory();

        subCategory.setId(subClassify.getId());
        subCategory.setName(subClassify.getTitle());

        return subCategory;
    }
}
