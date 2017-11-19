package com.example.jordan.icook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by edske on 11/16/2017.
 */

public class DatabaseRecipe extends SQLiteOpenHelper {
    public static final String DATABASE_NAME= "Recipe.db";
    public static final String TABLE_NAME = "recipe_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "INGREDIENT1";
    public static final String COL_4 = "QUANTITY1";
    public static final String COL_5 = "INGREDIENT2";
    public static final String COL_6 = "QUANTITY2";
    public static final String COL_7 = "INGREDIENT3";
    public static final String COL_8 = "QUANTITY3";
    public static final String COL_9 = "INGREDIENT4";
    public static final String COL_10 = "QUANTITY4";
    public static final String COL_11 = "INGREDIENT5";
    public static final String COL_12 = "QUANTITY5";
    public static final String COL_13 = "INSTRUCTION";

    public DatabaseRecipe(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, INGREDIENT1 TEXT, QUANTITY1 INTEGER, " +
                "INGREDIENT2 TEXT, QUANTITY2 INTEGER," +
                "INGREDIENT3 TEXT, QUANTITY3 INTEGER," +
                "INGREDIENT4 TEXT, QUANTITY4 INTEGER," +
                "INGREDIENT5 TEXT, QUANTITY5 INTEGER," +
                "INSTRUCTION TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    // Inserts data into specified columns
    public boolean insertData(String name, String ingredient1, String quantity1,
                              String ingredient2, String quantity2,
                              String ingredient3, String quantity3,
                              String ingredient4, String quantity4,
                              String ingredient5, String quantity5,
                              String instruction){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, ingredient1);
        contentValues.put(COL_4, quantity1);
        contentValues.put(COL_5, ingredient2);
        contentValues.put(COL_6, quantity2);
        contentValues.put(COL_7, ingredient3);
        contentValues.put(COL_8, quantity3);
        contentValues.put(COL_9, ingredient4);
        contentValues.put(COL_10, quantity4);
        contentValues.put(COL_11, ingredient5);
        contentValues.put(COL_12, quantity5);
        contentValues.put(COL_13, instruction);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    // Deletes recipes based on the NAME of the recipe
    public Integer deleteData (String name){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "NAME = ?", new String[] {name});
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME, null);
        return res;
    }
}
