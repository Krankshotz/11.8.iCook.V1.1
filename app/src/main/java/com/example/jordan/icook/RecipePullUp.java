package com.example.jordan.icook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by edske on 12/4/2017.
 */

public class RecipePullUp extends AppCompatActivity {
    TextView name, i1, q1, i2, q2, i3, q3, i4, q4, i5, qu5, ins;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_childrenlist);
        Intent identify = getIntent();
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
        name = (TextView) findViewById(R.id.textname);
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
    }
}
