package com.example.cardgit;

import android.accounts.NetworkErrorException;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmResults;

import static com.example.cardgit.DataBaseHelper.categories;

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


        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        List<Category> categoriesRest = null;
        List<CategoryRealm> categoryLocal = null;
        List<Category> categories = new ArrayList<>();

        categoryLocal = getCategoriesFromLocal();

        if (categoryLocal == null || categoryLocal.isEmpty()) {
            try
            {
                categoriesRest = getCategoriesFromRemote();
            }
            catch (NetworkErrorException e) {
                e.printStackTrace();
            }

            if (categoriesRest == null) {
                Toast.makeText(this, "Ошибка серевера", Toast.LENGTH_LONG).show();
                return;
            }

            if (categoriesRest != null) {
                addCategories(categoriesRest);
            }
        }
        categoryLocal = getCategoriesFromLocal();
        categories = map2Data(categoryLocal);

        CategoryAdapter adapter = new CategoryAdapter(this, categories, this);
        recyclerView.setAdapter(adapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    private void addCategories(List<Category> list) {
        final List<CategoryRealm> realmList = map2RealmList(list);
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(realmList);
            }
        });
        realm.close();
    }

    private RealmResults<CategoryRealm> getCategoriesFromLocal() {
        return Realm.getDefaultInstance().where(CategoryRealm.class).findAll();
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
    public void onItemClick (Category item) {
        Intent intent = new Intent(this,CardAddActivity.class);
        intent.putExtra(Category.class.getSimpleName(), item);
        setResult(RESULT_OK, intent);
        finish();
    }
    private  List<Category> getCategoriesFromRemote() throws NetworkErrorException
    {
        return  DataBaseHelper.getCategories();
    }

    private  List<Category> map2Data(List<CategoryRealm> realmList)
    {
        List<Category> categories = new ArrayList<>();
        for (CategoryRealm categoryRealm:realmList)
        {
            Category category = new Category(
                    categoryRealm.getId(),
                    categoryRealm.getName());
            categories.add(category);
        }
        return categories;
    }
    private List<CategoryRealm> map2RealmList(List<Category> categories)
    {
        List<CategoryRealm> realmList = new ArrayList<>();
        for(Category category: categories)
        {
            CategoryRealm categoryRealm = new CategoryRealm();
            categoryRealm.setId(category.getId());
            categoryRealm.setName(category.getName());
            realmList.add(categoryRealm);
        }
        return realmList;
    }
}
