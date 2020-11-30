package com.example.android.familyfinancemanagement;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> expDate;
    ArrayList<String> expName;
    ArrayList<String> expAmount;


    public ListAdapter(
            Context context2,
            ArrayList<String> exp_Date,
            ArrayList<String> exp_Name,
            ArrayList<String> exp_Amount
    )// parametrised constructor
    {

        this.context = context2;
        this.expDate = exp_Date;
        this.expName = exp_Name;
        this.expAmount = exp_Amount;
    }

    public int getCount() {
        // TODO Auto-generated method stub
        return expDate.size();
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    public View getView(int position, View child, ViewGroup parent) {

        Holder holder;

        LayoutInflater layoutInflater;

        if (child == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            child = layoutInflater.inflate(R.layout.items, null);

            holder = new Holder();

            holder.Exp_Date_TextView = (TextView) child.findViewById(R.id.textViewDate);
            holder.Exp_Name_TextView = (TextView) child.findViewById(R.id.textViewExpNAME);
            holder.Exp_Amount_TextView = (TextView) child.findViewById(R.id.textViewExpAmount);

            child.setTag(holder);

        } else {

            holder = (Holder) child.getTag();
        }
        holder.Exp_Date_TextView.setText(expDate.get(position));
        holder.Exp_Name_TextView.setText(expName.get(position));
        holder.Exp_Amount_TextView.setText(expAmount.get(position));

        return child;
    }

    public class Holder {

        TextView Exp_Date_TextView;
        TextView Exp_Name_TextView;
        TextView Exp_Amount_TextView;
    }

}