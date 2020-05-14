package cn.auto.news;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.auto.news.adapters.NewsListViewAdapter;
import cn.auto.news.beans.NewsBean;
import cn.auto.news.beans.TypeBean;
import cn.auto.weatherforcaststudy.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsInfoFragment extends Fragment {
    private ListView lvMain;
    private List<NewsBean.ResultBean.DataBean> mDatas;
    private NewsListViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_news_info, container, false);
        lvMain=view.findViewById(R.id.news_frag_lv);
        adapter=new NewsListViewAdapter(getActivity(),mDatas);

        Bundle bundle = getArguments();
        TypeBean typeBean=(TypeBean)bundle.getSerializable("type");
        String url = typeBean.getUrl();
        mDatas=new ArrayList<NewsBean.ResultBean.DataBean>();
        lvMain.setAdapter(adapter);
        return view;
    }

}
