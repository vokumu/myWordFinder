package com.moringaschool.mywordfinder;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WordsActivity extends AppCompatActivity {

    @BindView(R.id.wordTextView) TextView mWordTextView;
    @BindView(R.id.listView) ListView mListView;

    private String[] functions = new String[] {"Related", "Sounds like", "Starts With", "rhyme", "Adjectives describing" };
    private String[] descriptions = new String[] {"words Related to", "words that sound like your word", "words that start with the input", "words that rhyme with", "adjectives that describe our input" };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_activity);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String word = intent.getStringExtra("word");
        mWordTextView.setText("The word to be searched: " + word);
    }
}
