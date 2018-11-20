package com.example.cardgit;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class CardVH extends RecyclerView.ViewHolder {

    public TextView tvCardName;
    public TextView tvCardCategory;
    public TextView tvCardDiscount;
    public RecyclerView rvPhoto;

    CardVH(@NonNull View itemView) {
        super(itemView);
        tvCardName = itemView.findViewById(R.id.tvName);
        tvCardCategory = itemView.findViewById(R.id.tvCategory);
        tvCardDiscount = itemView.findViewById(R.id.tvDiscount);
        rvPhoto = itemView.findViewById(R.id.rvPhoto);
    }
}
