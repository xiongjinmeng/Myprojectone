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

import java.util.List;

import bwie.com.mydimensionmall.boods.LoginBoods;
import bwie.com.mydimensionmall.mvp.presenter.LoginPresenter;
import bwie.com.mydimensionmall.mvp.view.ILoginView;

public class MainActivity extends AppCompatActivity implements ILoginView {

    private Button bun_login;
    private EditText edit_login_name;
    private EditText edit_login_prass;
    private TextView text_jump_registered;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化
        initLayout();
        initData();
        initListening();
    }

    private void initLayout() {
        bun_login = findViewById(R.id.bun_login);
        edit_login_name = findViewById(R.id.edit_login_name);
        edit_login_prass = findViewById(R.id.edit_login_prass);
        text_jump_registered = findViewById(R.id.text_jump_registered);
        presenter = new LoginPresenter(this);
    }

    private void initData() {

    }

    private void initListening() {
        //跳转注册页面
        text_jump_registered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisteredActivity.class);
                startActivity(intent);
            }
        });
        //登录
        bun_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edit_login_name.getText().toString().trim();
                String prass = edit_login_prass.getText().toString().trim();
//                presenter.login(name,prass);
                Intent intent = new Intent(MainActivity.this, ShopActivity.class);
                startActivity(intent);
            }
        });
    }

    //成功
    @Override
    public void onLoginSucess(String string) {
        Log.i("----------",""+string);
        //传回的数据
        Gson gson = new Gson();
        LoginBoods loginBoods = gson.fromJson(string, LoginBoods.class);
        String message = loginBoods.getMessage();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, ShopActivity.class);
        startActivity(intent);
    }

    //失败
    @Override
    public void onLoginFailer(String string) {
        Log.i("-----",string);
        //传回的数据
        Gson gson = new Gson();
        LoginBoods loginBoods = gson.fromJson(string, LoginBoods.class);
        String message = loginBoods.getMessage();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
