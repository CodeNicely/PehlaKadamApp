package projects.com.codenicely.pehlakadam.home.views;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aman on 16/6/17.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter{

    private List<String> fragmentTitleList = new ArrayList<>();
    private List<Fragment> fragmentList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    private void setData(List<Fragment> fragmentList,List<String> fragmentTitleList){
        this.fragmentList=fragmentList;
        this.fragmentTitleList=fragmentTitleList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentTitleList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitleList.get(position);
    }

}
