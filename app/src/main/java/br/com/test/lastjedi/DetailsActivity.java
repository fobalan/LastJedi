package br.com.test.lastjedi;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import br.com.test.lastjedi.adapter.DetailsPagerAdapter;

public class DetailsActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private DetailsPagerAdapter detailsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager detailViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.detailToolbar);
        setSupportActionBar(toolbar);
        detailsPagerAdapter = new DetailsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        detailViewPager = (ViewPager) findViewById(R.id.detailViewPager);
        detailViewPager.setAdapter(detailsPagerAdapter);

        TabLayout detailTabLayout = (TabLayout) findViewById(R.id.detailTabLayout);
        detailTabLayout.setupWithViewPager(detailViewPager);
    }
}
