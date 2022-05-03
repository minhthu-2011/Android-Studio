package com.example.recipeapp_.enity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.recipeapp_.db.DBHelper;

public class FoodModify {
    public static final String QUERY_CREATE_TABLE = "creat table food (\n" +
            "\t-id interger primary key autoincrement,\n" +
            "\ttitle varchar(50),\n" +
            "\ttimecook float(10),\n" +
            "\tnguyenlieu text,\n" +
            "\tcongthuc text\n" +
            ")";
    public static Cursor findAll() {
        String sql = "select * from food";
        SQLiteDatabase sqLiteDatabase = DBHelper.getInstance(null).getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        return cursor;
    }
}
