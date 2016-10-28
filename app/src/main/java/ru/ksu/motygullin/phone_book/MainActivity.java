package ru.ksu.motygullin.phone_book;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.ksu.motygullin.phone_book.adapters.MyFragmentPagerAdapter;
import ru.ksu.motygullin.phone_book.adapters.MyItemRecyclerViewAdapter;

public class MainActivity extends FragmentActivity  {

    private ViewPager pager;
    private FragmentStatePagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);
    }

    public FragmentStatePagerAdapter getPagerAdapter(){
        return pagerAdapter;
    }


}
