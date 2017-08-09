package com.codespurt.eventbus;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by CodeSpurt on 09-08-2017.
 */

public class GlobalBus {

    private static EventBus bus;

    public static EventBus getBus() {
        if (bus == null) {
            bus = EventBus.getDefault();
        }
        return bus;
    }
}
