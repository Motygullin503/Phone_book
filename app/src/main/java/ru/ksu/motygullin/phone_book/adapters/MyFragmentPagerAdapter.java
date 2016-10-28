package ru.ksu.motygullin.phone_book.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import ru.ksu.motygullin.phone_book.PageFragment;

public class MyFragmentPagerAdapter extends FragmentStatePagerAdapter {


    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return PageFragment.newInstance(false);
            case 1:
                return PageFragment.newInstance(true);
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 1:
                return "Контакты";
            case 2:
                return "Удаленные";
        }
        return "";
    }
    public int getItemPosition(Object object) {
        if (object instanceof PageFragment)
            ((PageFragment) object).updateAdapter();
        return super.getItemPosition(object);
    }
}
