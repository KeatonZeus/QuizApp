package com.example.leo.quizapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.leo.quizapp.Model.Category;
import com.example.leo.quizapp.R;
import java.util.List;

//實作RecyclerView.Adapter抽象類別
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder>{

    Context context;
    List<Category> categories;

    //constructor
    public CategoryAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    //當現有的ViewHolder不夠用時，要求Adapter產生一個新的
    //新增
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.layout_category_item,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override//重用之前產生的ViewHolder，把特定位置的資料連結上去，準備顯示
    //顯示
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.category_name.setText(categories.get(position).getName());
    }

    @Override//總數
    public int getItemCount() {
        return categories.size();
    }

    //Adapter 資料來源配置
    //重複用ViewHolder
    public class MyViewHolder extends RecyclerView.ViewHolder {

        CardView card_category;
        TextView category_name;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);

        card_category = (CardView)itemView.findViewById(R.id.card_category);
        category_name = (TextView)itemView.findViewById(R.id.category_name);

        card_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Click on"+categories.get(getAdapterPosition()).getName(),Toast.LENGTH_LONG).show();
            }
        });
        }
    }
}
