package com.example.recipeapp_.models;

public class Food {
    int resId = 0 ;
    String title = "null" ;
    int timecook = 0;
    String nguyenlieu = "null";
    String congthuc = "null";

    public Food(){
        resId = 0;
        title = "null";
        timecook = 10;
        nguyenlieu = congthuc = "null";
    }

    public Food(int resId, String title, int timecook, String nguyenlieu, String congthuc) {
        this.resId = resId;
        this.title = title;
        this.timecook = timecook;
        this.nguyenlieu = nguyenlieu;
        this.congthuc = congthuc;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTimecook() {
        return timecook;
    }

    public void setTimecook(int timecook) {
        this.timecook = timecook;
    }

    public String getNguyenlieu() {
        return nguyenlieu;
    }

    public void setNguyenlieu(String nguyenlieu) {
        this.nguyenlieu = nguyenlieu;
    }

    public String getCongthuc() {
        return congthuc;
    }

    public void setCongthuc(String congthuc) {
        this.congthuc = congthuc;
    }
}
