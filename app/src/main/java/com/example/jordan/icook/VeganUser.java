package com.example.jordan.icook;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.CheckBox;

/**
 * Created by Oscar Esparza on 11/19/2017.
 * Last Updated on 11/24/17
 */

public class VeganUser extends Activity {
    int defaultAmount = 999;
    DatabaseHelper myDb;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from new_activity.xml
        setContentView(R.layout.vegan_user);
    }
    // Whatever is checked goes on the Pantry
    public void onCheckboxClicked(View view) {
        myDb = new DatabaseHelper(this);
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.CB0:
                if (checked) {
                    myDb.insertData("Pasta", defaultAmount);
                    break;
                }
            case R.id.CB1:
                if (checked) {
                    myDb.insertData("Rices", defaultAmount);
                    break;
                }
            case R.id.CB2:
                if (checked) {
                    myDb.insertData("Oats", defaultAmount);
                    break;
                }
            case R.id.CB3:
                if (checked) {
                    myDb.insertData("Cornmeal", defaultAmount);
                    break;
                }
            case R.id.CB4:
                if (checked) {
                    myDb.insertData("Peanuts", defaultAmount);
                    break;
                }

            case R.id.CB5:
                if (checked) {
                    myDb.insertData("Cashews", defaultAmount);
                    break;
                }
            case R.id.CB6:
                if (checked) {
                    myDb.insertData("Legumes", defaultAmount);
                    break;
                }
            case R.id.CB7:
                if (checked) {
                    myDb.insertData("Peas", defaultAmount);
                    break;
                }
            case R.id.CB8:
                if (checked) {
                    myDb.insertData("Almonds", defaultAmount);
                    break;
                }
            case R.id.CB9:
                if (checked) {
                    myDb.insertData("Millet", defaultAmount);
                    break;
                }
        }
    }
}
