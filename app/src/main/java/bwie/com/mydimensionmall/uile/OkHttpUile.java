package bwie.com.mydimensionmall.uile;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpUile {
    private static OkHttpUile uile;
    private static OkHttpClient okHttpClient;
    private static Handler handler;

    public OkHttpUile(){
        okHttpClient = new OkHttpClient();
        handler = new Handler(Looper.getMainLooper());
    }
    public static void init(Map headers){//拦截器
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(10000,TimeUnit.MILLISECONDS);
        builder.writeTimeout(10000,TimeUnit.MILLISECONDS);
        builder.connectTimeout(10000,TimeUnit.MILLISECONDS);
        builder.addInterceptor(new OKHeaderInterceptor(headers));//添加请求头
        builder.addInterceptor(new OkLogInterceptor());//日志
        okHttpClient = builder.build();
    }
    public static OkHttpUile getInstance(){
        if (uile==null){
            synchronized (OkHttpUile.class){
                if (uile==null){
                    uile = new OkHttpUile();
                }
            }
        }
        return uile;
    }
    public interface HttpCallBack{
        void onError(Request request, IOException e);
        void onSuccess(Request request, String result);
    }

    public class StringCallBack implements Callback{

        private final Request request;
        private final HttpCallBack httpCallBack;

        public StringCallBack(Request request, HttpCallBack httpCallBack){
            this.request=request;
            this.httpCallBack=httpCallBack;
        }

        @Override
        public void onFailure(Call call, IOException e) {
            final IOException ex=e;
            if (httpCallBack!=null){
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        httpCallBack.onError(request,ex);
                    }
                });
            }
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            int code = response.code();
            final String result = response.body().string();
            switch (code){
                case 200:
                    //解析
                    if (httpCallBack!=null){
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                httpCallBack.onSuccess(request,result);
                            }
                        });
                    }
                break;
                case 307:
                    //读取缓存
                    break;
                case 400:
                    //bad request
                    break;
                case 404:
                    //资源不存在
                    break;
                    default:
                        //服务器错误
                        break;
            }

        }
    }
    //get方法
    public void asyncGet(String url,HttpCallBack httpCallBack){
        Request request = new Request.Builder().url(url).get().build();
        okHttpClient.newCall(request).enqueue(new StringCallBack(request,httpCallBack));
    }
    //post方法
    public void asyncPost(String url, FormBody formBody, HttpCallBack httpCallBack){
        Request request = new Request.Builder().url(url).post(formBody).build();
        okHttpClient.newCall(request).enqueue(new StringCallBack(request,httpCallBack));
    }
}
