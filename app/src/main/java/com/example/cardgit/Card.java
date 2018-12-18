package com.example.cardgit;

import android.support.v7.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class   Card implements Serializable {

    public Card(int id, String name, Category category, String discount, List<Photo> photo) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.discount = discount;
        this.photo = photo;
    }

    private int id;
    private String name;
    private Category category;
    private String discount;

    public Card() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public List<Photo> getPhoto() {
        return photo;
    }

    public void setPhoto(List<Photo> photo) {
        this.photo = photo;
    }

    public List<Photo> photo;
}