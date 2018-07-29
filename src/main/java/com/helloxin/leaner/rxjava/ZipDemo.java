package com.helloxin.leaner.rxjava;

import rx.Observable;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Created by nandiexin on 2018/7/29.
 */
public class ZipDemo {

    public void testZip() throws IOException {
//        Observable.zip(
//                Observable.interval(100, TimeUnit.MILLISECONDS)
//                        .doOnNext(i -> System.out.println("Left emits " + i)),
//                Observable.interval(300, TimeUnit.MILLISECONDS)
//                        .doOnNext(i -> System.out.println("Right emits " + i)),
//                (i1,i2) -> i1 + " - " + i2)
//                .take(6)
//                .subscribe(System.out::println);

//        Observable.zip(
//                Observable.interval(100, TimeUnit.MILLISECONDS),
//                Observable.interval(150, TimeUnit.MILLISECONDS),
//                Observable.interval(050, TimeUnit.MILLISECONDS),
//                (i1,i2,i3) -> i1 + " - " + i2 + " - " + i3)
//                .take(6)
//                .subscribe(System.out::println);

        Observable.zip(
                Observable.range(0, 5),
                Observable.range(0, 3),
                Observable.range(0, 8),
                (i1,i2,i3) -> i1 + " - " + i2 + " - " + i3)
                .count()
                .subscribe(System.out::println);

        System.in.read();
    }

    public void testWithTest() throws IOException {
//        Observable.interval(100, TimeUnit.MILLISECONDS)
//                .zipWith(
//                        Observable.interval(150, TimeUnit.MILLISECONDS),
//                        (i1,i2) -> i1 + " - " + i2)
//                .take(6)
//                .subscribe(System.out::println);

        Observable.range(0, 5)
                .zipWith(
                        Arrays.asList(0,2,4,6,8),
                        (i1,i2) -> i1 + " - " + i2)
                .subscribe(System.out::println);
        System.in.read();
    }


    public void combineLatestTest() throws IOException {
        Observable.combineLatest(
                Observable.interval(100, TimeUnit.MILLISECONDS)
                        .doOnNext(i -> System.out.println("Left emits")),
                Observable.interval(150, TimeUnit.MILLISECONDS)
                        .doOnNext(i -> System.out.println("Right emits")),
                (i1,i2) -> i1 + " - " + i2
        )
                .take(6)
                .subscribe(System.out::println);
        System.in.read();

    }

    public static void main(String[] args) throws IOException {

        ZipDemo zipDemo = new ZipDemo();
        zipDemo.combineLatestTest();
    }
}
