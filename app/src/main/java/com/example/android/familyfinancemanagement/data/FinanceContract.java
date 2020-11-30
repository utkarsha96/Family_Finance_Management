package com.example.android.familyfinancemanagement.data;

public class FinanceContract {

    //Constructor
    FinanceContract(){}
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

    public static final class I_E_T_EntryTable
    {
        //store colmn names in sring
        public static final String TABLE_NAME = "ietTable";
        public static String _ID = "_ID";
        public static String COLUMN_INC_EXP_NAME = "expenseName";
        public static String COLUMN_EXPENSE_AMOUNT = "expenseAmount";
        public static String COLUMN_INCOME_AMOUNT = "incomeAmount";
        public static String COLUMN_TRANSFER_AMOUNT = "transferAmount";
        public static String COLUMN_DATE = "currentDate";
        public static String COLUMN_CURRENT_BALANCE = "currentBalance";
        public static String COLUMN_FROM_ACCOUNT = "fromAccount";
        public static String COLUMN_TO_ACCOUNT = "toAccount";
        public static String COLUMN_SAVING = "Savings";

        // Account/Cards
        public static final String TABLE1_NAME = "AccInfo";
        public static String ACC_ID = "Acc_Id";
        public static String COLUMN_ACC_NAME = "AccountName";
        public static String COLUMN_ACC_TYPE = "AccountType";
        public static String COLUMN_CURR_BAL = "AccountCurrentBalance";
        //Account final data
        public static final String TABLE2_NAME = "AccCBInfo";
        //public static String COLUMN_ACC_ID = "Acc_Id";
        //public static String COLUMN_ACC_NAME = "AccountName";
        //public static String COLUMN_ACC_NO = "AccountNo";
        //public static String COLUMN_CURR_BAL = "AccountCurrentBalance";

        public static final String EXPENSES_UNKNOWN = "UNKNOWN";
        public static final String EXPENSES_GROCERY = "Grocery";
        public static final String EXPENSES_HOUSE_MAINTENANCE = "House Maintenance";
        public static final String EXPENSES_FUEL = "Fuel";
        public static final String EXPENSES_ELECTRICITY_BILL = "Electricity Bill";
        public static final String EXPENSES_TAX = "Tax";
        public static final String EXPENSES_ENTERTAINMENT = "Entertainment";
        public static final String EXPENSES_TRAVEL = "Travel";
        public static final String EXPENSES_MEDICAL = "Medical";

        public static final String BANK_ACCOUNT = "Bank Account";
        public static final String DEBIT_CARD = "Debit Card";
        public static final String CREDIT_CARD = "Credit Card";
    }

}
