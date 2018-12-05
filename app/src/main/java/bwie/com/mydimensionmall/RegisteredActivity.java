package bwie.com.mydimensionmall;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import bwie.com.mydimensionmall.boods.RegisterBoods;
import bwie.com.mydimensionmall.mvp.presenter.LoginPresenter;
import bwie.com.mydimensionmall.mvp.view.ILoginView;
import bwie.com.mydimensionmall.mvp.view.IRegisteredView;

public class RegisteredActivity extends AppCompatActivity implements ILoginView {

    private EditText registered_name;
    private EditText registered_prass;
    private EditText edit_validation;
    private TextView text_validation;
    private TextView text_an_account;
    private TextView text_immediately_login;
    private Button bun_registered;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);
        //初始化
        initLayout();
        initData();
        initListening();
    }

    private void initLayout() {
        registered_name = findViewById(R.id.edit_registered_name);
        registered_prass = findViewById(R.id.edit_registered_prass);
        edit_validation = findViewById(R.id.edit_validation);
        bun_registered = findViewById(R.id.bun_registered);
        //获取验证码
        text_validation = findViewById(R.id.text_validation);
        //已有账户?
        text_an_account = findViewById(R.id.text_an_account);
        //立即登录
        text_immediately_login = findViewById(R.id.text_immediately_login);
        presenter = new LoginPresenter(this);
    }

    private void initData() {


    }

    private void initListening() {
        //注册
        bun_registered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = registered_name.getText().toString().trim();
                String prass = registered_prass.getText().toString().trim();
                presenter.registered(name,prass);
            }
        });
    }

    //成功
    @Override
    public void onLoginSucess(String string) {
        Log.i("------",""+string);
        //返回的数据
        Gson gson = new Gson();
        RegisterBoods registerBoods = gson.fromJson(string, RegisterBoods.class);
        String message = registerBoods.getMessage();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        //成功后跳转回登录页面
        finish();
    }

    //失败
    @Override
    public void onLoginFailer(String string) {
        Log.i("-----",string);
        //返回的数据
        Gson gson = new Gson();
        RegisterBoods registerBoods = gson.fromJson(string, RegisterBoods.class);
        String message = registerBoods.getMessage();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
