package cn.auto.weatherforcaststudy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.auto.weatherforcaststudy.R;

public class DeleteCityAdapter extends BaseAdapter {
    private Context context;
    private List<String> mDatas;
    private List<String> deleteCitys;
    public DeleteCityAdapter(Context context,List<String> data,List<String> deleteCitys){
    this.context=context;
    this.mDatas=data;
    this.deleteCitys=deleteCitys;

    }
    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public String getItem(int position) {
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
            convertView=LayoutInflater.from(context).inflate(R.layout.item_deletecity,null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }
        String city = mDatas.get(position);
        holder.cityTv.setText(city);
        holder.deleteIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatas.remove(city);
                deleteCitys.add(city);
                notifyDataSetChanged();//删除了提示适配器更新
            }
        });
        return convertView;
    }
    class ViewHolder{
        TextView cityTv;
        ImageView deleteIv;
        public ViewHolder(View view){
            cityTv=view.findViewById(R.id.item_delete_tv);
            deleteIv=view.findViewById(R.id.item_delete_iv);
        }
        
    }
}
