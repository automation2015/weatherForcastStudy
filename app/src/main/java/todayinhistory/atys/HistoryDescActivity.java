package todayinhistory.atys;

import androidx.appcompat.app.AppCompatActivity;
import cn.auto.weatherforcaststudy.R;
import cn.auto.weatherforcaststudy.base.BaseActivity;
import todayinhistory.atys.beans.HistoryDescBean;
import todayinhistory.atys.utils.Constant;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
public class HistoryDescActivity extends BaseActivity implements View.OnClickListener {
private TextView tvTitle,tvContent;
private ImageView ivBack,ivShare,ivPic;
private HistoryDescBean.ResultBean resultBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_desc);
        initViews();
        String id = getIntent().getStringExtra("id");
        String historyDescUrl=Constant.getHistoryDescURL("1.0",id);
        loadData(historyDescUrl);
    }

    @Override
    public void onSuccess(String result) {
      if(result!=null){
          HistoryDescBean historyDescBean = new Gson().fromJson(result, HistoryDescBean.class);
          resultBean = historyDescBean.getResult().get(0);
          int error_code = historyDescBean.getError_code();
          if(error_code ==0){
              tvTitle.setText(resultBean.getTitle());
              tvContent.setText(resultBean.getContent());
              if(TextUtils.isEmpty(resultBean.getPic())){
                  ivPic.setVisibility(View.GONE);
              }else{
                  ivPic.setVisibility(View.VISIBLE);
                  Picasso.with(this).load(resultBean.getPic()).into(ivPic);
              }
          }
      }
    }
    private void initViews() {
        tvTitle=findViewById(R.id.desc_tv_title);
        tvContent=findViewById(R.id.desc_tv_content);
        ivBack=findViewById(R.id.desc_iv_back);
        ivShare=findViewById(R.id.desc_iv_share);
        ivPic=findViewById(R.id.desc_iv_pic);
        ivBack.setOnClickListener(this);
        ivShare.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.desc_iv_back:
                finish();
                break;
            case R.id.desc_iv_share:
                String text="我发现一款好用的软件-历史上的今天，快来一起探索这个APP吧！";
                if(resultBean!=null){
                    text="想要了解"+resultBean.getTitle()+"详情吗？快来下载历史上的今天APP吧！";
                }
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT,text);
                startActivity(Intent.createChooser(intent,"历史上的今天"));
                break;
        }
    }
}
