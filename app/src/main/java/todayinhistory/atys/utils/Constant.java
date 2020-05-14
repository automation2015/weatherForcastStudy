package todayinhistory.atys.utils;

public class Constant {
    public static  String getHistoryUrl(String version,int month,int day){
        String url="http://api.juheapi.com/japi/toh?v="+version+"&month="+month+"&day="+day+"&key=122fb80f790670ba3635658f6e38e1cd";
        return url;
    }
    public static  String gethuangliUrl(String date){
//        http://v.juhe.cn/laohuangli/d?date=2014-09-11&key=您申请的KEY
        String url="http://v.juhe.cn/laohuangli/d?date="+date+"&key=7f5720be59d2534e13513425632f4a98";
        return url;
    }
//    http://api.juheapi.com/japi/toh?key=122fb80f790670ba3635658f6e38e1cd&v=1.0&month=11&day=1&id=17161201
//   http://api.juheapi.com/japi/tohdet?key=122fb80f790670ba3635658f6e38e1cd&v=1.0&id=17161201
public static String getHistoryDescURL(String version,String id){
        String url="http://api.juheapi.com/japi/tohdet?key=122fb80f790670ba3635658f6e38e1cd&v="+version+"&id="+id;
return url;
    }
}
