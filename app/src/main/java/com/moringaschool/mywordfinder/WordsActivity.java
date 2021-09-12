package com.moringaschool.mywordfinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WordsActivity extends AppCompatActivity {

    @BindView(R.id.text_view_result) TextView mWordTextView;

    private DataMuseApi dataMuseApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_activity);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String word = intent.getStringExtra("word");
        mWordTextView.setText("The word to be searched: " + word);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.datamuse.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        dataMuseApi = retrofit.create(DataMuseApi.class);
        getRymes();
        }
    private void getRymes(){
        Call<List<Ryme>> call = dataMuseApi.getRymes("forgetful");
        call.enqueue(new Callback<List<Ryme>>() {
            @Override
            public void onResponse(Call<List<Ryme>> call, Response<List<Ryme>> response) {
                if (!response.isSuccessful()) {
                    mWordTextView.setText("Code: " + response.code());
                    return;
                }
                List<Ryme> rymes = response.body();
                for (Ryme ryme : rymes) {
                    String content = "";
                    content += "Word: " + ryme.getWord() + "\n";
                    content += "Score: " + ryme.getScore() + "\n";
                    content += "numSyllables: " + ryme.getNumSyllables() + "\n\n";
                    mWordTextView.append(content);
                }
            }
            @Override
            public void onFailure(Call<List<Ryme>> call, Throwable t) {
                mWordTextView.setText(t.getMessage());
            }
        });
    }

    }

