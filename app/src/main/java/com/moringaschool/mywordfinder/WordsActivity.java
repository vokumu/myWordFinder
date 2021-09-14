package com.moringaschool.mywordfinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

   // @BindView(R.id.text_view_result) TextView mWordTextView;
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
        String location = intent.getStringExtra("word");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.datamuse.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        dataMuseApi = retrofit.create(DataMuseApi.class);

        Call<List<Ryme>> call = dataMuseApi.getRymes("forgetful");
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

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.words_activity);
//        ButterKnife.bind(this);
//        Intent intent = getIntent();
//        String word = intent.getStringExtra("word");
//        mWordTextView.setText("The word to be searched: " + word);
//        recyclerView=findViewById(R.id.recyclerView);
//
//        rymeList=new ArrayList<>();
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://api.datamuse.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        dataMuseApi = retrofit.create(DataMuseApi.class);
//        getRymes();
//        }
//    private void getRymes(){
//        Call<List<Ryme>> call = dataMuseApi.getRymes("forgetful");
//        call.enqueue(new Callback<List<Ryme>>() {
//            @Override
//            public void onResponse(Call<List<Ryme>> call, Response<List<Ryme>> response) {
//                if (!response.isSuccessful()) {
//
//                    mWordTextView.setText("Code: " + response.code());
//                    return;
//                }
//                List<Ryme> rymes = response.body();
//                rymeList.add(new Ryme("ear",10,11));
//                MyAdapter adapter=new MyAdapter(rymes);
//                RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
//                recyclerView.setLayoutManager(layoutManager);
//                recyclerView.setItemAnimator(new DefaultItemAnimator());
//                recyclerView.setAdapter(adapter);
////                for (Ryme ryme : rymes) {
////                    String content = "";
////                    content += "\nWord: " + ryme.getWord() + "\n";
////                    content += "Score: " + ryme.getScore() + "\n";
////                    content += "numSyllables: " + ryme.getNumSyllables() + "\n\n";
////                    mWordTextView.append(content);
////                }
//            }
//            @Override
//            public void onFailure(Call<List<Ryme>> call, Throwable t) {
//                mWordTextView.setText(t.getMessage());
//            }
//        });
//    }

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

