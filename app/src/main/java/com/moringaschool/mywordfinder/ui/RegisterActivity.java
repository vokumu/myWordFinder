package com.moringaschool.mywordfinder.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;
import com.moringaschool.mywordfinder.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
   TextInputLayout fullName,email,phone,password,confirmPassword;
   Button register;

    //Button mregisterUser = findViewById(R.id.register);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register = (Button)findViewById(R.id.register);
        fullName = (TextInputLayout) findViewById(R.id.fullName);
        email = (TextInputLayout) findViewById(R.id.email);
        phone = (TextInputLayout) findViewById(R.id.phone);
        password = (TextInputLayout) findViewById(R.id.password);
        confirmPassword = (TextInputLayout) findViewById(R.id.confirmPassword);
        register.setOnClickListener(this);
    }
    public boolean validateName(){
        String val=fullName.getEditText().getText().toString();
        if(val.isEmpty()){
            fullName.setError("Field cannot be empty");
            return false;
        }
        else{
            fullName.setError(null);
            fullName.setErrorEnabled(false);
            return true;
        }

    }
    public boolean validateEmail(){
        String val=email.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            email.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            email.setError("Invalid email address");
            return false;
        } else {
            email.setError(null);
            email.setErrorEnabled(false);
            return true;
        }

    }
    public boolean validatePhone(){
        String val = phone.getEditText().getText().toString();

        if (val.isEmpty()) {
            phone.setError("Field cannot be empty");
            return false;
        } else {
            phone.setError(null);
            phone.setErrorEnabled(false);
            return true;
        }

    }
    public boolean validatePassword(){
        String val = password.getEditText().getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";

        if (val.isEmpty()) {
            password.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            password.setError("Password is too weak");
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }

    }
    public void registerUser(View view) {
        if(!validateName() |!validatePassword() | !validatePhone() | !validateEmail() | !validateName())
        {
            return;
        }
    }

    @Override
    public void onClick(View v) {
        if (v == register){
            if(!validateName() |!validatePassword() | !validatePhone() | !validateEmail() | !validateName())
            {
                return;
            }
        }
    }

}