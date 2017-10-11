package br.com.test.lastjedi.helper;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import br.com.test.lastjedi.R;

/**
 * Created by ITST on 11/10/2017.
 */

public class FilmsHelper {
    private final RecyclerView filmsRecyclerView;

    public FilmsHelper(View rootView){
        filmsRecyclerView = (RecyclerView)rootView.findViewById(R.id.filmsRecyclerView);
    }

    public RecyclerView getRecyclerView() {
        return filmsRecyclerView;
    }
}
