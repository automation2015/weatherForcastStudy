package cn.auto.weatherforcaststudy.adapters;

import android.content.Context;
import android.transition.CircularPropagation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import cn.auto.weatherforcaststudy.R;
import cn.auto.weatherforcaststudy.bean.WeatherBean;
import cn.auto.weatherforcaststudy.db.DatabaseBean;

public class CityManagerAdapter extends BaseAdapter {
    private List<DatabaseBean> mdatas;
    private Context context;
    public CityManagerAdapter(Context context,List<DatabaseBean> data){
        this.context=context;
        this.mdatas=data;
    }
    @Override
    public int getCount() {
        return mdatas.size();
    }

    @Override
    public Object getItem(int position) {
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
            convertView=LayoutInflater.from(context).inflate(R.layout.item_city_manager,null,false);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        holder=(ViewHolder)convertView.getTag();
        DatabaseBean bean = mdatas.get(position);

        WeatherBean weatherBean = new Gson().fromJson(bean.getContent(), WeatherBean.class);
        WeatherBean.ResultsBean.WeatherDataBean dataBean = weatherBean.getResults().get(0).getWeather_data().get(0);
        holder.cityTv.setText(bean.getCity());
        holder.temprangeTv.setText(dataBean.getTemperature());
        holder.windTv.setText(dataBean.getWind());
        holder.conTv.setText("天气："+dataBean.getWeather());
        String[] split = dataBean.getDate().split("：");
        String todayTemprature = split[1].replace(")", "");
        holder.tempTv.setText(todayTemprature);
        return convertView;
    }
    class ViewHolder{
        TextView tempTv,cityTv,temprangeTv,windTv,conTv;
        public ViewHolder(View view){
            cityTv=view.findViewById(R.id.item_city_tv_city);
            tempTv=view.findViewById(R.id.item_city_tv_temp);
            temprangeTv=view.findViewById(R.id.item_city_tv_temprange);
            windTv=view.findViewById(R.id.item_city_tv_wind);
            conTv=view.findViewById(R.id.item_city_tv_condition);


        }
    }
}
