package br.com.test.lastjedi.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import br.com.test.lastjedi.fragment.DetailFragment;
import br.com.test.lastjedi.fragment.FilmsFragment;

/**
 * Created by Samurai on 10/10/2017.
 */

public class DetailsPagerAdapter extends FragmentPagerAdapter {
    public DetailsPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
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
