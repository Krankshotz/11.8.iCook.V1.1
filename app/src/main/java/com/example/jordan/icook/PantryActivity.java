package com.example.jordan.icook;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class PantryActivity extends AppCompatActivity {
    private static final String TAG = "PantryMain";
    DatabaseHelper myDb;
    EditText editItem, editQuantity;
    Button btnAddData;
    Button btnViewAll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantry);
        myDb = new DatabaseHelper(this);
        editItem = (EditText) findViewById(R.id.editText_Item);
        editQuantity = (EditText) findViewById(R.id.editText_Quantity);
        btnAddData = (Button) findViewById(R.id.btn_Add);
        btnViewAll = (Button) findViewById(R.id.btn_View);
        addData();
        viewAll();
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }
    public void addData(){
        btnAddData.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        if((!TextUtils.isEmpty(editItem.getText().toString())) && (!TextUtils.isEmpty(editQuantity.getText().toString()))){
                            myDb.insertData(editItem.getText().toString(),
                                    Integer.parseInt(editQuantity.getText().toString()));
                            editItem.setText("");
                            editQuantity.setText("");
                            Toast.makeText(PantryActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();

                        }else{
                            Toast.makeText(PantryActivity.this,"Data Not Inserted",Toast.LENGTH_LONG).show();
                        }

                    }
                }
        );
    }

    public void viewAll(){
        btnViewAll.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Intent PantryList = new Intent(PantryActivity.this, ListPantry.class);
                        startActivity(PantryList);
                    }
                        /*Cursor res = myDb.getAllData();
                        if(res.getCount() == 0) {
                            // show message
                            showMessage("Error","Nothing found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()){
                            buffer.append("Item :" + res.getString(0) + "\n");
                            buffer.append("Quantity :" + res.getString(1) + "\n\n");
                        }
                        // Show all data
                        showMessage("Data",buffer.toString());
                    }*/
                }
        );
    }
    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
