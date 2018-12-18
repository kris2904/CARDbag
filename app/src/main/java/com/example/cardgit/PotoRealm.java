package com.example.cardgit;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class PotoRealm extends RealmObject {


    @PrimaryKey
    private  long imgID;

    public long getImgID() {
        return imgID;
    }

    public void setImgID(long imgID) {
        this.imgID = imgID;
    }
}