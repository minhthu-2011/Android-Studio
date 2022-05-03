package com.example.recipeapp_.adapter;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recipeapp_.R;

public class FoodCursorAdapter extends CursorAdapter {
    Activity activity;

    public FoodCursorAdapter(Activity activity, Cursor c) {
        super(activity, c);
        this.activity = activity;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.item_food, null);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ImageView thumbnailImg = view.findViewById(R.id.img_food);
        TextView titleTxt = view.findViewById(R.id.name_food);
        TextView timeTxt = view.findViewById(R.id.time_food);



        String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
        int time = cursor.getInt(cursor.getColumnIndexOrThrow("timecook"));

        titleTxt.setText(title);
        timeTxt.setText(time);

    }
}