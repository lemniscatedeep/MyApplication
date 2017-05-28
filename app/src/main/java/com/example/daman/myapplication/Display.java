package com.example.daman.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Daman on 3/19/2017.
 */

public class Display extends Activity {

    RadioGroup rg;
    RadioButton rb;
    String loanPurpose, stateSpinner, citySpinner, tenureYear, tenureMonth, rbButtonSelected;
    DatabaseHelper helper = new DatabaseHelper(this);

    String name = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display);
        String username = getIntent().getStringExtra("Username");
        TextView tv = (TextView)findViewById(R.id.TVusername);
        tv.setText(username);
        name = username;
//spinner for loan
        Spinner LoanSpinner = (Spinner)findViewById(R.id.loanType);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.loan_array,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        LoanSpinner.setAdapter(adapter);

//        loanPurpose = LoanSpinner.getSelectedItem().toString();
        LoanSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
                loanPurpose = item.toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

//spinner for state
        Spinner StateSpinner = (Spinner)findViewById(R.id.selectState);

        ArrayAdapter<CharSequence> adapter_state = ArrayAdapter.createFromResource(this,R.array.state_array,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        StateSpinner.setAdapter(adapter_state);
        //stateSpinner = StateSpinner.getSelectedItem().toString();
        StateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
                stateSpinner = item.toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
//spinner for city
        Spinner CitySpinner = (Spinner)findViewById(R.id.selectCity);

        ArrayAdapter<CharSequence> adapter_city = ArrayAdapter.createFromResource(this,R.array.city_array,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        CitySpinner.setAdapter(adapter_city);
       // citySpinner = CitySpinner.getSelectedItem().toString();
        CitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
                citySpinner = item.toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
//spinner for tenure_year
        Spinner YearSpinner = (Spinner)findViewById(R.id.Tenure_years);

        ArrayAdapter<CharSequence> adapter_year = ArrayAdapter.createFromResource(this,R.array.tenure_year,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        YearSpinner.setAdapter(adapter_year);
//        tenureYear = YearSpinner.getSelectedItem().toString();
        YearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
                tenureYear = item.toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
//spinner for month
        Spinner MonthSpinner = (Spinner)findViewById(R.id.Tenure_months);

        ArrayAdapter<CharSequence> adapter_month = ArrayAdapter.createFromResource(this,R.array.tenure_month,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        MonthSpinner.setAdapter(adapter_month);
       // tenureMonth = MonthSpinner.getSelectedItem().toString();
        MonthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
                tenureMonth = item.toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        rg = (RadioGroup) findViewById(R.id.radioGroup);

    }

    public void rbclick(View v){
        int radiobuttonId = rg.getCheckedRadioButtonId();
        rb = (RadioButton)findViewById(radiobuttonId);
        rbButtonSelected = rb.getText().toString();
    }

    public void onButtonClick(View v){
        if(v.getId()== R.id.buttonContinue){
            EditText amount = (EditText) findViewById(R.id.editText_amount);
            EditText address = (EditText) findViewById(R.id.address);
            EditText pincode = (EditText) findViewById(R.id.editText_pincode);
            String amountStr = amount.getText().toString();
            String addressStr = address.getText().toString();
            String pincodeStr = pincode.getText().toString();
            String completeAdd = " ";
            completeAdd = addressStr+" " +citySpinner + " "+ stateSpinner +" "+ pincodeStr;
            String completeTenure = " ";
            completeTenure = tenureYear + " " + tenureMonth;

            Contact c = new Contact();
            c.setName(name);
            c.setLoan(loanPurpose);
            c.setAmount(amountStr);
            c.setTenure(completeTenure);
            c.setResidencetype(rbButtonSelected);
            c.setAddress(completeAdd);

            helper.insertApplicant(c);
            Toast toast = Toast.makeText(this, "data collected successfully", Toast.LENGTH_SHORT);
            toast.show();
            Intent intent = new Intent(Display.this, DetailsScreen.class);
            intent.putExtra("Purpose", loanPurpose);
            intent.putExtra("address", completeAdd);
            intent.putExtra("amount", amountStr);
            intent.putExtra("tenure",completeTenure);
            intent.putExtra("residence",rbButtonSelected);
            startActivity(intent);
        }
    }

}
