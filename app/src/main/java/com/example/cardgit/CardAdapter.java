package com.example.cardgit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardVH> {

    private LayoutInflater inflater;
    private List<Card> cards;
    Context context;

    public CardAdapter(Context context, List<Card> cards) {
        this.context = context;
        this.cards = new ArrayList<>();
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CardVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.item_card, viewGroup, false);
        return new CardVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardVH cardVH, int position) {
        final Card cardItem = cards.get(position);

        PhotoAdapter phAdapter = new PhotoAdapter(context, cardItem.getPhoto());
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);

        cardVH.rvPhoto.setLayoutManager(layoutManager);
        cardVH.rvPhoto.setAdapter(phAdapter);
        cardVH.tvCardName.setText(cardItem.getName());
        cardVH.tvCardCategory.setText(cardItem.getCategory());
        cardVH.tvCardDiscount.setText("Скидка " + cardItem.getDiscount() + "%");
    }

    public void insertItem(Card item) {
        cards.add(item);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }
}