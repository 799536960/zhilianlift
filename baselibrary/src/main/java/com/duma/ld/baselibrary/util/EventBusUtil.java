package com.duma.ld.baselibrary.util;


import com.duma.ld.baselibrary.model.EventModel;

import org.greenrobot.eventbus.EventBus;


/**
 * Created by liudong on 2017/6/6.
 */

public class EventBusUtil {
    public static void register(Object subscriber) {
        EventBus.getDefault().register(subscriber);
    }

    public static void unregister(Object subscriber) {
        EventBus.getDefault().unregister(subscriber);
    }

    public static void sendModel(int code, Object data) {
        EventBus.getDefault().post(new EventModel<>(code, data));
    }

    public static void sendModel(int code, String message) {
        EventBus.getDefault().post(new EventModel<>(code, message));
    }

    public static void sendModel(int code, Object data, String message) {
        EventBus.getDefault().post(new EventModel<>(code, data, message));
    }

    public static void sendModel(int code) {
        EventBus.getDefault().post(new EventModel<>(code));
    }

}
