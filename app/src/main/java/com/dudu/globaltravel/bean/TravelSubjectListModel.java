package com.dudu.globaltravel.bean;

/**
 * Created by mac on 2017/7/12.
 */

public class TravelSubjectListModel {
    public String name;
    public String image_url;
    public String title;
    public int destination_id;

    public TravelSubjectListModel() {
    }

    public String getName() {
        return name;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getTitle() {
        return title;
    }

    public int getDestination_id() {
        return destination_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDestination_id(int destination_id) {
        this.destination_id = destination_id;
    }
}
