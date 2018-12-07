package com.example.cardgit;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class CardRealm extends RealmObject {
    @PrimaryKey
    private int id;
    private String name;
    private CategoryRealm category;
    private String discount;
    private RealmList<PotoRealm> photo;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getCardDiscont() {
        return cardDiscont;
    }

    public void setCardDiscont(int cardDiscont) {
        this.cardDiscont = cardDiscont;
    }

    private int cardDiscont;

    public List<PotoRealm> getPhoto() {
        return photo;
    }

    public void setPhoto(RealmList<PotoRealm> photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryRealm getCategory() {
        return category;
    }

    public void setCategory(CategoryRealm category) {
        this.category = category;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }
}

