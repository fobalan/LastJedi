package br.com.test.lastjedi.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rengwuxian.materialedittext.MaterialEditText;

import br.com.test.lastjedi.R;
import br.com.test.lastjedi.listener.RecyclerViewListener;

/**
 * Created by Samurai on 05/10/2017.
 */

public class PeopleHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final ImageView characterImageView;
    private final TextView nameTextView;
    private final TextView urlTextView;
    private RecyclerViewListener listener;
    public PeopleHolder(View itemView, RecyclerViewListener listener, Context context) {
        super(itemView);
        this.listener = listener;

        characterImageView = (ImageView) itemView.findViewById(R.id.characterImageView);
        nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
        urlTextView = (TextView) itemView.findViewById(R.id.urlTextView);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(listener != null) {
            listener.onViewClick(v, getAdapterPosition());
        }
    }


    public TextView getName() {
        return  nameTextView;
    }

    public TextView getUrl() {
        return urlTextView;
    }

    public ImageView getImage() {
        return characterImageView;
    }
}
