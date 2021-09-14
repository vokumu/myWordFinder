package com.moringaschool.mywordfinder.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.mywordfinder.R;
import com.moringaschool.mywordfinder.models.Ryme;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<Ryme> mRymes;
    private Context mContext;

    public MyAdapter(Context context, List<Ryme> rymes) {
        mContext = context;
        mRymes = rymes;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyAdapter.MyViewHolder holder, int position) {
        holder.bindRyme(mRymes.get(position));
    }

    @Override
    public int getItemCount() {
        return mRymes.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        //@BindView(R.id.wordTextView) ImageView mRestaurantImageView;
        @BindView(R.id.wordTextView) TextView mWordTextView;
        @BindView(R.id.scoreTextView) TextView mScoreTextView;
        @BindView(R.id.numSyllablesTextView) TextView mnumSyllablesTextView;

        private Context mContext;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindRyme(Ryme rymes) {
            mWordTextView.setText(rymes.getWord());
            String scoreString=String.valueOf(rymes.getScore());
            mScoreTextView.setText(scoreString);
            String numSyllablesString=String.valueOf(rymes.getNumSyllables());
            mnumSyllablesTextView.setText(numSyllablesString);
        }
    }

}
