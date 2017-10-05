package br.com.test.lastjedi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.test.lastjedi.holder.CharacterListViewHolder;
import br.com.test.lastjedi.listener.CharacterListViewListener;

import static android.R.attr.resource;

/**
 * Created by Samurai on 05/10/2017.
 */

public class CharacterListAdapter extends RecyclerView.Adapter<CharacterListViewHolder> {

    private final Context context;
    private final List list;
    private final int resource;
    private CharacterListViewListener listener;

    public CharacterListAdapter(Context context, List list, int resource){
        this.context = context;
        this.list = list;
        this.resource = resource;
    }
    @Override
    public CharacterListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(resource, parent, false);
        CharacterListViewHolder viewHolder = new CharacterListViewHolder(view,listener,context);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CharacterListViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnItemClickListener(CharacterListViewListener listener) {
        this.listener = listener;
    }
}
