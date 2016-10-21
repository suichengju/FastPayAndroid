package com.example.qinfen.MainActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.qinfen.R;
import com.goodsrc.qynglibrary.utils.MSPUtils;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends Activity {
    private SplashActivity me;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        me = this;
        Timer timer = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                final MSPUtils mspUtils = new MSPUtils(me, "start");
                String isok = mspUtils.getString("isok");
//                if(MTextUtils.isEmpty(isok)){//引导页
//                    startActivity(new Intent(me, ViewPageActivity.class));//
//                    me.finish();
//                }else{
                startActivity(new Intent(me, MainActivity.class));//
                me.finish();
//                }
            }
        };
        timer.schedule(task, 1000 * 2); //2秒后
    }

}
