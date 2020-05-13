package cn.auto.weatherforcaststudy.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import cn.auto.weatherforcaststudy.R;
import cn.auto.weatherforcaststudy.db.DBHelper;
import cn.auto.weatherforcaststudy.db.DBManager;
import cn.auto.weatherforcaststudy.utils.Constanse;

public class DeleteCityAdapter extends BaseAdapter {
    private Context context;
    private List<String> mDatas;
    private List<String> deleteCitys;
    private DBHelper helper;
    private SQLiteDatabase db;

    public DeleteCityAdapter(Context context,List<String> data,List<String> deleteCitys){
    this.context=context;
    this.mDatas=data;
    this.deleteCitys=deleteCitys;
    helper=DBManager.getInstance(context);
    db=helper.getWritableDatabase();
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
        private int count;
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("提示信息").setMessage("确定删除城市信息吗？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mDatas.remove(city);
                                count= DBManager.deleteCityByName(db, Constanse.DB_TABAL_NAME, city);
                                if(count>0){
                                    Toast.makeText(context,"删除城市成功!",Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(context,"删除城市失败!",Toast.LENGTH_SHORT).show();
                                }
                                notifyDataSetChanged();//删除了提示适配器更新
                            }
                        }).setNegativeButton("取消",null);
                builder.create().show();
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
