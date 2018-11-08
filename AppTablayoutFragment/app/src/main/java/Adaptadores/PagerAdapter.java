package Adaptadores;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import Fragments.Fragmento1;
import Fragments.Fragmento2;
import Fragments.Fragmento3;


public class PagerAdapter extends FragmentPagerAdapter {

    int numberOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.numberOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Fragmento1 tab1 = new Fragmento1();
                return tab1;
            case 1:
                Fragmento2 tab2 = new Fragmento2();
                return tab2;
            case 2:
                Fragmento3 tab3 = new Fragmento3();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }

}