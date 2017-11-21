package com.example.jordan.icook;

import android.database.Cursor;
import android.nfc.Tag;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RecipeActivity extends AppCompatActivity {
    int check[]; //IF it ITerates to 5, all ingredients are listed
    int x = 0;  //for array testing iterate x across the List Adapters
    int test = 0;
    int ingredientThreshold = 1;   //amount of items allowed to be missing from pantry to display recipe
    DatabaseRecipe myDb;
    DatabaseHelper pantryDb;
    EditText editName, editIngredient1, editQuantity1,
            editIngredient2, editQuantity2,
            editIngredient3, editQuantity3,
            editIngredient4, editQuantity4,
            editIngredient5, editQuantity5,
            editInstruction;
    Button btnAdd, btnView, btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        myDb = new DatabaseRecipe(this);
        pantryDb = new DatabaseHelper(this);
        editName = (EditText)findViewById(R.id.editText_Name);
        editIngredient1 = (EditText)findViewById(R.id.editText_Ingredient1);
        editQuantity1 = (EditText)findViewById(R.id.editText_Quantity1);
        editIngredient2 = (EditText)findViewById(R.id.editText_Ingredient2);
        editQuantity2 = (EditText)findViewById(R.id.editText_Quantity2);
        editIngredient3 = (EditText)findViewById(R.id.editText_Ingredient3);
        editQuantity3 = (EditText)findViewById(R.id.editText_Quantity3);
        editIngredient4 = (EditText)findViewById(R.id.editText_Ingredient4);
        editQuantity4 = (EditText)findViewById(R.id.editText_Quantity4);
        editIngredient5 = (EditText)findViewById(R.id.editText_Ingredient5);
        editQuantity5 = (EditText)findViewById(R.id.editText_Quantity5);
        editInstruction = (EditText)findViewById(R.id.editText_Instruction);
        btnAdd = (Button)findViewById(R.id.button_Add);
        btnView = (Button)findViewById(R.id.button_View);
        btnDelete = (Button)findViewById(R.id.button_Delete);
        AddData();
        ingredientsCheck();
        viewAll();
        DeleteData();
    }


    public void ingredientsCheck(){
        //Create Comparison to Only show recipes that have ingredients
        Cursor curPan = pantryDb.getAllRows();   //used for comparison only
        Cursor curRec = myDb.getAllData();
        //curPan.moveToFirst(); //resets to top of DB
        //curRec.moveToFirst(); //resets to top of DB
        x = 0; //reset for searching
        while(curRec.moveToNext()) //test for every recipe until null
        {
            //Toast.makeText(RecipeActivity.this, "1", Toast.LENGTH_LONG).show(); //testing purposes
            curPan.moveToFirst(); //reset to beginning of pantry for every ingredient lookpup.
            //First match the ingredients
            int flagged = 0; //for looping either reaches end of pantry or finds it.
            for(int k = 2; k < 11; k = k+2) {  //plus 2 because every other is the recipe ingredient, not qty
                curPan.moveToFirst();
                while (curPan.moveToNext()) { //test for first ingredient
                    flagged = 0; //resets flag
                    if (curRec.getString(k).contains(curPan.getString(1)) || curRec.isNull(k)) {  //ITs OK, if it matches or Recipe is empty.
                        //check[x]++;
                        test++;
                        flagged = 1;
                        Toast.makeText(RecipeActivity.this, "1", Toast.LENGTH_LONG).show(); //testing purposes
                    }
                    if(flagged == 1)  //if found, move to next recipe ingredient, whick is k
                        break;
                }
            }

            //test = 0; //reset test
            x++;  //next recipe
            //Now test for correct Quantity, not being tested yet.
        }



    }
    public void AddData(){
        btnAdd.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Will insert data if there is data input in the text fields
                        boolean isInserted = myDb.insertData(editName.getText().toString(),
                                editIngredient1.getText().toString(),
                                editQuantity1.getText().toString(),
                                editIngredient2.getText().toString(),
                                editQuantity2.getText().toString(),
                                editIngredient3.getText().toString(),
                                editQuantity3.getText().toString(),
                                editIngredient4.getText().toString(),
                                editQuantity4.getText().toString(),
                                editIngredient5.getText().toString(),
                                editQuantity5.getText().toString(),
                                editInstruction.getText().toString());
                        if (isInserted = true) {
                            Toast.makeText(RecipeActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();

                            // This following block of code sets text field to blank after user inputs data
                            editName.setText("");
                            editIngredient1.setText("");
                            editQuantity1.setText("");
                            editIngredient2.setText("");
                            editQuantity2.setText("");
                            editIngredient3.setText("");
                            editQuantity3.setText("");
                            editIngredient4.setText("");
                            editQuantity4.setText("");
                            editIngredient5.setText("");
                            editQuantity5.setText("");
                            editInstruction.setText("");
                        }else {
                            Toast.makeText(RecipeActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }


    public void DeleteData(){
        btnDelete.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDb.deleteData(editName.getText().toString());
                        if(deletedRows > 0){
                            Toast.makeText(RecipeActivity.this, "Data Deleted", Toast.LENGTH_LONG).show();
                            editName.setText("");
                        }else{
                            Toast.makeText(RecipeActivity.this, "Data not Deleted", Toast.LENGTH_LONG).show();
                        }

                    }

                }
        );
    }

    public void viewAll(){

        btnView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if(res.getCount() == 0) {
                            // show message
                            showMessage("Error","Nothing found");
                            return;
                        }
                        // ingredientsCheck();  //checks for ingrecients before displaying.

                        // Now Should Display if all 5 ingredients are available
                        StringBuffer buffer = new StringBuffer();
                        x = 0; //x is used for show[x] to test if a Recipe has the required Ingredients
                        //res.moveToFirst();
                        while (res.moveToNext()) {
                            // ID is optional to keep track of recipe number
                            //buffer.append("ID : "+ res.getString(0)+"\n\);
                           // if(test >= ingredientThreshold){ //skip if not all ingredients present
                                buffer.append("Recipe Name : " + res.getString(1) + "\n\n");
                                buffer.append("Ingredient1 : " + res.getString(2) + "\n");
                                buffer.append("Quantity1 : " + res.getString(3) + "\n");
                                buffer.append("Ingredient2 : " + res.getString(4) + "\n");
                                buffer.append("Quantity2 : " + res.getString(5) + "\n");
                                buffer.append("Ingredient3 : " + res.getString(6) + "\n");
                                buffer.append("Quantity3 : " + res.getString(7) + "\n");
                                buffer.append("Ingredient4 : " + res.getString(8) + "\n");
                                buffer.append("Quantity4 : " + res.getString(9) + "\n");
                                buffer.append("Ingredient5 : " + res.getString(10) + "\n");
                                buffer.append("Quantity5 : " + res.getString(11) + "\n\n");

                                // If user sets instructions to null (empty field)
                                // will result in app that will crash due to error on line 140
                                buffer.append("Instructions : " + res.getString(12) + "\n\n\n\n");
                          //  }
                        }

                        // Show all data
                        showMessage("Recipes",buffer.toString());
                    }
                }
        );


    }

    public void showMessage (String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}

