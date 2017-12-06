package com.example.jordan.icook;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.content.ContentValues;
import android.database.Cursor;

import com.example.jordan.icook.DatabaseHelper;

import static com.example.jordan.icook.DatabaseHelper.*;

/**
 * Created by Veeg on 12/4/2017.
 */

public class PantryTesting extends AndroidTestCase
{

    private static String pItem;
    private static int pQuant;
    private static long id;

    // Test to drop pantry database if it exists
    // (removes old database so we can test the creation of a new database)
    public void testDropDB()
    {
        assertTrue(mContext.deleteDatabase(PANTRY_DB));
        Utilities.Log("testDropDB Pass");
    }

    // Test if pantry database is created and exists
    public void testCreateDB()
    {
        DatabaseHelper dbTester = new DatabaseHelper(mContext);
        SQLiteDatabase dBase = dbTester.getWritableDatabase();
        assertTrue(dBase.isOpen());
        dBase.close();
        Utilities.Log("testCreateDB Pass");
    }


    /*
//  Test if data inserted is expected value
    public void testInsertData()
    {
        DatabaseHelper dBase = new DatabaseHelper(mContext);
        SQLiteDatabase db = dBase.getWritableDatabase();

        pItem = "Potatoes";
        pQuant = 12;

        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_1, pItem);
        contentValues.put(DatabaseHelper.COL_2, pQuant);

        id = db.insert(DatabaseHelper.PANTRY_DB, null, contentValues);
        assertTrue(id != -1);
        Utilities.Log("testInsertData Pass - ID "+ id);
    }

    public void testIsDataCorrectInPantryDB()
    {
        DatabaseHelper dBase = new DatabaseHelper(mContext);
        SQLiteDatabase db = dBase.getWritableDatabase();
        Cursor cursor = db.query(DatabaseHelper.TABLE_PANTRY,null,null,null,null,null,null);
        assertTrue(cursor.moveToFirst());

        int itemColumnIndex = cursor.getColumnIndex(DatabaseHelper.COL_1);
        String dbItem = cursor.getString(itemColumnIndex);

        int quantColumnIndex = cursor.getColumnIndex(DatabaseHelper.COL_2);
        int dbQuant = cursor.getInt(quantColumnIndex);

        assertEquals(pItem, dbItem);
        assertEquals(pQuant, dbQuant);

        Utilities.Log("testIsDataCorrectInPantryDB Pass");
    }
*/

}
