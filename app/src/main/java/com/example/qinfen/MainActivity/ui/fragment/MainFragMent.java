package com.example.qinfen.MainActivity.ui.fragment;


import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.example.qinfen.MainActivity.base.BaseFragment;
import com.example.qinfen.MainActivity.config.FastPayApplication;
import com.example.qinfen.MainActivity.config.FastPayConstant;
import com.example.qinfen.MainActivity.ui.VipManageUI.FastPayActivity;
import com.example.qinfen.MainActivity.ui.LoginActivity;
import com.example.qinfen.R;
import com.goodsrc.qynglibrary.utils.MTextUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 设置主页面
 *
 * @author suichengju
 *         created at 2016/9/7 16:23
 */
public class MainFragMent extends BaseFragment {


    private int[] icon;
    private String[] iconName;
    private ArrayList<Map<String, Object>> data_list;
    private Object data;
    private SimpleAdapter sim_adapter;
    private GridView gvMain;

    @Override
    protected int getContentResid() {
        return R.layout.main_fragment;
    }

    @Override
    protected void init(View view) {
        gvMain = (GridView) view.findViewById(R.id.gv_main);
    }

    @Override
    protected void loadDatas() {
        icon = new int[]{R.drawable.a,
                R.drawable.b, R.drawable.c, R.drawable.d,
                R.drawable.e, R.drawable.f, R.drawable.g,
                R.drawable.h, R.drawable.i, R.drawable.j,
                R.drawable.k, R.drawable.l};
        iconName = new String[]{"快速消费", "商品消费", "扫码消费", "计次消费", "新建会员", "会员充值", "会员冲次",
                "积分兑换", "退款", "库存盘点", "自助付款", "房台消费"};
    }

    @Override
    protected void initset() {
        //新建List
        data_list = new ArrayList<Map<String, Object>>();
        //获取数据
        getData();
        //新建适配器
        String[] from = {"image", "text"};
        int[] to = {R.id.image, R.id.text};
        sim_adapter = new SimpleAdapter(getContext(), data_list, R.layout.item, from, to);
        //配置适配器
        gvMain.setAdapter(sim_adapter);
        gvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (MTextUtils.isEmpty(FastPayApplication.getPhone())) {//判断是否登陆
                    Intent i = new Intent(getContext(), LoginActivity.class);
                    startActivityForResult(i, FastPayConstant.IS_LOGIN);
                } else {
                    String titlename = iconName[position];
                    if (position == 0) {//快速消费
                        Intent intent = new Intent(getContext(), FastPayActivity.class);
                        intent.putExtra("title", titlename);
                        startActivity(intent);
                    } else if (position == 1) {

                    }
                }
            }
        });
    }

    public List<Map<String, Object>> getData() {
        //cion和iconName的长度是相同的，这里任选其一都可以
        for (int i = 0; i < icon.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", icon[i]);
            map.put("text", iconName[i]);
            data_list.add(map);
        }

        return data_list;
    }
}
