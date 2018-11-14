package com.example.cardgit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryVH> {
    private onItemClickListener clickListener;
    private LayoutInflater inflater;
    private List<Category> categories;

    public CategoryAdapter(Context context, List<Category> categories, onItemClickListener clickListener) {
        this.categories = categories;
        this.inflater = LayoutInflater.from(context);
        this.clickListener= clickListener;
    }

    @NonNull
    @Override
    public CategoryVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = inflater.inflate(R.layout.item_view_category, viewGroup, false);
        return new CategoryVH(view);
    }



    @Override
    public void onBindViewHolder(@NonNull CategoryVH categoryVH, int postion) {
        final Category chatItem = categories.get(postion);

        categoryVH.txtCategoryName.setText(chatItem.getName());
        categoryVH.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onItemClick(chatItem);
            }
        });

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public interface onItemClickListener {
        void onItemClick(Category item);
    }
}
