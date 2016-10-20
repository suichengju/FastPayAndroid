package com.example.qinfen.MainActivity.config;

/**
 * 接口地址配置
 * Created by zcw on 2016/5/13.
 */
public class ParkingConfig {

    public static final String IP = "http://app.tehaoting.cn";//测试版接口地址


    //*****************接口配置***************************
    /**
     * 获取停车场
     */
    public static final String GETBOUND = IP + "/T_TingCheChang/GetBound";
    public static final String GETBOUND4APP = IP + "/T_TingCheChang/GetBound4APP";
    public static final String GETPARKDETAIL = IP+ "/T_TingCheChang/mapDetails";

    /**
     * 我的车辆
     */
    public class WoDeCheLiang {
        /**
         * 车牌列表
         */
        public static final String T_ChePai = IP + "/T_ChePai/List";
        /**
         * 添加车牌
         */
        public static final String AddChePai = IP+"/T_ChePai/AddChePai";
        /**
         * 删除车牌
         */
        public static final String DelChePai = IP+"/T_ChePai/DelChePai";
        /**
         * 品牌车系列表
         */
        public static final String ChePaiList = IP +"/T_ChePai/RenZheng";
        /**
         * 历史停车记录
         */
        public static final String TingCheJiLu = IP +"/T_TingCheJiLu/List4TingCheJiLu";
        /**
         * 提交认证
         */
        public static final String RenZhengSave = IP+ "/T_ChePai/RenZhengSave";
        /**
         * 充值及消费记录
         */
        public static final String ChongZhiXiaoFei = IP+ "/M_QianBao/QianBaoJiLu";
    }


    /**
     * 获取充电桩
     */
    public static final String GETBOUNDFORCHONG = IP + "/T_ChongDianZhan/GetBound";
    public static final String GETBOUND4APPCHONG = IP + "/T_ChongDianZhan/GetBound4APP";
    /**
     * 获取验证码
     */
    public static final String SENDYANZHENGMA4REG = IP + "/M_User/SendYanZhengMa4Reg";
    /**
     * 通过验证码登录
     */
    public static final String REG = IP + "/M_User/Reg";
    public static final String LOGOUT = IP +"/M_User/Logout";
    /**
     * 加载设置
     */
    public static final String SET = IP + "/M_Set/Set";

    /**
     * 加载钱包
     */
    public static final String WALLET = IP +"/M_QianBao/QianBao";
    /**
     * 获取红包
     */
    public static final String HoneBao = IP +"/M_QianBao/HongBao";

}
