package com.example.qinfen.MainActivity.ui.fragment;


import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.qinfen.MainActivity.base.AbsBaseAdapter;
import com.example.qinfen.MainActivity.base.BaseFragment;
import com.example.qinfen.MainActivity.config.FastPayConfig;
import com.example.qinfen.MainActivity.ui.VipUsetDetailsActivity;
import com.example.qinfen.MainActivity.utils.LayoutAniMationUtils;
import com.example.qinfen.MainActivity.widget.NiceSpinner.NiceSpinner;
import com.example.qinfen.R;
import com.goodsrc.qynglibrary.http.HttpManager;

import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 会员管理页面
 *
 * @author suichengju
 *         created at 2016/9/7 16:23
 */
public class VipUserFragMent extends BaseFragment {


    private NiceSpinner paiXuSP;
    private NiceSpinner leVeSP;
    private List<String> dataset;
    private List<String> dataset2;
    private ListView vipUserLv;
    private List<String> dates;

    @Override
    protected int getContentResid() {

        return R.layout.vipuser_activity;
    }

    @Override
    protected void init(View view) {
        paiXuSP = (NiceSpinner) view.findViewById(R.id.sp_paixu);
        vipUserLv = (ListView) view.findViewById(R.id.vipuser_lv);
        leVeSP = (NiceSpinner) view.findViewById(R.id.leve_sp);
        dataset = new LinkedList<>(Arrays.asList("排序", "创建时间", "储金额", "累计消费", "剩余积分", "消费时间"));
        dataset2 = new LinkedList<>(Arrays.asList("等级", "会员默认等级"));
        paiXuSP.attachDataSource(dataset);
        leVeSP.attachDataSource(dataset2);
    }

    @Override
    protected void initset() {
        paiXuSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {//排序点击事件控制

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                paiXuSP.setTag("排序");
            }
        });
        leVeSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {//等级点击事件控制

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                paiXuSP.setTag("等级");
            }
        });
        vipUserLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), VipUsetDetailsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void loadDatas() {
        dates = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            dates.add("30647767" + i);
        }
        AbsBaseAdapter<String> adapter = new AbsBaseAdapter<String>(getContext(), R.layout.vipuserlistitem) {
            @Override
            protected void bindDatas(ViewHolder viewHolder, String data, int position) {
                viewHolder.bindTextView(R.id.cardnum_tv, data);
            }
        };
        adapter.setDatas(dates);
        vipUserLv.setLayoutAnimation(new LayoutAniMationUtils().getAnimationController());
        vipUserLv.setAdapter(adapter);
        String url = FastPayConfig.VIPMANGER.VIPMANGER_USER;
        HttpManager httpManager = new HttpManager.Builder().setHttpMethod(HttpMethod.POST).build();
        RequestParams params = httpManager.params(url);

    }


}
