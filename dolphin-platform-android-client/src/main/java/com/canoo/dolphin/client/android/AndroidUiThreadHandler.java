package com.canoo.dolphin.client.android;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import com.canoo.dolphin.util.Assert;

import java.util.concurrent.Executor;

public class AndroidUiThreadHandler implements Executor {

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
    public void execute(@NonNull Runnable runnable) {
        handler.post(runnable);
    }
}
