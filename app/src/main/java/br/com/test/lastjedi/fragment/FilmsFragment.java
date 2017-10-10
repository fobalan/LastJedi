package br.com.test.lastjedi.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.test.lastjedi.R;

/**
 * Created by Samurai on 10/10/2017.
 */

public class FilmsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_films, container, false);
        return rootView;
    }
}
