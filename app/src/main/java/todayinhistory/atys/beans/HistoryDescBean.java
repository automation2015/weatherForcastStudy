package todayinhistory.atys.beans;

import java.util.List;

public class HistoryDescBean {

    /**
     * result : [{"_id":"17550512","title":"意大利小提琴家、作曲家维奥蒂诞生","pic":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/201005/10/2923150878.jpg","year":1755,"month":5,"day":12,"des":"在265年前的今天，1755年5月12日 (农历四月初二)，意大利小提琴家、作曲家维奥蒂诞生。","content":"在265年前的今天，1755年5月12日 (农历四月初二)，意大利小提琴家、作曲家维奥蒂诞生。\n","lunar":"乙亥年四月初二"}]
     * reason : 请求成功！
     * error_code : 0
     */

    private String reason;
    private int error_code;
    private List<ResultBean> result;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * _id : 17550512
         * title : 意大利小提琴家、作曲家维奥蒂诞生
         * pic : http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/201005/10/2923150878.jpg
         * year : 1755
         * month : 5
         * day : 12
         * des : 在265年前的今天，1755年5月12日 (农历四月初二)，意大利小提琴家、作曲家维奥蒂诞生。
         * content : 在265年前的今天，1755年5月12日 (农历四月初二)，意大利小提琴家、作曲家维奥蒂诞生。

         * lunar : 乙亥年四月初二
         */

        private String _id;
        private String title;
        private String pic;
        private int year;
        private int month;
        private int day;
        private String des;
        private String content;
        private String lunar;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getLunar() {
            return lunar;
        }

        public void setLunar(String lunar) {
            this.lunar = lunar;
        }
    }
}
