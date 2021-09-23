package com.moringaschool.mywordfinder.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.mywordfinder.R;
import com.moringaschool.mywordfinder.models.WordHelperClass;

import java.util.ArrayList;

public class FirebaseWordsViewHolder  extends RecyclerView.Adapter<FirebaseWordsViewHolder.MyViewHolder>  {
  Context context;
  ArrayList<WordHelperClass> list;

    public FirebaseWordsViewHolder(Context context, ArrayList<WordHelperClass> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v= LayoutInflater.from(context).inflate(R.layout.single_view_layout,parent,false);
       return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    WordHelperClass wordHelperClass=list.get(position);
    holder.phrase.setText(wordHelperClass.getPhrase());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  static class MyViewHolder extends RecyclerView.ViewHolder{
TextView phrase;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            phrase=itemView.findViewById(R.id.searchHistoryPhrase);

        }
    }
}
