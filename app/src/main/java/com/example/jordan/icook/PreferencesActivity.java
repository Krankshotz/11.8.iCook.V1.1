package com.example.jordan.icook;

//Updated on 12.5.17
import android.app.AlertDialog;
import android.content.Intent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PreferencesActivity extends AppCompatActivity {

    //private GestureDetectorCompat gestureObject; //for Gestre class
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        myDb = new DatabaseHelper(this);
        Button normalButton;
        Button veganButton;
        Button vegButton;
        Button delAll;
        Button pantryInfo;
        if(myDb.isEmpty()) {
            normalButton = (findViewById(R.id.button));
            normalButton.setOnClickListener(new View.OnClickListener() {
                @Override
                //upon click, pantry will be filled and list of items will be shown
                public void onClick(View view) {
                    Intent normalWindow = new Intent(PreferencesActivity.this, NormalUser.class);
                    startActivity(normalWindow);
                }
            });

            veganButton = (findViewById(R.id.button2));
            veganButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent veganWindow = new Intent(PreferencesActivity.this, VeganUser.class);
                    startActivity(veganWindow);
                }
            });

            vegButton = (findViewById(R.id.button3));
            vegButton.setOnClickListener(new View.OnClickListener() {
                @Override
                //upon click, pantry will be filled and list of items will be shown
                public void onClick(View view) {
                    Intent vegWindow = new Intent(PreferencesActivity.this, VegUser.class);
                    startActivity(vegWindow);
                }
            });

            delAll = findViewById(R.id.deleteAll);
            delAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(PreferencesActivity.this,"Your Pantry is Empty",Toast.LENGTH_LONG).show();
                }
            });
            pantryInfo = findViewById(R.id.infoPantryLoadout);
            pantryInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder infoDialog = new AlertDialog.Builder(PreferencesActivity.this);
                    infoDialog.setMessage("A default pantry loadout is to help create a pantry.\n\nPlease choose one, " +
                            "the pre-made loadout wil be added to your pantry. \n\nNote: This is great for first time users.");
                    infoDialog.setCancelable(true);
                    infoDialog.setPositiveButton("OK", null);
                    infoDialog.setTitle("Defaults");
                    infoDialog.show();
                }
            });
        }
        else {
            normalButton = (findViewById(R.id.button));
            normalButton.setOnClickListener(new View.OnClickListener() {
                @Override
                //upon click, pantry will be filled and list of items will be shown
                public void onClick(View view) {
                    Toast.makeText(PreferencesActivity.this,"This is only available on initial setup",Toast.LENGTH_LONG).show();

                }
            });

            veganButton = (findViewById(R.id.button2));
            veganButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(PreferencesActivity.this,"This is only available on initial setup",Toast.LENGTH_LONG).show();

                }
            });

            vegButton = (findViewById(R.id.button3));
            vegButton.setOnClickListener(new View.OnClickListener() {
                @Override
                //upon click, pantry will be filled and list of items will be shown
                public void onClick(View view) {
                    Toast.makeText(PreferencesActivity.this,"This is only available on initial setup",Toast.LENGTH_LONG).show();

                }
            });

            delAll = findViewById(R.id.deleteAll);
            delAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myDb.deleteAll();
                    Toast.makeText(PreferencesActivity.this,"Your Pantry has been deleted",Toast.LENGTH_LONG).show();
                    Intent goHome = new Intent(PreferencesActivity.this, MainActivity.class);
                    startActivity(goHome);
                }
            });
            pantryInfo = findViewById(R.id.infoPantryLoadout);
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
    }
//FOR GESTURES, So Swipes Open New Activites. Shortcuts :)
    /*@Override
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
            Intent openPantry = new Intent(PreferencesActivity.this, MainActivity.class);
            finish(); //Ends current activities Actions
            startActivity(openPantry);
        }
        else
            if(event2.getX() < event1.getX()){
                //Here is the code for what you want the swipe to do for Right to Left
                Intent openPantry = new Intent(PreferencesActivity.this, ListPantry.class);
                finish(); //Ends current activities Actions
                startActivity(openPantry);
            }
            return true;
        }

    }*/

}