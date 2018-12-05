package bwie.com.mydimensionmall.mvp.model;

public interface ILoginModel {
    void onLoginFailer(String string);
    void onLoginSucess(String string);
    void onDataEmpity();
}
