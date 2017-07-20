package com.dudu.globaltravel.bean;

import java.util.List;

/**
 * Created by mac on 2017/7/13.
 */

public class TravelipsTModel {
    public String category;
    public List<TravelTipsListModel> destinations;

    public TravelipsTModel() {
    }

    public String getCategory() {
        return category;
    }

    public List<TravelTipsListModel> getDestinations() {
        return destinations;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDestinations(List<TravelTipsListModel> destinations) {
        this.destinations = destinations;
    }
}
