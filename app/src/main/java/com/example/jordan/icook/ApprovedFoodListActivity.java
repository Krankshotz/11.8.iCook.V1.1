package com.example.jordan.icook;
//Paul Figueroa 12/6/17
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.Arrays;

public class ApprovedFoodListActivity extends AppCompatActivity {

    TextView textview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);
        Resources res = getResources();
        String[] foodList = res.getStringArray(R.array.approved_food_list);
        textview = findViewById(R.id.food_list_text);
        textview.setText("FOOD APPROVED LIST\n\n"+Arrays.toString(foodList));

    }
}
