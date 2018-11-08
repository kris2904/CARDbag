package com.example.cardgit;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private Toolbar toolbar;
    private Card card;
    private EditText name;
    private EditText category;
    private EditText discount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Добавить карту");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        name = findViewById(R.id.Name);
        category = findViewById(R.id.Category);
        discount = findViewById(R.id.Discount);

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

    public void onClick (View view) {
        card.setName(name.getText().toString());
        card.setCategory(category.getText().toString());
        card.setDiscount(discount.getText().toString());

        Intent intent = new Intent(this, CardListActivity.class);
        intent.putExtra(Card.class.getSimpleName(),card);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
 {
    }
}