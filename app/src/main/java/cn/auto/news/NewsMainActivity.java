package cn.auto.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import cn.auto.news.adapters.NewsInfoAdapter;
import cn.auto.news.beans.TypeBean;
import cn.auto.news.views.PagerSlidingTabStrip;
import cn.auto.weatherforcaststudy.R;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class NewsMainActivity extends AppCompatActivity implements View.OnClickListener {
private ViewPager vpMain;
private PagerSlidingTabStrip tabStrip;
private ImageView ivAdd;
private List<TypeBean> selectTypeList;//所选中的类型的集合
    private List<Fragment> fragmentList;
    private NewsInfoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_main);
        initViews();
        initPagers();
    }

    private void initViews() {
        vpMain=findViewById(R.id.news_main_vp);
        tabStrip=findViewById(R.id.news_main_tabstrip);
        ivAdd=findViewById(R.id.news_main_iv_add);
        ivAdd.setOnClickListener(this);
        fragmentList=new ArrayList<>();
        selectTypeList=new ArrayList<>();
        adapter=new NewsInfoAdapter(getSupportFragmentManager(),this,fragmentList,selectTypeList);
    vpMain.setAdapter(adapter);//ViewPager设置适配器
    tabStrip.setViewPager(vpMain);//ViewPager与PagerSlidingTabStrip关联
    }
/*
初始化页面的函数
 */
    private void initPagers() {
        List<TypeBean> typeList=TypeBean.getTypeList();
        selectTypeList.addAll(typeList);
        for(int i=0;i<selectTypeList.size();i++){
            TypeBean typeBean = selectTypeList.get(i);
            NewsInfoFragment infoFragment=new NewsInfoFragment();
//            想Fragment当中传递数据
            Bundle bundle=new Bundle();
            bundle.putSerializable("type",typeBean);
            infoFragment.setArguments(bundle);
            fragmentList.add(infoFragment);
        }

    }

    @Override
    public void onClick(View v) {

    }
}
