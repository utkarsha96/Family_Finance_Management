package com.example.android.familyfinancemanagement;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android.familyfinancemanagement.data.FinanceContract.ExpenseEntry;
import com.example.android.familyfinancemanagement.data.FinanceDbHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ExpensesActivity extends AppCompatActivity {

    private FinanceDbHelper mFinanceHelper = new FinanceDbHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ExpensesActivity.this, AddExpenseActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();

    }

    public void displayExpenseInfo() {

        SQLiteDatabase db = mFinanceHelper.getReadableDatabase();

        String[] projection = {
              ExpenseEntry._ID,
               ExpenseEntry.COLUMN_EXPENSE_NAME,
               ExpenseEntry.COLUMN_EXPENSE_AMOUNT
        };

        Cursor cursor = db.query(
                ExpenseEntry.TABLE2_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);


        TextView displayView = (TextView) findViewById(R.id.show_total_expenses);

        try {
            int idColumnIndex = cursor.getColumnIndex(ExpenseEntry._ID);
            int expenseAmountColumn = cursor.getColumnIndex(ExpenseEntry.COLUMN_EXPENSE_AMOUNT);
            int expenseNameColumn = cursor.getColumnIndex(ExpenseEntry.COLUMN_EXPENSE_NAME);

            cursor = db.rawQuery("SELECT * FROM " + ExpenseEntry.TABLE2_NAME , null);

            if (cursor.moveToLast()) {

                int currentID = cursor.getInt(idColumnIndex);
                int currentName = cursor.getInt(expenseNameColumn);
                int currentExpense = cursor.getInt(expenseAmountColumn);

                displayView.append((currentName + currentExpense + "" ));
            }


        } finally {
            cursor.close();
        }
    }



}