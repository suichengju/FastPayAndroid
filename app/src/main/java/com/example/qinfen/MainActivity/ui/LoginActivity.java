package com.example.qinfen.MainActivity.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.EditText;

import com.example.qinfen.MainActivity.MainActivity;
import com.example.qinfen.MainActivity.base.BaseActivity;
import com.example.qinfen.MainActivity.config.ParkApplication;
import com.example.qinfen.MainActivity.model.FastPayUserModel;
import com.example.qinfen.R;
import com.goodsrc.qynglibrary.utils.permissions.PermissionsManager;
import com.goodsrc.qynglibrary.utils.permissions.PermissionsResultAction;

/**
 * 类介绍：登录页
 * 作者：suichengju on 2016/10/19 14:40
 * 邮箱：325927775@qq.com
 */
public class LoginActivity extends BaseActivity {
    LoginActivity me;
    String testTopic = "";
    private LocalBroadcastManager instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        init();
        me = this;
    }

    @Override
    protected void init() {
        setTitle("登录");
        ParkApplication.init(getApplication());
        PermissionsManager.getInstance().requestAllManifestPermissionsIfNecessary(this, new PermissionsResultAction() {
            @Override
            public void onGranted() {

            }

            @Override
            public void onDenied(String permission) {

            }
        });
    }


    /**
     * 初始化IM
     */
    public void initIMApplication(String userName, String pwd) {
        Intent intent = new Intent(me, MainActivity.class);
        FastPayUserModel model = new FastPayUserModel();
        model.setPassword(pwd);
        model.setPhone(userName);
        ParkApplication.setToken(userName);
        ParkApplication.setPhone(userName);
        ParkApplication.setDeviceid(pwd);
        intent.putExtra("user", model);
        startActivity(intent);
    }

    public void login(View view) {
        EditText userNameEt = (EditText) findViewById(R.id.username);
        EditText pwdEt = (EditText) findViewById(R.id.password);
        String userName = userNameEt.getText().toString();
        String pwd = pwdEt.getText().toString();
        initIMApplication(userName, pwd);
    }


}
