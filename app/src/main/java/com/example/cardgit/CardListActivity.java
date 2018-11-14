package com.example.cardgit;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

public class CardListActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RelativeLayout rlCard;
    private RelativeLayout rlNoCard;
    private TextView tvName;
    private TextView tvCategory;
    private TextView tvDiscount;
    private static final int ADD_CARD = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_list);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Мои карты");



        rlCard = findViewById(R.id.rl_with_card);
        rlCard.setVisibility(View.GONE);

        rlNoCard = findViewById(R.id.rl_no_card);

        tvName = findViewById(R.id.tvName);
        tvCategory = findViewById(R.id.tvCategory);
        tvDiscount = findViewById(R.id.tvDiscount);

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

      switch (requestCode){
            case ADD_CARD:
                if (resultCode == RESULT_OK) {
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

                    String name = card.getName();
                    String category = card.getCategory();
                    String discount = card.getDiscount();

                    tvName.setText(name);
                    tvCategory.setText(category);
                    tvDiscount.setText("Скидка " + discount + "%");
                }
      }
        }



    public void onClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivityForResult(intent, ADD_CARD);
    }
}