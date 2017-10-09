package br.com.test.lastjedi.helper;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import br.com.test.lastjedi.PeopleList;
import br.com.test.lastjedi.R;

/**
 * Created by Samurai on 05/10/2017.
 */

public class PeopleListHelper {

    private final FloatingActionButton fabActionButton;
    private final Toolbar peopleListToolbar;
    private final RecyclerView peopleRecyclerView;
    private PeopleList context;

    public PeopleListHelper(PeopleList context) {
        this.context = context;
        peopleListToolbar = (Toolbar) context.findViewById(R.id.peopleToolbar);
        peopleRecyclerView = (RecyclerView) context.findViewById(R.id.peopleRecyclerView);
        fabActionButton = (FloatingActionButton) context.findViewById(R.id.fabActionButton);

    }

    public Toolbar getToolbar() {
        return peopleListToolbar;
    }

    public RecyclerView getRecyclerView() {
        return peopleRecyclerView;
    }

    public FloatingActionButton getFabActionButton() {
        return fabActionButton;
    }
}
