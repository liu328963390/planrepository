package com.big.data.flume;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class LogIntercepte implements Interceptor {

    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {
        byte[] body = event.getBody();
        String log = new String(body, Charset.forName("UTF-8"));
        if (log.contains("start")){
            if (LogUtil.validateStart(log))
                return event;
        }else {
            if (LogUtil.validateEvent(log))
                return event;
        }
        return null;
    }

    @Override
    public List<Event> intercept(List<Event> events) {
        ArrayList<Event> list = new ArrayList<>();
        for (Event event : events) {
            Event intercept = intercept(event);
            if (intercept != null)
                list.add(event);
        }
        return list;
    }

    @Override
    public void close() {

    }

    public static class Builder implements Interceptor.Builder{

        @Override
        public Interceptor build() {
            return new LogIntercepte();
        }

        @Override
        public void configure(Context context) {

        }
    }
}
