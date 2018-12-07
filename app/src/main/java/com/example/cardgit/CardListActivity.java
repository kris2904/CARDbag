package com.example.cardgit;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmResults;

public class CardListActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView rlCard;
    private RelativeLayout rlNoCard;
    private static final int ADD_CARD = 1;
    private List<Card> cards;
    CardAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_list);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Мои карты");
        adapter = new CardAdapter(this);

        cards=map2Data(getCards());
        adapter.setCards(cards);

        rlCard = findViewById(R.id.rvCard);
        rlCard.setVisibility(View.GONE);
        rlNoCard = findViewById(R.id.rl_no_card);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        rlCard.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,linearLayoutManager.getOrientation());
        rlCard.addItemDecoration(dividerItemDecoration);
        rlCard.setAdapter(adapter);
        if (cards == null || cards.isEmpty()){
            rlCard.setVisibility(View.GONE);
            rlNoCard.setVisibility(View.VISIBLE);
        }
        else {
            rlCard.setVisibility(View.VISIBLE);
            rlNoCard.setVisibility(View.GONE);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

                if (resultCode == RESULT_OK) {
                    switch (requestCode){
                        case ADD_CARD:
                    rlCard.setVisibility(View.VISIBLE);
                    rlNoCard.setVisibility(View.GONE);

                    Bundle arg = data.getExtras();
                    if (arg == null) {
                        return;
                    }

                    Card card = (Card) arg.getSerializable(Card.class.getSimpleName());
                    if (card == null) {
                        return;
                    }


                    rlCard.setAdapter(adapter);
                    adapter.insertItem(card);
                }
      }
        }



    public void onClick(View view) {
        Intent intent = new Intent(this, CardAddActivity.class);
        startActivityForResult(intent, ADD_CARD);
    }
    private Category categoryMap2Date(CategoryRealm categoryRealm){
        Category category = new Category();
        category.setName(categoryRealm.getName());
        category.setId(categoryRealm.getId());
        return category;
    }
    private List<Card> map2Data(List<CardRealm> realmList) {
        List<Card> cards = new ArrayList<>();
        for (CardRealm cardRealm : realmList) {
            Card card = new Card (
                    cardRealm.getId(),
                    cardRealm.getName(),
                    categoryMap2Date(cardRealm.getCategory()),
                    cardRealm.getDiscount(),
                    photoMap2Date(cardRealm.getPhoto())
            );
            cards.add(card);
        }
        return cards;
    }
    private List<Photo> photoMap2Date(List<PotoRealm> realmList) {
        List<Photo> photos = new ArrayList<>();
        for (PotoRealm photoRealm : realmList) {
            Photo photo = new Photo(
                    photoRealm.getIconUrl()
            );
            photos.add(photo);
        }
        return photos;
    }
    private RealmResults<CardRealm> getCards(){
        return Realm.getDefaultInstance().where(CardRealm.class).findAll();
    }
}