package bwie.com.mydimensionmall.mvp.presenter;

import bwie.com.mydimensionmall.mvp.model.ILoginModel;
import bwie.com.mydimensionmall.mvp.model.LoginModel;
import bwie.com.mydimensionmall.mvp.view.ILoginView;

public class LoginPresenter {

    private ILoginView iLoginView;
    private final LoginModel model;

    public LoginPresenter(ILoginView iLoginView){
        this.iLoginView=iLoginView;
        model = new LoginModel();
    }

    public void login(String name, String prass) {
        model.login(name,prass, new ILoginModel() {
            @Override
            public void onLoginFailer(String string) {
                iLoginView.onLoginFailer(string);
            }

            @Override
            public void onLoginSucess(String string) {
                iLoginView.onLoginSucess(string);
            }

            @Override
            public void onDataEmpity() {

            }
        });
    }

    public void registered(String name, String prass) {
        model.registered(name,prass, new ILoginModel() {
            @Override
            public void onLoginFailer(String string) {
                iLoginView.onLoginFailer(string);
            }

            @Override
            public void onLoginSucess(String string) {
                iLoginView.onLoginSucess(string);
            }

            @Override
            public void onDataEmpity() {

            }
        });
    }
    public void onDestory(){
        if (iLoginView!=null){
            iLoginView=null;
        }
        model.onDestory();
    }
}
