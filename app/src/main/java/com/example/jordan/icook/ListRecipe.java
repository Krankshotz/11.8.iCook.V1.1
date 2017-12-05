package com.example.jordan.icook;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.SimpleCursorTreeAdapter;

/**
 * Created by edske on 11/17/2017.
 */

public class ListRecipe extends AppCompatActivity {
    private static final String TAG = "ListRecipe";
    private Cursor cur;
    DatabaseHelper pantryDb;
    DatabaseRecipe myDb;
    FloatingActionButton btnAddRecipe;

    EditText text1;
    FloatingActionButton btnAdd;
    int[] RecipeChecks = new int[400]; //create of arry up to 399 if array1 is == 5 show arrray Recipe
    int count = 0; //for Recipe Checks
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_recipe);
        text1 = findViewById(R.id.myRecipe);
        text1.setEnabled(false);
        btnAdd = findViewById(R.id.btn_AddRecipe);
        myDb = new DatabaseRecipe(this);
        pantryDb = new DatabaseHelper(this);
        btnAddRecipe = findViewById(R.id.btn_AddItemsRL);
        //Creates Listener to Open new Activity, this is the top right button for home PA = pantry activity
        ImageButton ButtonhomeRL = findViewById(R.id.homeButtonRL);
        ButtonhomeRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ButtonhomeRL = new Intent(ListRecipe.this, MainActivity.class);
                startActivity(ButtonhomeRL);
            }
        });
        //Creates Listener to Open new Activity, this is the top left button for recipe
        ImageButton ButtonPantryRL = findViewById(R.id.recipeButtonRL);
        ButtonPantryRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ButtonPantryRL = new Intent(ListRecipe.this, ListPantry.class);
                startActivity(ButtonPantryRL);
            }
        });
        addRecipe(); //Floating Button to Add another recupe, brings up new activity
        compareRecipesToPantry();
        populateListView();
        addItem();
        ListViewItemClick();
    }

    //the floating add button will to a different screen that let you add items.
    public void addRecipe(){
        btnAddRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent PantryAdd = new Intent(ListRecipe.this, RecipeActivity.class);
                startActivity(PantryAdd);
            }
        });
    }

    public void compareRecipesToPantry(){
        Cursor pantryC = pantryDb.getAllRows();  //Cursor creates a copy of the DTB we c;an iterate through
        Cursor recipeC = myDb.getAllData();
        count = 0; //reset count every time;
        for(int reset = 0; reset < 399; reset++)  //Loops through entire array
            RecipeChecks[reset] = 0;              //Resets all checked values to 0, as recipe ingredients may have changed.
        while(recipeC.moveToNext()){              //Goes until end of Recipes
            for (int x = 2; x < 11; x = x + 2) {  //INCREMENTS BY 2 BECAUSE IT IS CHECKING THE THE INGREDIENTS WHICH ARE EVERY OTHER COL
                if (pantryC.moveToFirst())
                    do {
                        if (recipeC.getString(x).equals(pantryC.getString(1)) || recipeC.getString(x).isEmpty()) {
                            RecipeChecks[count]++;                  //incrememnts means it found the ingredient
                            break;
                        }
                    } while (pantryC.moveToNext());
            }
            count++;  //increment to next recipe window
        }
    }

    private void populateListView(){
        Cursor cursor = myDb.getAllData();

        //Setup mapping from cursor to view fields:
        String[] fromFieldNames = new String[]
                {DatabaseRecipe.COL_2};
        int[] toViewIDs = new int[]
                {R.id.listHeader};
        // Create Adapter to column of the DB onto element in the UI
        SimpleCursorAdapter myCursorAdaptor = new SimpleCursorAdapter(
                this,     //Context
                R.layout.recipe_group,    //Row Layout template
                cursor,                  //cursor
                fromFieldNames,          // DB col names
                toViewIDs                // View IDs to put data in
        );
        ListView myList = findViewById(R.id.ListViewRecipe);
        myList.setAdapter(myCursorAdaptor);
        //ListViewItemClick();
        /*public void ListViewItemClick(){
            ListView myList =findViewById(R.id.ListViewRecipe);
            myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    detail(i);

                }
            });
        }*/

    }

    /*private void populateListView() {
        Cursor cur = myDb.getAllData();
        //HEADER
                String[] groupField = new String[]{DatabaseRecipe.COL_2};
                //push Header into the listHeader textField
                int[] toGroup = new int[]{R.id.listHeader};
                //Cursor ingredients
                String[] childField = new String[]{DatabaseRecipe.COL_3, DatabaseRecipe.COL_4, DatabaseRecipe.COL_5, DatabaseRecipe.COL_6,
                        DatabaseRecipe.COL_7, DatabaseRecipe.COL_8, DatabaseRecipe.COL_9, DatabaseRecipe.COL_10,
                        DatabaseRecipe.COL_11, DatabaseRecipe.COL_12, DatabaseRecipe.COL_13};
                //push cursor to certain textField
                int[] toChild = new int[]{R.id.ig1, R.id.qu1, R.id.ig2, R.id.qu2,
                        R.id.ig3, R.id.qu3, R.id.ig4, R.id.qu4,
                        R.id.ig5, R.id.qu5, R.id.instruction};
                SimpleCursorTreeAdapter setAdapter = new RecipeExpandableListAdpator(cur, this, R.layout.recipe_group, R.layout.recipe_childrenlist,
                        groupField, toGroup, childField, toChild);
                ExpandableListView expandableListView = findViewById(R.id.lvExp);
                expandableListView.setAdapter(setAdapter);
    }*/
   public void ListViewItemClick(){
        ListView myList =findViewById(R.id.ListViewRecipe);
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                detail(i);
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
    public void detail(int i){
        cur = myDb.getAllData();
        String id = "";
        String name = "";
        String i1 = "";
        String q1= "";
        String i2 = "";
        String q2 = "";
        String i3 = "";
        String q3 = "";
        String i4 = "";
        String q4 = "";
        String i5 = "";
        String q5 = "";
        String ins = "";
        if (cur!=null && cur.getCount()>0 && cur.moveToFirst()){
            cur.moveToPosition(i);
            id = cur.getString(0);
            name = cur.getString(1);
            i1 = cur.getString(2);
            q1 = cur.getString(3);
            i2 = cur.getString(4);
            q2 = cur.getString(5);
            i3 = cur.getString(6);
            q3 = cur.getString(7);
            i4 = cur.getString(8);
            q4 = cur.getString(9);
            i5 = cur.getString(10);
            q5 = cur.getString(11);
            ins = cur.getString(12);
        }
        Intent iIntent = new Intent(this, RecipePullUp.class);
        iIntent.putExtra("COL_1", id);
        iIntent.putExtra("COL_2", name);
        iIntent.putExtra("COL_3", i1);
        iIntent.putExtra("COL_4", q1);
        iIntent.putExtra("COL_5", i2);
        iIntent.putExtra("COL_6", q2);
        iIntent.putExtra("COL_7", i3);
        iIntent.putExtra("COL_8", q3);
        iIntent.putExtra("COL_9", i4);
        iIntent.putExtra("COL_10", q4);
        iIntent.putExtra("COL_11", i5);
        iIntent.putExtra("COL_12", q5);
        iIntent.putExtra("COL_13", ins);
        setResult(RESULT_OK, iIntent);
        startActivity(iIntent);
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
    public void addItem(){
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent RecipeAdd = new Intent(ListRecipe.this, RecipeActivity.class);
                startActivity(RecipeAdd);
            }
        });
    }

}
