package com.moringaschool.mywordfinder.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.mywordfinder.models.Ryme;
import com.moringaschool.mywordfinder.network.DataMuseApi;
import com.moringaschool.mywordfinder.adapters.MyAdapter;
import com.moringaschool.mywordfinder.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WordsActivity extends AppCompatActivity {

    //@BindView(R.id.text_view_result) TextView mWordTextView;
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    private MyAdapter mAdapter;


    public List<Ryme> rymes;


    private DataMuseApi dataMuseApi;
    private RecyclerView recyclerView;
    private ArrayList<Ryme> rymeList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_activity);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String word = intent.getStringExtra("word");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.datamuse.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        dataMuseApi = retrofit.create(DataMuseApi.class);

        Call<List<Ryme>> call = dataMuseApi.getRymes(word);
        call.enqueue(new Callback<List<Ryme>>() {
            @Override
            public void onResponse(Call<List<Ryme>> call, Response<List<Ryme>> response) {
                hideProgressBar();

                if (response.isSuccessful()) {
                    rymes = response.body();
                    mAdapter = new MyAdapter(WordsActivity.this, rymes);
                    mRecyclerView.setAdapter(mAdapter);
                    RecyclerView.LayoutManager layoutManager =
                            new LinearLayoutManager(WordsActivity.this);
                    mRecyclerView.setLayoutManager(layoutManager);
                    mRecyclerView.setHasFixedSize(true);
                    showRestaurants();
                } else {
                    showUnsuccessfulMessage();
                }
            }

            @Override
            public void onFailure(Call<List<Ryme>> call, Throwable t) {

            }

        });
    }

    private void showFailureMessage() {
        mErrorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showUnsuccessfulMessage() {
        mErrorTextView.setText("Something went wrong. Please try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showRestaurants() {
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    }

