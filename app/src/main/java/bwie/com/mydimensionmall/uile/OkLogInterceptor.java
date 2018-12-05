package bwie.com.mydimensionmall.uile;

import android.util.Log;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

class OkLogInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String method = request.method();
        okhttp3.HttpUrl url = request.url();
        LogUtil.d("本次请求", "ulr：" + url.toString() + "  method:" + method);
        Headers headers = request.headers();
        Set<String> names = headers.names();
        Iterator<String> iterator = names.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            String value = headers.get(next);
            LogUtil.d(next + ":" + value);
        }
        return chain.proceed(request);
    }
}
