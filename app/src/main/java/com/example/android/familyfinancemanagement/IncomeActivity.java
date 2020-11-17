package com.example.android.familyfinancemanagement;



import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android.familyfinancemanagement.data.FinanceDbHelper;
import com.example.android.familyfinancemanagement.data.FinanceContract.IncomeEntry;

public class IncomeActivity extends AppCompatActivity {

    private FinanceDbHelper mFinanceHelper = new FinanceDbHelper(this);
    Cursor cursor;
    private EditText mIncomeEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);

        mIncomeEditText = (EditText) findViewById(R.id.edit_income);

        TextView save = (TextView) findViewById(R.id.save_income);
        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                insertIncome();
                dispalayInfo();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
       // dispalayInfo();
    }

    private void insertIncome() {
        FinanceDbHelper mFinanceHelper = new FinanceDbHelper(this);
        SQLiteDatabase db = mFinanceHelper.getWritableDatabase();

        String incomeString = mIncomeEditText.getText().toString().trim();


        ContentValues contentValues = new ContentValues();
        contentValues.put(IncomeEntry.COLUMN_INCOME_AMOUNT, incomeString);

        long newRowId = db.insert(IncomeEntry.TABLE1_NAME, null, contentValues);

        // Show a toast message depending on whether or not the insertion was successful
        if (newRowId == -1) {
            // If the row ID is -1, then there was an error with insertion.
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast with the row ID.
            Toast.makeText(this, "Saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }


    private void dispalayInfo() {

        SQLiteDatabase db = mFinanceHelper.getReadableDatabase();

        String[] projection = {
                IncomeEntry._ID,
                IncomeEntry.COLUMN_INCOME_AMOUNT
        };

        Cursor cursor = db.query(
                IncomeEntry.TABLE1_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);


        TextView displayView = (TextView) findViewById(R.id.show_amount);

        try {
            int idColumnIndex = cursor.getColumnIndex(IncomeEntry._ID);
            int incomeColumnIndex = cursor.getColumnIndex(IncomeEntry.COLUMN_INCOME_AMOUNT);


                cursor = db.rawQuery("SELECT * FROM " + IncomeEntry.TABLE1_NAME , null);

            if (cursor.moveToLast()) {

                int currentID = cursor.getInt(idColumnIndex);
                int currentAmount = cursor.getInt(incomeColumnIndex);

                displayView.append((currentAmount + ""));
            }


        } finally {
            cursor.close();
        }
    }
}


