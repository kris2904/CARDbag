package com.example.cardgit;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.util.List;



public class PhotoAdapter extends RecyclerView.Adapter<PhotoVH>{

    private List<Photo> photoList;
     Context context;
    private LayoutInflater layoutInflater;



    public PhotoAdapter(Context context, List<Photo> photo)
    {
        this.context = context;
        this.photoList = photo;

        this.layoutInflater = LayoutInflater.from(context);


    }

    @NonNull
    @Override
    public PhotoVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.item_photo_card, viewGroup, false);
        return new PhotoVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoVH photoVH, int position) {
        final Photo photo = photoList.get(position);

        File imgFile = new File(
                context.getExternalFilesDir(Environment.DIRECTORY_PICTURES) + "/" +
                        photo.getIconSources() + ".jpg"
        );

        if (imgFile.exists()) {
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            photoVH.ivPhoto.setImageBitmap(myBitmap);
            return;
        }

        //photoVH.ivPhoto.setImageResource(context.getResources().getDrawable(photo.getIconSources()));


    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }
    public void setPhotosList(List<Photo> photosList) {
        this.photoList = photosList;
    }
}
