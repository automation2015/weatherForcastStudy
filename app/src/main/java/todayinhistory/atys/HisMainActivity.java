package todayinhistory.atys;

import cn.auto.weatherforcaststudy.R;
import cn.auto.weatherforcaststudy.base.BaseActivity;
import todayinhistory.atys.adapters.HistoryAdapter;
import todayinhistory.atys.beans.HistoryBean;
import todayinhistory.atys.beans.LaohuangliBean;
import todayinhistory.atys.utils.Constant;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import org.xutils.http.RequestParams;
import org.xutils.x;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
/**
 * UnitApp BaseActivity 共用天气预报的
 */
public class HisMainActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private ListView lvMain;
    private ImageButton imbMain;
    private List<HistoryBean.ResultBean> mDatas;
    private HistoryAdapter adapter;
    private Calendar calendar;
    private Date date;
    private TextView tvSolar, tvLunar, tvDay, tvWeek, tvBaiji, tvYi, tvJi, tvXSh, tvJSh, tvWX, tvCSh;
    private View headerView, footerView;
    private HistoryBean historyBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_his_main);
        initViews();
        addHeaderAndFooter();
        initHeaderViews(headerView);

        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String historyUrl = Constant.getHistoryUrl("1.0", month, day);
        loadData(historyUrl);
        // 将日期对象转换为字符串格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formatDate = sdf.format(date);
        String laoHuangLiUrl = Constant.gethuangliUrl(formatDate);
        loadHeaderData(laoHuangLiUrl);
    }

    /**
     * 获取头部接口数据
     * @param laoHuangLiUrl
     */
    private void loadHeaderData(String laoHuangLiUrl) {
        RequestParams params = new RequestParams(laoHuangLiUrl);
        x.http().get(params, new CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (result != null) {
                    LaohuangliBean laohuangliBean = new Gson().fromJson(result, LaohuangliBean.class);
                    if (laohuangliBean.getError_code() == 0) {
                        LaohuangliBean.ResultBean resultBean = laohuangliBean.getResult();
                        tvBaiji.setText("彭祖百忌：" + resultBean.getBaiji());
                        tvYi.setText("宜：" + resultBean.getYi());
                        tvJi.setText("忌：" + resultBean.getJi());
                        tvXSh.setText("凶神宜忌：" + resultBean.getXiongshen());
                        tvJSh.setText("吉神宜趋：" + resultBean.getJishen());
                        tvWX.setText("五行：" + resultBean.getWuxing());
                        tvLunar.setText("农历 " + resultBean.getYinli() + "(阴历)");
                        tvCSh.setText("冲煞：" + resultBean.getChongsha());
                        /*
                        通过阳历的内容获取年、月、日，并通过getWeek()方法获得星期
                         */
                        String[] yangliArr = resultBean.getYangli().split("-");
                        String week = getWeek(Integer.parseInt(yangliArr[0]), Integer.parseInt(yangliArr[1]), Integer.parseInt(yangliArr[2]));
                        tvSolar.setText("公历 " + yangliArr[0] + "-" + yangliArr[1] + "-" + yangliArr[2] + " " + week);
                        tvDay.setText(yangliArr[2]);
                        tvWeek.setText(week);
                    } else {
                        Toast.makeText(HisMainActivity.this, "数据请求失败，请检查网络后重新请求数据！", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            }
            @Override
            public void onCancelled(CancelledException cex) {
            }
            @Override
            public void onFinished() {
            }
        });
    }
    /**
     * 给定日期，获得星期
     * @param year
     * @param month
     * @param day
     * @return 星期
     */
    private String getWeek(int year, int month, int day) {
        String[] weeks = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        //month与实际的月份不同
        calendar.set(year, month - 1, day);
        int index = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (index < 0) {
            index = 0;
        }
        return weeks[index];
    }
    /**
     * ListView中添加头部布局和尾部布局
     */
    private void addHeaderAndFooter() {
        headerView = LayoutInflater.from(this).inflate(R.layout.his_main_listview_head, null);
        footerView = LayoutInflater.from(this).inflate(R.layout.his_main_listview_footer, null);
        lvMain.addHeaderView(headerView);
        lvMain.addFooterView(footerView);
        footerView.setTag("footer");
        footerView.setOnClickListener(this);
    }
    /**
     * 初始化头部布局控件
     */
    private void initHeaderViews(View view) {
        tvBaiji = view.findViewById(R.id.his_main_header_tv_baiji);
        tvCSh = view.findViewById(R.id.his_main_header_tv_chongsha);
        tvDay = view.findViewById(R.id.his_main_header_tv_day);
        tvJSh = view.findViewById(R.id.his_main_header_tv_jishen);
        tvLunar = view.findViewById(R.id.his_main_header_tv_lunar);
        tvSolar = view.findViewById(R.id.his_main_header_tv_solar);
        tvWeek = view.findViewById(R.id.his_main_header_tv_week);
        tvWX = view.findViewById(R.id.his_main_header_tv_wuxing);
        tvYi = view.findViewById(R.id.his_main_header_tv_yi);
        tvJi = view.findViewById(R.id.his_main_header_tv_ji);
        tvXSh = view.findViewById(R.id.his_main_header_tv_xiongshen);
    }

    @Override
    public void onSuccess(String result) {
        mDatas.clear();
        historyBean = new Gson().fromJson(result, HistoryBean.class);
        List<HistoryBean.ResultBean> resultBean = historyBean.getResult();
        Log.e("tag", resultBean.toString());
        for (int i = 0; i < 5; i++) {
            mDatas.add(resultBean.get(i));
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
        super.onError(ex, isOnCallback);
    }
    /**
     * 初始化主布局中的控件
     */
    private void initViews() {
        lvMain = findViewById(R.id.his_main_lv);
        imbMain = findViewById(R.id.his_main_imb);
        imbMain.setOnClickListener(this);
        lvMain.setOnItemClickListener(this);
        mDatas = new ArrayList<>();
        adapter = new HistoryAdapter(this, mDatas);
        lvMain.setAdapter(adapter);
//        获取日历对象
        calendar = Calendar.getInstance();
        date = new Date();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.his_main_imb) {
            popCalendarDialog();
            return;
        }
        String tag = (String) v.getTag();
        if (tag.equals("footer")) {
            Intent intent = new Intent(HisMainActivity.this, HisMoreActivity.class);
            if (historyBean != null) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("historyBean", historyBean);
                intent.putExtras(bundle);
            }
            startActivity(intent);
        }
    }
    //显示日历对话框
    private void popCalendarDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
   //改变老黄历显示的内容
                String time = year + "-" + (month + 1) + "-" + dayOfMonth;
                loadHeaderData(Constant.gethuangliUrl(time));
   //改变历史上的今天数据
                loadData(Constant.getHistoryUrl("1.0", (month + 1), dayOfMonth));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        HistoryBean.ResultBean resultBean = mDatas.get(position-1);
        if (resultBean != null) {
            String id1 = resultBean.get_id();
            Intent intent = new Intent(HisMainActivity.this, HistoryDescActivity.class);
            intent.putExtra("id", id1);
            startActivity(intent);
        } else {

        }
    }
}
