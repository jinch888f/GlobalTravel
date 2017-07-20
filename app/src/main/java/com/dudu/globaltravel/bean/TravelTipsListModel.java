package com.dudu.globaltravel.bean;

/**
 * Created by mac on 2017/7/13.
 */

public class TravelTipsListModel {
    public String name_zh_cn;
    public String name_en;
    public int poi_count;
    public float lat;
    public float lng;
    public String image_url;
    public long updated_at;

    public TravelTipsListModel() {
    }

    public String getName_zh_cn() {
        return name_zh_cn;
    }

    public String getName_en() {
        return name_en;
    }

    public int getPoi_count() {
        return poi_count;
    }

    public float getLat() {
        return lat;
    }

    public float getLng() {
        return lng;
    }

    public String getImage_url() {
        return image_url;
    }

    public long getUpdated_at() {
        return updated_at;
    }

    public void setName_zh_cn(String name_zh_cn) {
        this.name_zh_cn = name_zh_cn;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public void setPoi_count(int poi_count) {
        this.poi_count = poi_count;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public void setUpdated_at(long updated_at) {
        this.updated_at = updated_at;
    }
}
