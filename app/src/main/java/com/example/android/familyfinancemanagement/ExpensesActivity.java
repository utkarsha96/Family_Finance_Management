package com.example.android.familyfinancemanagement;

import android.content.ContentValues;
import android.content.Intent;
import android.database.AbstractCursor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android.familyfinancemanagement.data.FinanceContract;
import com.example.android.familyfinancemanagement.data.FinanceContract.ExpenseEntry;
import com.example.android.familyfinancemanagement.data.FinanceDbHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ExpensesActivity extends AppCompatActivity {

    private FinanceDbHelper mFinanceHelper = new FinanceDbHelper(this);
    EditText mExpenseAmountEdit , mExpenseNameEdit;
    int expensesTotal=0 , currentBalance;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);

        mExpenseNameEdit = (EditText) findViewById(R.id.edit_expense_name);
        mExpenseAmountEdit = (EditText)findViewById(R.id.edit_expense_amount);

        TextView save = (TextView) findViewById(R.id.save_expense);
        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                insertExpense();
                displayExpenseInfo();
                displayCurrentBalance();

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
                ExpenseEntry.COLUMN_EXPENSE_AMOUNT,
               ExpenseEntry.COLUMN_EXPENSE_NAME,
              // ExpenseEntry.COLUMN_EXPENSE_AMOUNT
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

           /* if (cursor.moveToLast()) {

                int currentID = cursor.getInt(idColumnIndex);
                int currentName = cursor.getInt(expenseNameColumn);
                int currentExpense = cursor.getInt(expenseAmountColumn);

                displayView.append((currentExpense +currentName + "" ));
            }*/
            while (cursor.moveToNext()) {

              //  int currentID = cursor.getInt(idColumnIndex);
              //  String currentName = cursor.getString(expenseNameColumn);
                int currentExpense = cursor.getInt(expenseAmountColumn);
                 expensesTotal = expensesTotal +currentExpense;
                // Display the values from each column of the current row in the cursor in the TextView
               // displayView.append((total + ""));
            }
            displayView.append((expensesTotal + ""));



        } finally {
            cursor.close();
        }
    }
  public void displayCurrentBalance()
  {
      currentBalance =  IncomeActivity.mresult -   expensesTotal;
      TextView show = (TextView) findViewById(R.id.show_current_balance);
      show.append(IncomeActivity.mresult + "");
      try {
          show.append(currentBalance + "");
      } catch (Exception e) {
          e.printStackTrace();
      }




  }

  /*  private void insertExpense()
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

    }*/
  private void insertExpense() {
      FinanceDbHelper mFinanceHelper = new FinanceDbHelper(this);
      SQLiteDatabase db = mFinanceHelper.getWritableDatabase();

      String expenseAmountString = mExpenseAmountEdit.getText().toString().trim();
      String expenseNameString = mExpenseNameEdit.getText().toString().trim();


      ContentValues contentValues = new ContentValues();
      contentValues.put(ExpenseEntry.COLUMN_EXPENSE_AMOUNT, expenseAmountString);
      contentValues.put(ExpenseEntry.COLUMN_EXPENSE_NAME, expenseNameString);

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