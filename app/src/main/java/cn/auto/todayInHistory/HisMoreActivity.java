package cn.auto.todayInHistory;

import androidx.appcompat.app.AppCompatActivity;
import cn.auto.weatherforcaststudy.R;
import cn.auto.todayInHistory.adapters.HistoryAdapter;
import cn.auto.todayInHistory.beans.HistoryBean;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HisMoreActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private TextView tvEmpty;
    private ListView lvHistory;
    private ImageView ivBack;
    private List<HistoryBean.ResultBean> mDatas;
    private HistoryAdapter adapter;
    private HistoryBean historyBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_his_more);
        initViews();
        try {
            Bundle bundle = getIntent().getExtras();
            historyBean = (HistoryBean) bundle.getSerializable("historyBean");
            if (historyBean != null) {
                List<HistoryBean.ResultBean> resultBean = historyBean.getResult();
                mDatas.addAll(resultBean);
                adapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
            tvEmpty.setVisibility(View.VISIBLE);
        }
        lvHistory.setOnItemClickListener(this);
    }

    private void initViews() {
        tvEmpty = findViewById(R.id.his_more_tv_none);
        lvHistory = findViewById(R.id.his_more_lv);
        ivBack = findViewById(R.id.his_more_iv_back);
        ivBack.setOnClickListener(this);
        mDatas = new ArrayList<>();
        adapter = new HistoryAdapter(this, mDatas);
        lvHistory.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.his_more_iv_back:
                finish();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        HistoryBean.ResultBean resultBean = mDatas.get(position);
        if (resultBean != null) {
            String id1 = resultBean.get_id();
            Intent intent = new Intent(HisMoreActivity.this, HistoryDescActivity.class);
            intent.putExtra("id", id1);
            startActivity(intent);
        } else {

        }

    }
}
