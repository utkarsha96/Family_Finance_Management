package com.example.android.familyfinancemanagement;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.android.familyfinancemanagement.data.FinanceContract.ExpenseEntry;
import com.example.android.familyfinancemanagement.data.FinanceDbHelper;


public class AddExpenseActivity extends AppCompatActivity {
    EditText mExpenseAmountEdit , mExpenseNameEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_expenses);

        mExpenseAmountEdit = (EditText) findViewById(R.id.edit_expense_name);

        TextView save = (TextView) findViewById(R.id.save_income);
        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                insertExpense();
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();

          
    }

    private void insertExpense()
    {
        FinanceDbHelper mFinanceHelper = new FinanceDbHelper(this);
        SQLiteDatabase db = mFinanceHelper.getWritableDatabase();

        String expenseAmountString = mExpenseAmountEdit.getText().toString().trim();
        String expenseNameString = mExpenseNameEdit.getText().toString().trim();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ExpenseEntry.COLUMN_EXPENSE_AMOUNT, expenseAmountString);
        contentValues.put(ExpenseEntry.COLUMN_EXPENSE_NAME,expenseNameString);

        long newRowId = db.insert(ExpenseEntry.TABLE2_NAME, null, contentValues);

        // Show a toast message depending on whether or not the insertion was successful
        if (newRowId == -1) {
            // If the row ID is -1, then there was an error with insertion.
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast with the row ID.
            Toast.makeText(this, "Saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }

    }
}
