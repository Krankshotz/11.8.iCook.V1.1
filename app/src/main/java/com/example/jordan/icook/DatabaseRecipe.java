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
    public static final String DATABASE_NAME= "new.db";
    public static final String TABLE_NAME = "recipe_table";
    public static final String COL_1 = "_id";
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

    public static final String[] All_Keys = new String[] {COL_1, COL_2, COL_3, COL_4,
                                                          COL_5, COL_6, COL_7, COL_8,
                                                          COL_9, COL_10, COL_11, COL_12,
                                                          COL_13};

    public DatabaseRecipe(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, INGREDIENT1 TEXT, QUANTITY1 TEXT, " +
                "INGREDIENT2 TEXT, QUANTITY2 TEXT," +
                "INGREDIENT3 TEXT, QUANTITY3 TEXT," +
                "INGREDIENT4 TEXT, QUANTITY4 TEXT," +
                "INGREDIENT5 TEXT, QUANTITY5 TEXT," +
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

    public boolean deleteRow(long rowId){
        SQLiteDatabase db = this.getWritableDatabase();
        String where = COL_1 + "=" + rowId;
        return db.delete(TABLE_NAME, where, null) != 1;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String where = null;
        Cursor res = db.query(true, TABLE_NAME, All_Keys, where,
                              null, null, null ,null, null);
        if(res != null){
            res.moveToFirst();
        }

        return res;
    }
    public Cursor fetchRow(String rowId) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.query(TABLE_NAME, All_Keys,"_id=?", new String[] {rowId}, null,null,null);
    }

}
