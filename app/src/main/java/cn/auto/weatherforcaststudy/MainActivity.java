package cn.auto.weatherforcaststudy;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import cn.auto.weatherforcaststudy.adapters.CityFragmentPageAdapter;
import cn.auto.weatherforcaststudy.cityManager.CityManagerActivity;
import cn.auto.weatherforcaststudy.db.DBManager;
import cn.auto.weatherforcaststudy.utils.Constanse;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView addCityIv, moreIv;
    private ViewPager mainVp;
    private LinearLayout pointLayout;
    //ViewPager的数据源
    private List<Fragment> fragmentList;
    // 表示选中的城市的集合
    private List<String> cityList = new ArrayList<>();
    // 表示ViewPager的个数，指示器的个数
    private List<ImageView> imvList;
    private CityFragmentPageAdapter cityFragmentPageAdapter;
    private SQLiteOpenHelper dbHelper;
    private SQLiteDatabase db;
    private RelativeLayout rlMain;//用于切换主题背景
    private String defaultCity="济南";
private  SharedPreferences pref;
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //db.close();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        exchangeBg();
        db=dbHelper.getWritableDatabase();
        cityList = DBManager.queryAllCityName(db,Constanse.DB_TABAL_NAME);//获取数据库中的城市列表
        db.close();
        if (cityList.size() == 0) {
            cityList.add(defaultCity);
        }
        /*
        搜索界面点击跳转到此界面，会传值，此处获取一下
         */
        Intent intent=getIntent();
        String city=intent.getStringExtra("city");
        if(!cityList.contains(city)&& !TextUtils.isEmpty(city)){
            cityList.add(city);
        }
        imvList = new ArrayList<>();
        addCityIv.setOnClickListener(this);
        moreIv.setOnClickListener(this);
//     初始化ViewPager页面的方法
        initPager();
         cityFragmentPageAdapter = new CityFragmentPageAdapter(getSupportFragmentManager(), fragmentList);
        mainVp.setAdapter(cityFragmentPageAdapter);
//       创建小圆点指示器
        initPoints();
//       设置最后一个城市信息
        mainVp.setCurrentItem(fragmentList.size() - 1);
//        设置ViewPager监听
        setPagerListener();
    }

    private void initViews() {
        addCityIv = findViewById(R.id.main_iv_add);
        moreIv = findViewById(R.id.main_iv_more);
        mainVp = findViewById(R.id.main_vg);
        pointLayout = findViewById(R.id.main_layout_point);
        rlMain=findViewById(R.id.main_rl);
        fragmentList = new ArrayList<>();
        cityList = new ArrayList<>();
        dbHelper=DBManager.getInstance(this);
        pref=getSharedPreferences("defaultCity",MODE_PRIVATE);
    }


    private void initPager() {
//      创建Fragment对象，添加到fragmentList中
        for (int i = 0; i < cityList.size(); i++) {
            CityWeatherFragment cwFrag = new CityWeatherFragment();
            Bundle bundle = new Bundle();
            bundle.putString("city", cityList.get(i));
            cwFrag.setArguments(bundle);
            fragmentList.add(cwFrag);
        }
    }
    private void initPoints() {
        for (int i = 0; i < fragmentList.size(); i++) {
            ImageView pIv = new ImageView(this);
            pIv.setImageResource(R.mipmap.a1);
            pIv.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) pIv.getLayoutParams();
            lp.setMargins(0, 0, 20, 0);
            imvList.add(pIv);
            pointLayout.addView(pIv);
        }
        imvList.get(imvList.size() - 1).setImageResource(R.mipmap.a2);
    }

    private void setPagerListener() {
        mainVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < imvList.size(); i++) {
                    imvList.get(i).setImageResource(R.mipmap.a1);
                }
                imvList.get(position).setImageResource(R.mipmap.a2);
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.main_iv_add:
                intent.setClass(this, CityManagerActivity.class);
                break;
            case R.id.main_iv_more:
                intent.setClass(this,MoreActivity.class);
                break;
        }
        startActivity(intent);
    }
/*
当页面重新加载时会调用的函数，这个函数在页面获取焦点之前进行调用，此处完成ViewPager页数的更新
 */
    @Override
    protected void onRestart() {
        super.onRestart();
//        获取数据库删除之后剩余的城市集合
        db=dbHelper.getWritableDatabase();
        List<String> remainCity = DBManager.queryAllCityName(db, Constanse.DB_TABAL_NAME);
        db.close();
        if(remainCity.size()==0){
            remainCity.add(defaultCity);
        }
        cityList.clear();
        cityList.addAll(remainCity);
//        为剩余的城市创建对应的Fragment页面
        fragmentList.clear();
        initPager();
        cityFragmentPageAdapter.notifyDataSetChanged();
//        页面数量放生改变，指示器的数量也要变化
        pointLayout.removeAllViews();
        imvList.clear();
        initPoints();
        mainVp.setCurrentItem(0);
    }
    private void exchangeBg(){
        SharedPreferences pref = getSharedPreferences("bg_pref", MODE_PRIVATE);
        int bgNum = pref.getInt("bg", 2);
        switch (bgNum){
            case 0:
                rlMain.setBackgroundResource(R.mipmap.bg);
                break;
            case 1:
                rlMain.setBackgroundResource(R.mipmap.bg2);
                break;
            case 2:
                rlMain.setBackgroundResource(R.mipmap.bg3);
                break;
        }
    }
}
