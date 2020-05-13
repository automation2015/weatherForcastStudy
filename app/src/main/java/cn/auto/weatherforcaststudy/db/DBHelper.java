package cn.auto.weatherforcaststudy.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import cn.auto.weatherforcaststudy.utils.Constanse;
public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, Constanse.DB_NAME, null, Constanse.DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
//  创建表的操作
        String sql = "create table "+Constanse.DB_TABAL_NAME+"("+Constanse.DB_COLUMN_ID+" integer primary key autoincrement,"+Constanse.DB_COLUMN_CITY+" varchar(20) unique not null,"+Constanse.DB_COLUMN_CONTENT+" text not null)";
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
