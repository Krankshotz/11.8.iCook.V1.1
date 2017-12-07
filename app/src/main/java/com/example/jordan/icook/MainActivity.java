package com.example.jordan.icook;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "";
    public static int test = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //check if first run
        String pref_previously_started="";
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean previouslyStarted = prefs.getBoolean(pref_previously_started, false);
        if(!previouslyStarted) {
            requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            requestPermissions(new String[]{android.Manifest.permission.CAMERA}, 1001);
            SharedPreferences.Editor edit = prefs.edit();
            edit.putBoolean(pref_previously_started, Boolean.TRUE);
            edit.commit();
            Toast.makeText(MainActivity.this,"First-time users click on the ?",Toast.LENGTH_LONG).show();
        }

        ImageButton receiptButton1 = (ImageButton)(findViewById(R.id.receiptButton1));
        receiptButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent receiptswindow = new Intent(MainActivity.this, receipt_Scanner.class);
                startActivity(receiptswindow);
            }
        });
        //Creates Listener to Open new Activity
        ImageButton pantryButton2 = (ImageButton)(findViewById(R.id.pantryButton2));
        pantryButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pantrywindow = new Intent(MainActivity.this, ListPantry.class);
                startActivity(pantrywindow);
            }
        });
        //Creates Listener to Open new Activity
        ImageButton recipesButton3 = (ImageButton)(findViewById(R.id.recipesButton3));
        recipesButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent recipewindow = new Intent(MainActivity.this, ListRecipe.class);
                startActivity(recipewindow);

            }
        });
        //Creates Listener to Open new Activity
        ImageButton Button5help = (ImageButton)(findViewById(R.id.Button5Help));
        Button5help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent helpwindow = new Intent(MainActivity.this, HelpActivity.class);
                startActivity(helpwindow);

            }
        });

        //Creates Listener to Open new Activity
        ImageButton ButtonPref = (ImageButton)(findViewById(R.id.preferencesButton4));
        ButtonPref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent prefwindow = new Intent(MainActivity.this, PreferencesActivity.class);
                startActivity(prefwindow);
            }
        });
    }
}
