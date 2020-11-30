package com.example.android.familyfinancemanagement;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.android.familyfinancemanagement.data.FinanceDbHelper;
import com.example.android.familyfinancemanagement.data.FinanceContract.I_E_T_EntryTable;



public class ExpensesActivity extends AppCompatActivity {

    private final FinanceDbHelper mFinanceHelper = new FinanceDbHelper(this);


    public static int currentExpenseAmount=0;
    public static int total=0;
    EditText mExpenseAmountEdit ;
    public static int expensesTotal=0 ;
    private Spinner expensesSpinner;
    private String selectExpenses = I_E_T_EntryTable.EXPENSES_UNKNOWN ;
    Button btnSave, btnBack;
    //-----------------------------

    //-----------------------------



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);
        //method to check if empty
        checkIfEmpty();

        // to display current balance
        TextView displayView = (TextView) findViewById(R.id.show_current_balance);
        total = getLastCB();
        String s = ""+total;
        displayView.append(s);


        mExpenseAmountEdit = (EditText)findViewById(R.id.edit_expense_amount);

        expensesSpinner = (Spinner) findViewById(R.id.edit_expense_name);
        setupSpinner();

        btnSave = (Button) findViewById(R.id.btn_exp_save);
        btnBack=(Button)findViewById(R.id.btn_exp_back);


        btnSave.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        insertExpense();
                        displayExpenseInfo();

                    }//end of on click
                }//end of on click listner
        );//end of listner

        btnBack.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent expenseIntent = new Intent(ExpensesActivity.this, MainActivity.class);
                        startActivity(expenseIntent);

                    }//end of on click
                }//end of on click listner
        );//end of listner


        //-----------------------------------------------------------------------------


        //-------------------------------------------------------------------------------

    }


    @Override
    protected void onStart() {
        super.onStart();

    }

    public void checkIfEmpty(){
        Cursor cursor = mFinanceHelper.getAllData();
        if(getLastCB()==0){
            showMessage("Error :","Your Current Balance is 0, add income",1);
        }
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
                    Intent expenseIntent = new Intent(ExpensesActivity.this, MainActivity.class);
                    startActivity(expenseIntent);
                }
            });
        }
        AlertDialog alert = builder.create();
        alert.show();
    }

   public void displayExpenseInfo() {

       Cursor cursor = mFinanceHelper.getAllData();

       TextView displayView = (TextView) findViewById(R.id.show_total_expenses);

       try {

           int expenseAmountColumn = cursor.getColumnIndex(I_E_T_EntryTable.COLUMN_EXPENSE_AMOUNT);

           //calculate total expense from first
           while (cursor.moveToNext()) {

               currentExpenseAmount = cursor.getInt(expenseAmountColumn);
               expensesTotal = expensesTotal + currentExpenseAmount;

           }
           displayView.append((""+expensesTotal));


       } finally {
           cursor.close();
       }

       //cursor.close();
   }//end of display info




    //to insert expense in database
  private void insertExpense() {
      //create object of database class
      FinanceDbHelper mFinanceHelper = new FinanceDbHelper(this);
      SQLiteDatabase db = mFinanceHelper.getWritableDatabase();


      String expenseAmountString = mExpenseAmountEdit.getText().toString().trim();
      int exp = Integer.parseInt(expenseAmountString);
      //String expenseNameString = mExpenseNameEdit.getText().toString().trim();

      int bal = getLastCB();
      if(bal < exp){
          bal = exp - bal;
      }
      else{
          bal = bal - exp;
      }
      String balStr = "";
      balStr = ""+ bal;

      //date function
      DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      LocalDateTime now = LocalDateTime.now();
      String currentDate = dtf.format(now);

      ContentValues contentValues = new ContentValues();
      //put expense amount
      contentValues.put(I_E_T_EntryTable.COLUMN_EXPENSE_AMOUNT, expenseAmountString);
      //put expense amount
      contentValues.put(I_E_T_EntryTable.COLUMN_INC_EXP_NAME, selectExpenses);
      //put current date f expense
      contentValues.put(I_E_T_EntryTable.COLUMN_DATE, currentDate);
      ////put current balance subtracting from expense
      contentValues.put(I_E_T_EntryTable.COLUMN_CURRENT_BALANCE, balStr);
      ////put income value zero
      contentValues.put(I_E_T_EntryTable.COLUMN_INCOME_AMOUNT, "0");



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

    private void setupSpinner()
    {

        ArrayAdapter expensesSpinnerAdapter = ArrayAdapter.createFromResource(this , R.array.expense_list ,
                R.layout.support_simple_spinner_dropdown_item);

        expensesSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);


        expensesSpinner.setAdapter(expensesSpinnerAdapter);

        // Set the integer mSelected to the constant values
        expensesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);

                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.expenses_grocery))) {
                        selectExpenses = I_E_T_EntryTable.EXPENSES_GROCERY;
                        //totalGrocery = +totalGrocery;
                    }
                    else if (selection.equals(getString(R.string.expenses_houseMaintenance))) {
                        selectExpenses = I_E_T_EntryTable.EXPENSES_HOUSE_MAINTENANCE;
                        // totalHouseMaintenance = +totalHouseMaintenance;
                    }
                    else if (selection.equals(getString(R.string.expenses_fuel))) {
                        selectExpenses = I_E_T_EntryTable.EXPENSES_FUEL;
                    }
                    else if (selection.equals(getString(R.string.expenses_electricityBill))) {
                        selectExpenses = I_E_T_EntryTable.EXPENSES_ELECTRICITY_BILL;
                    }
                    else if (selection.equals(getString(R.string.expenses_tax))) {
                        selectExpenses = I_E_T_EntryTable.EXPENSES_TAX;
                    }
                    else if (selection.equals(getString(R.string.expenses_entertainment))) {
                        selectExpenses = I_E_T_EntryTable.EXPENSES_ENTERTAINMENT;
                    }
                    else if (selection.equals(getString(R.string.expenses_travel))) {
                        selectExpenses = I_E_T_EntryTable.EXPENSES_TRAVEL;
                    }
                    else if (selection.equals(getString(R.string.expenses_medical))) {
                        selectExpenses = I_E_T_EntryTable.EXPENSES_MEDICAL;
                    }
                    else {
                        selectExpenses = I_E_T_EntryTable.EXPENSES_UNKNOWN;
                    }


                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                selectExpenses = I_E_T_EntryTable.EXPENSES_UNKNOWN;
            }
        });

    }

    //-----------------------------------------------------------------------------------

    //------------------------------------------------

}//end of class