package com.example.jordan.icook;

/**
 * Created by edske on 10/29/2017.
 */

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

/**
 * Created by Henry on 10/21/2017.
 */

public class ListPantry extends AppCompatActivity {
    private static final String TAG = "ListPantry";
    DatabaseHelper myDb;
    EditText text1;
    EditText text2;
    FloatingActionButton btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_pantry);
        text1 = (EditText) findViewById(R.id.food_Header);
        text2 = (EditText) findViewById(R.id.quantity_header);
        text1.setEnabled(false);
        text2.setEnabled(false);
        myDb = new DatabaseHelper(this);
        btnAdd = (FloatingActionButton) findViewById(R.id.btn_AddItems);
        ImageButton homebtn = (ImageButton)(findViewById(R.id.homeButton));
        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainwindow = new Intent(ListPantry.this, MainActivity.class);
                startActivity(mainwindow);
            }
        });

/////////////////////
/// TEST CASE////////
/////////////////////
        if(MainActivity.test == 1) {
            myDb.insertData("eggs", 12);
            MainActivity.test++;
        }
//////////////////////////////////////////////////////////////////////////////////////////////////

        addItem();
        populateListViewFromDB();
        listViewItemLongClick();

    }

    private void populateListViewFromDB(){
        Cursor cursor = myDb.getAllRows();

        //Setup mapping from cursor to view fields:
        String[] fromFieldNames = new String[]
                {DatabaseHelper.COL_1, DatabaseHelper.COL_2};
        int[] toViewIDs = new int[]
                {R.id.textViewItem, R.id.textViewItemQuantity};
        // Create Adapter to column of the DB onto element in the UI
        SimpleCursorAdapter myCursorAdaptor = new SimpleCursorAdapter(
                this,     //Context
                R.layout.item_layout,    //Row Layout template
                cursor,                  //cursor
                fromFieldNames,          // DB col names
                toViewIDs                // View IDs to put data in
        );
        ListView myList = (ListView) findViewById(R.id.listViewPantry);
        myList.setAdapter(myCursorAdaptor);
    }
 //the floating add button will to a different screen that let you add items.
    public void addItem(){
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent PantryAdd = new Intent(ListPantry.this, PantryActivity.class);
                startActivity(PantryAdd);
            }
        });
    }

    private void listViewItemLongClick(){
        ListView myList = (ListView) findViewById(R.id.listViewPantry);
        myList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int arg2, long id){

                myDb.deleteRow(id);
                populateListViewFromDB();

                return false;
            }
        });
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}