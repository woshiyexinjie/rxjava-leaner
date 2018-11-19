package com.helloxin.leaner.rxjava;

import rx.Observable;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Created by nandiexin on 2018/11/19.
 */
public class SchedulingandThreadingDemo {

    public static void main(String[] args) throws IOException {

        /*final BehaviorSubject<Integer> subject = BehaviorSubject.create();
        subject.subscribe(i -> {
            System.out.println("Received " + i + " on " + Thread.currentThread().getId());
        });

        int[] i = {1}; // naughty side-effects for examples only ;)
        Runnable r = () -> {
            synchronized(i) {
                System.out.println("onNext(" + i[0] + ") on " + Thread.currentThread().getId());
                subject.onNext(i[0]++);
            }
        };

        r.run(); // Execute on main thread
        new Thread(r).start();
        new Thread(r).start();*/

//        System.out.println("Main: " + Thread.currentThread().getId());
//
//        Observable.create(o -> {
//            System.out.println("Created on " + Thread.currentThread().getId());
//            o.onNext(1);
//            o.onNext(2);
//            o.onCompleted();
//        })
//                .subscribeOn(Schedulers.newThread())
//                .subscribe(i -> {
//                    System.out.println("Received " + i + " on " + Thread.currentThread().getId());
//                });
//
//        System.out.println("Finished main: " + Thread.currentThread().getId());

   /*     System.out.println("Main: " + Thread.currentThread().getId());

        Observable.interval(100, TimeUnit.MILLISECONDS)
                .subscribe(i -> {

                    System.out.println("Received " + i + " on " + Thread.currentThread().getId());
                });

        System.out.println("Finished main: " + Thread.currentThread().getId());
        System.in.read();*/

//        Observable.create(o -> {
//            System.out.println("Created on " + Thread.currentThread().getId());
//            o.onNext(1);
//            o.onNext(2);
//            o.onCompleted();
//        })
//                .observeOn(Schedulers.newThread())
//                .subscribe(i ->
//                        System.out.println("Received " + i + " on " + Thread.currentThread().getId()));

       /* Observable.create(o -> {
            System.out.println("Created on " + Thread.currentThread().getId());
            o.onNext(1);
            o.onNext(2);
            o.onCompleted();
        })
                .doOnNext(i ->
                        System.out.println("Before " + i + " on " + Thread.currentThread().getId()))
                .observeOn(Schedulers.newThread())
                .doOnNext(i ->
                        System.out.println("After " + i + " on " + Thread.currentThread().getId()))
                .subscribe();*/

        Observable<Object> source = Observable.using(
                () -> {
                    System.out.println("Subscribed on " + Thread.currentThread().getId());
                    return Arrays.asList(1,2);
                },
                (ints) -> {
                    System.out.println("Producing on " + Thread.currentThread().getId());
                    return Observable.from(ints);
                },
                (ints) -> {
                    System.out.println("Unubscribed on " + Thread.currentThread().getId());
                }
        );

        source
                .unsubscribeOn(Schedulers.newThread())
                .subscribe(System.out::println);

    }
}
