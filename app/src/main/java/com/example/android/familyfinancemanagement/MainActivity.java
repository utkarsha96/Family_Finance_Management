package com.example.android.familyfinancemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ****Income/Add Activity****
        TextView income = (TextView) findViewById(R.id.add_income);
        income.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent numbersIntent = new Intent(MainActivity.this, IncomeActivity.class);
                startActivity(numbersIntent);
            }
        });



        // ****Expense Activity****
        TextView expense = (TextView) findViewById(R.id.add_expense);
        expense.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent numbersIntent = new Intent(MainActivity.this, ExpensesActivity.class);
                startActivity(numbersIntent);
            }
        });
    }
}