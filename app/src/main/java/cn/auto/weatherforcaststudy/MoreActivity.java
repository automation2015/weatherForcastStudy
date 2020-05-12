package cn.auto.weatherforcaststudy;

import androidx.appcompat.app.AppCompatActivity;
import cn.auto.weatherforcaststudy.db.DBHelper;
import cn.auto.weatherforcaststudy.db.DBManager;
import cn.auto.weatherforcaststudy.utils.Constanse;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MoreActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvShare, tvCache, tvBg,tvVersion;
    private RadioGroup rgExBg;
    private ImageView ivBack;
    private DBHelper helper;
    private SQLiteDatabase db;
    private SharedPreferences pref;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        initViews();
        tvVersion.setText("当前版本号：   V " + getVersionName());
        setRgListrner();

    }


    private String getVersionName() {
        /*获取应用的版本名称*/
        PackageManager manager = getPackageManager();
        String versionName = "";
        try {
            PackageInfo info = manager.getPackageInfo(getPackageName(), 0);
            versionName = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    private void setRgListrner() {
        rgExBg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                获取目前的默认壁纸
                int bg = pref.getInt("bg", 0);
                SharedPreferences.Editor editor = pref.edit();
                Intent intent = new Intent(MoreActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                switch (checkedId) {
                    case R.id.more_rb_green:
                        if (bg == 0) {
                            Toast.makeText(MoreActivity.this, "您选择的为当前背景，无需改变！", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        editor.putInt("bg", 0);
                        editor.commit();
                        break;
                    case R.id.more_rb_red:
                        if (bg == 1) {
                            Toast.makeText(MoreActivity.this, "您选择的为当前背景，无需改变！", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        editor.putInt("bg", 1);
                        editor.commit();
                        break;
                    case R.id.more_rb_blue:
                        if (bg == 2) {
                            Toast.makeText(MoreActivity.this, "您选择的为当前背景，无需改变！", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        editor.putInt("bg", 2);
                        editor.commit();
                        break;
                    default:
                        break;
                }
                startActivity(intent);
            }
        });
    }

    private void initViews() {
        tvBg=findViewById(R.id.more_tv_exchange);
        tvCache = findViewById(R.id.more_tv_cache);
        tvShare = findViewById(R.id.more_tv_share);
        tvVersion = findViewById(R.id.more_tv_version);
        rgExBg = findViewById(R.id.more_rg);
        ivBack = findViewById(R.id.more_iv_back);
        helper = DBManager.getInstance(this);
        db = helper.getWritableDatabase();
        pref = getSharedPreferences("bg_pref", MODE_PRIVATE);
        tvCache.setOnClickListener(this);
        tvBg.setOnClickListener(this);
        tvShare.setOnClickListener(this);
        rgExBg.setOnClickListener(this);
        ivBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.more_tv_exchange:
                if(rgExBg.getVisibility() == View.GONE){
                    rgExBg.setVisibility(View.VISIBLE);
                }else {
                    rgExBg.setVisibility(View.GONE);
                }
                break;
            case R.id.more_iv_back:
                finish();
                break;
            case R.id.more_tv_cache:
                clearCache();

                break;
            case R.id.more_tv_share:
                shareSoftwareMsg("老天爷情绪预测软件是一款精准的情绪预测软件，进而情绪管理的高级软件，快快下载吧！");
                break;


        }
    }

    private void shareSoftwareMsg(String s) {
        /*分享软件的函数*/
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, s);
        startActivity(Intent.createChooser(intent, "老天爷情绪预测"));
    }

    private void clearCache() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示信息").setMessage("确定清除所有缓存吗？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (db != null) {
                            int count = DBManager.deleteAll(db, Constanse.DB_TABAL_NAME);
                            if (count > 0) {
                                Toast.makeText(MoreActivity.this, "成功清除缓存！", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(MoreActivity.this, MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            } else {
                                Toast.makeText(MoreActivity.this, "清除缓存失败！", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(MoreActivity.this, "数据库为空，不需要清除！", Toast.LENGTH_LONG).show();
                        }
                    }
                }).setNegativeButton("取消", null);
        builder.create().show();
    }
}
