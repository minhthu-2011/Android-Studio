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

public class DetailAdapter<view> extends BaseAdapter {
        Activity activity;
        List<Food> dataList;

        public DetailAdapter(Activity activity, List<Food> dataList) {
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
            view = inflater.inflate(R.layout.activity_detail, null);

            TextView ingredient = view.findViewById(R.id.txtNguyenlieu);
            TextView congthuc = view.findViewById(R.id.txtCongthuc);

            Food food = dataList.get(i);
            ingredient.setText(food.getNguyenlieu());
            congthuc.setText(food.getCongthuc());

            return view;
        }




    }


