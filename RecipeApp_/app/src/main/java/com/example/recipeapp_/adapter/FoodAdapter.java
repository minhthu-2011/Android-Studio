package com.example.recipeapp_.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recipeapp_.R;
import com.example.recipeapp_.models.Food;

import java.util.List;

public class FoodAdapter<view> extends BaseAdapter {
    Activity activity;
    List<Food> dataList;

    public FoodAdapter(Activity activity, List<Food> dataList) {
    this.activity = activity;
    this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int i) {
        return dataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = activity.getLayoutInflater();
        view = inflater.inflate(R.layout.item_food, null);

        ImageView thumail = view.findViewById(R.id.img_food);
        TextView title = view.findViewById(R.id.name_food);
        TextView time = view.findViewById(R.id.time_food);


        Food food = dataList.get(i);

        thumail.setImageResource(food.getResId());
        title.setText(food.getTitle());
        time.setText(String.valueOf(food.getTimecook()));



        return view;
    }




}
