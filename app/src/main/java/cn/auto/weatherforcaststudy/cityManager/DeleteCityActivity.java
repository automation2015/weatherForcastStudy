package cn.auto.weatherforcaststudy.cityManager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import cn.auto.weatherforcaststudy.R;
import cn.auto.weatherforcaststudy.adapters.DeleteCityAdapter;
import cn.auto.weatherforcaststudy.db.DBHelper;
import cn.auto.weatherforcaststudy.db.DBManager;
import cn.auto.weatherforcaststudy.utils.Constanse;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import org.xutils.DbManager;

import java.util.ArrayList;
import java.util.List;

public class DeleteCityActivity extends AppCompatActivity implements View.OnClickListener{
private ImageView errorIv, rightIv;
private ListView deleteLv;
private List<String> mDatas;//listView的数据源
    private List<String> deleteCitys;//表示存储了删除的城市信息
    private DeleteCityAdapter adapter;
    private DBHelper helper;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delety_city);
        initViews();
        deleteLv.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<String> cityList = DBManager.queryAllCityName(db, Constanse.DB_TABAL_NAME);
        mDatas.addAll(cityList);
        adapter.notifyDataSetChanged();
    }

    private void initViews() {
        errorIv=findViewById(R.id.delete_iv_error);
        rightIv =findViewById(R.id.delete_iv_right);
        deleteLv=findViewById(R.id.delete_lv);
        mDatas=new ArrayList<>();
        deleteCitys=new ArrayList<>();
        adapter=new DeleteCityAdapter(this,mDatas,deleteCitys);
        helper=DBManager.getInstance(this);
        db=helper.getWritableDatabase();
        errorIv.setOnClickListener(this);
        rightIv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.delete_iv_error:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("提示信息").setMessage("您确定要放弃修改吗？")

                        .setPositiveButton("放弃修改", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();//关闭当前的activity
                            }
                        })
                        .setNegativeButton("取消",null);
                builder.create().show();
                break;
            case R.id.delete_iv_right:
                int count=0;
                for(int i=0;i<deleteCitys.size();i++){
                    String deleteCity = deleteCitys.get(i);
                    count=DBManager.deleteCityByName(db,Constanse.DB_TABAL_NAME,deleteCity);

                }
                if(count>0){
                    Toast.makeText(this,"删除城市成功!",Toast.LENGTH_LONG).show();
               finish();
                }else{
                    Toast.makeText(this,"删除城市失败!",Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
