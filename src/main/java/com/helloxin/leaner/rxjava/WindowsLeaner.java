package com.helloxin.leaner.rxjava;

import rx.Observable;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by nandiexin on 2018/12/11.
 */
public class WindowsLeaner {

    public static void testWindows() throws IOException {

//        Observable
//                .merge(
//                        Observable.range(0, 5)
//                                .window(3,1))
//                .subscribe(System.out::println);


//        Observable.range(0, 5)
//                .window(3, 1)
//                .flatMap(o -> o.toList())
//                .subscribe(System.out::println);

//        Observable.interval(100, TimeUnit.MILLISECONDS)
//                .take(5)
//                .window(250, 100, TimeUnit.MILLISECONDS)
//                .flatMap(o -> o.toList())
//                .subscribe(System.out::println);
//
//        System.in.read();

        Observable.interval(100, TimeUnit.MILLISECONDS)
                .take(5)
                .window(
                        Observable.interval(100, TimeUnit.MILLISECONDS),
                        o -> Observable.timer(250, TimeUnit.MILLISECONDS))
                .flatMap(o -> o.toList())
                .subscribe(System.out::println);

        System.in.read();

    }

    public static void main(String[] args) throws IOException {


        testWindows();
    }
}
