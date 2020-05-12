package cn.auto.weatherforcaststudy.cityManager;

import androidx.appcompat.app.AppCompatActivity;
import cn.auto.weatherforcaststudy.MainActivity;
import cn.auto.weatherforcaststudy.R;
import cn.auto.weatherforcaststudy.base.BaseActivity;
import cn.auto.weatherforcaststudy.bean.WeatherBean;
import cn.auto.weatherforcaststudy.utils.Constanse;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.List;

public class SearchCityActivity extends BaseActivity implements View.OnClickListener {
    private EditText searchEt;
    private ImageView submitIv;
    private GridView searchGv;
    private ArrayAdapter<String> searchGvAdapder;
    private String[] hotCity = {"北京", "上海", "广州", "深圳", "珠海", "佛山", "南京", "苏州",
            "厦门", "长沙", "武汉", "南昌", "合肥", "济南", "青岛", "天津", "福州", "杭州"};
    private String city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_city);
        initViews();
        setListeners();
    }


    private void initViews() {
        searchEt = findViewById(R.id.search_et);
        searchGv = findViewById(R.id.search_gv);
        submitIv = findViewById(R.id.search_iv_submit);
        submitIv.setOnClickListener(this);
        searchGvAdapder = new ArrayAdapter<>(this, R.layout.item_hotcity, hotCity);
        searchGv.setAdapter(searchGvAdapder);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_iv_submit:
                if (!TextUtils.isEmpty(searchEt.getText().toString())) {
                    city = searchEt.getText().toString();
                    String url = Constanse.URL1 + city + Constanse.URL2;
                    loadData(url);
                } else {
                    Toast.makeText(this, "城市名称不能为空！", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }

    @Override
    public void onSuccess(String result) {
        if (result == null) {
            Toast.makeText(this, "查询结果为空，请检查网络是否正常！", Toast.LENGTH_SHORT).show();
            return;
        }
        WeatherBean weatherBean = new Gson().fromJson(result, WeatherBean.class);
        if (weatherBean.getError() == 0) {
// 跳转到MainActivity，并清除原有的栈，开启新的栈
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("city", city);
            startActivity(intent);


        } else {
            Toast.makeText(this, "暂未收录该城市天气信息", Toast.LENGTH_LONG).show();
        }
    }

    private void setListeners() {
        searchGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                city = hotCity[position];
                String url=Constanse.URL1+city+Constanse.URL2;
                loadData(url);
            }
        });


    }
}
