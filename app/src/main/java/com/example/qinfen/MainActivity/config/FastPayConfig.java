package com.example.qinfen.MainActivity.config;

/**
 * 类介绍：
 * 作者：suichengju on 2016/10/24 10:11
 * 邮箱：325927775@qq.com
 */
public class FastPayConfig {
    public static String IP = "http://192.168.14.2:8080/";


    /**
     * 会员管理界面接口URL
     *
     * @author suichengju
     *         created at 2016/10/24 10:19
     */
    public static class VIPMANGER {
        /*会员管理列表*/
        public static String VIPMANGER_USER = IP + "Fycloud/MmansgeServlet.do";
        /*充值记录*/
        public static String SEEK_Recharge = IP + "Fycloud/RechargeServlet.d1";
        /*剩余项目*/
        public static String SEEK_Rechargetime = IP + "Fycloud/RechargeTimeServlet.d3";
        /*消费记录*/
        public static String SEEK_ConsumeCount = IP + "Fycloud/ConsumeCountActionServlet.d2";
        /*积分记录*/
        public static String SEEK_PointDetail = IP + "Fycloud/PointDetailServlet.d4";
        /*冲次记录*/
        public static String SEEK_ChargeTime = IP + "Fycloud/ChargeTimeServlet.d5";
    }

    /*登录*/
    public static class LOGINS {
        public static String LOGIN = IP + "Fycloud/LoginServlet.d6";
    }
}
