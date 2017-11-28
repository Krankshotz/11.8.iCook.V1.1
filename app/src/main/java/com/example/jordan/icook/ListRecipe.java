package com.example.jordan.icook;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorTreeAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by edske on 11/17/2017.
 */

public class ListRecipe extends AppCompatActivity {
    private static final String TAG = "ListRecipe";
    DatabaseRecipe myDb;
    EditText text1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_recipe);
        text1 = (EditText) findViewById(R.id.myRecipe);
        text1.setEnabled(false);
        myDb = new DatabaseRecipe(this);
        populateListView();
    }

    private void populateListView() {
        Cursor cur = myDb.getAllData();
        //HEADER
        String[] groupField = new String[]{DatabaseRecipe.COL_2};
        //push Header into the listHeader textField
        int[] toGroup = new int[]{R.id.listHeader};
        //Cursor ingredients
        String[] childField =new String[] {DatabaseRecipe.COL_3,DatabaseRecipe.COL_4, DatabaseRecipe.COL_5, DatabaseRecipe.COL_6,
                                        DatabaseRecipe.COL_7, DatabaseRecipe.COL_8, DatabaseRecipe.COL_9, DatabaseRecipe.COL_10,
                                        DatabaseRecipe.COL_11, DatabaseRecipe.COL_12, DatabaseRecipe.COL_13};
        //push cursor to certain textField
        int[] toChild = new int[] {R.id.ig1, R.id.qu1, R.id.ig2, R.id.qu2,
                                   R.id.ig3, R.id.qu3, R.id.ig4, R.id.qu4,
                                   R.id.ig5, R.id.qu5, R.id.instruction};
        SimpleCursorTreeAdapter setAdapter = new RecipeExpandableListAdpator(cur, this, R.layout.recipe_group, R.layout.recipe_childrenlist,
                                                                             groupField, toGroup, childField, toChild);
        ExpandableListView expandableListView = findViewById(R.id.lvExp);
        expandableListView.setAdapter(setAdapter);
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
///////////////////////////////////////////
/// Adapter that makes the list expands  //
///////////////////////////////////////////
    public class RecipeExpandableListAdpator extends SimpleCursorTreeAdapter{

        public RecipeExpandableListAdpator(Cursor cursor, Context context, int groupLayout,
                                           int childLayout, String[] groupFrom, int[] groupTo, String[] childrenFrom,
                                           int[] childrenTo) {
            super(context, cursor, groupLayout, groupFrom, groupTo,
                    childLayout, childrenFrom, childrenTo);
        }
        @Override
        protected Cursor getChildrenCursor(Cursor groupCursor) {
            int columnIndex = groupCursor.getColumnIndex(DatabaseRecipe.COL_1);
            return myDb.getAllData();
        }



    }



}
