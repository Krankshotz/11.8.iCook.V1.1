package com.example.jordan.icook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ms.square.android.expandabletextview.ExpandableTextView;

public class HelpActivity extends AppCompatActivity {

    ExpandableTextView expandableTextView;
    String longText = "Welcome to iCook.\nWe are glad you are here.\n\nClick to Get Started\n" +
            "1. For new users we recommend importing a default Pantry layout located in our Preference Page\n\n" +
            "2. To Import Receipts:\nPress -SCAN RECEIPT- button to capture your receipt and automatically add the items in iCook pantry\n"
            + "(Make Sure you give the permission to use your phone camera, then back up to the main menu and try again)\n\n"
            + "3. To Manually Add Pantry Items :\nPress -MY PANTRY- button"
            + "- Enter the name of the item and its amount\n"
            + "- Press + to add it in iCook pantry\n"
            + "- Press -VIEW PANTRY- to see your iCook pantry list\n\n"
            + "4. To Add Recipes:\nPress -RECIPES- button to add your own recipes, check the recipes list and delete any recipe\n"
            + "- Enter the title, ingredients, their quatities and the instructions for the recipe you want to add\n"
            + "- Press + button to enter this recipe to the iCook recipes list\n"
            + "- Press -RECIPES- button to see your iCook recipes list\n\n"
            + "5. Have questions how to do something? Most pages have an information icon\n\n"
            + "6. Use the shortcuts:\nMost pages have quick access buttons located at the top right and left. The left will"
            + " always take you to the main menu.\n\n"
            + "Good Luck using iCook!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        expandableTextView = findViewById(R.id.expandable_text_view);
        expandableTextView.setText(longText);

    }
}
