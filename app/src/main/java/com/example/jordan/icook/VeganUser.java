package com.example.jordan.icook;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

/**
 * Created by Oscar Esparza on 11.19.17
 * Last Updated on 12.6.17
 */

public class VeganUser extends Activity {
    int defaultAmount = 999;
    DatabaseHelper myDb;
    Button finishedButton;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vegan_user); // Get the view from new_activity.xml
    }

    public void onCheckboxClicked(View view) {     // Whatever is checked goes on the Pantry
        myDb = new DatabaseHelper(this);
        boolean checked = ((CheckBox) view).isChecked(); // Is the view now checked?

        switch (view.getId()) {
            case R.id.CB0:
                if (checked) {
                    myDb.insertData("pasta", defaultAmount);
                    Toast.makeText(VeganUser.this,"Pasta Inserted",Toast.LENGTH_LONG).show();
                    break;
                }
            case R.id.CB1:
                if (checked) {
                    myDb.insertData("rice", defaultAmount);
                    Toast.makeText(VeganUser.this,"Rice Inserted",Toast.LENGTH_LONG).show();
                    break;
                }
            case R.id.CB2:
                if (checked) {
                    myDb.insertData("oatmeal", defaultAmount);
                    Toast.makeText(VeganUser.this,"Oatmeal Inserted",Toast.LENGTH_LONG).show();
                    break;
                }
            case R.id.CB3:
                if (checked) {
                    myDb.insertData("cornmeal", defaultAmount);
                    Toast.makeText(VeganUser.this,"Cornmeal Inserted",Toast.LENGTH_LONG).show();
                    break;
                }
            case R.id.CB4:
                if (checked) {
                    myDb.insertData("peanuts", defaultAmount);
                    Toast.makeText(VeganUser.this,"Peanuts Inserted",Toast.LENGTH_LONG).show();
                    break;
                }
            case R.id.CB5:
                if (checked) {
                    myDb.insertData("cashews", defaultAmount);
                    Toast.makeText(VeganUser.this,"Cashews Inserted",Toast.LENGTH_LONG).show();
                    break;
                }
            case R.id.CB6:
                if (checked) {
                    myDb.insertData("legumes", defaultAmount);
                    Toast.makeText(VeganUser.this,"Legumes Inserted",Toast.LENGTH_LONG).show();
                    break;
                }
            case R.id.CB7:
                if (checked) {
                    myDb.insertData("peas", defaultAmount);
                    Toast.makeText(VeganUser.this,"Peas Inserted",Toast.LENGTH_LONG).show();
                    break;
                }
            case R.id.CB8:
                if (checked) {
                    myDb.insertData("almonds", defaultAmount);
                    Toast.makeText(VeganUser.this,"Almonds Inserted",Toast.LENGTH_LONG).show();
                    break;
                }
            case R.id.CB9:
                if (checked) {
                    myDb.insertData("millet", defaultAmount);
                    Toast.makeText(VeganUser.this,"Millet Inserted",Toast.LENGTH_LONG).show();
                    break;
                }
        }

        finishedButton = findViewById(R.id.finishButton);
        finishedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {  //upon click, pantry will be filled and list of items will be shown
                Intent goToPantry = new Intent(VeganUser.this, ListPantry.class);
                startActivity(goToPantry); //needs to go to pantry so preference activity refreshes
            }
        });
    }
}