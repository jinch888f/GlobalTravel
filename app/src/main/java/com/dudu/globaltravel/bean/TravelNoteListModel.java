package com.dudu.globaltravel.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mac on 2017/7/12.
 */

public class TravelNoteListModel {
    //处理JSON数据中与关键字冲突的字段
    @SerializedName("id")
    public int t_id;
    public String name;
    public int photos_count;
    public String start_date;
    public String end_date;
    public int days;

    public void setDays(int days) {
        this.days = days;
    }

    public int getDays() {

        return days;
    }

    public String front_cover_photo_url;
    public User user;

    public TravelNoteListModel() {
    }

    public void setT_id(int t_id) {
        this.t_id = t_id;
    }

    public int getT_id() {

        return t_id;
    }
    public String getName() {
        return name;
    }

    public int getPhotos_count() {
        return photos_count;
    }

    public String getStart_date() {
        return start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public String getFront_cover_photo_url() {
        return front_cover_photo_url;
    }

    public User getUser() {
        return user;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhotos_count(int photos_count) {
        this.photos_count = photos_count;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public void setFront_cover_photo_url(String front_cover_photo_url) {
        this.front_cover_photo_url = front_cover_photo_url;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public class User {
        public String name;
        public String image;
    }
}
