package com.moringaschool.mywordfinder.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moringaschool.mywordfinder.R;
import com.moringaschool.mywordfinder.adapters.FirebaseWordsViewHolder;
import com.moringaschool.mywordfinder.adapters.MyAdapter;
import com.moringaschool.mywordfinder.models.WordHelperClass;

import java.util.ArrayList;

public class SearchHistoryActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FirebaseWordsViewHolder firebaseWordsViewHolder;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    private FirebaseRecyclerOptions<WordHelperClass> options;
    private FirebaseRecyclerAdapter<WordHelperClass, myViewHolder> adapter;
  ArrayList<WordHelperClass> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_history);

        recyclerView=findViewById(R.id.recyclerView);
        rootNode=FirebaseDatabase.getInstance();
        reference = FirebaseDatabase.getInstance().getReference("words");
        //reference=rootNode.getReference("words");
        //recyclerView.setHasFixedSize(true);
       // recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(SearchHistoryActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        list=new ArrayList<>();
        firebaseWordsViewHolder=new FirebaseWordsViewHolder(this,list);
        recyclerView.setAdapter(firebaseWordsViewHolder);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    WordHelperClass wordHelperClass=dataSnapshot.getValue(WordHelperClass.class);
                    list.add(wordHelperClass);
                }
                firebaseWordsViewHolder.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}