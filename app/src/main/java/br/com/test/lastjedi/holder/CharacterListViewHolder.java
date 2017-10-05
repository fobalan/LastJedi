package br.com.test.lastjedi.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import br.com.test.lastjedi.CharacterList;
import br.com.test.lastjedi.listener.CharacterListViewListener;

/**
 * Created by Samurai on 05/10/2017.
 */

public class CharacterListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private CharacterListViewListener listener;
    public CharacterListViewHolder(View itemView, CharacterListViewListener listener, Context context) {
        super(itemView);
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        listener.onViewClick(v, getAdapterPosition());
    }
}
