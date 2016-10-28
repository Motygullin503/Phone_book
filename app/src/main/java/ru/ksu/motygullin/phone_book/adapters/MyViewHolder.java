package ru.ksu.motygullin.phone_book.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ru.ksu.motygullin.phone_book.R;
import ru.ksu.motygullin.phone_book.User;

public class MyViewHolder extends RecyclerView.ViewHolder {

    private TextView mIdView;
    private TextView mContentView;
    private Context context;

    public MyViewHolder(View view, Context context) {
        super(view);
        this.context = context;
        mIdView = (TextView) view.findViewById(R.id.username);
        mContentView = (TextView) view.findViewById(R.id.number);
    }
    public void bind(User user){
        mIdView.setText(user.getUserName());
        mContentView.setText(String.valueOf(user.getNumber()));
    }

    @Override
    public String toString() {
        return super.toString() + " '" + mContentView.getText() + "'";
    }
}
