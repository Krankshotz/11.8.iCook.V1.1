package com.example.jordan.icook;

//Updated on 11.24.17

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PreferencesActivity extends AppCompatActivity {
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        myDb = new DatabaseHelper(this);

        Button normalButton = (findViewById(R.id.button));
        normalButton.setOnClickListener(new View.OnClickListener() {
                @Override
                //upon click, pantry will be filled and list of items will be shown
                public void onClick(View view) {
                    Intent normalwindow = new Intent(PreferencesActivity.this, NormalUser.class);
                    startActivity(normalwindow);
                }
            });

        Button veganButton = (findViewById(R.id.button2));
        veganButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent veganwindow = new Intent(PreferencesActivity.this, VeganUser.class);
                startActivity(veganwindow);
            }
        });

        Button vegButton = (findViewById(R.id.button3));
        vegButton.setOnClickListener(new View.OnClickListener() {
                @Override
                //upon click, pantry will be filled and list of items will be shown
                public void onClick(View view) {
                    Intent vegwindow = new Intent(PreferencesActivity.this, VegUser.class);
                    startActivity(vegwindow);
                }
            });

    }
}