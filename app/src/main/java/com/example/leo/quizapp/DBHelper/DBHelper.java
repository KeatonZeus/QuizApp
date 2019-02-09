package com.example.leo.quizapp.DBHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.leo.quizapp.Model.Category;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteAssetHelper{

    private static final String DB_NAME = "EDMTQuiz2019.db";
    private static final int DB_VER = 1;

    private static DBHelper instance;

    public static synchronized DBHelper getInstance(Context context){
        if (instance == null){
            instance = new DBHelper(context);
        }
        return instance;
    }

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }


    //Get  All Category from DB
    public List<Category> getAllCategory(){
        SQLiteDatabase db = instance.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM Category",null);
        List<Category> categories = new ArrayList<>();

        if(cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                //讀欄
                Category category = new Category(cursor.getInt(cursor.getColumnIndex("ID")),
                                                    cursor.getString(cursor.getColumnIndex("NAME")),
                                                    cursor.getString(cursor.getColumnIndex("IMAGE")));
                //加入List
                categories.add(category);
                cursor.moveToNext();
            }
        }
        cursor.close();
        db.close();

        return categories;
    }
}
