package com.example.qinfen.MainActivity.model;

import java.io.Serializable;

/**
 * 类介绍：会员
 * 作者：suichengju on 2016/10/24 11:07
 * 邮箱：325927775@qq.com
 */
public class Mmanage implements Serializable {
    private int member_id;  //主键
    private int grade_id;   //会员等级外键，关联自定义会员等级表member_grade
    private String phone_number; //电话号码
    private String member_name;  //会员姓名
    private String member_qq;    //会员QQ
    private int shop_id;         //会员所属店铺ID外键，关联表shopmanage
    private String birth_date;   //生日，由年月日构成字符串‘2015-2-15’
    private String wechat;       //微信号
    private int rent_id;         //企业编码
    private String telephone;    //固定电话
    private String area;         //区域“山东省-烟台市-莱山区”
    private String address;      //详细地址
    private int initial_integration; //初始积分
    private int deposit;         //押金
    private String work_unit;    //工作单位
    private String remark;       //备注
    private String gender;       //性别
    private String id_type;      //证件类型
    private String id_number;    //证件号
    private String member_password; //会员密码
    private String initial_amount;  //初始金额
    private String email;           //电子邮箱
    private String expiry_date;     //有效期
    private String card_number;     //会有卡号
    private String recommend_number;//推荐人卡号
    private int identity;           //会员整体识别id
    private int staff_id;           //导购人员外键关联导购表
    private String image;           //头像
    private String score;			//积分
    private int balance;   		//余额
    private String member_state;    //会员状态
    private String photo;           //会员照片
    private int state;
    private int operator_id;

}
