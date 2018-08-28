package com.helloxin.leaner.rxjava;

import rx.Observable;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by nandiexin on 2018/8/27.
 */
public class DebouncingDemo {

    public void debouncingTest() throws IOException {

//        Observable.concat(
//                Observable.interval(100, TimeUnit.MILLISECONDS).take(3),
//                Observable.interval(500, TimeUnit.MILLISECONDS).take(3),
//                Observable.interval(100, TimeUnit.MILLISECONDS).take(3)
//        )
//                .scan(0, (acc, v) -> acc+1)
//                .timeInterval()
//                .subscribe(System.out::println);

//        Observable.concat(
//                Observable.interval(100, TimeUnit.MILLISECONDS).take(3),
//                Observable.interval(500, TimeUnit.MILLISECONDS).take(3),
//                Observable.interval(100, TimeUnit.MILLISECONDS).take(3)
//        )
//                .scan(0, (acc, v) -> acc+1)
//                .debounce(150, TimeUnit.MILLISECONDS)
//                .subscribe(System.out::println);

        Observable.concat(
                Observable.interval(100, TimeUnit.MILLISECONDS).take(3),
                Observable.interval(500, TimeUnit.MILLISECONDS).take(3),
                Observable.interval(100, TimeUnit.MILLISECONDS).take(3)
        )
                .scan(0, (acc, v) -> acc+1)
                .debounce(i -> Observable.timer(i * 50, TimeUnit.MILLISECONDS))
                .subscribe(System.out::println);

        System.in.read();

    }

    public static void main(String[] args) throws IOException {

        DebouncingDemo debouncingDemo= new DebouncingDemo();
        debouncingDemo.debouncingTest();
    }
}
