package cn.auto.weatherforcaststudy.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import cn.auto.weatherforcaststudy.utils.Constanse;

public class DBManager {
    private static DBHelper helper;
    public static DBHelper getInstance(Context context){
        if(helper==null){
            helper=new DBHelper(context);
        }
        return helper;
    }
    //查找数据库当中城市列表
    public static List<String> queryAllCityName(SQLiteDatabase db,String tableName) {
        Cursor cursor = db.query(tableName, null, null,
                null, null, null, null,null);
        List<String> cityList = new ArrayList<>();
        while (cursor.moveToNext()) {
            String city = cursor.getString(cursor.getColumnIndex("city"));
            cityList.add(city);
        }
        return cityList;
    }
    /**
     * 根据城市名称，更新信息内容
     * @param city    城市名称
     * @param content 天气信息
     * @return 更新数据的总条目
     */
    public static int updateInfoByCity(SQLiteDatabase db,String tableName, String city, String content) {
        ContentValues values = new ContentValues();
        values.put("content", content);
        return db.update(tableName, values, "city=?", new String[]{city});
    }
    /**
     * 新增一条城市记录
     * @param city    城市名称
     * @param content 天气情况
     * @return 插入数据的总条目
     */
    public static long addCityInfo(SQLiteDatabase db,String tableName, String city, String content) {
        ContentValues values = new ContentValues();
        values.put("city", city);
        values.put("content", content);
        return db.insert(tableName, null, values);
    }

    /**
     * 根据城市名称查询数据
     * @param tableName 数据表
     * @param city 城市名称
     * @return 天气情况
     */
    public static String queryInfoByCity(SQLiteDatabase db,String tableName, String city) {
        Cursor cursor = db.query(tableName, null, "city=?",
                new String[]{city}, null, null, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            String content = cursor.getString(cursor.getColumnIndex(Constanse.DB_COLUMN_CONTENT));
            return content;
        }
        return null;
    }

    /**
     * 查询数据库中存储数据的数量，一旦超过5个城市就不能存储了
     * @param tableName
     * @return
     */
    public static int getCityCount(SQLiteDatabase db,String tableName){
        Cursor cursor=db.query(tableName,null,null,null,
                null,null,null);
        int count = cursor.getCount();
        return count;
    }

    /**
     * 查询数据库中的全部信息
     * @param tableName
     * @return
     */
    public static List<DatabaseBean> queryAllInfo(SQLiteDatabase db,String tableName){
        Cursor cursor=db.query(tableName,null,null,null,null
        ,null,null,null);
        List<DatabaseBean> datas=new ArrayList<>();
        while (cursor.moveToNext()){
            int _id = cursor.getInt(cursor.getColumnIndex(Constanse.DB_COLUMN_ID));
            String city = cursor.getString(cursor.getColumnIndex(Constanse.DB_COLUMN_CITY));
            String content = cursor.getString(cursor.getColumnIndex(Constanse.DB_COLUMN_CONTENT));
            DatabaseBean databaseBean=new DatabaseBean(_id,city,content);
            datas.add(databaseBean);
        }
        return datas;
    }

    /**
     * @param db  数据库
     * @param tableName 数据表
     * @param city 要删除的城市名称
     * @return  删除的条目数
     */
    public static  int deleteCityByName(SQLiteDatabase db,String tableName,String city) {
        int count = 0;
        if (db != null) {
            count = db.delete(tableName, "city=?", new String[]{city});
    }
        return count;
    }

    /**删除数据库总所有的记录
     * @param db 数据库对象
     * @param tableName 表名
     * @return 删除的条目数
     */
    public static int deleteAll(SQLiteDatabase db,String tableName){
        int count=0;
        if(db!=null){
            count= db.delete(tableName, null, null);
        }
        return count;
    }
}
