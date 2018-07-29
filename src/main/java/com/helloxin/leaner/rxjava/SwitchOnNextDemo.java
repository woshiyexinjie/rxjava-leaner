package com.helloxin.leaner.rxjava;


import rx.Observable;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by nandiexin on 2018/7/9.
 */
public class SwitchOnNextDemo {


    public void switchOnNextTest() throws IOException {

        Observable.switchOnNext(
                Observable.interval(100, TimeUnit.MILLISECONDS)
                        .map(i ->
                                Observable.interval(30, TimeUnit.MILLISECONDS)
                                        .map(i2 -> i)
                        )
        )
                .take(9)
                .subscribe(System.out::println);

        System.in.read();


    }

    public void switchMapTest() throws IOException {
        Observable.interval(100, TimeUnit.MILLISECONDS)
                .switchMap(i ->
                        Observable.interval(30, TimeUnit.MILLISECONDS)
                                .map(l -> i))
                .take(9)
                .subscribe(System.out::println);

        System.in.read();
    }


    public static void main(String[] args) throws IOException {

        SwitchOnNextDemo switchOnNextDemo = new SwitchOnNextDemo();
        switchOnNextDemo.switchMapTest();
    }
}
