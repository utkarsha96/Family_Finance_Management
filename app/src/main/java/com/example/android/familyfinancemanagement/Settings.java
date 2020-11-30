package com.example.android.familyfinancemanagement;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android.familyfinancemanagement.data.FinanceContract;
import com.example.android.familyfinancemanagement.data.FinanceDbHelper;


public class Settings extends AppCompatActivity implements dialog.dialogListner {

    public TextView accountName;
    public TextView accountDescription;
    public Button createAcc;

    private final FinanceDbHelper mFinanceHelper = new FinanceDbHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        accountName = (TextView) findViewById(R.id.accountName);
        accountDescription = (TextView) findViewById(R.id.accountDescription);

        //----------button create account --------------------------------
        createAcc = (Button) findViewById(R.id.createAcc);
        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openDialog();

            }
        });

    }

    public void openDialog(){
        dialog Dialog = new dialog();
        Dialog.show(getSupportFragmentManager(), "Example Dialog");
    }

    @Override
    public void applyTexts(String name, String description) {
        accountName.setText(name);
        accountDescription.setText(description);
    }
}