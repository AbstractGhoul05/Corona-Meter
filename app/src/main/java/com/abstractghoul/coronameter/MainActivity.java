package com.abstractghoul.coronameter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import android.view.LayoutInflater;

import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    PageAdapter pageAdapter;
    TabItem tabStats;
    TabItem tabInfo;
    TabItem tabCountries;
    TabItem tabSymptoms;
    TabItem tabNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.myToolbar);
        TextView mTitle = toolbar.findViewById(R.id.toolbar_title);

        setSupportActionBar(toolbar);
        /*mTitle.setText(toolbar.getTitle());*/

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        tabLayout = findViewById(R.id.myTabLayout);
        tabStats = findViewById(R.id.tabStats);
        tabInfo = findViewById(R.id.tabInfo);
        tabCountries = findViewById(R.id.tabCountries);
        tabSymptoms = findViewById(R.id.tabSymptoms);
        tabNews = findViewById(R.id.tabNews);
        viewPager = findViewById(R.id.viewPager);
//        tabLayout.setupWithViewPager(viewPager);

        pageAdapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }
}
