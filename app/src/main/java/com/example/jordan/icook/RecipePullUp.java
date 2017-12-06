package com.example.jordan.icook;


import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by edske and Henry Cao on 12/4/2017.
 */

public class RecipePullUp extends AppCompatActivity {
    TextView name, i1, q1, i2, q2, i3, q3, i4, q4, i5, qu5, ins;
    DatabaseRecipe myDb;
    TextView Header_ig;
    TextView Header_qu;
    TextView Header_Ins;
    DatabaseHelper pantryDb = new DatabaseHelper(this);
    String[] ingredients;
    String[] quantitys;
    int[] cook = new int[2];
    Button btnCook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_childrenlist);

        Header_ig = findViewById(R.id.header_ig);
        Header_qu = findViewById(R.id.header_qu);
        Header_Ins = findViewById(R.id.header_ins);
        //Underlines the headers
        Header_ig.setPaintFlags(Header_ig.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        Header_qu.setPaintFlags(Header_qu.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        Header_Ins.setPaintFlags(Header_Ins.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        Intent identify = getIntent();
        myDb = new DatabaseRecipe(this);
        //grabs that data that was pushed into the iIntent in ListRecipe.java and puts it into string

        String d1 = identify.getStringExtra("COL_2");
        String d2 = identify.getStringExtra("COL_3");
        String d3 = identify.getStringExtra("COL_4");
        String d4 = identify.getStringExtra("COL_5");
        String d5 = identify.getStringExtra("COL_6");
        String d6 = identify.getStringExtra("COL_7");
        String d7 = identify.getStringExtra("COL_8");
        String d8 = identify.getStringExtra("COL_9");
        String d9 = identify.getStringExtra("COL_10");
        String d10 = identify.getStringExtra("COL_11");
        String d11 = identify.getStringExtra("COL_12");
        String d12 = identify.getStringExtra("COL_13");


        //Initializes the global TextViews.

        name = (TextView) findViewById(R.id.name);
        i1 = (TextView) findViewById(R.id.ig1);
        q1 = (TextView) findViewById(R.id.qu1);
        i2 = (TextView) findViewById(R.id.ig2);
        q2 = (TextView) findViewById(R.id.qu2);
        i3 = (TextView) findViewById(R.id.ig3);
        q3 = (TextView) findViewById(R.id.qu3);
        i4 = (TextView) findViewById(R.id.ig4);
        q4 = (TextView) findViewById(R.id.qu4);
        i5 = (TextView) findViewById(R.id.ig5);
        qu5 = (TextView) findViewById(R.id.qu5);
        ins = (TextView) findViewById(R.id.instruction);

        //Set Strings to the the global TextViews

        name.setText(d1);
        i1.setText(d2);
        q1.setText(d3);
        i2.setText(d4);
        q2.setText(d5);
        i3.setText(d6);
        q3.setText(d7);
        i4.setText(d8);
        q4.setText(d9);
        i5.setText(d10);
        qu5.setText(d11);
        ins.setText(d12);
        ingredients = new String[] {d2,d4,d6,d8,d10};
        quantitys = new String[] {d3,d5,d7,d9,d11};
        cook = availableIgQu(ingredients, quantitys);
        cookIt(cook);
        
    }
//Will let the User know if they the ingredients to cook the recipe.
    public int[] availableIgQu(String[] ig, String [] qu)
    {
        Cursor pantryC = pantryDb.getAllRows();  //Cursor creates a copy of the DTB we c;an iterate through
        int[] cook = new int[2];
        int cookIg = 0;
        int cookQu = 0;
        for(int i=0; i < ig.length; i++) {
             if (pantryC.moveToFirst() ) {
                 //Checks if the ingredients in the recipe matching with any of the Ingredients in the pantry if does switch text to red.
                 A:do {
                     if(ig[i].equals(""))
                     {
                        cookIg++;
                        cookQu++;
                        break A;

                     }else {
                         if (ig[i].equalsIgnoreCase(pantryC.getString(1))) {
                             switch (i) {                                      ////////////////////////////////////////////////////////////////////////////
                                 case 0:    i1.setTextColor(Color.GREEN);        //if i = (any of the cases) switch the text to red, and break out of loop.//
                                            cookIg++;                          ////////////////////////////////////////////////////////////////////////////
                                            if(Integer.parseInt(qu[i]) <= Integer.parseInt(pantryC.getString(2))) {
                                                q1.setTextColor(Color.GREEN);
                                                cookQu++;
                                            }
                                            break A;

                                 case 1:    i2.setTextColor(Color.GREEN);
                                            cookIg++;
                                            if(Integer.parseInt(qu[i]) <= Integer.parseInt(pantryC.getString(2))) {
                                                q2.setTextColor(Color.GREEN);
                                                cookQu++;
                                            }
                                            break A;

                                 case 2:    i3.setTextColor(Color.GREEN);
                                            cookIg++;
                                            if(Integer.parseInt(qu[i]) <= Integer.parseInt(pantryC.getString(2))) {
                                                q3.setTextColor(Color.GREEN);
                                                cookQu++;
                                            }
                                            break A;

                                 case 3:    i4.setTextColor(Color.GREEN);
                                            cookIg++;
                                            if(Integer.parseInt(qu[i]) <= Integer.parseInt(pantryC.getString(2))) {
                                                q4.setTextColor(Color.GREEN);
                                                cookQu++;
                                            }
                                            break A;
                                 case 4:    i5.setTextColor(Color.GREEN);
                                            cookIg++;
                                            if(Integer.parseInt(qu[i]) <= Integer.parseInt(pantryC.getString(2))) {
                                                qu5.setTextColor(Color.GREEN);
                                                cookQu++;
                                            }
                                            break A;
                             }
                         }
                     }
                     } while (pantryC.moveToNext());
             }
        }
        cook[0] = cookIg;
        cook[1] = cookQu;
        return cook;
    }


//Cook the recipe, decreases all the quantity of ingredients/ delete
    public void cookIt(final int[] cook){
        final int cookIg = cook[0];
        final int cookQu = cook[1];
        btnCook = (Button) findViewById(R.id.btnCook);
        btnCook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(RecipePullUp.this);
                View mView = getLayoutInflater().inflate(R.layout.cook_dialog,null);
                mBuilder.setView(mView);
                mBuilder.setTitle("Do you want cook recipe?");
//////////////////
///     No     ///             Cancels dialog and go back to the recipe.
//////////////////
                mBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
//////////////////
///    Yes     ///             If user wants to cook recipe.
//////////////////
                mBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int j) {
                        if(cookIg == ingredients.length && cookQu == quantitys.length)  //checks if number of available ingredients...
                        {                                                               //quantitys match up to length of array.
                            Cursor pantryData = pantryDb.getAllRows();
                            int newQu;
                            String s;
                            for(int i = 0; i < ingredients.length; i++)
                            {
                                if(pantryData.moveToFirst())
                                {
                                    do{
                                        if(ingredients[i].equalsIgnoreCase(pantryData.getString(1))) //looks for the ingredients in the Pantry
                                        {
                                            newQu = Integer.parseInt(pantryData.getString(2)) -Integer.parseInt(quantitys[i]); //gets new quantity
                                            if(newQu==0)
                                            {
                                                pantryDb.deleteRow(Integer.parseInt(pantryData.getString(0))); //if new quantity = 0, deletes ingredient

                                            }else
                                            {
                                                s = newQu + "";
                                                pantryDb.updateData(pantryData.getString(0),s); // updates new quantity
                                            }
                                        }

                                    }while(pantryData.moveToNext());
                                }
                            }
                            Intent backToMain = new Intent(RecipePullUp.this, MainActivity.class);
                            View mView = getLayoutInflater().inflate(R.layout.recipe_childrenlist, null);
                            Toast.makeText(RecipePullUp.this, "Recipe Cooked",Toast.LENGTH_LONG).show();
                            startActivity(backToMain);
                        }else{
                            ////////////////////////////////////////////////////////////////////////////////////////////////////
                            //Recipe failed to cook pops up this message and divert user to either recipe list or pantry list.//
                            ////////////////////////////////////////////////////////////////////////////////////////////////////
                            AlertDialog.Builder mBuilder = new AlertDialog.Builder(RecipePullUp.this);
                            View mView = getLayoutInflater().inflate(R.layout.fail_cook,null);
                            Button failRecipe = (Button) mView.findViewById(R.id.failRecipe);
                            Button failPantry = (Button) mView.findViewById(R.id.failPantry);
                            failRecipe.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(RecipePullUp.this, ListRecipe.class);
                                    startActivity(intent);
                                }
                            });
                            failPantry.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(RecipePullUp.this, ListPantry.class);
                                    startActivity(intent);
                                }
                            });
                            mBuilder.setView(mView);
                            AlertDialog dialog = mBuilder.create();
                            dialog.show();
                            dialog.getWindow().setLayout(1050, 1000);

                        }
                    }
                });
                AlertDialog dialog = mBuilder.create();
                dialog.show();
                dialog.getWindow().setLayout(900, 450);
            }
        });
    }


}
