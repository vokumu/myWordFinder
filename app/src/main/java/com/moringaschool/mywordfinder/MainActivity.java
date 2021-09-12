package com.moringaschool.mywordfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.common.collect.Range;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {
    @BindView(R.id.findWord)
    Button mFindWord;
    @BindView(R.id.wordEditText)
    EditText mWordEditText;
    @BindView(R.id.appNameTextView)
    TextView mAppNameTextView;




    private AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this, R.id.wordEditText, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.worderror);
        mFindWord.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == mFindWord) {
            if (awesomeValidation.validate()) {
                Toast.makeText(this, "Validation Successfull", Toast.LENGTH_LONG).show();
                String word = mWordEditText.getText().toString();
                Intent intent = new Intent(MainActivity.this, WordsActivity.class);
                intent.putExtra("word", word);
                startActivity(intent);
            }
        }
    }
}