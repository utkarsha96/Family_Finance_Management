package com.example.android.familyfinancemanagement;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.android.familyfinancemanagement.data.FinanceContract.I_E_T_EntryTable;
import com.example.android.familyfinancemanagement.data.FinanceDbHelper;

public class dialog extends AppCompatDialogFragment {
    public EditText AccountName;
    public EditText AccountDescription;
    public dialogListner listner;
    private Spinner accTypeSpinner;
    private String selectAccType;

    private final FinanceDbHelper mFinanceHelper = new FinanceDbHelper(this);

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog, null);

        accTypeSpinner = (Spinner) findViewById(R.id.edit_expense_name);
        setupSpinner();



        builder.setView(view)
                .setTitle("New Account")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = AccountName.getText().toString();
                        String description = AccountDescription.getText().toString();
                        listner.applyTexts(name, description);
                    }
                });

        AccountName = view.findViewById(R.id.AccountName);

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listner = (dialogListner) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement dialogListner");
        }
    }

    public interface dialogListner{
        void applyTexts(String name, String description);
    }
    
    //---------------------------------------------
    private void setupSpinner()
    {

        ArrayAdapter accTypeSpinnerAdapter = ArrayAdapter.createFromResource(this , R.array.account_type ,
                R.layout.support_simple_spinner_dropdown_item);

        accTypeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);


        accTypeSpinner.setAdapter(accTypeSpinnerAdapter);

        // Set the integer mSelected to the constant values
        accTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);

                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.bank_account))) {
                        selectAccType = I_E_T_EntryTable.BANK_ACCOUNT;

                    }
                    else if (selection.equals(getString(R.string.debit_card))) {
                        selectAccType = I_E_T_EntryTable.DEBIT_CARD;

                    }
                    else if (selection.equals(getString(R.string.credit_card))) {
                        selectAccType = I_E_T_EntryTable.CREDIT_CARD;
                    }

                    else {
                        selectAccType = I_E_T_EntryTable.EXPENSES_UNKNOWN;
                    }


                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                selectAccType = I_E_T_EntryTable.EXPENSES_UNKNOWN;
            }
        });

    }
}//end of class