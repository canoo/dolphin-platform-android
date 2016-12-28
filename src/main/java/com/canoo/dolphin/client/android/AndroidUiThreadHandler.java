package com.canoo.dolphin.client.android;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.canoo.dolphin.util.Assert;
import org.opendolphin.core.client.comm.UiThreadHandler;

public class AndroidUiThreadHandler implements UiThreadHandler {

    private final Handler handler;

    public AndroidUiThreadHandler() {
        this(new Handler(Looper.getMainLooper()));
    }

    public AndroidUiThreadHandler(Context context) {
        Assert.requireNonNull(context, "context");
        this.handler = new Handler(context.getMainLooper());
    }

    public AndroidUiThreadHandler(Handler handler) {
        this.handler = Assert.requireNonNull(handler, "handler");
    }

    @Override
    public void executeInsideUiThread(Runnable runnable) {
        handler.post(runnable);
    }
}
