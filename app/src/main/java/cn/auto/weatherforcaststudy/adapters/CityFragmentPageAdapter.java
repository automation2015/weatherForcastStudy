package cn.auto.weatherforcaststudy.adapters;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class CityFragmentPageAdapter extends FragmentStatePagerAdapter {
private List<Fragment> fragmentList;
private int childCount;
    public CityFragmentPageAdapter( FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList=fragmentList;
    }
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public void notifyDataSetChanged() {
        this.childCount=getCount();
        super.notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        if(childCount>0){
            childCount--;
            return  POSITION_NONE;
        }
        return super.getItemPosition(object);
    }
}

