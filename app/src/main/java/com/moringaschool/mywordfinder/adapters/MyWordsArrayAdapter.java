package com.moringaschool.mywordfinder.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

public class MyWordsArrayAdapter extends ArrayAdapter{

    private Context mContext;
    private String[] mFunctions;
    private String[] mDescriptions;

    public MyWordsArrayAdapter(Context mContext, int resource, String[] mFunctions, String[] mDescriptions) {
        super(mContext, resource);
        this.mContext = mContext;
        this.mFunctions = mFunctions;
        this.mDescriptions = mDescriptions;
    }
    @Override
    public Object getItem(int position) {
        String function = mFunctions[position];
        String description = mDescriptions[position];
        return String.format(function, description);
    }

    @Override
    public int getCount() {
        return mFunctions.length;
    }
}
