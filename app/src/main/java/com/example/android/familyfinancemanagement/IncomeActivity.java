package com.example.android.familyfinancemanagement;



import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android.familyfinancemanagement.data.FinanceContract;
import com.example.android.familyfinancemanagement.data.FinanceDbHelper;
import com.example.android.familyfinancemanagement.data.FinanceContract.I_E_T_EntryTable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class IncomeActivity extends AppCompatActivity {

    public static int total;
    private FinanceDbHelper mFinanceHelper = new FinanceDbHelper(this);

    private EditText mIncomeEditText;
    Button btnIncSave, btnBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);

        //mIncomeName = (EditText) findViewById(R.id.edit_name);
        mIncomeEditText = (EditText) findViewById(R.id.edit_income);

        // this will show old current balance
        TextView displayView = (TextView) findViewById(R.id.show_amount);
        total=0;
        total = getLastCB();
        displayView.append((""+total));


        btnIncSave = (Button)findViewById(R.id.btn_inc_save);
        btnIncSave.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        insertIncome();
                        dispalayInfo();

                    }//end of on click
                }//end of on click listner
        );//end of listner

        btnBack=(Button)findViewById(R.id.btn_inc_back);
        btnBack.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent incomeIntent = new Intent(IncomeActivity.this, MainActivity.class);
                        startActivity(incomeIntent);

                    }//end of on click
                }//end of on click listner
        );//end of listner

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    private void insertIncome() {

        SQLiteDatabase db = mFinanceHelper.getWritableDatabase();

        String incomeString = mIncomeEditText.getText().toString().trim();


        int inc = Integer.parseInt(incomeString);
        int bal = getLastCB() + inc;
        String balStr = "";
        balStr = ""+ bal;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        String currentDate = dtf.format(now);

        ContentValues contentValues = new ContentValues();
        contentValues.put(I_E_T_EntryTable.COLUMN_INCOME_AMOUNT, incomeString);

        contentValues.put(I_E_T_EntryTable.COLUMN_CURRENT_BALANCE, balStr);

        contentValues.put(I_E_T_EntryTable.COLUMN_DATE, currentDate);
        //put expense value zero
        contentValues.put(I_E_T_EntryTable.COLUMN_EXPENSE_AMOUNT, "0");

        contentValues.put(I_E_T_EntryTable.COLUMN_INC_EXP_NAME, "Income");



        long newRowId = db.insert(I_E_T_EntryTable.TABLE_NAME, null, contentValues);

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

        TextView displayView = (TextView) findViewById(R.id.show_amount);
        total=0;
            total = getLastCB();
            displayView.append((""+total));

    }// end of method display

    public int getLastCB(){ //this method returns the last current ballance from the database


        Cursor cursor = mFinanceHelper.getAllData();
        int currentBalCol = cursor.getColumnIndex(I_E_T_EntryTable.COLUMN_CURRENT_BALANCE);
        //cursor = db.rawQuery("SELECT * FROM " + I_E_T_EntryTable.TABLE2_NAME,null);
        cursor.moveToFirst();
        //int icount = cursor.getInt(0);
        if(cursor.getCount() == 0){
            return 0;
        }
        //cursor = db.rawQuery("SELECT * FROM " + I_E_T_EntryTable.TABLE2_NAME+"DESC Limit 1",null);

        cursor.moveToLast();
        int bal = Integer.parseInt(cursor.getString(cursor.getColumnIndex(I_E_T_EntryTable.COLUMN_CURRENT_BALANCE)));

        cursor.close();
        return bal;

    }//end of getLastCB
}// end of class