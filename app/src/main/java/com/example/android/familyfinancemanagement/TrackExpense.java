package com.example.android.familyfinancemanagement;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.database.Cursor;
import android.widget.Button;
import android.widget.ListView;


import com.example.android.familyfinancemanagement.data.FinanceContract;
import com.example.android.familyfinancemanagement.data.FinanceDbHelper;

import java.util.ArrayList;

public class TrackExpense extends AppCompatActivity {

    Button btnBack;
    private FinanceDbHelper mFinanceHelper = new FinanceDbHelper(this);
    //-----------------------------------

    //-----------------------------------


    Cursor cursor;
    ListAdapter listAdapter ;
    ListView LISTVIEW;

    ArrayList<String> Exp_Date;
    ArrayList<String> Exp_Name;
    ArrayList<String> Exp_Amount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_expense);
        //------------------Pie Chat---------------------------------------

        //---------------------------------------------------------


        LISTVIEW = (ListView) findViewById(R.id.listView1);

        Exp_Date = new ArrayList<String>();

        Exp_Name = new ArrayList<String>();

        Exp_Amount = new ArrayList<String>();

        //mFinanceHelper = new mFinanceHelper(this);

        btnBack=(Button)findViewById(R.id.btn_track_back);
        btnBack.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent trackIntent = new Intent(TrackExpense.this, MainActivity.class);
                        startActivity(trackIntent);

                    }//end of on click
                }//end of on click listner
        );//end of listner


    }
    //-------------------------------------------------------------------------------

    //-------------------------------------------------------------------------------
    @Override
    protected void onResume() {

        ShowSQLiteDBdata() ;

        super.onResume();
    }

    private void ShowSQLiteDBdata() {

        //sqLiteDatabase = mFinanceHelper.getWritableDatabase();

        cursor = mFinanceHelper.getAllData();
        if(cursor.getCount() == 0){
            //show empty dataase
            showMessage("Error","No data found",1);
            return;
        }
        Exp_Date.clear();
        Exp_Name.clear();
        Exp_Amount.clear();

        if (cursor.moveToFirst()) {
            do {

                Exp_Date.add(cursor.getString(cursor.getColumnIndex(FinanceContract.I_E_T_EntryTable.COLUMN_DATE)));

                Exp_Name.add(cursor.getString(cursor.getColumnIndex(FinanceContract.I_E_T_EntryTable.COLUMN_INC_EXP_NAME)));

                String str = cursor.getString(cursor.getColumnIndex(FinanceContract.I_E_T_EntryTable.COLUMN_INC_EXP_NAME));
                if(str.equals("Income")){
                    Exp_Amount.add(cursor.getString(cursor.getColumnIndex(FinanceContract.I_E_T_EntryTable.COLUMN_INCOME_AMOUNT)));
                }
            else{
                    Exp_Amount.add(cursor.getString(cursor.getColumnIndex(FinanceContract.I_E_T_EntryTable.COLUMN_EXPENSE_AMOUNT)));
                }



            } while (cursor.moveToNext());
        }

        listAdapter = new ListAdapter(TrackExpense.this,

                Exp_Date,
                Exp_Name,
                Exp_Amount
        );

        LISTVIEW.setAdapter(listAdapter);

        cursor.close();
    }
    //------------------------------------------------
    @Override
    protected void onStart() {
        super.onStart();

    }


    public void showMessage(String title, String message,int flag){
        AlertDialog.Builder builder = new AlertDialog.Builder((this));
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        if(flag==1){
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    //do things
                    Intent trackIntent = new Intent(TrackExpense.this, MainActivity.class);
                    startActivity(trackIntent);
                }
            });
        }
        AlertDialog alert = builder.create();
        alert.show();
    }
}//end of class