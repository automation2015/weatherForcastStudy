package todayinhistory.atys.beans;

public class LaohuangliBean {

    /**
     * reason : successed
     * result : {"id":"3690","yangli":"2020-05-12","yinli":"庚子(鼠)年四月二十","wuxing":"大溪水 开执位","chongsha":"冲鸡(己酉)煞西","baiji":"乙不栽植千株不长 卯不穿井水泉不香","jishen":"母仓 阴德 时阴 生气 普护 五合 官日 鸣犬对","yi":"嫁娶 祭祀 祈福 求嗣 斋醮 订盟 纳采 解除 出行 动土 破土 习艺 针灸 理发 会亲友 起基 修造 动土 竖柱 定磉 安床 拆卸 纳畜 牧养 放水 破土 除服 成服 修坟 立碑","xiongshen":"灾煞 天火 四耗 元武","ji":"开市 入宅 探病 出火 盖屋"}
     * error_code : 0
     */

    private String reason;
    private ResultBean result;
    private int error_code;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean {
        /**
         * id : 3690
         * yangli : 2020-05-12
         * yinli : 庚子(鼠)年四月二十
         * wuxing : 大溪水 开执位
         * chongsha : 冲鸡(己酉)煞西
         * baiji : 乙不栽植千株不长 卯不穿井水泉不香
         * jishen : 母仓 阴德 时阴 生气 普护 五合 官日 鸣犬对
         * yi : 嫁娶 祭祀 祈福 求嗣 斋醮 订盟 纳采 解除 出行 动土 破土 习艺 针灸 理发 会亲友 起基 修造 动土 竖柱 定磉 安床 拆卸 纳畜 牧养 放水 破土 除服 成服 修坟 立碑
         * xiongshen : 灾煞 天火 四耗 元武
         * ji : 开市 入宅 探病 出火 盖屋
         */

        private String id;
        private String yangli;
        private String yinli;
        private String wuxing;
        private String chongsha;
        private String baiji;
        private String jishen;
        private String yi;
        private String xiongshen;
        private String ji;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getYangli() {
            return yangli;
        }

        public void setYangli(String yangli) {
            this.yangli = yangli;
        }

        public String getYinli() {
            return yinli;
        }

        public void setYinli(String yinli) {
            this.yinli = yinli;
        }

        public String getWuxing() {
            return wuxing;
        }

        public void setWuxing(String wuxing) {
            this.wuxing = wuxing;
        }

        public String getChongsha() {
            return chongsha;
        }

        public void setChongsha(String chongsha) {
            this.chongsha = chongsha;
        }

        public String getBaiji() {
            return baiji;
        }

        public void setBaiji(String baiji) {
            this.baiji = baiji;
        }

        public String getJishen() {
            return jishen;
        }

        public void setJishen(String jishen) {
            this.jishen = jishen;
        }

        public String getYi() {
            return yi;
        }

        public void setYi(String yi) {
            this.yi = yi;
        }

        public String getXiongshen() {
            return xiongshen;
        }

        public void setXiongshen(String xiongshen) {
            this.xiongshen = xiongshen;
        }

        public String getJi() {
            return ji;
        }

        public void setJi(String ji) {
            this.ji = ji;
        }
    }
}
