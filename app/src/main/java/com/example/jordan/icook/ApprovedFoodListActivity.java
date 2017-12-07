package com.example.jordan.icook;
//Paul Figueroa 12/6/17
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.Arrays;

public class ApprovedFoodListActivity extends AppCompatActivity {

    ExpandableTextView expandableTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);
        Resources res = getResources();
        String[] foodList = res.getStringArray(R.array.approved_food_list);
        expandableTextView = findViewById(R.id.expandable_text_view);
        expandableTextView.setText(Arrays.toString(foodList));

    }
}
