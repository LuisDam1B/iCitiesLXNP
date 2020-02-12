package com.example.icitieslxnp;

import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyRecyclerAdapter extends CursorReciclerAdapterABS {

    private int mLayout;
    private int[] mFrom;
    private int[] mTo;

    public MyRecyclerAdapter(Cursor mCursor, int mLayout, String[] from, int[] mTo) {
        super(mCursor);
        this.mLayout = mLayout;
        this.mTo = mTo;
        findColumns(mCursor, from);
    }

    private void findColumns(Cursor mCursor, String[] from) {
        if (mCursor != null){
            int i;
            int count = from.length;
            if (mFrom == null || mFrom.length != count){
                mFrom = new int[count];
            }
            for (i = 0; i < count ; i++){
                mFrom[i] = mCursor.getColumnIndexOrThrow(from[i]);
            }
        }else {
            mFrom = null;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, Cursor cursor) {

        ((SimpleViewHolder)holder).bind(0,cursor.getString(mFrom[0]));
        ((SimpleViewHolder)holder).bind(1,cursor.getString(mFrom[1]));


    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(mLayout,parent,false);

        return new SimpleViewHolder(view,mTo);
    }
}
