package com.helloxin.leaner.rxjava.prepare;

import rx.Subscriber;

/**
 * Created by nandiexin on 2018/3/23.
 */
public class PrintSubscriber extends Subscriber {
    private final String name;
    public PrintSubscriber(String name) {
        this.name = name;
    }
    @Override
    public void onCompleted() {
        System.out.println(name + ": Completed");
    }
    @Override
    public void onError(Throwable e) {
        System.out.println(name + ": Error: " + e);
    }
    @Override
    public void onNext(Object v) {
        System.out.println(name + ": " + v);
    }
}
