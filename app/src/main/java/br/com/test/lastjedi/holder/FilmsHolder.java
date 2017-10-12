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

public class FilmsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final TextView titleFilmTextView;
    private final ImageView imageFilmImageView;
    private final TextView descriptionFilmTextView;
    private RecyclerViewListener listener;
    public FilmsHolder(View itemView, RecyclerViewListener listener, Context context) {
        super(itemView);
        this.listener = listener;

        imageFilmImageView = (ImageView)itemView.findViewById(R.id.imageFilmImageView);
        titleFilmTextView = (TextView) itemView.findViewById(R.id.titleFilmTextView);
        descriptionFilmTextView = (TextView) itemView.findViewById(R.id.descriptionFilmTextView);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(listener != null) {
            listener.onViewClick(v, getAdapterPosition());
        }
    }

    public ImageView getImage() {
        return imageFilmImageView;
    }

    public TextView getTitle() {
        return titleFilmTextView;
    }

    public TextView getDescription(){
        return descriptionFilmTextView;
    }
}
