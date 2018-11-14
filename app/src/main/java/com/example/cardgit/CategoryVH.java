package com.example.cardgit;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class CategoryVH extends RecyclerView.ViewHolder {


    public TextView txtCategoryName;

    public CategoryVH(@NonNull View itemView) {
        super(itemView);
        txtCategoryName = (TextView) itemView.findViewById(R.id.cv);

    }}
