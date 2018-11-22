package com.example.cardgit;



import android.support.v7.widget.RecyclerView;

import java.io.Serializable;


public class Photo implements Serializable
 {

    private int iconUrl;



    public Photo(int iconSources) {
        this.iconUrl = iconSources;


    }


    public int getIconSources() {
        return iconUrl;
    }

}
