package com.example.jordan.icook;

/**
 * Created by edske on 10/29/2017.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Objects;

import static com.google.android.gms.common.internal.safeparcel.SafeParcelable.NULL;


/**
 * Created by Henry on 10/21/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    //DB info: name and table
    private static DatabaseHelper sInstance;
    public static final String PANTRY_DB = "D.db";
    public static final String TABLE_PANTRY = "pantry_table";

    //DB FIELDS
    public static final String ROW_ID = "_id";
    //Set fields here
    public static final String COL_1 = "ITEM";
    public static final String COL_2 = "QUANTITY";
    //set field numbers
    public static final int COL_ID = 0;
    public static final int COL_ITEM = 1;
    public static final int COL_QUANTITY =2;

    public static final String[] ALL_KEYS = new String[]{ROW_ID, COL_1, COL_2};

    public static final String DATA_CREATE_SQL = "create table " + TABLE_PANTRY
            + " (" + ROW_ID + " integer primary key autoincrement, "
           //place fields here
           // -{TYPE} is one of: text, integer, real, blob
           // "NOT NULL" means it is a required field (must be given a value).
            + COL_1 + " text not null, "
            + COL_2 + " integer not null"

            + ");";

////////////////////////////
//  PUBLIC METHODS:       //
////////////////////////////

    public DatabaseHelper(Context context) {
        super(context, PANTRY_DB, null, 1);

    }
    @Override
    protected void finalize() throws Throwable { //explicitly close db
        this.close();
        super.finalize();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATA_CREATE_SQL);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_PANTRY);
        onCreate(db);

    }

    public long insertData(String item, int quantity){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, item);
        contentValues.put(COL_2, quantity);
        // Insert it into the database
        return db.insert(TABLE_PANTRY, null, contentValues);
    }

    public int insertUpdate(String item, int quantity){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put(COL_1, item); //get values ready to insert
        initialValues.put(COL_2, quantity);

        int id = db.update(TABLE_PANTRY, initialValues, "ITEM=?", new String[] {item}); //try doing an update on values
        if (id == 0) { //if update not successful ==0 then do an insert
            db.insertWithOnConflict(TABLE_PANTRY, null, initialValues, SQLiteDatabase.CONFLICT_IGNORE);
            return 1;
        }
        else return 0;
    }

    public boolean deleteRow(long rowId){
        SQLiteDatabase db = this.getWritableDatabase();
        String where = ROW_ID + "=" + rowId;
        return db.delete(TABLE_PANTRY, where, null) != 0;
    }

    public void deleteAll(){
        Cursor c = getAllRows();
        long rowId = c.getColumnIndexOrThrow(ROW_ID);
        if (c.moveToFirst()){
            do{
                deleteRow(c.getLong((int) rowId));
            } while(c.moveToNext());
        }
        c.close();
    }

    //Returns all data in the database
    public Cursor getAllRows(){
        SQLiteDatabase db = this.getWritableDatabase();
        String where = null;
        Cursor c = db.query(true, TABLE_PANTRY, ALL_KEYS,
                            where, null, null, null, null, null);
        if (c != null){
            c.moveToFirst();
        }
        return c;
    }

    public boolean isEmpty(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor mCursor = db.rawQuery("SELECT * FROM " + TABLE_PANTRY, null);

        if (mCursor.moveToFirst())
        {
            // I AM FULL
            return false;

        } else
        {
            // I AM EMPTY
            return true;
        }
    }
    public boolean updateData(String id, String quantity)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ROW_ID, id);
        contentValues.put(COL_2, quantity);
        db.update(TABLE_PANTRY,contentValues,"_id=?", new String[] { id });
        return true;
    }

    /*public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ TABLE_PANTRY,null);
        return res;
    }*/

}