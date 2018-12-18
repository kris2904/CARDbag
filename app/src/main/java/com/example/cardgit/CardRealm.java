package com.example.cardgit;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class CardRealm extends RealmObject {
    @PrimaryKey
    public int id;
    public String name;
    public CategoryRealm category;
    public String discount;
    public RealmList<PotoRealm> photo;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public RealmList<PotoRealm> getPhoto() {
        return photo;
    }

    public void setPhoto(RealmList<PotoRealm> photo) {
        this.photo = photo;
    }
}
