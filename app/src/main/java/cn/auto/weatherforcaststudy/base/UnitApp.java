package cn.auto.weatherforcaststudy.base;

import android.app.Application;
import org.xutils.x;
public class UnitApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        xUtils初始化
        x.Ext.init(this);
    }
}
