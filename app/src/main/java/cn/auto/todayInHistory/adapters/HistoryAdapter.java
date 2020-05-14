package cn.auto.todayInHistory.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import cn.auto.weatherforcaststudy.R;
import cn.auto.todayInHistory.beans.HistoryBean;

public class HistoryAdapter extends BaseAdapter {
    private Context context;
    private List<HistoryBean.ResultBean> mDatas;
    public HistoryAdapter(Context context,List<HistoryBean.ResultBean> data){
        this.context=context;
        this.mDatas=data;
    }
    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if(convertView==null){
            convertView=LayoutInflater.from(context).inflate(R.layout.his_main_item_timeline,null,false);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }
//        判断当前位置的年份和上一个位置是否相同
        HistoryBean.ResultBean resultBean = mDatas.get(position);
        if(position!=0){
            HistoryBean.ResultBean lastBean = mDatas.get(position - 1);
            if(resultBean.getYear()==lastBean.getYear()){
                holder.llItemTime.setVisibility(View.GONE);
            }else{
                holder.llItemTime.setVisibility(View.VISIBLE);
            }
        }else {
            holder.llItemTime.setVisibility(View.VISIBLE);
        }
 holder.tvItemTitle.setText(resultBean.getTitle());
        holder.tvItemTime.setText(resultBean.getYear()+"-"+resultBean.getMonth()+"-"+resultBean.getDay());
        String picUrl=resultBean.getPic();
        if(TextUtils.isEmpty(picUrl)){
            holder.ivItemPic.setVisibility(View.GONE);
        }else{
            holder.ivItemPic.setVisibility(View.VISIBLE);
            Picasso.with(context).load(picUrl).into(holder.ivItemPic);
        }

        return convertView;
    }
    class ViewHolder{
        TextView tvItemTime,tvItemTitle;
        ImageView ivItemPic;
        LinearLayout llItemTime;
        public ViewHolder(View view){
            tvItemTime=view.findViewById(R.id.his_item_main_tv_time);
            tvItemTitle=view.findViewById(R.id.his_item_main_tv_title);
            ivItemPic=view.findViewById(R.id.his_item_main_iv_pic);
            llItemTime=view.findViewById(R.id.his_item_main_ll);
        }
    }
}
