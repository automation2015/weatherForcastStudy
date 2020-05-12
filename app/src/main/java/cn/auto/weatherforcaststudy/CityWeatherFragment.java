package cn.auto.weatherforcaststudy;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;
import androidx.fragment.app.Fragment;
import cn.auto.weatherforcaststudy.bean.WeatherBean;
import cn.auto.weatherforcaststudy.db.DBManager;
import cn.auto.weatherforcaststudy.utils.Constanse;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class CityWeatherFragment extends BaseFragment implements View.OnClickListener {
    private  TextView tempTv, cityTv, conditionTv, windTv, tempRangeTv, dateTv, clothIndexTv,
            carIndexTv, coldIndexTv, sportIndexTv, raysIndexTv;
    private ImageView dayIv;
    private LinearLayout futureLayout;
    private List<WeatherBean.ResultsBean.IndexBean> indexList;
    private String city;
    private SQLiteOpenHelper dbHelper;
    private SQLiteDatabase db;

    private ScrollView svFregContainer;
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        db.close();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_city_weather, container, false);
        initViews(view);
       exchangeBg();
//   可以通过activity传值获取到fragment加载的是哪个地方的天气情况
        Bundle bundle = getArguments();
        city = bundle.getString("city");
        String url = Constanse.URL1 + city + Constanse.URL2;
//   调用父类获取数据的方法
        loadData(url);
        return view;
    }

    @Override
    public void onSuccess(String result) {
//   解析并展示数据
        parseShowData(result);
//   更新数据
        int count = DBManager.updateInfoByCity(db,Constanse.DB_TABAL_NAME, city, result);
        if(count<=0){
//    更新数据失败，说明没有这条城市信息，增加这个城市信息
       DBManager.addCityInfo(db,Constanse.DB_TABAL_NAME,city,result);
       }
        Log.e("tag",result);
    }
    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
//    在数据库当中查找上一次信息显示在Fragment中
//        String s=DBManager.queryInfoByCity(db,Constanse.DB_TABAL_NAME,city);
//        if(!TextUtils.isEmpty(s)){
////            parseShowData(s);
//        }
    }
    private void parseShowData(String result) {
//      使用Gson解析数据
        WeatherBean weatherBean = new Gson().fromJson(result, WeatherBean.class);
        WeatherBean.ResultsBean resultsBean = weatherBean.getResults().get(0);
//    获取指数信息集合列表
        indexList = resultsBean.getIndex();
        dateTv.setText(weatherBean.getDate());
        cityTv.setText(resultsBean.getCurrentCity());
//     获取今日天气情况
        WeatherBean.ResultsBean.WeatherDataBean todayDataBean = resultsBean.getWeather_data().get(0);
        windTv.setText(todayDataBean.getWind());
        tempRangeTv.setText(todayDataBean.getTemperature());
        conditionTv.setText(todayDataBean.getWeather());
//   获取实时天气温度情况，需要处理字符串
        String[] split = todayDataBean.getDate().split("：");
        String todayTemp = split[1].replace(")", "");
        tempTv.setText(todayTemp);
//    设置显示的天气情况图片
        Picasso.with(getActivity()).load(todayDataBean.getDayPictureUrl()).into(dayIv);
//   获取未来三天的天气情况，加载到layout中
        List<WeatherBean.ResultsBean.WeatherDataBean> futureList = resultsBean.getWeather_data();
        futureList.remove(0);
        for (int i = 0; i < futureList.size(); i++) {
            View itemView = LayoutInflater.from(getActivity()).inflate(R.layout.item_main_center, null);
            itemView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            futureLayout.addView(itemView);
            TextView idataTv = itemView.findViewById(R.id.item_center_tv_date);
            TextView iconTv = itemView.findViewById(R.id.item_center_tv_con);
            TextView itemprageTv = itemView.findViewById(R.id.item_center_tv_temp);
            ImageView iIv = itemView.findViewById(R.id.item_center_iv);
//            获取对应位置的天气情况
            WeatherBean.ResultsBean.WeatherDataBean dataBean = futureList.get(i);
            idataTv.setText(dataBean.getDate());
            iconTv.setText(dataBean.getWeather());
            itemprageTv.setText(dataBean.getTemperature());
            Picasso.with(getActivity()).load(dataBean.getDayPictureUrl()).into(iIv);
        }
    }
    private void initViews(View view) {
        //设置控件初始化
        tempTv = view.findViewById(R.id.frag_tv_currenttemp);
        cityTv = view.findViewById(R.id.frag_tv_city);
        conditionTv = view.findViewById(R.id.frag_tv_condition);
        windTv = view.findViewById(R.id.frag_tv_wind);
        tempRangeTv = view.findViewById(R.id.frag_tv_temprange);
        dateTv = view.findViewById(R.id.frag_tv_date);
        clothIndexTv = view.findViewById(R.id.frag_index_tv_dress);
        carIndexTv = view.findViewById(R.id.frag_index_tv_washcar);
        coldIndexTv = view.findViewById(R.id.frag_index_tv_cold);
        sportIndexTv = view.findViewById(R.id.frag_index_tv_sport);
        raysIndexTv = view.findViewById(R.id.frag_index_tv_light);
        dayIv = view.findViewById(R.id.frag_iv_today);
        futureLayout = view.findViewById(R.id.frag_center_layout);
        svFregContainer =view.findViewById(R.id.frag_sv);
        dbHelper=DBManager.getInstance(getActivity());
        db=dbHelper.getWritableDatabase();
        SharedPreferences pref = getActivity().getSharedPreferences("bg_pref", MODE_PRIVATE);
//   设置控件监听事件
        clothIndexTv.setOnClickListener(this);
        carIndexTv.setOnClickListener(this);
        coldIndexTv.setOnClickListener(this);
        sportIndexTv.setOnClickListener(this);
        raysIndexTv.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        switch (v.getId()) {
            case R.id.frag_index_tv_dress:
                builder.setTitle("穿衣指数");
                WeatherBean.ResultsBean.IndexBean indexBean = indexList.get(0);
                String msg = indexBean.getZs() + "\n" + indexBean.getDes();
                builder.setMessage(msg);
                builder.setPositiveButton("确定", null);

                break;
            case R.id.frag_index_tv_washcar:
                builder.setTitle("洗车指数");
                indexBean = indexList.get(1);
                msg = indexBean.getZs() + "\n" + indexBean.getDes();
                builder.setMessage(msg);
                builder.setPositiveButton("确定", null);
                break;
            case R.id.frag_index_tv_cold:
                builder.setTitle("感冒指数");
                indexBean = indexList.get(2);
                msg = indexBean.getZs() + "\n" + indexBean.getDes();
                builder.setMessage(msg);
                builder.setPositiveButton("确定", null);
                break;
            case R.id.frag_index_tv_sport:
                builder.setTitle("运动指数");
                indexBean = indexList.get(3);
                msg = indexBean.getZs() + "\n" + indexBean.getDes();
                builder.setMessage(msg);
                builder.setPositiveButton("确定", null);
                break;
            case R.id.frag_index_tv_light:
                builder.setTitle("紫外线指数");
                indexBean = indexList.get(4);
                msg = indexBean.getZs() + "\n" + indexBean.getDes();
                builder.setMessage(msg);
                builder.setPositiveButton("确定", null);
                break;
        }
        builder.create().show();
    }
    private void exchangeBg(){
        SharedPreferences pref = getActivity().getSharedPreferences("bg_pref", MODE_PRIVATE);
        int bgNum = pref.getInt("bg", 2);
        switch (bgNum){
            case 0:
                svFregContainer.setBackgroundResource(R.mipmap.bg);
                break;
            case 1:
                svFregContainer.setBackgroundResource(R.mipmap.bg2);
                break;
            case 2:
                svFregContainer.setBackgroundResource(R.mipmap.bg3);
                break;
        }
    }
}
