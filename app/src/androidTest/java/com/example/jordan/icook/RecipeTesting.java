package com.example.jordan.icook;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import static com.example.jordan.icook.DatabaseRecipe.*;

/**
 * Created by Veeg on 12/4/2017.
 */

public class RecipeTesting extends AndroidTestCase
{
    // Test to drop recipe database if it exists
    // (removes old database so we can test the creation of a new database)
    public void testDropRecipeDB()
    {
        assertTrue(mContext.deleteDatabase(DATABASE_NAME));
        Utilities.Log("testDropRecipeDB Pass");
    }

    // Test if recipe database is created and exists
    public void testCreateRecipeDB()
    {
        DatabaseRecipe dbTester = new DatabaseRecipe(mContext);
        SQLiteDatabase dBase = dbTester.getWritableDatabase();
        assertTrue(dBase.isOpen());
        dBase.close();
        Utilities.Log("testCreateRecipeDB Pass");
    }

}
