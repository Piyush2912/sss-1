package com.devsebastian.gtbit;

import java.util.ArrayList;

public class FeedRow {
    String shopName;
    ArrayList<Item> items;

    public FeedRow() {
    }

    public FeedRow(String shopName, ArrayList<Item> items) {
        this.shopName = shopName;
        this.items = items;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
}
