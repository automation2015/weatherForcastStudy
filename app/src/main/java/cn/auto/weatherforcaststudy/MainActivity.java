package cn.auto.weatherforcaststudy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
ImageView addCityIv,moreIv;
ViewPager mainVp;
LinearLayout pointLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        addCityIv.setOnClickListener(this);
        moreIv.setOnClickListener(this);
    }

    private void initViews() {
        addCityIv=findViewById(R.id.main_iv_add);
        moreIv=findViewById(R.id.main_iv_more);
        mainVp=findViewById(R.id.main_vg);
        pointLayout=findViewById(R.id.main_layout_point);
    }
    @Override
    public void onClick(View view){

    }
}
