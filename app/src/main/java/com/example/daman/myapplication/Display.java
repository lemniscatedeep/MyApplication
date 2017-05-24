package com.example.daman.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display);
        String username = getIntent().getStringExtra("Username");
        TextView tv = (TextView)findViewById(R.id.TVusername);
        tv.setText(username);
//spinner for loan
        Spinner LoanSpinner = (Spinner)findViewById(R.id.loanType);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.loan_array,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        LoanSpinner.setAdapter(adapter);

        //loanSpinner = LoanSpinner.getSelectedItem().toString();
//spinner for state
        Spinner StateSpinner = (Spinner)findViewById(R.id.selectState);

        ArrayAdapter<CharSequence> adapter_state = ArrayAdapter.createFromResource(this,R.array.state_array,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        StateSpinner.setAdapter(adapter_state);
//spinner for city
        Spinner CitySpinner = (Spinner)findViewById(R.id.selectCity);

        ArrayAdapter<CharSequence> adapter_city = ArrayAdapter.createFromResource(this,R.array.city_array,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        CitySpinner.setAdapter(adapter_city);
//spinner for tenure_year
        Spinner YearSpinner = (Spinner)findViewById(R.id.Tenure_years);

        ArrayAdapter<CharSequence> adapter_year = ArrayAdapter.createFromResource(this,R.array.tenure_year,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        YearSpinner.setAdapter(adapter_year);
        //final TextView addr = (TextView) findViewById(R.id.textView12);
        //address = (String) addr.getText().toString();

//spinner for month
        Spinner MonthSpinner = (Spinner)findViewById(R.id.Tenure_months);

        ArrayAdapter<CharSequence> adapter_month = ArrayAdapter.createFromResource(this,R.array.tenure_month,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        MonthSpinner.setAdapter(adapter_month);

        rg = (RadioGroup) findViewById(R.id.radioGroup);

    }

    public void rbclick(View v){
        int radiobuttonId = rg.getCheckedRadioButtonId();
        rb = (RadioButton)findViewById(radiobuttonId);
    }

    public void onButtonClick(View v){
        if(v.getId()== R.id.buttonContinue){
            
            Toast toast = Toast.makeText(this, "nothing to do",Toast.LENGTH_SHORT);
            toast.show();
        }
    }

}
