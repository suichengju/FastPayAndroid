package com.example.qinfen.MainActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.qinfen.MainActivity.base.BaseActivity;
import com.example.qinfen.MainActivity.ui.SetingFragMent;
import com.example.qinfen.MainActivity.utils.permissions.PermissionsManager;
import com.example.qinfen.MainActivity.utils.permissions.PermissionsResultAction;
import com.example.qinfen.R;

import java.util.ArrayList;


public class MainActivity extends BaseActivity {

    private ArrayList<Fragment> fragments;
    private RadioButton zhuye_rbt, cheliang_rbt, qianbao_rbt, shezhi_rbt;
    private ViewPager main_VP;
    private RadioGroup home_radiogroup;
    private long exitTime = 0;// 双击返回
    private int mPosition;

    @Override
    protected int getContentResid() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        //处理android6.0权限
        PermissionsManager.getInstance().requestAllManifestPermissionsIfNecessary(this, new PermissionsResultAction() {
            @Override
            public void onGranted() {

            }

            @Override
            public void onDenied(String permission) {

            }
        });
        fragments = new ArrayList<>();
        fragments.add(new SetingFragMent());
        fragments.add(new SetingFragMent());
        fragments.add(new SetingFragMent());
        fragments.add(new SetingFragMent());
        zhuye_rbt = (RadioButton) findViewById(R.id.zhuye_rbt);
        cheliang_rbt = (RadioButton) findViewById(R.id.cheliang_rbt);
        qianbao_rbt = (RadioButton) findViewById(R.id.qianbao_rbt);
        shezhi_rbt = (RadioButton) findViewById(R.id.shezhi_rbt);
        main_VP = (ViewPager) findViewById(R.id.main_VP);
        home_radiogroup = (RadioGroup) findViewById(R.id.home_radiogroup);
        main_VP.setOffscreenPageLimit(3);
        main_VP.setPageTransformer(true, new DepthPageTransformer());//为了解决viewpager与mapview出现闪黑边的问题。
    }

    @Override
    protected void initset() {
        TabFragmentPagerAdapter adapter = new TabFragmentPagerAdapter(getSupportFragmentManager());
        main_VP.setAdapter(adapter);
        home_radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int indexOfChild = group.indexOfChild(group.findViewById(checkedId));
                main_VP.setCurrentItem(indexOfChild, true);
            }
        });
        ((RadioButton) home_radiogroup.getChildAt(0)).setChecked(true);
        home_radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                if (MTextUtils.isEmpty(ParkApplication.getToken())) {//判断是否登陆
//                    int indexOfChild = group.indexOfChild(group.findViewById(checkedId));
//                    if (indexOfChild != 0) {
//                        setRadioGroup();
//                        Intent i = new Intent(MainActivity.this, LoginActivity.class);
//                        startActivityForResult(i, ParkConstant.FIRSTLOGIN);
//                    }
//                } else {
                int indexOfChild = group.indexOfChild(group.findViewById(checkedId));
                main_VP.setCurrentItem(indexOfChild, true);
//                }
            }
        });
        main_VP.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


            }

            @Override
            public void onPageSelected(int position) {
                ((RadioButton) home_radiogroup.getChildAt(position)).setChecked(true);
                mPosition = position;
                switch (position) {
                    case 0:
                        setTitle("停车场");
                        break;
                    case 1:
                        setTitle("我的车辆");
                        break;
                    case 2:
                        setTitle("我的钱包");
                        break;
                    case 3:
                        setTitle("设置");

                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void setRadioGroup() {
        zhuye_rbt.setChecked(true);
        cheliang_rbt.setChecked(false);
        qianbao_rbt.setChecked(false);
        shezhi_rbt.setChecked(false);
    }

    private class TabFragmentPagerAdapter extends FragmentPagerAdapter {

        public TabFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int arg0) {
            return fragments.get(arg0);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public class DepthPageTransformer implements ViewPager.PageTransformer {//为了解决viewpager与mapview出现闪黑边的问题。
        private static final float MIN_SCALE = 0.75f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 0) { // [-1,0]
                // Use the default slide transition when moving to the left page
                view.setAlpha(1);
                view.setTranslationX(0);
                view.setScaleX(1);
                view.setScaleY(1);

            } else if (position <= 1) { // (0,1]
                // Fade the page out.
                view.setAlpha(1 - position);

                // Counteract the default slide transition
                view.setTranslationX(pageWidth * -position);

                // Scale the page down (between MIN_SCALE and 1)
                float scaleFactor = MIN_SCALE
                        + (1 - MIN_SCALE) * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }
}
