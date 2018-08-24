package com.helloxin.leaner.rxjava;

import rx.Observable;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by nandiexin on 2018/8/22.
 */
public class SampleDemo {

    public void sampleTest() throws IOException {

//        Observable.interval(150, TimeUnit.MILLISECONDS)
//                .sample(1, TimeUnit.SECONDS)
//                .subscribe(System.out::println);

//        Observable.interval(150, TimeUnit.MILLISECONDS)
//                .sample(Observable.interval(1, TimeUnit.SECONDS))
//                .subscribe(System.out::println);

//        Observable.interval(150, TimeUnit.MILLISECONDS)
//                .throttleFirst(1, TimeUnit.SECONDS)
//                .subscribe(System.out::println);

        Observable.interval(150, TimeUnit.MILLISECONDS)
                .throttleLast(1, TimeUnit.SECONDS)
                .subscribe(System.out::println);

        System.in.read();

    }

    public static void main(String[] args) throws IOException {

        SampleDemo sampleDemo = new SampleDemo();
        sampleDemo.sampleTest();

    }
}
