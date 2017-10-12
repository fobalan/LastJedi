package br.com.test.lastjedi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.test.lastjedi.holder.PeopleHolder;
import br.com.test.lastjedi.listener.RecyclerViewListener;
import br.com.test.lastjedi.model.People;

/**
 * Created by Samurai on 05/10/2017.
 */

public class PeopleListAdapter extends RecyclerView.Adapter<PeopleHolder> {

    private final Context context;
    private final List<People> list;
    private final int resource;
    private RecyclerViewListener listener;

    public PeopleListAdapter(Context context, List<People> list, int resource){
        this.context = context;
        this.list = list;
        this.resource = resource;
    }
    @Override
    public PeopleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(resource, parent, false);
        PeopleHolder viewHolder = new PeopleHolder(view,listener,context);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PeopleHolder holder, int position) {
        holder.getName().setText(list.get(position).getName());
        holder.getUrl().setText(list.get(position).getUrl());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnItemClickListener(RecyclerViewListener listener) {
        this.listener = listener;
    }
}
