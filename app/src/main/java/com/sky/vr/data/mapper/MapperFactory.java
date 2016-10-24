package com.sky.vr.data.mapper;

/**
 * Created by sky on 16-10-24.
 */

public class MapperFactory {

    public static CategoryMapper createCategoryMapper() {
        return new CategoryMapper();
    }

    public static ResListMapper createResListMapper() {
        return new ResListMapper();
    }

    public static ResRetailsMapper createResRetailsMapper() {
        return new ResRetailsMapper();
    }
}
