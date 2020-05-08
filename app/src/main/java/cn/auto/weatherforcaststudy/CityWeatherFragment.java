package cn.auto.weatherforcaststudy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class CityWeatherFragment extends BaseFragment implements View.OnClickListener{
TextView tempTv,cityTv,conditionTv,windTv,tempRangeTv,dateTv,clothIndexTv,
    carIndexTv,coldIndexTv,sportIndexTv,raysIndexTv;
ImageView dayIv;
LinearLayout futureLayout;
String url1="http://api.map.baidu.com/telematics/v3/weather?location=";
String url2="&output=json&ak=FkPhtMBK0HTIQNh7gG4cNUttSTyr0nzo";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_city_weather, container, false);
        initViews(view);
//        可以通过activity传值获取到fragment加载的是哪个地方的天气情况
        Bundle bundle=getArguments();
        String city=bundle.getString("city");
        String url=url1+city+url2;
//        调用父类获取数据的方法
        loadData(url);
        return view ;
    }

    @Override
    public void onSuccess(String result) {
//   解析并展示数据
    }

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
        super.onError(ex, isOnCallback);
    }

    private void initViews(View view) {
        //设置控件初始化
        tempTv=view.findViewById(R.id.frag_tv_currenttemp);
        cityTv=view.findViewById(R.id.frag_tv_city);
        conditionTv=view.findViewById(R.id.frag_tv_condition);
        windTv=view.findViewById(R.id.frag_tv_wind);
        tempRangeTv=view.findViewById(R.id.frag_tv_temprange);
        dateTv=view.findViewById(R.id.frag_tv_date);
        clothIndexTv=view.findViewById(R.id.frag_index_tv_dress);
        carIndexTv=view.findViewById(R.id.frag_index_tv_washcar);
        coldIndexTv=view.findViewById(R.id.frag_index_tv_cold);
        sportIndexTv=view.findViewById(R.id.frag_index_tv_sport);
        raysIndexTv=view.findViewById(R.id.frag_index_tv_light);
        dayIv=view.findViewById(R.id.frag_iv_today);
        futureLayout=view.findViewById(R.id.frag_center_layout);
//设置控件监听事件
        clothIndexTv.setOnClickListener(this);
        carIndexTv.setOnClickListener(this);
        coldIndexTv.setOnClickListener(this);
        sportIndexTv.setOnClickListener(this);
        raysIndexTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.frag_index_tv_dress:

                break;
            case R.id.frag_index_tv_washcar:

                break;
            case R.id.frag_index_tv_cold:

                break;
            case R.id.frag_index_tv_sport:

                break;
            case R.id.frag_index_tv_light:

                break;

        }
    }
}
