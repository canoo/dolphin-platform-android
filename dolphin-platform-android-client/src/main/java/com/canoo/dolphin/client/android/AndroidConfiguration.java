package com.canoo.dolphin.client.android;


import com.canoo.dolphin.client.ClientConfiguration;

import java.net.URL;
import java.util.concurrent.Executors;

public class AndroidConfiguration extends ClientConfiguration {

    /**
     * Default constructor of a client configuration
     *
     * @param serverEndpoint  the Dolphin Platform server url
     */
    public AndroidConfiguration(URL serverEndpoint) {
        super(serverEndpoint, Executors.newCachedThreadPool());
    }
}
