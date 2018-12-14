package com.helloxin.leaner.rxjava;

import rx.Observable;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by nandiexin on 2018/12/12.
 */
public class JoinLeaner {


    public static void testJoin() throws IOException {
//
//        Observable<String> left =
//                Observable.interval(100, TimeUnit.MILLISECONDS)
//                        .map(i -> "L" + i);
//        Observable<String> right =
//                Observable.interval(200, TimeUnit.MILLISECONDS)
//                        .map(i -> "R" + i);
//
//        left
//                .join(
//                        right,
//                        i -> Observable.never(),
//                        i -> Observable.timer(0, TimeUnit.MILLISECONDS),
//                        (l,r) -> l + " - " + r
//                )
//                .take(10)
//                .subscribe(System.out::println);

    Observable<String> left =
            Observable.interval(100, TimeUnit.MILLISECONDS)
                    .map(i -> "L" + i);
    Observable<String> right =
            Observable.interval(100, TimeUnit.MILLISECONDS)
                    .map(i -> "R" + i);

    left
                .join(
                        right,
                        i -> Observable.timer(150, TimeUnit.MILLISECONDS),
                        i -> Observable.timer(0, TimeUnit.MILLISECONDS),
                        (l,r) -> l + " - " + r
                )
                .take(10)
                .subscribe(System.out::println);

        System.in.read();
    }

    public static void testGroupJoin() throws IOException {

        Observable<String> left =
                Observable.interval(100, TimeUnit.MILLISECONDS)
                        .map(i -> "L" + i)
                        .take(6);
        Observable<String> right =
                Observable.interval(200, TimeUnit.MILLISECONDS)
                        .map(i -> "R" + i)
                        .take(3);

        left
                .groupJoin(
                        right,
                        i -> Observable.never(),
                        i -> Observable.timer(0, TimeUnit.MILLISECONDS),
                        (l, rs) -> rs.toList().subscribe(list -> System.out.println(l + ": " + list))
                )
                .subscribe();

        System.in.read();

    }


    public static void main(String[] args) throws IOException {

        testGroupJoin();

    }

}
