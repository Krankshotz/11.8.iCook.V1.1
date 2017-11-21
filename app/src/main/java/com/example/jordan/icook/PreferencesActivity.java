package com.example.jordan.icook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PreferencesActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    int defaultAmount = 999;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        myDb = new DatabaseHelper(this);

        Button normalButton = (Button)(findViewById(R.id.button));
        normalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            //upon click, pantry will be filled and list of items will be shown
            public void onClick(View view) {
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

                //Intent normalwindow = new Intent(PreferencesActivity.this, NormalUser.class);
                //startActivity(normalwindow);
            }
        });
        Button veganButton = (Button)(findViewById(R.id.button2));
        veganButton.setOnClickListener(new View.OnClickListener() {
            @Override
            //upon click, pantry will be filled and list of items will be shown
            public void onClick(View view) {
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

                //Intent veganwindow = new Intent(PreferencesActivity.this,  VeganUser.class);
                //startActivity(veganwindow);
            }
        });
        Button vegButton = (Button)(findViewById(R.id.button3));
        vegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            //upon click, pantry will be filled and list of items will be shown
            public void onClick(View view) {
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

                //Intent vegwindow = new Intent(PreferencesActivity.this,  VegUser.class);
                //startActivity(vegwindow);
            }
        });
    }
}