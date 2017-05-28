package com.example.daman.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Daman on 3/25/2017.
 */

public class SignUp extends Activity {

    DatabaseHelper helper = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
    }

    public void onSignUpClick(View v){
        if(v.getId() == R.id.Bsignup){
            EditText name = (EditText) findViewById(R.id.TFname);
            EditText email = (EditText)findViewById(R.id.TFemail);
            EditText username = (EditText)findViewById(R.id.TFuname);
            EditText pass1 = (EditText)findViewById(R.id.TFpass1);
            EditText pass2 = (EditText)findViewById(R.id.TFpass2);

            String nameStr = name.getText().toString();
            String emailStr = email.getText().toString();
            String unameStr = username.getText().toString();
            String pass1Str = pass1.getText().toString();
            String pass2Str = pass2.getText().toString();

            if(!pass1Str.equals(pass2Str)){
                Toast.makeText(this, "Password is incorrect", Toast.LENGTH_SHORT).show();
            }
            if(pass1Str.equals(pass2Str)){
                Contact c = new Contact();
                c.setName(nameStr);
                c.setEmail(emailStr);
                c.setUname(unameStr);
                c.setPass(pass1Str);

                helper.insertContact(c);
                Intent i = new Intent(this, MainActivity.class);
                i.putExtra("Name",nameStr);
                i.putExtra("Email",emailStr);
                startActivity(i);
            }
        }
    }
}
