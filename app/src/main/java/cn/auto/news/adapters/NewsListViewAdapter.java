package cn.auto.news.adapters;

import android.content.Context;
import android.text.Layout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import androidx.viewpager.widget.ViewPager;
import cn.auto.news.beans.NewsBean;
import cn.auto.weatherforcaststudy.R;

public class NewsListViewAdapter extends BaseAdapter {
    private Context context;
    private List<NewsBean.ResultBean.DataBean> mdatas;
    private ImageLoader imageLoader;
    private DisplayImageOptions options;
    public NewsListViewAdapter(Context context,List<NewsBean.ResultBean.DataBean> data){
        this.context=context;
        this.mdatas=data;
        imageLoader=ImageLoader.getInstance();
        options=new DisplayImageOptions.Builder()
                .showImageOnLoading(null)
                .showImageForEmptyUri(null)
                .showImageOnFail(null)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .build();
    }
    @Override
    public int getCount() {
        return mdatas.size();
    }

    @Override
    public NewsBean.ResultBean.DataBean getItem(int position) {
        return mdatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if(convertView==null){
            convertView=LayoutInflater.from(context).inflate(R.layout.news_item_lv,null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        NewsBean.ResultBean.DataBean dataBean= mdatas.get(position);
        holder.tvTitle.setText(dataBean.getTitle());
        holder.tvTime.setText(dataBean.getDate());
        holder.tvSource.setText(dataBean.getAuthor_name());
        String pic1=dataBean.getThumbnail_pic_s();
        String pic2=dataBean.getThumbnail_pic_s02();
        String pic3=dataBean.getThumbnail_pic_s03();
        if(TextUtils.isEmpty(pic1)){
            holder.ivPic1.setVisibility(View.GONE);
        }else{
            holder.ivPic1.setVisibility(View.VISIBLE);
            imageLoader.displayImage(pic1,holder.ivPic1,options);
        }
        if(TextUtils.isEmpty(pic2)){
            holder.ivPic2.setVisibility(View.GONE);
        }else{
            holder.ivPic2.setVisibility(View.VISIBLE);
            imageLoader.displayImage(pic1,holder.ivPic2,options);
        }
        if(TextUtils.isEmpty(pic3)){
            holder.ivPic3.setVisibility(View.GONE);
        }else{
            holder.ivPic3.setVisibility(View.VISIBLE);
            imageLoader.displayImage(pic1,holder.ivPic3,options);
        }
        return convertView;
    }
    class ViewHolder{
        private TextView tvTitle,tvTime,tvSource;
        private ImageView ivPic1,ivPic2,ivPic3;
        public ViewHolder(View view){
            tvTime=view.findViewById(R.id.news_item_tv_time);
            tvTitle=view.findViewById(R.id.news_item_tv_title);
            tvSource=view.findViewById(R.id.news_item_tv_source);
            ivPic1=view.findViewById(R.id.news_item_iv1);
            ivPic2=view.findViewById(R.id.news_item_iv2);
            ivPic3=view.findViewById(R.id.news_item_iv3);
        }
    }
}
