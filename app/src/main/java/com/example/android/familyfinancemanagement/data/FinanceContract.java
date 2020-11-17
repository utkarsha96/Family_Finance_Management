package com.example.android.familyfinancemanagement.data;

public class FinanceContract {

    //Constructor
    FinanceContract(){}

    public static final class IncomeEntry
    {
        //Database for income
        public static final String TABLE1_NAME = "Income";

        public static String _ID = "_ID";
        public static String COLUMN_INCOME_AMOUNT = "incomeAmount";




    }

    public static final class ExpenseEntry
    {
        public static final String TABLE2_NAME = "expense";
        public static String _ID = "_ID";
        public static String COLUMN_EXPENSE_NAME = "expenseName";
        public static String COLUMN_EXPENSE_AMOUNT = "expenseAmount";
    }

}
