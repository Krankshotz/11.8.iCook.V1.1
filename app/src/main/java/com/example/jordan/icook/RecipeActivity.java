package com.example.jordan.icook;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class RecipeActivity extends AppCompatActivity {
    DatabaseRecipe myDb;
    private GestureDetectorCompat gestureObject; //for Gestre class
    EditText editName, editIngredient1, editQuantity1,
            editIngredient2, editQuantity2,
            editIngredient3, editQuantity3,
            editIngredient4, editQuantity4,
            editIngredient5, editQuantity5,
            editInstruction;
    Button btnAdd, btnView, btnDelete,btnAddList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //For Gestures
        //gestureObject = new GestureDetectorCompat(this, new LearnGesture());
        //Class File
        setContentView(R.layout.activity_recipe);
        myDb = new DatabaseRecipe(this);

        editName = findViewById(R.id.editText_Name);
        editIngredient1 = findViewById(R.id.editText_Ingredient1);
        editQuantity1 = findViewById(R.id.editText_Quantity1);
        editIngredient2 = findViewById(R.id.editText_Ingredient2);
        editQuantity2 = findViewById(R.id.editText_Quantity2);
        editIngredient3 = findViewById(R.id.editText_Ingredient3);
        editQuantity3 = findViewById(R.id.editText_Quantity3);
        editIngredient4 = findViewById(R.id.editText_Ingredient4);
        editQuantity4 = findViewById(R.id.editText_Quantity4);
        editIngredient5 = findViewById(R.id.editText_Ingredient5);
        editQuantity5 = findViewById(R.id.editText_Quantity5);
        editInstruction = findViewById(R.id.editText_Instruction);
        btnAdd = findViewById(R.id.button_Add);
        btnView = findViewById(R.id.button_View);
        btnDelete = findViewById(R.id.button_Delete);
        btnAddList = findViewById(R.id.ingredient_btn);

        //listener to add more ingredients
        final Button showMoreIng = findViewById(R.id.ingredient_btn);
        showMoreIng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editIngredient4.setVisibility(View.VISIBLE);
                editIngredient5.setVisibility(View.VISIBLE);
                editQuantity4.setVisibility(View.VISIBLE);
                editQuantity5.setVisibility(View.VISIBLE);
                showMoreIng.setVisibility(View.INVISIBLE);
            }
        });

        //End of hides ingredients until needed
        AddData();
        viewAll();
        DeleteData();
    }


    public void AddData(){
        btnAdd.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Will insert data if there is data input in the text fields
                        boolean isInserted = myDb.insertData(editName.getText().toString(),
                                editIngredient1.getText().toString(),
                                Integer.parseInt(editQuantity1.getText().toString()),
                                editIngredient2.getText().toString(),
                                Integer.parseInt(editQuantity2.getText().toString()),
                                editIngredient3.getText().toString(),
                                Integer.parseInt(editQuantity3.getText().toString()),
                                editIngredient4.getText().toString(),
                                Integer.parseInt(editQuantity4.getText().toString()),
                                editIngredient5.getText().toString(),
                                Integer.parseInt(editQuantity5.getText().toString()),
                                editInstruction.getText().toString());
                        if (isInserted = true) {
                            Toast.makeText(RecipeActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                            //Hide buttons after added a list of ingredients
                            editIngredient4.setVisibility(View.INVISIBLE);
                            editIngredient5.setVisibility(View.INVISIBLE);
                            editQuantity4.setVisibility(View.INVISIBLE);
                            editQuantity5.setVisibility(View.INVISIBLE);
                            btnAddList.setVisibility(View.VISIBLE);

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
                        Intent RecipeList = new Intent(RecipeActivity.this, ListRecipe.class);
                        startActivity(RecipeList);
                        /*Cursor res = myDb.getAllData();
                        if(res.getCount() == 0) {
                            // show message
                            showMessage("Error","Nothing found");
                            return;
                        }

                        // Displays all recipes
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            // ID is optional to keep track of recipe number
                            //buffer.append("ID : "+ res.getString(0)+"\n\);
                            buffer.append("Recipe Name : "+ res.getString(1)+"\n\n");
                            buffer.append("Ingredient1 : "+ res.getString(2)+"\n");
                            buffer.append("Quantity1 : "+ res.getString(3)+"\n");
                            buffer.append("Ingredient2 : "+ res.getString(4)+"\n");
                            buffer.append("Quantity2 : "+ res.getString(5)+"\n");
                            buffer.append("Ingredient3 : "+ res.getString(6)+"\n");
                            buffer.append("Quantity3 : "+ res.getString(7)+"\n");
                            buffer.append("Ingredient4 : "+ res.getString(8)+"\n");
                            buffer.append("Quantity4 : "+ res.getString(9)+"\n");
                            buffer.append("Ingredient5 : "+ res.getString(10)+"\n");
                            buffer.append("Quantity5 : "+ res.getString(11)+"\n\n");

                            // If user sets instructions to null (empty field)
                            // will result in app that will crash due to error on line 140
                            buffer.append("Instructions : " + res.getString(12) + "\n\n\n\n");
                        }

                        // Show all data
                        showMessage("Recipes",buffer.toString());*/
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


  /*  //FOR GESTURES, So Swipes Open New Activites. Shortcuts :)
    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.gestureObject.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
    //For Gesture Object Class
    class LearnGesture extends GestureDetector.SimpleOnGestureListener{
        //Simple Gesture Listener for what we want to do, this opens the pantry
        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY){
            if(event2.getX() > event1.getX()){
                //Here is the code for what you want the swipe to do for Left to Right
                Intent openPantry = new Intent(RecipeActivity.this, MainActivity.class);
                finish(); //Ends current activities Actions
                startActivity(openPantry);
            }
            else
            if(event2.getX() < event1.getX()){
                //Here is the code for what you want the swipe to do for Right to Left
                Intent openPantry = new Intent(RecipeActivity.this, ListPantry.class);
                finish(); //Ends current activities Actions
                startActivity(openPantry);
            }
            return true;
        }

    }*/
}

