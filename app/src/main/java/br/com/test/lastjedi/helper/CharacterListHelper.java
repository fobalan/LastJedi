package br.com.test.lastjedi.helper;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import br.com.test.lastjedi.CharacterList;
import br.com.test.lastjedi.R;
import br.com.test.lastjedi.adapter.CharacterListAdapter;

/**
 * Created by Samurai on 05/10/2017.
 */

public class CharacterListHelper {

    private Toolbar characterListToolbar;
    private RecyclerView characterRecyclerView;
    private CharacterList context;
    private LinearLayoutManager layoutManager;
    private CharacterListAdapter adapter;

    public CharacterListHelper(CharacterList context) {
        this.context = context;
        characterListToolbar = (Toolbar) context.findViewById(R.id.characterRecyclerView);
        characterRecyclerView = (RecyclerView) context.findViewById(R.id.characterRecyclerView);

        onActionBar(characterListToolbar);
        onConfigureRyclerView(characterRecyclerView);
    }

    public Toolbar getToolbar() {
        return characterListToolbar;
    }

    public RecyclerView getRecyclerView() {
        return characterRecyclerView;
    }

    private void onActionBar(Toolbar toolbar) {
        context.setSupportActionBar(toolbar);
    }

    private void onConfigureRyclerView(RecyclerView recyclerView) {
        layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        adapter = new CharacterListAdapter();
    }


}
