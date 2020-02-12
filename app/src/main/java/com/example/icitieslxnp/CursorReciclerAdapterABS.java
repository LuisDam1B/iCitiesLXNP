package com.example.icitieslxnp;

import android.database.Cursor;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class CursorReciclerAdapterABS extends RecyclerView.Adapter {

    Cursor mCursor;

    public CursorReciclerAdapterABS(Cursor mCursor) {
        this.mCursor = mCursor;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (mCursor == null){
            throw new IllegalStateException("Erron, Cursor Vacio");
        }

        if (!mCursor.moveToPosition(position)){
            throw new IllegalStateException("Erron, No se piuede encontrar la posicion "+position);
        }else {
            onBindViewHolder(holder,mCursor);
        }

    }

    public abstract void onBindViewHolder(RecyclerView.ViewHolder holder, Cursor cursor);

    @Override
    public long getItemId(int position) {
        if (hasStableIds() && mCursor != null){
            if (mCursor.moveToPosition(position)){
                return mCursor.getLong(mCursor.getColumnIndexOrThrow("_id")); }}
            return RecyclerView.NO_ID;

    }

    @Override
    public int getItemCount() {

        if (mCursor != null){return  mCursor.getCount();}
        else {return 0;}
    }
}
