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
 * Last Updated on 12.3.17
 */

public class NormalUser extends Activity {
    int defaultAmount = 999;
    DatabaseHelper myDb;
    Button finishedButton;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.normal_user); // Get the view from new_activity.xml
    }

    public void onCheckboxClicked(View view) { // Whatever is checked goes to pantry
        myDb = new DatabaseHelper(this);
        boolean checked = ((CheckBox) view).isChecked();  // Is the view now checked?

        switch(view.getId()) {
            case R.id.CB0:
                if(checked) {
                    myDb.insertData("olive oil", defaultAmount);
                    Toast.makeText(NormalUser.this,"Olive Oil Inserted",Toast.LENGTH_LONG).show();
                    break;
                }
            case R.id.CB1:
                if (checked) {
                    myDb.insertData("garlic", defaultAmount);
                    Toast.makeText(NormalUser.this,"Garlic Inserted",Toast.LENGTH_LONG).show();
                    break;
                }
            case R.id.CB2:
                if(checked) {
                    myDb.insertData("beef", defaultAmount);
                    Toast.makeText(NormalUser.this,"Beef Inserted",Toast.LENGTH_LONG).show();
                    break;
                }
            case R.id.CB3:
                if (checked){
                    myDb.insertData("eggs", defaultAmount);
                    Toast.makeText(NormalUser.this,"Eggs Inserted",Toast.LENGTH_LONG).show();
                    break;
            }
            case R.id.CB4:
                if (checked){
                    myDb.insertData("salt", defaultAmount);
                    Toast.makeText(NormalUser.this,"Salt Inserted",Toast.LENGTH_LONG).show();
                    break;
            }
            case R.id.CB5:
                if (checked) {
                    myDb.insertData("oatmeal", defaultAmount);
                    Toast.makeText(NormalUser.this,"Oatmeal Inserted",Toast.LENGTH_LONG).show();
                    break;
            }
            case R.id.CB6:
                if (checked) {
                    myDb.insertData("chicken", defaultAmount);
                    Toast.makeText(NormalUser.this,"Chicken Inserted",Toast.LENGTH_LONG).show();
                    break;
            }
            case R.id.CB7:
                if (checked) {
                    myDb.insertData("cilantro", defaultAmount);
                    Toast.makeText(NormalUser.this,"Cilantro Inserted",Toast.LENGTH_LONG).show();
                    break;
            }
            case R.id.CB8:
                if (checked) {
                    myDb.insertData("basil", defaultAmount);
                    Toast.makeText(NormalUser.this,"Basil Inserted",Toast.LENGTH_LONG).show();
                    break;
            }
            case R.id.CB9:
                if (checked) {
                    myDb.insertData("rosemary", defaultAmount);
                    Toast.makeText(NormalUser.this,"Rosemary Inserted",Toast.LENGTH_LONG).show();
                    break;
            }
        }

        finishedButton = findViewById(R.id.finishButton);
        finishedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //upon click, pantry will be filled and list of items will be shown
                Intent goToPantry = new Intent(NormalUser.this, ListPantry.class);
                startActivity(goToPantry); //needs to go to pantry so preference activity refreshes
            }
        });
    }
}
