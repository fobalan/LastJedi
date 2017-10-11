package br.com.test.lastjedi.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import br.com.test.lastjedi.fragment.DetailFragment;
import br.com.test.lastjedi.fragment.FilmsFragment;
import br.com.test.lastjedi.model.People;

/**
 * Created by Samurai on 10/10/2017.
 */

public class DetailsPagerAdapter extends FragmentPagerAdapter {

    private People people;
    public DetailsPagerAdapter(FragmentManager fragmentManager, People people) {
        super(fragmentManager);
        this.people = people;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new Fragment();
        switch (position){
            case 0:
                fragment = new DetailFragment();
                break;
            case 1:
                fragment = new FilmsFragment();
                break;
        }
        Bundle bundle = new Bundle();
        bundle.putSerializable("people",people);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Detalhes";
            case 1:
                return "Filmes";
        }
        return null;
    }
}
