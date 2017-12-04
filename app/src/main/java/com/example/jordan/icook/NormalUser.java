package com.example.jordan.icook;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

/**
 * Created by Oscar Esparza on 11/19/2017.\
 * Last Updated on 12.3.17
 */

public class NormalUser extends Activity {
    int defaultAmount = 999;
    DatabaseHelper myDb;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from new_activity.xml
        setContentView(R.layout.normal_user);
    }
    // Whatever is checked goes to pantry
    public void onCheckboxClicked(View view) {
        myDb = new DatabaseHelper(this);
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();
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
                    Toast.makeText(NormalUser.this,"Basil",Toast.LENGTH_LONG).show();
                    break;
            }
            case R.id.CB9:
                if (checked) {
                    myDb.insertData("rosemary", defaultAmount);
                    Toast.makeText(NormalUser.this,"Rosemary Inserted",Toast.LENGTH_LONG).show();
                    break;
            }


        }


    }

}
