package rommateapp.development.albie.therommateapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Currency;


public class BillRowAdapter extends BaseAdapter {
    private final Context context;
    private ArrayList<Bill> bills;
    public BillRowAdapter( Context context, ArrayList<Bill> bills) {
        this.context = context;
        this.bills = bills;
    }

    @Override
    public int getCount() {
        return bills.size();
    }

    @Override
    public Object getItem(int pos) {
        return bills.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return 0l;
        //just return 0 if your list items do not have an Id variable.
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.bill_row, parent, false);

        TextView choreNameTv = (TextView) rowView.findViewById(R.id.billName);
        TextView choreAssignedToTv = (TextView) rowView.findViewById(R.id.payTo);
        TextView choreDescTv = (TextView) rowView.findViewById(R.id.amount);
        TextView billTo = (TextView) rowView.findViewById(R.id.userToBill);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);


        // Change the icon for Windows and iPhone
        Bill b = bills.get(position);

        choreAssignedToTv.setText("Pay: "+b.getUserToPay());
        if(b.getDesc().length() > 20){
            choreNameTv.setText(b.getDesc().substring(0, 17)+"...");
        }else{

            choreNameTv.setText(b.getDesc());
        }
        Currency c = Currency.getInstance("USD");

        double cents =  b.getTotalAmount() * 100;
        int dollarAmount =(int) cents /100;
        int centsAmount =(int) cents %100;
        String space="";
        if(centsAmount<10){
            space="0";
        }
        choreDescTv.setText("$"+dollarAmount+ "."+space+centsAmount);
        billTo.setText("User Billed: "+b.getUserToBill());

        final int pos = position;


        //we can create the function we want to attach to the  image here
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Bill b = bills.get(position);

                if(context instanceof  BillsActivity){
                    BillsActivity act = (BillsActivity) context;
                    act.openPaymentDialog(b);
                }else{
                    MainActivity act = (MainActivity) context;
                    act.openPaymentDialog(b);
                }

            }
        });

        return rowView;
    }


} 