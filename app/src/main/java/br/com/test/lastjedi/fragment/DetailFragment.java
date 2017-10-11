package br.com.test.lastjedi.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rengwuxian.materialedittext.MaterialEditText;

import br.com.test.lastjedi.R;
import br.com.test.lastjedi.helper.DetailHelper;
import br.com.test.lastjedi.model.People;

/**
 * Created by Samurai on 10/10/2017.
 */

public class DetailFragment extends Fragment {

    private DetailHelper helper;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_details, container, false);
        People people = (People) getArguments().getSerializable("people");
        helper = new DetailHelper(rootView);
        helper.onPopulateFields(people);
        return rootView;
    }
}
