package br.com.test.lastjedi.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.rengwuxian.materialedittext.MaterialEditText;

import br.com.test.lastjedi.R;
import br.com.test.lastjedi.listener.RecyclerViewListener;

/**
 * Created by Samurai on 05/10/2017.
 */

public class PeopleListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final MaterialEditText nameEditText;
    private final MaterialEditText urlEditText;
    private RecyclerViewListener listener;
    public PeopleListViewHolder(View itemView, RecyclerViewListener listener, Context context) {
        super(itemView);
        this.listener = listener;

        nameEditText = (MaterialEditText)itemView.findViewById(R.id.nameEditText);
        urlEditText = (MaterialEditText) itemView.findViewById(R.id.urlEditText);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(listener != null) {
            listener.onViewClick(v, getAdapterPosition());
        }
    }

    public MaterialEditText getName() {
        return nameEditText;
    }

    public MaterialEditText getUrl() {
        return urlEditText;
    }
}
