package com.moringaschool.mywordfinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WordsActivity extends AppCompatActivity {

    @BindView(R.id.wordTextView) TextView mWordTextView;
    @BindView(R.id.listView) ListView mListView;

    private String[] functions = new String[] {"Get related", "Get word that sounds like", "Get words that starts With", "Get words that rhyme", "Get adjectives describing" };
    private String[] descriptions = new String[] {"words Related to", "words that sound like your word", "words that start with the input", "words that rhyme with", "adjectives that describe our input" };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_activity);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String word = intent.getStringExtra("word");
        mWordTextView.setText("The word to be searched: " + word);
        MyWordsArrayAdapter adapter = new MyWordsArrayAdapter(this, android.R.layout.simple_list_item_1, functions, descriptions);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String restaurant = ((TextView)view).getText().toString();
                Toast.makeText(WordsActivity.this, restaurant, Toast.LENGTH_LONG).show();
            }
        });
        }
    }

