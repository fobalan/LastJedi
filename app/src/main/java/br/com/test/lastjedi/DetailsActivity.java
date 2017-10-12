package br.com.test.lastjedi;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.MenuItem;

import br.com.test.lastjedi.adapter.DetailsPagerAdapter;
import br.com.test.lastjedi.model.People;

public class DetailsActivity extends AppCompatActivity {


    private DetailsPagerAdapter detailsPagerAdapter;

    private ViewPager detailViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        People people = (People) intent.getSerializableExtra("people");

        Toolbar toolbar = (Toolbar) findViewById(R.id.detailToolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(people.getName());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        detailsPagerAdapter = new DetailsPagerAdapter(getSupportFragmentManager(),people);

        // Set up the ViewPager with the sections adapter.
        detailViewPager = (ViewPager) findViewById(R.id.detailViewPager);
        detailViewPager.setAdapter(detailsPagerAdapter);

        TabLayout detailTabLayout = (TabLayout) findViewById(R.id.detailTabLayout);
        detailTabLayout.setupWithViewPager(detailViewPager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
