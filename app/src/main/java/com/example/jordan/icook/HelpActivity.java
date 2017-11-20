package com.example.jordan.icook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ms.square.android.expandabletextview.ExpandableTextView;

public class HelpActivity extends AppCompatActivity {

    ExpandableTextView expandableTextView;
    String longText = "Press -SCAN RECEIPT- button to capture your receipt and automatically add the items in Icook pantry\n"
            + "(Make Sure you give the permission to use your phone camera)\n" + "\n"
            + "Press -MY PANTRY- button to manually add items in the Icook pantry and check your Icook pantry\n"
            + "- Enter the name of the item and its amount\n"
            + "- Press -ADD ITEM- to add it in Icook pantry\n"
            + "- Press -VIEW ALL- to see your Icook pantry list\n" + "\n"
            + "Press -RECIPES- button to add your own recipes, check the recipes list and delete any recipe\n"
            + "- Enter the title, ingredients, their quatities and the instructions for the recipe you want to add\n"
            + "- Press -ADD RECIPE- button to enter this recipe to the Icook recipes list\n"
            + "- Press -VIEW ALL- button to see your Icook recipes list\n"
            + "- Press -DELETE RECIPE- to delete a recipe\n" + "\n"
            + "Press -PREFERENCES- to check out additional Icook options\n" + "\n"
            + "Good Luck using Icook!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        expandableTextView = (ExpandableTextView)findViewById(R.id.expandable_text_view);
        expandableTextView.setText(longText);

    }
}
