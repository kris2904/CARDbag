package com.example.cardgit;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.realm.Realm;
import io.realm.RealmList;

public class CardAddActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private Card card;
    private EditText edName;
    private EditText edCategory;
    private EditText edDiscount;
    private static final int REQUEST_CODE_FRONT_PHOTO = 1;
    private static final int REQUEST_CODE_BACK_PHOTO =2;

    private static final int ADD_CATEGORY = 0;
    ImageView ivPhotoFront ;
    ImageView ivPhotoBack;

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
        findViewById(R.id.flFronPhoto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImagGromGallery(REQUEST_CODE_FRONT_PHOTO);
            }
        });

        findViewById(R.id.flBackPhoto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImagGromGallery(REQUEST_CODE_BACK_PHOTO);
            }
        });

        ivPhotoFront = findViewById(R.id.ivPhotoFront);
        ivPhotoBack = findViewById(R.id.ivPhotoBack);
        long currentTime = System.currentTimeMillis();
        Photo photoFront = new Photo( currentTime+ 1);
        Photo photoBack = new Photo(currentTime + 2);
        card.photo = new ArrayList<>();
        card.photo.add(photoFront);
        card.photo.add(photoBack);        }
    private void chooseImagGromGallery (int requestCode){
        // Интент для получения всех приложений которые могут отображать изображения
        Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
        getIntent.setType("image/*");

        // Вызываем системное диалоговое окно для выбора приложения, которое умеет отображать изображения
        // и возвращать выбранную фотографию
        Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType("image/*");

        Intent chooserIntent = Intent.createChooser(getIntent, "Выберите изображение");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{pickIntent});
        // Запускаем приложения и ожидаем результат
        startActivityForResult(chooserIntent, requestCode);}

    private void showImage(int requestCode, Intent data) {
        try {
            //Получаем URI изображения, преобразуем его в Bitmap
            //объект и отображаем в элементе ImageView нашего интерфейса:
            final Uri imageUri = data.getData();
            final InputStream imageStream = getContentResolver().openInputStream(imageUri);
            final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);

            switch (requestCode) {
                case REQUEST_CODE_FRONT_PHOTO:
                    ivPhotoFront.setImageBitmap(selectedImage);
                    break;
                case REQUEST_CODE_BACK_PHOTO:
                    ivPhotoBack.setImageBitmap(selectedImage);
                    break;
            }
            ivPhotoFront.setImageBitmap(selectedImage);

        } catch (FileNotFoundException e) {
            // Эта ошибка отобразится в случае если не удалось найти изображение
            e.printStackTrace();
        }

    }

    @Nullable
    private File createImageFile() {
        // Генерируем имя файла
        String filename = System.currentTimeMillis() + ".jpg";

        // Получаем приватную директорию на карте памяти для хранения изображений
        // Выглядит она примерно так:
        // /sdcard/Android/data/info.goodline.department.learnandroid./files/Pictures
        // Директория будет создана автоматически, если ещё не существует
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        // Создаём файл
        File image = new File(storageDir, filename);
        try {
            if (image.createNewFile()) {
                return image;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onClick (View view){
        card.setName(edName.getText().toString());
        //card.setCategory(edCategory.getText().toString());
        card.setDiscount(edDiscount.getText().toString());


        ArrayList<Photo> photos = new ArrayList<>();
        photos.add(new Photo(R.drawable.lenta));
        photos.add(new Photo(R.drawable.lenta));
        card.setPhoto(photos);

        Random random = new Random();
        int id = random.nextInt(2000);
        card.setId(id);
        Intent intent = new Intent(this, CardListActivity.class);
        intent.putExtra(Card.class.getSimpleName(), card);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void etCategory (View view){
        Intent intent = new Intent(this, CategoryListActivity.class);
        startActivityForResult(intent, ADD_CATEGORY);
    }
    private void addCard (Card card){

        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(map2Realm(card));

            }
        });
    }
    private CardRealm map2Realm (Card card){
        CardRealm cardRealm = new CardRealm();
        cardRealm.setId(card.getId());
        cardRealm.setName(card.getName());
        cardRealm.setDiscount(card.getDiscount());
        cardRealm.setCategory(categoreMap2Realm(card.getCategory()));
        return cardRealm;
    }
    private CategoryRealm categoreMap2Realm (Category category){
        CategoryRealm categoryRealm = new CategoryRealm();
        categoryRealm.setId(category.getId());
        categoryRealm.setName(category.getName());
        return categoryRealm;
    }

    @Override
    protected void onActivityResult ( int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);


        switch (requestCode) {
            case ADD_CATEGORY:
                if (resultCode == RESULT_OK) {
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
                    card.setCategory(category);
                }
                break;
            case REQUEST_CODE_FRONT_PHOTO:
            case REQUEST_CODE_BACK_PHOTO:
                showImage(requestCode,data);
                break;



        }

    }
    private RealmList<PotoRealm> potoMap2Realm (List < Photo > photo) {
        RealmList<PotoRealm> potoRealms = new RealmList<>();
        for (Photo photos : photo) {
            PotoRealm photoRealm1 = new PotoRealm();
            photoRealm1.setImgID(photos.getIconSources());
            potoRealms.add(photoRealm1);
        }
        return potoRealms;
    }
}