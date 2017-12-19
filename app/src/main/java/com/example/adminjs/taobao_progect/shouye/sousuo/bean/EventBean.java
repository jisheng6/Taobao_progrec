package com.example.adminjs.taobao_progect.shouye.sousuo.bean;

/**
 * Created by Adminjs on 2017/12/7.
 */
public class EventBean {
    public String url;
    public String title;
    public String price;

    public EventBean(String images, String s, String price, String title, String url) {
        this.price = price;
        this.title = title;
        this.url = url;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
