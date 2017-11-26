package com.example.jordan.icook;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class PreferencesActivity extends AppCompatActivity {

    private GestureDetectorCompat gestureObject; //for Gestre class
    DatabaseHelper myDb;
    int defaultAmount = 999;
    static int click = 0; //once you click, you can't click multiple times
                          // which adds a bunch to the pantry


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        myDb = new DatabaseHelper(this);

        //For Gestures
        gestureObject = new GestureDetectorCompat(this, new LearnGesture());
        //Class File

        Button normalButton = findViewById(R.id.button);
        normalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            //upon click, pantry will be filled and list of items will be shown
            public void onClick(View view) {
                if(click == 0) {
                    //Default for Anybody
                    myDb.insertData("Extra-Virgin Olive Oil", defaultAmount);
                    myDb.insertData("Garlic", defaultAmount);
                    myDb.insertData("Beef", defaultAmount);
                    myDb.insertData("Eggs", defaultAmount);
                    myDb.insertData("Sea Salt", defaultAmount);
                    myDb.insertData("Oatmeal", defaultAmount);
                    myDb.insertData("Chicken", defaultAmount);
                    myDb.insertData("Cilantro", defaultAmount);
                    myDb.insertData("Basil", defaultAmount);
                    myDb.insertData("Rosemary", defaultAmount);

                    Intent normalwindow = new Intent(PreferencesActivity.this, NormalUser.class);
                    startActivity(normalwindow);
                    click++;
                }
            }
        });
        Button veganButton = findViewById(R.id.button2);
        veganButton.setOnClickListener(new View.OnClickListener() {
            @Override
            //upon click, pantry will be filled and list of items will be shown
            public void onClick(View view) {
                if(click == 0) {
                    //Default for Vegans
                    myDb.insertData("Pasta", defaultAmount);
                    myDb.insertData("Rice", defaultAmount);
                    myDb.insertData("Oats", defaultAmount);
                    myDb.insertData("Cornmeal", defaultAmount);
                    myDb.insertData("Peanuts", defaultAmount);
                    myDb.insertData("Cashews", defaultAmount);
                    myDb.insertData("Legumes", defaultAmount);
                    myDb.insertData("Chick Peas", defaultAmount);
                    myDb.insertData("Almonds", defaultAmount);
                    myDb.insertData("Millet", defaultAmount);

                    Intent veganwindow = new Intent(PreferencesActivity.this, VeganUser.class);
                    startActivity(veganwindow);
                    click++;
                }
            }
        });
        Button vegButton = findViewById(R.id.button3);
        vegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            //upon click, pantry will be filled and list of items will be shown
            public void onClick(View view) {
                if(click == 0) {
                    //default for Vegitarians
                    myDb.insertData("Bread", defaultAmount);
                    myDb.insertData("Pasta", defaultAmount);
                    myDb.insertData("Rice", defaultAmount);
                    myDb.insertData("Eggs", defaultAmount);
                    myDb.insertData("Greek Yogurt", defaultAmount);
                    myDb.insertData("Broccoli", defaultAmount);
                    myDb.insertData("Olive Oil", defaultAmount);
                    myDb.insertData("Canola Oil", defaultAmount);
                    myDb.insertData("Lettuce", defaultAmount);
                    myDb.insertData("Carrots", defaultAmount);

                    Intent vegwindow = new Intent(PreferencesActivity.this, VegUser.class);
                    startActivity(vegwindow);
                    click++;
                }
            }
        });

        Button pantryInfo = findViewById(R.id.infoPantryLoadout);
        pantryInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder infoDialog = new AlertDialog.Builder(PreferencesActivity.this);
                infoDialog.setMessage("A default pantry loadout is to help create a pantry.\n\nPlease choose one, " +
                        "the pre-made loadout wil be added to your pantry. \n\nNote: This is great for first time users.");
                infoDialog.setCancelable(true);
                infoDialog.setPositiveButton("OK", null);
                infoDialog.setTitle("Pantry Loadouts");
                infoDialog.show();
            }
        });
    }
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
            Intent openPantry = new Intent(PreferencesActivity.this, ListPantry.class);
            finish(); //Ends current activities Actions
            startActivity(openPantry);
        }
        else
            if(event2.getX() < event1.getX()){
                //Here is the code for what you want the swipe to do for Right to Left

            }
            return true;
        }

    }

}