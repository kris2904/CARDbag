package com.example.cardgit;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class PotoRealm extends RealmObject {


    public void setIconUrl(int iconUrl) {
        this.iconUrl = iconUrl;
    }

    public int getIconUrl() {
        return iconUrl;
    }

    @PrimaryKey
    private  int iconUrl;


}
