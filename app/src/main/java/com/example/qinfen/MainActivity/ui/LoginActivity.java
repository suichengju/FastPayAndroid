package com.example.qinfen.MainActivity.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.EditText;

import com.example.qinfen.MainActivity.base.BaseActivity;
import com.example.qinfen.MainActivity.config.FastPayApplication;
import com.example.qinfen.MainActivity.config.FastPayConfig;
import com.example.qinfen.MainActivity.model.dto.LoginReturnModel;
import com.example.qinfen.R;
import com.goodsrc.qynglibrary.http.HttpManager;
import com.goodsrc.qynglibrary.http.RequestCallBack;
import com.goodsrc.qynglibrary.utils.ToastUtil;
import com.goodsrc.qynglibrary.utils.permissions.PermissionsManager;
import com.goodsrc.qynglibrary.utils.permissions.PermissionsResultAction;

import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;

/**
 * 类介绍：登录页
 * 作者：suichengju on 2016/10/19 14:40
 * 邮箱：325927775@qq.com
 */
public class LoginActivity extends BaseActivity {
    LoginActivity me;
    private LocalBroadcastManager instance;
    private int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        me = this;
        setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("flag", 0);
                setResult(RESULT_OK, intent);
                me.finish();
            }
        });
    }

    @Override
    protected void init() {
        setTitle("登录");
        FastPayApplication.init(getApplication());
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
        Intent intent = new Intent();
        FastPayApplication.setPhone(userName);
        FastPayApplication.setPassword(pwd);
        intent.putExtra("flag", flag);
        setResult(RESULT_OK, intent);
    }

    public void login(View view) {
        EditText userNameEt = (EditText) findViewById(R.id.username);
        EditText pwdEt = (EditText) findViewById(R.id.password);
        String userName = userNameEt.getText().toString().trim();
        String pwd = pwdEt.getText().toString().trim();
        initLogin(userName, pwd);
    }

    private void initLogin(final String userName, final String pwd) {
        String url = FastPayConfig.LOGINS.LOGIN;
        HttpManager httpManager = new HttpManager.Builder().setHttpMethod(HttpMethod.POST).build();
        RequestParams params = httpManager.params(url);
        params.addBodyParameter("OperatorTel", userName);
        params.addBodyParameter("Password", pwd);
        httpManager.request(params, new RequestCallBack<LoginReturnModel>() {
            @Override
            public void onSuccess(LoginReturnModel result) {
                Intent intent = new Intent();
                flag = result.getFlag();
                intent.putExtra("flag", flag);
                setResult(RESULT_OK, intent);
                if (flag == 0) {
                    ToastUtil.showShort("用户名或密码错误");
                } else if (flag == 2) {
                    initIMApplication(userName, pwd);
                    ToastUtil.showShort("登陆成功");
                    FastPayApplication.setPhone(userName);
                    FastPayApplication.setPassword(pwd);
                    finish();
                } else if (flag == 3) {
                    ToastUtil.showShort("帐号被管理员锁定");
                }
            }
        });
    }


}
