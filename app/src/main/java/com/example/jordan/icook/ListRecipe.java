package com.example.jordan.icook;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by edske on 11/17/2017.
 */

public class ListRecipe extends AppCompatActivity {
    private static final String TAG = "ListRecipe";
    DatabaseRecipe myDb;
    private ListView Name;
    private ListView Ingredient1;
    private ListView Quantity1;
    private ListView Ingredient2;
    private ListView Quantity2;
    private ListView Ingredient3;
    private ListView Quantity3;
    private ListView Ingredient4;
    private ListView Quantity4;
    private ListView Ingredient5;
    private ListView Quantity5;
    private ListView Instruction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_recipe);
        Name = (ListView) findViewById(R.id.ListView);
        Ingredient1 = (ListView) findViewById(R.id.ListView2);
        Quantity1 =(ListView)findViewById(R.id.ListView3);
        Ingredient2 = (ListView)findViewById(R.id.ListView4);
        Quantity2 = (ListView) findViewById(R.id.ListView5);
        Ingredient3 = (ListView) findViewById(R.id.ListView6);
        Quantity3 = (ListView) findViewById(R.id.ListView7);
        Ingredient4=(ListView) findViewById(R.id.ListView8);
        Quantity4 =(ListView) findViewById(R.id.ListView9);
        Ingredient5 = (ListView) findViewById(R.id.ListView10);
        Quantity5 = (ListView) findViewById(R.id.ListView11);
        Instruction = (ListView) findViewById(R.id.ListView12);
        myDb = new DatabaseRecipe(this);
        populateListView();
    }

    private void populateListView() {
        Cursor res = myDb.getAllData();
        if(res.getCount() == 0) {
            // show message
            showMessage("Error","Nothing found");
            return;
        }
        ArrayList<String> listData = new ArrayList<>();
        ArrayList<String> listData2 = new ArrayList<>();
        ArrayList<String> listData3 = new ArrayList<>();
        ArrayList<String> listData4 = new ArrayList<>();
        ArrayList<String> listData5 = new ArrayList<>();
        ArrayList<String> listData6 = new ArrayList<>();
        ArrayList<String> listData7 = new ArrayList<>();
        ArrayList<String> listData8 = new ArrayList<>();
        ArrayList<String> listData9 = new ArrayList<>();
        ArrayList<String> listData10 = new ArrayList<>();
        ArrayList<String> listData11 = new ArrayList<>();
        ArrayList<String> listData12 = new ArrayList<>();
        while (res.moveToNext()){
            listData.add(res.getString(0));
            listData2.add(res.getString(1));
            listData3.add(res.getString(2));
            listData4.add(res.getString(3));
            listData5.add(res.getString(4));
            listData6.add(res.getString(5));
            listData7.add(res.getString(6));
            listData8.add(res.getString(7));
            listData9.add(res.getString(8));
            listData10.add(res.getString(9));
            listData11.add(res.getString(10));
            listData12.add(res.getString(11));
        }
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listData);
        ListAdapter adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listData2);
        ListAdapter adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listData3);
        ListAdapter adapter4 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listData4);
        ListAdapter adapter5 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listData5);
        ListAdapter adapter6 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listData6);
        ListAdapter adapter7 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listData7);
        ListAdapter adapter8 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listData8);
        ListAdapter adapter9 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listData9);
        ListAdapter adapter10 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listData10);
        ListAdapter adapter11 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listData11);
        ListAdapter adapter12 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listData12);
        Name.setAdapter(adapter);
        Ingredient1.setAdapter(adapter2);
        Quantity1.setAdapter(adapter3);
        Ingredient2.setAdapter(adapter4);
        Quantity2.setAdapter(adapter5);
        Ingredient3.setAdapter(adapter6);
        Quantity3.setAdapter(adapter7);
        Ingredient4.setAdapter(adapter8);
        Quantity4.setAdapter(adapter9);
        Ingredient5.setAdapter(adapter10);
        Quantity5.setAdapter(adapter11);
        Instruction.setAdapter(adapter12);
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }



}
