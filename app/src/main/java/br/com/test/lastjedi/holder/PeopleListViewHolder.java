package br.com.test.lastjedi.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.com.test.lastjedi.R;
import br.com.test.lastjedi.listener.PeopleListViewListener;

/**
 * Created by Samurai on 05/10/2017.
 */

public class PeopleListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final TextView nameTextView;
    private final TextView urlTextView;
    private PeopleListViewListener listener;
    public PeopleListViewHolder(View itemView, PeopleListViewListener listener, Context context) {
        super(itemView);
        this.listener = listener;

        nameTextView = (TextView)itemView.findViewById(R.id.nameTextView);
        urlTextView = (TextView) itemView.findViewById(R.id.urlTextView);
    }

    @Override
    public void onClick(View v) {
        listener.onViewClick(v, getAdapterPosition());
    }

    public TextView getName() {
        return nameTextView;
    }

    public TextView getUrl() {
        return urlTextView;
    }
}
