package com.example.cardgit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

public class CategoryListActivity extends AppCompatActivity implements CategoryAdapter.onItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Выбрать категорию");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

       RecyclerView recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        CategoryAdapter adapter = new CategoryAdapter(this, DataBaseHelper.getCategories(),this);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);
        //layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_activity_category, menu);
        return true;
    }

    @Override
    public void onItemClick(Category item) {
        Intent intent = new Intent(this,CardAddActivity.class);
        intent.putExtra(Category.class.getSimpleName(), item);
        setResult(RESULT_OK, intent);
        finish();
    }
}
