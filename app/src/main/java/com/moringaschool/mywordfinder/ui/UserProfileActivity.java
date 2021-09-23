package com.moringaschool.mywordfinder.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.moringaschool.mywordfinder.R;

import butterknife.ButterKnife;

public class UserProfileActivity extends AppCompatActivity  implements View.OnClickListener {
    TextInputLayout fullName,email,phone;
    Button search,history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        //hooks
        fullName=findViewById(R.id.full_name);
        email=findViewById(R.id.email);
        phone=findViewById(R.id.phone);

        search = (Button)findViewById(R.id.search);
        history=(Button)findViewById(R.id.history);
        search.setOnClickListener(this);
        history.setOnClickListener(this);
        
        //show Data
        showAllUSerData();


    }

    private void showAllUSerData() {
        Intent intent=getIntent();
        String user_name=intent.getStringExtra("name");
        String user_email = intent.getStringExtra("email");
        String user_phoneNo = intent.getStringExtra("phone");
        fullName.getEditText().setText(user_name);
        email.getEditText().setText(user_email);
        phone.getEditText().setText(user_phoneNo);
    }

    @Override
    public void onClick(View view) {
        if(view == search){
            Intent intent = new Intent(UserProfileActivity.this, MainActivity.class);
            startActivity(intent);
        }
        if(view==history){
            Intent intent=new Intent(UserProfileActivity.this,SearchHistoryActivity.class);
            startActivity(intent);
        }

    }
}