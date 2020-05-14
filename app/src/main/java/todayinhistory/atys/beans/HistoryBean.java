package todayinhistory.atys.beans;

import java.io.Serializable;
import java.util.List;
//Intent传值必须实现Serializable
public class HistoryBean implements Serializable{

    /**
     * result : [{"_id":"17550512","title":"意大利小提琴家、作曲家维奥蒂诞生","pic":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/201005/10/2923150878.jpg","year":1755,"month":5,"day":12,"des":"在265年前的今天，1755年5月12日 (农历四月初二)，意大利小提琴家、作曲家维奥蒂诞生。","lunar":"乙亥年四月初二"},{"_id":"18030512","title":"德国化学家李比希诞辰","pic":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/201005/10/FD234357944.jpg","year":1803,"month":5,"day":12,"des":"在217年前的今天，1803年5月12日 (农历三月廿二)，德国化学家李比希诞辰。","lunar":"癸亥年三月廿二"},{"_id":"18200512","title":"近代护理学和护理教育创始人弗洛伦斯·南丁格尔女士诞生","pic":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/201005/10/AF234632873.jpg","year":1820,"month":5,"day":12,"des":"在200年前的今天，1820年5月12日 (农历四月初一)，近代护理学和护理教育创始人弗洛伦斯·南丁格尔女士诞生。","lunar":"庚辰年四月初一"},{"_id":"18840512","title":"捷克民族乐派奠基人、歌剧和交响诗作曲家斯美塔那逝世","pic":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/201005/10/F8234951351.jpg","year":1884,"month":5,"day":12,"des":"在136年前的今天，1884年5月12日 (农历四月十八)，捷克民族乐派奠基人、歌剧和交响诗作曲家斯美塔那逝世。","lunar":"甲申年四月十八"},{"_id":"19020512","title":"伊朗伊斯兰共和国最高领袖霍梅尼诞辰","pic":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/200905/12/E701217216.jpg","year":1902,"month":5,"day":12,"des":"在118年前的今天，1902年5月12日 (农历四月初五)，伊朗伊斯兰共和国最高领袖霍梅尼诞辰。","lunar":"壬寅年四月初五"},{"_id":"19090512","title":"著名影星凯瑟琳·赫本出生于美国","pic":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/200905/12/CA01350806.jpg","year":1909,"month":5,"day":12,"des":"在111年前的今天，1909年5月12日 (农历三月廿三)，著名影星凯瑟琳·赫本出生于美国。","lunar":"己酉年三月廿三"},{"_id":"19150512","title":"卢西塔尼亚号客轮被德军鱼雷击沉","pic":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/200905/17/42161518929.jpg","year":1915,"month":5,"day":12,"des":"在105年前的今天，1915年5月12日 (农历三月廿九)，卢西塔尼亚号客轮被德军鱼雷击沉。","lunar":"乙卯年三月廿九"},{"_id":"19310512","title":"张国焘主持鄂豫皖中央分局","pic":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/200905/17/75161123937.jpg","year":1931,"month":5,"day":12,"des":"在89年前的今天，1931年5月12日 (农历三月廿五)，张国焘主持鄂豫皖中央分局。","lunar":"辛未年三月廿五"},{"_id":"19360512","title":"国民党元老胡汉民病逝","pic":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/200905/12/FB01544792.jpg","year":1936,"month":5,"day":12,"des":"在84年前的今天，1936年5月12日 (农历闰三月廿二)，国民党元老胡汉民病逝。","lunar":"丙子年闰三月廿二"},{"_id":"19390512","title":"诺门坎事件发生","pic":"","year":1939,"month":5,"day":12,"des":"在81年前的今天，1939年5月12日 (农历三月廿三)，诺门坎事件发生。","lunar":"己卯年三月廿三"},{"_id":"19400512","title":"日军执行\u201c101号作战协定\u201d空袭重庆","pic":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/200905/12/FE01654410.jpg","year":1940,"month":5,"day":12,"des":"在80年前的今天，1940年5月12日 (农历四月初六)，日军执行\u201c101号作战协定\u201d空袭重庆。","lunar":"庚辰年四月初六"},{"_id":"19430512","title":"日本在台湾实行海军\u201c志愿兵制度\u201d","pic":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/200905/17/A116541778.jpg","year":1943,"month":5,"day":12,"des":"在77年前的今天，1943年5月12日 (农历四月初九)，日本在台湾实行海军\u201c志愿兵制度\u201d。","lunar":"癸未年四月初九"},{"_id":"19430512m1","title":"北非的战争结束","pic":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/200905/17/3816545422.jpg","year":1943,"month":5,"day":12,"des":"在77年前的今天，1943年5月12日 (农历四月初九)，北非的战争结束。","lunar":"癸未年四月初九"},{"_id":"19490512","title":"柏林封锁解除","pic":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/200905/12/D221953753.jpg","year":1949,"month":5,"day":12,"des":"1949年5月12日 (农历四月十五)，柏林封锁解除。","lunar":"己丑年四月十五"},{"_id":"19490512m1","title":"解放军发动上海战役","pic":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/200905/17/B616117984.jpg","year":1949,"month":5,"day":12,"des":"1949年5月12日 (农历四月十五)，解放军发动上海战役。","lunar":"己丑年四月十五"},{"_id":"19510512","title":"第一颗氢弹爆炸试验成功","pic":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/200905/12/B501854837.jpg","year":1951,"month":5,"day":12,"des":"1951年5月12日 (农历四月初七)，第一颗氢弹爆炸试验成功。","lunar":"辛卯年四月初七"},{"_id":"19560512","title":"国务院调整组织机构","pic":"","year":1956,"month":5,"day":12,"des":"1956年5月12日 (农历四月初三)，国务院调整组织机构。","lunar":"丙申年四月初三"},{"_id":"19680512","title":"中国现代雕塑家萧传玖逝世","pic":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/201005/10/C7235548331.jpg","year":1968,"month":5,"day":12,"des":"1968年5月12日 (农历四月十六)，中国现代雕塑家萧传玖逝世。","lunar":"戊申年四月十六"},{"_id":"19770512","title":"苏丹解除苏联军事专家在苏丹工作的合同","pic":"","year":1977,"month":5,"day":12,"des":"1977年5月12日 (农历三月廿五)，苏丹解除苏联军事专家在苏丹工作的合同。","lunar":"丁巳年三月廿五"},{"_id":"19790512","title":"中央批准撤销\u201c五·一二\u201d对体育系统的命令","pic":"","year":1979,"month":5,"day":12,"des":"1979年5月12日 (农历四月十七)，中央批准撤销\u201c五·一二\u201d对体育系统的命令。","lunar":"己未年四月十七"},{"_id":"19880512","title":"中国发表西沙、南沙群岛问题备忘录","pic":"","year":1988,"month":5,"day":12,"des":"1988年5月12日 (农历三月廿七)，中国发表西沙、南沙群岛问题备忘录。","lunar":"戊辰年三月廿七"},{"_id":"19890512","title":"埃总统令全体官员自己掏腰包装汽车电话","pic":"","year":1989,"month":5,"day":12,"des":"1989年5月12日 (农历四月初八)，埃总统令全体官员自己掏腰包装汽车电话。","lunar":"己巳年四月初八"},{"_id":"19900512","title":"首届中国大众文学奖颁奖大会在京举行","pic":"","year":1990,"month":5,"day":12,"des":"1990年5月12日 (农历四月十八)，首届中国大众文学奖颁奖大会在京举行。","lunar":"庚午年四月十八"},{"_id":"19910512m2","title":"中共中央国务院作出决定加强计划生育","pic":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/201303/15/D194330214.jpg","year":1991,"month":5,"day":12,"des":"1991年5月12日 (农历三月廿八)，中共中央国务院作出决定加强计划生育。","lunar":"辛未年三月廿八"},{"_id":"19910512","title":"联合国发表报告　全世界3000万儿童无家可归","pic":"","year":1991,"month":5,"day":12,"des":"1991年5月12日 (农历三月廿八)，联合国发表报告　全世界3000万儿童无家可归。","lunar":"辛未年三月廿八"},{"_id":"19910512m1","title":"上海静安古寺恢复原貌","pic":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/201005/9/AE183653505.jpg","year":1991,"month":5,"day":12,"des":"1991年5月12日 (农历三月廿八)，上海静安古寺恢复原貌。","lunar":"辛未年三月廿八"},{"_id":"19920512","title":"我国与斯洛文尼亚建立外交关系","pic":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/200405/12/300350823.jpg","year":1992,"month":5,"day":12,"des":"1992年5月12日 (农历四月初十)，我国与斯洛文尼亚建立外交关系。","lunar":"壬申年四月初十"},{"_id":"19930512","title":"河姆渡遗址博物馆开放","pic":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/201005/10/78225510623.jpg","year":1993,"month":5,"day":12,"des":"1993年5月12日 (农历闰三月廿一)，河姆渡遗址博物馆开放。","lunar":"癸酉年闰三月廿一"},{"_id":"19970512","title":"东方红三号通信卫星发射升空","pic":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/201005/10/09225615716.jpg","year":1997,"month":5,"day":12,"des":"1997年5月12日 (农历四月初六)，东方红三号通信卫星发射升空。","lunar":"丁丑年四月初六"},{"_id":"19990512","title":"斯捷帕申当选俄罗斯代总理","pic":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/200405/12/D2049779.jpg","year":1999,"month":5,"day":12,"des":"1999年5月12日 (农历三月廿七)，斯捷帕申当选俄罗斯代总理。","lunar":"己卯年三月廿七"},{"_id":"20030512","title":"利雅得发生3起爆炸造成213人伤亡","pic":"","year":2003,"month":5,"day":12,"des":"2003年5月12日 (农历四月十二)，利雅得发生3起爆炸造成213人伤亡。","lunar":"癸未年四月十二"},{"_id":"20050512","title":"胡锦涛和宋楚瑜在北京举行正式会谈","pic":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/201005/10/C722586121.jpg","year":2005,"month":5,"day":12,"des":"2005年5月12日 (农历四月初五)，胡锦涛和宋楚瑜在北京举行正式会谈。","lunar":"乙酉年四月初五"},{"_id":"20080512","title":"四川汶川发生8.0级大地震","pic":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/200905/11/7E225518399.jpg","year":2008,"month":5,"day":12,"des":"2008年5月12日 (农历四月初八)，四川汶川发生8.0级大地震。","lunar":"戊子年四月初八"},{"_id":"20100512","title":"利比亚坠机事故致103人遇难1人幸存","pic":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/201005/12/0D225115217.jpg","year":2010,"month":5,"day":12,"des":"2010年5月12日 (农历三月廿九)，利比亚坠机事故致103人遇难1人幸存。","lunar":"庚寅年三月廿九"},{"_id":"20100512m1","title":"中国50天发生6起校园惨案","pic":"","year":2010,"month":5,"day":12,"des":"2010年5月12日 (农历三月廿九)，中国50天发生6起校园惨案。","lunar":"庚寅年三月廿九"}]
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
//Intent传值必须实现Serializable
    public static class ResultBean implements Serializable {
        /**
         * _id : 17550512
         * title : 意大利小提琴家、作曲家维奥蒂诞生
         * pic : http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/201005/10/2923150878.jpg
         * year : 1755
         * month : 5
         * day : 12
         * des : 在265年前的今天，1755年5月12日 (农历四月初二)，意大利小提琴家、作曲家维奥蒂诞生。
         * lunar : 乙亥年四月初二
         */

        private String _id;
        private String title;
        private String pic;
        private int year;
        private int month;
        private int day;
        private String des;
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

        public String getLunar() {
            return lunar;
        }

        public void setLunar(String lunar) {
            this.lunar = lunar;
        }
    }
}
