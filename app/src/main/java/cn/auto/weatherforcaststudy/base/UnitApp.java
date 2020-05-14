package cn.auto.weatherforcaststudy.base;

import android.app.Application;
import android.content.Context;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import org.xutils.x;

public class UnitApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        xUtils初始化
        x.Ext.init(this);
//        图片加载框架ImageLoader初始化
        initImageLoader(getApplicationContext());
    }

    private void initImageLoader(Context context) {
        ImageLoaderConfiguration configuration=new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.MAX_PRIORITY).denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator()).
                        tasksProcessingOrder(QueueProcessingType.LIFO)
                .writeDebugLogs()
                .build();
        com.nostra13.universalimageloader.core.ImageLoader.getInstance().init(configuration);
    }
}
