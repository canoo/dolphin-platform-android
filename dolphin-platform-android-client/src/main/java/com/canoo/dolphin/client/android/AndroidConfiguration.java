package com.canoo.dolphin.client.android;

import android.content.Context;
import android.os.Handler;
import com.canoo.dolphin.client.ClientConfiguration;

import java.net.URL;

public class AndroidConfiguration extends ClientConfiguration {

    /**
     * Default constructor of a client configuration
     *
     * @param serverEndpoint  the Dolphin Platform server url
     */
    public AndroidConfiguration(URL serverEndpoint) {
        super(serverEndpoint, new AndroidUiThreadHandler());
    }

    public AndroidConfiguration(URL serverEndpoint, Context context) {
        super(serverEndpoint, new AndroidUiThreadHandler(context));
    }

    public AndroidConfiguration(URL serverEndpoint, Handler handler) {
        super(serverEndpoint, new AndroidUiThreadHandler(handler));
    }
}
