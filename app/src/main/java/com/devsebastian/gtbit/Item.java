package com.devsebastian.gtbit;

public class Item {
    String title, deal, imgUrl;
    Integer cost, id;

    public Item() {
    }

    public Item(String title, String deal, String imgUrl, Integer cost, Integer id) {
        this.title = title;
        this.deal = deal;
        this.imgUrl = imgUrl;
        this.cost = cost;
        this.id = id;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDeal() {
        return deal;
    }

    public void setDeal(String deal) {
        this.deal = deal;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
