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
 * Last Updated 12.6.17
 */

public class VegUser extends Activity {
    int defaultAmount = 999;
    Button finishedButton;
    DatabaseHelper myDb;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from new_activity.xml
        setContentView(R.layout.veg_user);
    }

    //Whatever is checked goes in database
    public void onCheckboxClicked(View view) {
        myDb = new DatabaseHelper(this);
        boolean checked = ((CheckBox) view).isChecked(); // Is the view now checked?

        switch (view.getId()) {
            case R.id.CB0:
                if (checked) {
                    myDb.insertData("bread", defaultAmount);
                    Toast.makeText(VegUser.this,"Bread Inserted",Toast.LENGTH_LONG).show();
                    break;
                }
            case R.id.CB1:
                if (checked) {
                    myDb.insertData("pasta", defaultAmount);
                    Toast.makeText(VegUser.this,"Pasta Inserted",Toast.LENGTH_LONG).show();
                    break;
                }
            case R.id.CB2:
                if (checked) {
                    myDb.insertData("rice", defaultAmount);
                    Toast.makeText(VegUser.this,"Rice Inserted",Toast.LENGTH_LONG).show();
                    break;
                }
            case R.id.CB3:
                if (checked) {
                    myDb.insertData("eggs", defaultAmount);
                    Toast.makeText(VegUser.this,"Eggs Inserted",Toast.LENGTH_LONG).show();
                    break;
                }
            case R.id.CB4:
                if (checked) {
                    myDb.insertData("yogurt", defaultAmount);
                    Toast.makeText(VegUser.this,"Yogurt Inserted",Toast.LENGTH_LONG).show();
                    break;
                }
            case R.id.CB5:
                if (checked) {
                    myDb.insertData("broccoli", defaultAmount);
                    Toast.makeText(VegUser.this,"Broccoli Inserted",Toast.LENGTH_LONG).show();
                    break;
                }
            case R.id.CB6:
                if (checked) {
                    myDb.insertData("olive Oil", defaultAmount);
                    Toast.makeText(VegUser.this,"Olive Oil Inserted",Toast.LENGTH_LONG).show();
                    break;
                }
            case R.id.CB7:
                if (checked) {
                    myDb.insertData("canola Oil", defaultAmount);
                    Toast.makeText(VegUser.this,"Canola Oil Inserted",Toast.LENGTH_LONG).show();
                    break;
                }
            case R.id.CB8:
                if (checked) {
                    myDb.insertData("lettuce", defaultAmount);
                    Toast.makeText(VegUser.this,"Lettuce Inserted",Toast.LENGTH_LONG).show();
                    break;
                }
            case R.id.CB9:
                if (checked) {
                    myDb.insertData("carrots", defaultAmount);
                    Toast.makeText(VegUser.this,"Carrots Inserted",Toast.LENGTH_LONG).show();
                    break;
                }
        }

        finishedButton = findViewById(R.id.finishButton);
        finishedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //upon click, pantry will be filled and list of items will be shown
                Intent goToPantry = new Intent(VegUser.this, ListPantry.class);
                startActivity(goToPantry); //needs to go to pantry so preference activity refreshes
            }
        });
    }
}