package com.example.jordan.icook;

/**
 * Created by edske on 10/29/2017.
 */

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Henry on 10/21/2017.
 */

public class ListPantry extends AppCompatActivity {
    private static final String TAG = "ListPantry";
    DatabaseHelper myDb;
    EditText text1;
    EditText text2;
    EditText editQuantity;  //copied from pantryActivity to edit quanity of Pantry

    FloatingActionButton btnAdd;
    private GestureDetectorCompat gestureObject; //for Gestre class

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_pantry);
        text1 = findViewById(R.id.food_Header);
        text2 = findViewById(R.id.quantity_header);
        text1.setEnabled(false);
        text2.setEnabled(false);
        editQuantity = findViewById(R.id.editText_Quantity);  //copied from pantryActivity
        myDb = new DatabaseHelper(this);
        //For Gestures
        gestureObject = new GestureDetectorCompat(this, new LearnGesture());
        //End for Gestures
        btnAdd = findViewById(R.id.btn_AddItems);

/////////////////////
/// TEST CASE////////
/////////////////////
        if(MainActivity.test == 1) {
            myDb.insertData("eggs", 12);
            MainActivity.test++;
        }
//////////////////////////////////////////////////////////////////////////////////////////////////
        //pantryCompare(); //this deletes duplicates in DB pantry
        addItem();
        populateListViewFromDB();

        //TEST();
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
        ListView myList = findViewById(R.id.listViewPantry);
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

    private void listViewItemLongClick(){  //Creates feature, a long hold will delete the item
        ListView myList = findViewById(R.id.listViewPantry);
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


    //Creates Pantry Comparisons, if items have == Names, merge the quantity
    private void pantryCompare(){
        Cursor cursor = myDb.getAllRows();  //creates a placement to iterate through all pantry items
        Cursor cursor2 = myDb.getAllRows(); //for comparing the same pantry against same pantry
        while(cursor.moveToNext()){         //goes to next item until hits null
            if(cursor2.moveToFirst() == false)  //Cursor2 moves through the entire pantry for every move to next of cursor1, if null break
                break;
                while(cursor2.moveToNext()){
                   if(cursor.getInt(1) == cursor2.getInt(1))  //if same ID, we don't compare, because cursors are at the same place
                        cursor2.moveToNext();
                    if(cursor.getString(2).equals(cursor2.getString(2))){  //if item names are delete cursor2 location from DB, add quantites
                        int updateQuantity = cursor2.getInt(3) + cursor.getInt(3);  //add quantities together
                        String itemName = cursor.getString(2);   //gets item name, both DB entries are deleted and a new one is added with both quanities
                        int idLocation = cursor2.getInt(3);      //grabs id to delete it from DB
                        myDb.deleteRow(idLocation);                //this may stop the iteration of the loop because it creates a null value?
                        populateListViewFromDB();
                        idLocation = cursor.getInt(3);            //find location of cursor 1 location to update quanity to DB
                        myDb.deleteRow(idLocation);                 //delete second copy from DB
                        populateListViewFromDB();
                        //Now Insert a new value into database with updated quantity and matching item Name
                        myDb.insertData(itemName, updateQuantity);
                        Toast.makeText(ListPantry.this, "Just a Test", Toast.LENGTH_LONG).show();

                        //reset cursors because DBs items were deleted
                        cursor = myDb.getAllRows();
                        cursor2 = myDb.getAllRows();

                    }
                }
        }

    }
    //--------------End of Pantry Comparison-------------------------------

    //FOR GESTURES, So Swipes Open New Activites. Shortcuts :)
    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.gestureObject.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
    //For Gesture Object Class
    class LearnGesture extends GestureDetector.SimpleOnGestureListener{
        //Simple Gesture Listener for what we want to do, this opens the pantry
        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY){
            if(event2.getX() > event1.getX()){
                //Here is the code for what you want the swipe to do for Left to Right
                Intent openPantry = new Intent(ListPantry.this, MainActivity.class);
                finish(); //Ends current activities Actions
                startActivity(openPantry);
            }
            else
            if(event2.getX() < event1.getX()){
                //Here is the code for what you want the swipe to do for Right to Left
                Intent openPantry = new Intent(ListPantry.this, RecipeActivity.class);
                finish(); //Ends current activities Actions
                startActivity(openPantry);
            }
            return true;
        }

    }
}