package cn.auto.news.adapters;

import android.content.Context;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import cn.auto.news.beans.TypeBean;

/**
 * FragmentStatePagerAdapter:多个页面跳转，返回主页面时会刷新数据的情况选择
 * FragmentPagerAdapter:多个页面跳转，返回主页面时会不改变数据的情况选择
 */
public class NewsInfoAdapter extends FragmentStatePagerAdapter {
    private Context context;
    private List<Fragment> fragmentList;
    private List<TypeBean> typeBeanList;
    public NewsInfoAdapter(@NonNull FragmentManager fm, Context context,List<Fragment> fragmentList,List<TypeBean> typeBeanList) {
        super(fm);
        this.context=context;
        this.fragmentList=fragmentList;
        this.typeBeanList=typeBeanList;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    /**
     * 返回指定位置的标题
     * @param position
     * @return
     */
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        TypeBean typeBean = typeBeanList.get(position);
        String title = typeBean.getTitle();
        return title;
    }
}
