package com.moringaschool.mywordfinder;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WordsActivity extends AppCompatActivity {
    @BindView(R.id.wordEditText)
    TextView mWordTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_activity);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String word = intent.getStringExtra("word");
       // mLocationTextView.setText("Here are all the restaurants near: " + location);
    }
}
