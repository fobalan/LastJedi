package br.com.test.lastjedi.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

import br.com.test.lastjedi.DetailsActivity;
import br.com.test.lastjedi.R;
import br.com.test.lastjedi.fragment.DetailFragment;
import br.com.test.lastjedi.holder.FilmsHolder;
import br.com.test.lastjedi.listener.RecyclerViewListener;
import br.com.test.lastjedi.model.Films;

/**
 * Created by Samurai on 05/10/2017.
 */

public class FilmsAdapter extends RecyclerView.Adapter<FilmsHolder> {

    private final Context context;
    private final List<Films> list;
    private final int resource;
    private RecyclerViewListener listener;

    public FilmsAdapter(Context context, List<Films> list, int resource) {
        this.context = context;
        this.list = list;
        this.resource = resource;
    }

    @Override
    public FilmsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(resource, parent, false);
        FilmsHolder viewHolder = new FilmsHolder(view, listener, context);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final FilmsHolder holder, final int position) {
        holder.getTitle().setText(list.get(position).getTitle());
        holder.getDescription().setText(list.get(position).getOpeningCrawl());
        Picasso.with(context)
                .load(list.get(position).getImageUrl())
                .placeholder(R.drawable.image_background)
                .into(holder.getImage());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnItemClickListener(RecyclerViewListener listener) {
        this.listener = listener;
    }
}
