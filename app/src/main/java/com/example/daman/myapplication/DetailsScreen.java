package com.example.daman.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


public class DetailsScreen extends Activity {

//    DatabaseHelper helper = new DatabaseHelper(this);
//    String array[] = new String[6];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailsofapplicant);
        String purpose = getIntent().getStringExtra("Purpose");
        String amount = getIntent().getStringExtra("amount");
        String address = getIntent().getStringExtra("address");
        String tenure = getIntent().getStringExtra("tenure");
        String residence = getIntent().getStringExtra("residence");

        TextView TFloan = (TextView)findViewById(R.id.textView15);
        TextView TFamount = (TextView)findViewById(R.id.textView16);
        TextView TFaddress = (TextView)findViewById(R.id.textView19);
        TextView TFtenure = (TextView)findViewById(R.id.textView17);
        TextView TFresidence = (TextView)findViewById(R.id.textView18);

        TFloan.setText(purpose);
        TFamount.setText(amount);
        TFaddress.setText(address);
        TFtenure.setText(tenure);
        TFresidence.setText(residence);

    }
}