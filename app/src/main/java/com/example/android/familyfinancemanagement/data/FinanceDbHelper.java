
package com.example.android.familyfinancemanagement.data;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;




import com.example.android.familyfinancemanagement.data.FinanceContract.IncomeEntry;
import com.example.android.familyfinancemanagement.data.FinanceContract.ExpenseEntry;

public class FinanceDbHelper extends SQLiteOpenHelper {

    //Database file name
    public static String DATABASE_NAME = "Finance.db";
    private static final int DATABASE_VERSION = 1;

    String CREATE_EXPENSE_TABLE = "CREATE TABLE " + ExpenseEntry.TABLE2_NAME +"("
            + ExpenseEntry._ID  + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ExpenseEntry.COLUMN_EXPENSE_AMOUNT + " INTEGER, "
            + ExpenseEntry.COLUMN_EXPENSE_NAME + " TEXT "
            + ")";


    String CREATE_INCOME_TABLE = "CREATE TABLE " + IncomeEntry.TABLE1_NAME + "("
            + IncomeEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + IncomeEntry.COLUMN_INCOME_AMOUNT + " INTEGER " + ")";
    public FinanceDbHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }



    @Override
    public void onCreate(SQLiteDatabase db) {


          /*  String CREATE_INCOME_TABLE = "CREATE TABLE " + IncomeEntry.TABLE1_NAME + "("
                    + IncomeEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + IncomeEntry.COLUMN_INCOME_AMOUNT + " INTEGER " + ")";*/

           db.execSQL(CREATE_INCOME_TABLE);

      /*  String CREATE_EXPENSE_TABLE = "CREATE TABLE " + ExpenseEntry.TABLE2_NAME +"("
                + ExpenseEntry._ID  + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ExpenseEntry.COLUMN_EXPENSE_AMOUNT + " INTEGER, "
                + ExpenseEntry.COLUMN_EXPENSE_NAME + " TEXT "
                   + ")";
*/
          db.execSQL(CREATE_EXPENSE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
