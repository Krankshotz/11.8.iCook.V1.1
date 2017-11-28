package com.example.jordan.icook;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.CheckBox;

/**
 * Created by Oscar Esparza on 11/19/2017.
 * Last Updated 11/14/17
 */

public class VegUser extends Activity {
    int defaultAmount = 999;
    DatabaseHelper myDb;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from new_activity.xml
        setContentView(R.layout.veg_user);
    }
    //Whatever is checked goes in database
    public void onCheckboxClicked(View view) {
        myDb = new DatabaseHelper(this);
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.CB0:
                if (checked) {
                    myDb.insertData("Bread", defaultAmount);
                    break;
                }
            case R.id.CB1:
                if (checked) {
                    myDb.insertData("Pasta", defaultAmount);
                    break;
                }
            case R.id.CB2:
                if (checked) {
                    myDb.insertData("Rice", defaultAmount);
                    break;
                }
            case R.id.CB3:
                if (checked) {
                    myDb.insertData("Eggs", defaultAmount);
                    break;
                }
            case R.id.CB4:
                if (checked) {
                    myDb.insertData("Yogurt", defaultAmount);
                    break;
                }
            case R.id.CB5:
                if (checked) {
                    myDb.insertData("Broccoli", defaultAmount);
                    break;
                }
            case R.id.CB6:
                if (checked) {
                    myDb.insertData("Olive Oil", defaultAmount);
                    break;
                }
            case R.id.CB7:
                if (checked) {
                    myDb.insertData("Canola Oil", defaultAmount);
                    break;
                }
            case R.id.CB8:
                if (checked) {
                    myDb.insertData("Lettuce", defaultAmount);
                    break;
                }
            case R.id.CB9:
                if (checked) {
                    myDb.insertData("Carrots", defaultAmount);
                    break;
                }
        }
    }
}