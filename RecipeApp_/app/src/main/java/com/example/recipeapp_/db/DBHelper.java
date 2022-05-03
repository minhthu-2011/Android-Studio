package com.example.recipeapp_.db;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.recipeapp_.enity.FoodModify;

public class DBHelper extends SQLiteOpenHelper {
    private static  final String DB_NAME = "db_food";
    private static final int DB_VERSION = 1;


    private static DBHelper instance = null;

    public synchronized static DBHelper getInstance(Context context) {
        if(instance == null) {
            instance = new DBHelper(context);
        }
        return instance;
    }

    private DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //ứng dụng được cài đặt lần đầu tiên sẽ gọi vào hàm này , 1 lần duy nhât
        String sql = FoodModify.QUERY_CREATE_TABLE;

        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // những lần update sau thì chỉ gọi vào đây
    }


    public DBHelper(@Nullable Context context, @Nullable String name) {
        super(context, name, null, DB_VERSION);
    }

}
