package com.moringaschool.mywordfinder.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.moringaschool.mywordfinder.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    TextInputLayout phone,password;
    Button login;
    @BindView(R.id.signup)
    Button mSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        login = (Button)findViewById(R.id.login);
        phone = (TextInputLayout) findViewById(R.id.phone);
        password = (TextInputLayout) findViewById(R.id.password);
        ButterKnife.bind(this);
        mSignUp.setOnClickListener(this);
        login.setOnClickListener(this);

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

        if (val.isEmpty()) {
            password.setError("Field cannot be empty");
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }

    }
    @Override
    public void onClick(View v) {
        if (v == mSignUp){
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        if(v==login){
            if (!validatePassword() | !validatePhone()) {
                return;
            }
            else{
                isUser();
            }


        }
        }

    private void isUser() {
        String userEnteredPhone=phone.getEditText().getText().toString().trim();
        String userEnteredPassword=password.getEditText().getText().toString().trim();
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("users");
        Query checkUser=reference.orderByChild("phone").equalTo(userEnteredPhone);
       checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
         @Override
       public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){
                    phone.setError(null);
                    phone.setErrorEnabled(false);
                    //String passwordFromDB=snapshot.child(userEnteredEmail).child("password").getValue(String.class);
                    String passwordFromDB = snapshot.child(userEnteredPhone).child("password").getValue(String.class);
                    if(passwordFromDB.equals(userEnteredPassword)){
                        password.setError(null);
                        password.setErrorEnabled(false);
                        String emailFromDB=snapshot.child(userEnteredPhone).child("email").getValue(String.class);
                        String nameFromDB=snapshot.child(userEnteredPhone).child("name").getValue(String.class);
                        String phoneFromDB=snapshot.child(userEnteredPhone).child("phone").getValue(String.class);
                        Intent intent =new Intent(getApplicationContext(),UserProfileActivity.class);
                        intent.putExtra("name",nameFromDB);
                        intent.putExtra("phone",phoneFromDB);
                        intent.putExtra("password",passwordFromDB);
                        intent.putExtra("email",emailFromDB);
                        startActivity(intent);

                    }
                    else{
                        password.setError("Wrong Password");
                        password.requestFocus();
                    }
                }
                else {
                    phone.setError("No such User Exists");
                    phone.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}
