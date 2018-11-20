package com.example.cardgit;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class CardListActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView rlCard;
    private RelativeLayout rlNoCard;
   // private TextView tvName;
   // private TextView tvCategory;
   // private TextView tvDiscount;
  //  private RecyclerView recyclerView;
    private static final int ADD_CARD = 1;
    private List<Card> cards;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_list);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Мои карты");


        cards = new ArrayList<>();

        rlCard = findViewById(R.id.rvCard);
        rlCard.setVisibility(View.GONE);
        rlCard.setLayoutManager(new LinearLayoutManager(this));
        rlNoCard = findViewById(R.id.rl_no_card);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
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

                    CardAdapter adapter = new CardAdapter(this, cards);
                    rlCard.setAdapter(adapter);
                    adapter.insertItem(card);
                }
      }
        }



    public void onClick(View view) {
        Intent intent = new Intent(this, CardAddActivity.class);
        startActivityForResult(intent, ADD_CARD);
    }
}