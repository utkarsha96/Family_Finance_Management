
package com.example.android.familyfinancemanagement.data;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;





import com.example.android.familyfinancemanagement.data.FinanceContract.I_E_T_EntryTable;
import com.example.android.familyfinancemanagement.dialog;

public class FinanceDbHelper extends SQLiteOpenHelper {

    //Database file name
    public static String DATABASE_NAME = "Finance.db";
    private static final int DATABASE_VERSION = 1;

    /*
    Table column sequense :

    id
    Date
    From Account
    CurrentBalance from default account
    Income Amount
    Expense name
    Expense amount
    Transfer amount
    To account
    Savings

     */
    /*
    Table column sequense :

    id
    Type : Account / Debit card / Credit Card
    Name :
    current Balance

     */
    /*
    Table column sequense :

    id
    Account Name
    Date
    last current balance

     */

    String CREATE_I_E_T_TABLE = "CREATE TABLE " + I_E_T_EntryTable.TABLE_NAME +"("
            + I_E_T_EntryTable._ID  + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + I_E_T_EntryTable.COLUMN_DATE + " TEXT, "
            + I_E_T_EntryTable.COLUMN_FROM_ACCOUNT + " TEXT, "
            + I_E_T_EntryTable.COLUMN_CURRENT_BALANCE + " INTEGER, "
            + I_E_T_EntryTable.COLUMN_INCOME_AMOUNT + " INTEGER, "
            + I_E_T_EntryTable.COLUMN_INC_EXP_NAME + " TEXT,"
            + I_E_T_EntryTable.COLUMN_EXPENSE_AMOUNT + " INTEGER, "
            + I_E_T_EntryTable.COLUMN_TRANSFER_AMOUNT + " INTEGER, "
            + I_E_T_EntryTable.COLUMN_TO_ACCOUNT + " TEXT, "
            + I_E_T_EntryTable.COLUMN_SAVING + " TEXT "
            + ")";
    //------------------Account Info-------------------------------------------------
    String CREATE_ACC_INFO_TABLE = "CREATE TABLE " + I_E_T_EntryTable.TABLE1_NAME +"("
            + I_E_T_EntryTable._ID  + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + I_E_T_EntryTable.COLUMN_ACC_NAME + " TEXT, "
            + I_E_T_EntryTable.COLUMN_ACC_TYPE + " TEXT, "
            + I_E_T_EntryTable.COLUMN_CURR_BAL + " INTEGER "
            + ")";
    //-------------------------------------------------------------------
    /*
    String CREATE_ACC_CB_TABLE = "CREATE TABLE " + I_E_T_EntryTable.TABLE2_NAME +"("
            + I_E_T_EntryTable._ID  + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + I_E_T_EntryTable.COLUMN_DATE + " TEXT, "
            + I_E_T_EntryTable.COLUMN_FROM_ACCOUNT + " TEXT, "
            + I_E_T_EntryTable.COLUMN_CURRENT_BALANCE + " INTEGER, "
            + I_E_T_EntryTable.COLUMN_INCOME_AMOUNT + " INTEGER, "
            + I_E_T_EntryTable.COLUMN_INC_EXP_NAME + " TEXT,"
            + I_E_T_EntryTable.COLUMN_EXPENSE_AMOUNT + " INTEGER, "
            + I_E_T_EntryTable.COLUMN_TRANSFER_AMOUNT + " INTEGER, "
            + I_E_T_EntryTable.COLUMN_TO_ACCOUNT + " TEXT, "
            + I_E_T_EntryTable.COLUMN_SAVING + " TEXT "
            + ")";


     */

    //Constructor of the class
    public FinanceDbHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }



    @Override
    public void onCreate(SQLiteDatabase db) {


        // pass the stored query (which is in the string in onCreate method() )
          db.execSQL(CREATE_I_E_T_TABLE);
        db.execSQL(CREATE_ACC_INFO_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + FinanceContract.I_E_T_EntryTable.TABLE_NAME,null);
        return res;
    }
}
