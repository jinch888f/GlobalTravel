package com.dudu.globaltravel.bean;

/**
 * Created by mac on 2017/7/12.
 */
//cmd+N 快捷创建构造函数和get、set方法
public class AdInfoModel {
    String position;
    String image_url;
    String advert_type;
    String content;
    Boolean rotation;

    public AdInfoModel() {

    }

    public AdInfoModel(String position, String image_url, String advert_type, String content,
                       Boolean rotation) {
        this.position = position;
        this.image_url = image_url;
        this.advert_type = advert_type;
        this.content = content;
        this.rotation = rotation;
    }

    public String getPosition() {
        return position;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getAdvert_type() {
        return advert_type;
    }

    public String getContent() {
        return content;
    }

    public Boolean getRotation() {
        return rotation;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public void setAdvert_type(String advert_type) {
        this.advert_type = advert_type;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setRotation(Boolean rotation) {
        this.rotation = rotation;
    }
}
