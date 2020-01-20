package com.devsebastian.gtbit;

public class BillItem {

    String title, imgurl;
    Integer id, quantity;
    Double cost;

    public BillItem(int id, String title, String imgUrl, int quantity, Double cost) {
        this.id = id;
        this.title = title;
        this.imgurl = imgUrl;
        this.quantity = quantity;
        this.cost = cost;
    }

    public BillItem(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public Integer getId() {
        return id;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
