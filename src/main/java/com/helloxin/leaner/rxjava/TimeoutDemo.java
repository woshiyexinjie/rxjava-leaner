package com.helloxin.leaner.rxjava;

import rx.Observable;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by nandiexin on 2018/8/30.
 */
public class TimeoutDemo {

    public static void  timeoutTest() throws IOException {

//        Observable.concat(
//                Observable.interval(100, TimeUnit.MILLISECONDS).take(3),
//                Observable.interval(500, TimeUnit.MILLISECONDS).take(3),
//                Observable.interval(100, TimeUnit.MILLISECONDS).take(3)
//        )
//                .scan(0, (acc, v) -> acc+1)
//                .timeout(200, TimeUnit.MILLISECONDS)
//                .subscribe(
//                        System.out::println,
//                        System.out::println);

//        Observable.concat(
////                Observable.interval(100, TimeUnit.MILLISECONDS).take(3),
////                Observable.interval(500, TimeUnit.MILLISECONDS).take(3),
////                Observable.interval(100, TimeUnit.MILLISECONDS).take(3)
////        )
////                .scan(0, (acc, v) -> acc+1)
////                .timeout(200, TimeUnit.MILLISECONDS, Observable.just(-1))
////                .subscribe(
////                        System.out::println,
////                        System.out::println);

        Observable.concat(
                Observable.interval(100, TimeUnit.MILLISECONDS).take(3),
                Observable.interval(500, TimeUnit.MILLISECONDS).take(3),
                Observable.interval(100, TimeUnit.MILLISECONDS).take(3)
        )
                .scan(0, (acc, v) -> acc+1)
                .timeout(i -> Observable.timer(200, TimeUnit.MILLISECONDS))
                .subscribe(
                        System.out::println,
                        System.out::println);

        System.in.read();
    }

    public static void main(String[] args) throws IOException {
        timeoutTest();
    }
}
