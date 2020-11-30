package com.example.android.familyfinancemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnAddIncome,btnAddExpense,btnTrack,btnExpStats,btnSavStats,btnSetting,btnSetGoal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnAddIncome = (Button) findViewById(R.id.btn_add_income);
        btnAddExpense = (Button) findViewById(R.id.btn_add_expense);
        btnTrack = (Button) findViewById(R.id.btn_track);
        btnExpStats = (Button) findViewById(R.id.btn_exp_stats);
        btnSavStats = (Button) findViewById(R.id.btn_sav_stats);
        btnSetting = (Button) findViewById(R.id.btn_setting);

        //--------------------------------------------------------
        btnAddIncome.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent incomeIntent = new Intent(MainActivity.this, IncomeActivity.class);
                        startActivity(incomeIntent);

                    }//end of on click
                }//end of on click listner
        );//end of listner
        //==========================================
        btnAddExpense.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent expenseIntent = new Intent(MainActivity.this, ExpensesActivity.class);
                        startActivity(expenseIntent);

                    }//end of on click
                }//end of on click listner
        );//end of listner
        //----------------TRACK EXPENSE-----------------------------------
        //==========================================
        btnTrack.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent trackExpenseIntent = new Intent(MainActivity.this, TrackExpense.class);
                        startActivity(trackExpenseIntent);

                    }//end of on click
                }//end of on click listner
        );//end of listner
        //---------------------EXPENSE STATISTICS-----------------------------------
        //==========================================
        btnExpStats.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent expenseStatsIntent = new Intent(MainActivity.this, IncExpSavStats.class);
                        startActivity(expenseStatsIntent);

                    }//end of on click
                }//end of on click listner
        );//end of listner
        //------------------SAVING STATISTICS--------------------------------------
        //==========================================
        btnSavStats.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent savingsStatsIntent = new Intent(MainActivity.this, SavingStats.class);
                        startActivity(savingsStatsIntent);

                    }//end of on click
                }//end of on click listner
        );//end of listner
        //--------------SETTINGS------------------------------------------
        btnSetting.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent settingIntent = new Intent(MainActivity.this, Settings.class);
                        startActivity(settingIntent);

                    }//end of on click
                }//end of on click listner
        );//end of listner
        //--------------------------------------------------------


    }//end of on create method
    @Override
    public void onPause(){
        // Suspend UI updates, threads, or CPU intensive processes
        // that don't need to be updated when the Activity isn't
        // the active foreground Activity.
        super.onPause();
    }
    @Override
    public void onResume(){
        super.onResume();
        // Resume any paused UI updates, threads, or processes required
        // by the Activity but suspended when it was inactive.
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate and
        // onRestoreInstanceState if the process is
        // killed and restarted by the run time.
        super.onSaveInstanceState(savedInstanceState);
    }
}//end of class