package com.example.cardgit;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class CardAddActivity extends AppCompatActivity {


    private Toolbar toolbar;
    private Card card;
    private EditText edName;
    private EditText edCategory;
    private EditText edDiscount;


    private static final int ADD_CATEGORY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_card_activity);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Добавить карту");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        edName = findViewById(R.id.edName);
        edCategory = findViewById(R.id.edCategory);
        edDiscount = findViewById(R.id.edDiscount);
        card = new Card();


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

    public void onClick(View view) {
        card.setName(edName.getText().toString());
        card.setCategory(edCategory.getText().toString());
        card.setDiscount(edDiscount.getText().toString());

        List<Photo> photos = new ArrayList<>();
        photos.add(new Photo(R.drawable.lenta));
        photos.add(new Photo(R.drawable.lenta));
        card.setPhoto(photos);

        Intent intent = new Intent(this, CardListActivity.class);
        intent.putExtra(Card.class.getSimpleName(), card);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void etCategory(View view) {
        Intent intent = new Intent(this, CategoryListActivity.class);
        startActivityForResult(intent, ADD_CATEGORY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case ADD_CATEGORY:

                    Bundle arg = data.getExtras();
                    if (arg == null) {
                        return;
                    }

                    Category category = (Category) arg.getSerializable(Category.class.getSimpleName());
                    if (category == null) {
                        return;
                    }
                    String nameCateg = category.getName();
                    edCategory.setText(nameCateg);
            }
        }
    }
}