package com.example.jordan.icook;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

/**
 * Created by Oscar Esparza on 11/19/2017.
 * Last Updated on 12/3/17
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
    }
}
