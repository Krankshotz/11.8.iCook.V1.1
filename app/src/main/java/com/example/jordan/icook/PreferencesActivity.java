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

        normalButton = (findViewById(R.id.button));
        veganButton = (findViewById(R.id.button2));
        vegButton = (findViewById(R.id.button3));
        delAll = findViewById(R.id.deleteAll);
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

        if (myDb.isEmpty()) { //if empty then user can choose from default preferences
            normalButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { // takes you to screen that lets you choose
                    Intent normalWindow = new Intent(PreferencesActivity.this, NormalUser.class);
                    startActivity(normalWindow);
                }
            });

            veganButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { // takes you to screen that lets you choose
                    Intent veganWindow = new Intent(PreferencesActivity.this, VeganUser.class);
                    startActivity(veganWindow);
                }
            });

            vegButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { // takes you to screen that lets you choose
                    Intent vegWindow = new Intent(PreferencesActivity.this, VegUser.class);
                    startActivity(vegWindow);
                }
            });

            delAll.setOnClickListener(new View.OnClickListener() { //will do nothing
                @Override
                public void onClick(View v) {
                    Toast.makeText(PreferencesActivity.this, "Your Pantry is Empty", Toast.LENGTH_LONG).show();
                }
            });
        } 
        else {
            normalButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { 
                    Toast.makeText(PreferencesActivity.this, "This is only available on initial setup", Toast.LENGTH_LONG).show();
                }
            });

            veganButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { 
                    Toast.makeText(PreferencesActivity.this, "This is only available on initial setup", Toast.LENGTH_LONG).show();
                }
            });

            vegButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { 
                    Toast.makeText(PreferencesActivity.this, "This is only available on initial setup", Toast.LENGTH_LONG).show();
                }
            });

            delAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myDb.deleteAll();
                    Toast.makeText(PreferencesActivity.this, "Your Pantry has been deleted", Toast.LENGTH_LONG).show();
                    Intent goHome = new Intent(PreferencesActivity.this, MainActivity.class); //needs to home to refresh preference
                    startActivity(goHome);
                }
            });            
        }
    }
}