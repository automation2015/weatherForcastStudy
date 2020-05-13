package cn.auto.weatherforcaststudy.cityManager;

import androidx.appcompat.app.AppCompatActivity;
import cn.auto.weatherforcaststudy.R;
import cn.auto.weatherforcaststudy.adapters.CityManagerAdapter;
import cn.auto.weatherforcaststudy.db.DBManager;
import cn.auto.weatherforcaststudy.db.DatabaseBean;
import cn.auto.weatherforcaststudy.utils.Constanse;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CityManagerActivity extends AppCompatActivity implements View.OnClickListener{
private ImageView ivAdd, ivDelete, ivBack;
private ListView cityLv;
private List<DatabaseBean> mDatas;
private CityManagerAdapter cityManagerAdapter;
private SQLiteOpenHelper dbHelper;
private SQLiteDatabase db;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_manager);
        initViews();
        cityManagerAdapter=new CityManagerAdapter(this,mDatas);
        cityLv.setAdapter(cityManagerAdapter);
    }

    /**
     * 获取数据库中的真实数据源，添加到原有数据源中，提示适配器更新
     */
    @Override
    protected void onResume() {
        super.onResume();
        List<DatabaseBean> list = DBManager.queryAllInfo(db,Constanse.DB_TABAL_NAME);
        mDatas.clear();
        mDatas.addAll(list);
        cityManagerAdapter.notifyDataSetChanged();
    }

    private void initViews() {
        ivAdd =findViewById(R.id.city_iv_add);
        ivDelete =findViewById(R.id.city_iv_edit);
        ivBack =findViewById(R.id.city_iv_back);
        cityLv=findViewById(R.id.city_lv);
        dbHelper=DBManager.getInstance(this);
        db=dbHelper.getWritableDatabase();
        mDatas=new ArrayList<>();
        ivAdd.setOnClickListener(this);
        ivDelete.setOnClickListener(this);
        ivBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.city_iv_add:
                int cityCount = DBManager.getCityCount(db,Constanse.DB_TABAL_NAME);
                if(cityCount<5){
                Intent intent=new Intent(this,SearchCityActivity.class);
                startActivity(intent);}else{
                    Toast.makeText(this,"存储城市数量已达上限，请删除后再添加！",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.city_iv_edit:
                Intent intent1=new Intent(this,DeleteCityActivity.class);
                startActivity(intent1);
                break;
            case R.id.city_iv_back:
                finish();
                break;
        }
    }
}
