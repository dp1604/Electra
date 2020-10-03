package com.hdsoft.electra;

public class ReqModle {

    private int id;
    private String title,category,value1,value2;

    public ReqModle(){

    }
    public ReqModle(int id, String title, String category, String value1, String value2) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.value1 = value1;
        this.value2 = value2;

    }

    public ReqModle(String title, String category, String value1, String value2) {
        this.title = title;
        this.category = category;
        this.value1 = value1;
        this.value2 = value2;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }

}
