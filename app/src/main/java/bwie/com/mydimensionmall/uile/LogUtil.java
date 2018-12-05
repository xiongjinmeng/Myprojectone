package bwie.com.mydimensionmall.uile;

import android.text.TextUtils;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

public class LogUtil {
    public static void init() {
        PrettyFormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)
                .methodCount(0)
                .methodOffset(7)
                .tag("baway")
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
    }

    public static void d(String s, String string) {
        if (!TextUtils.isEmpty(string)){
            Logger.t(s).d(string);
        }
    }

    public static void d(String string) {
        if (!TextUtils.isEmpty(string)){
            Logger.d(string);
        }
    }
}
