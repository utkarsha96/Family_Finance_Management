package com.example.android.familyfinancemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Color;

import android.widget.TextView;

import com.example.android.familyfinancemanagement.data.FinanceContract;
import com.example.android.familyfinancemanagement.data.FinanceDbHelper;
import com.example.android.familyfinancemanagement.data.FinanceContract.I_E_T_EntryTable;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;


import android.os.Bundle;

public class IncExpSavStats extends AppCompatActivity {

    PieChart pieChart;
    private final FinanceDbHelper mFinanceHelper = new FinanceDbHelper(this);

    private TextView showTravel, showGrocery, showEntertainment, showFuel, showTax, showMedical, showUnknown,
            showHouseMaintenance, showElectricityBill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inc_exp_sav_stats);

        pieChart = findViewById(R.id.pieChart);


        showGrocery = findViewById(R.id.show_grocery);
        showFuel = findViewById(R.id.show_fuel);
        showElectricityBill = findViewById(R.id.show_electricityBill);
        showEntertainment = findViewById(R.id.show_entertainment);
        showMedical = findViewById(R.id.show_medical);
        showTax = findViewById(R.id.show_tax);
        showTravel = findViewById(R.id.show_travel);
        showUnknown = findViewById(R.id.show_unknown);
        showHouseMaintenance = findViewById(R.id.show_houseMaintenance);

        // Creating a method setData()
        // to set the text in text view and pie chart
        setData();
    }// end of on create()

    public int returnSum(String value){
        Cursor res = mFinanceHelper.getAllData();
        int sum = 0;
        int typeId, valId;
        typeId = res.getColumnIndex(I_E_T_EntryTable.COLUMN_INC_EXP_NAME);
        valId =  res.getColumnIndex(I_E_T_EntryTable.COLUMN_EXPENSE_AMOUNT);
        while(res.moveToNext()){
            String s = res.getString(typeId);
            if(s.equals("value")){
                int x = Integer.parseInt(res.getString(valId));
                sum = sum + x;

            }
        }
        return 1;
    }
    private void setData() {

        {
            showGrocery.setText(Integer.toString(10));
            showHouseMaintenance.setText(Integer.toString(20));
            showFuel.setText(Integer.toString(30));
            showMedical.setText(Integer.toString(40));
            showEntertainment.setText(Integer.toString(50));
            showElectricityBill.setText(Integer.toString(60));
            showUnknown.setText(Integer.toString(70));
            showTravel.setText(Integer.toString(80));
            showTax.setText(Integer.toString(90));

            pieChart.addPieSlice(
                    new PieModel(
                            //"Grocery",
                            Integer.parseInt(showGrocery.getText().toString()),
                            Color.parseColor("#FFA726")));

            pieChart.addPieSlice(
                    new PieModel(
                            //"House Maintenance",
                            Integer.parseInt(showHouseMaintenance.getText().toString()),
                            Color.parseColor("#66BB6A")));
            pieChart.addPieSlice(
                    new PieModel(
                            //"Fuel",
                            Integer.parseInt(showFuel.getText().toString()),
                            Color.parseColor("#EF5350")));
            pieChart.addPieSlice(
                    new PieModel(
                            //"Electricity Bill",
                            Integer.parseInt(showElectricityBill.getText().toString()),
                            Color.parseColor("#29B6F6")));
            pieChart.addPieSlice(
                    new PieModel(
                            //"Tax",
                            Integer.parseInt(showTax.getText().toString()),
                            Color.parseColor("#7E8182")));
            pieChart.addPieSlice(
                    new PieModel(
                            //"Entertainment",
                            Integer.parseInt(showEntertainment.getText().toString()),
                            Color.parseColor("#FF000000")));
    /*    pieChart.addPieSlice(
                    new PieModel(
                            //"Travel",
                            Integer.parseInt(showTravel.getText().toString()),
                            Color.parseColor("#58D68D")));
           pieChart.addPieSlice(
                    new PieModel(
                           // "Medical",
                            Integer.parseInt(showMedical.getText().toString()),
                            Color.parseColor("#7D3C98")));
            pieChart.addPieSlice(
                    new PieModel(
                           // "Unknown",
                            Integer.parseInt(showUnknown.getText().toString()),
                            Color.parseColor("#FFFF00")));*/


            // To animate the pie chart
            pieChart.startAnimation();
        }


    }
}// end of class