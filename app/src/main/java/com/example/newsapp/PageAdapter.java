package com.example.newsapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PageAdapter extends FragmentStatePagerAdapter {

    public PageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {



        String TopHeadLines_URL="https://newsapi.org/v2/top-headlines?country=us&apiKey=63a7ebe91e2e414e808de3968a9035fc";

        String BBCNEWS_URL="https://newsapi.org/v2/top-headlines?sources=bbc-news&apiKey=63a7ebe91e2e414e808de3968a9035fc";

        String Apple_URL="https://newsapi.org/v2/everything?q=Apple&from=2019-10-09&sortBy=popularity&apiKey=63a7ebe91e2e414e808de3968a9035fc";

        switch (position){
            case 0: return new TopHeadlinesFragment(TopHeadLines_URL);
            case 1: return new TopHeadlinesFragment(BBCNEWS_URL);
            case 2: return new TopHeadlinesFragment(Apple_URL);
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position){
            case 0:return "Top Headlines";
            case 1:return "BBC News";
            case 2:return "Apple";
            default: return null;
        }
    }
}
