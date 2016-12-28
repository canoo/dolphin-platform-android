package com.canoo.dolphin.client.android;

import android.app.Activity;
import android.os.Bundle;
import com.canoo.dolphin.client.ClientContext;
import com.canoo.dolphin.client.ClientContextFactory;
import com.canoo.dolphin.client.ControllerProxy;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.function.BiConsumer;

public abstract class AbstractDolphinActivity<T> extends Activity {

    private final AndroidConfiguration androidConfiguration;

    private final String controllerName;

    public AbstractDolphinActivity(final AndroidConfiguration androidConfiguration, final String controllerName) {
        this.androidConfiguration = androidConfiguration;
        this.controllerName = controllerName;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            ClientContextFactory.connect(androidConfiguration).whenComplete(new BiConsumer<ClientContext, Throwable>() {
                @Override
                public void accept(final ClientContext clientContext, Throwable throwable) {
                    if (throwable != null) {
                        throw new RuntimeException("Can not create Connection for " + androidConfiguration.getServerEndpoint(), throwable);
                    }
                    clientContext.createController(controllerName).whenComplete(new BiConsumer<ControllerProxy<Object>, Throwable>() {
                        @Override
                        public void accept(ControllerProxy<Object> objectControllerProxy, Throwable throwable) {
                            if (throwable != null) {
                                throw new RuntimeException("Can not create Controller " + controllerName, throwable);
                            }
                            onInitialized((ControllerProxy<T>) objectControllerProxy);
                        }
                    });
                }
            });

        } catch (Exception e) {
            throw new RuntimeException("ARG", e);
        }
    }

    protected abstract void onInitialized(ControllerProxy<T> controllerProxy);

    protected static URL createUrl(final String url) {
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Can not create URL for " + url, e);
        }
    }

    protected void bla() {
    }

}
