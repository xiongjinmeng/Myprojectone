package bwie.com.mydimensionmall.mvp.model;

import java.io.IOException;

import bwie.com.mydimensionmall.uile.OkHttpUile;
import okhttp3.FormBody;
import okhttp3.Request;

public class LoginModel {
    private String path = "http://172.17.8.100/small/user/v1/login/";
    private String pathzc = "http://172.17.8.100/small/user/v1/register/";

    public void login(String name, String prass, final ILoginModel iLoginModel) {
        FormBody formBody = new FormBody.Builder().add("phone", name).add("pwd", prass).build();
        OkHttpUile.getInstance().asyncPost(path, formBody, new OkHttpUile.HttpCallBack() {
            @Override
            public void onError(Request request, IOException e) {
                //失败
                iLoginModel.onLoginFailer(e.toString());
            }

            @Override
            public void onSuccess(Request request, String result) {
                iLoginModel.onLoginSucess(result);
            }
        });
    }

    public void registered(String name, String prass, final ILoginModel iLoginModel) {
                FormBody formBody = new FormBody.Builder().add("phone", name).add("pwd", prass).build();
                OkHttpUile.getInstance().asyncPost(pathzc, formBody, new OkHttpUile.HttpCallBack() {
                    @Override
                    public void onError(Request request, IOException e) {
                        //失败
                        iLoginModel.onLoginFailer(e.toString());
                    }

                    @Override
                    public void onSuccess(Request request, String result) {
                        iLoginModel.onLoginSucess(result);
                    }
        });
    }

    public void onDestory() {
        OkHttpUile.getInstance().notify();
    }
}
