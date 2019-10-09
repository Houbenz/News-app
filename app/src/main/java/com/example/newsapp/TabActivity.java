package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;

public class TabActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_tab);

        ViewPager viewPager=findViewById(R.id.viewPager);
        PageAdapter pageAdapter=new PageAdapter(getSupportFragmentManager(),
                PageAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(pageAdapter);

        TabLayout tabLayout =viewPager.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);




    }
}
