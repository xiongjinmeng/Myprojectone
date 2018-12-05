package bwie.com.mydimensionmall.uile;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

class OKHeaderInterceptor implements Interceptor {
    private  Map<String,String> headers;
    private Request request;
    private String sessionId;
    private String userId;

    public OKHeaderInterceptor(Map headers) {
        if (headers!=null){
            this.headers=headers;
        }
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request()
                .newBuilder()
                .addHeader("sessionId","sessionId")
                .addHeader("userId","userId")
                .build();
        return chain.proceed(request);
    }
}
