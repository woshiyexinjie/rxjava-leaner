package com.helloxin.leaner.rxjava;

import rx.Observable;
import rx.schedulers.Schedulers;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by nandiexin on 2018/12/14.
 */
public class Backpressure {

    public static void testSyncOperator(){

       // Produce
        Observable<Integer> producer = Observable.create(o -> {
            o.onNext(1);
            o.onNext(2);
            o.onCompleted();
        });
         // Consume
        producer.subscribe(i -> {
            try {
                Thread.sleep(1000);
                System.out.println(i);
            } catch (Exception e) {	}
        });

    }


    public static void testPush() throws IOException {

//        Observable.interval(1, TimeUnit.MILLISECONDS)
//                .observeOn(Schedulers.newThread())
//                .subscribe(
//                        i -> {
//                            System.out.println(i);
//                            try {
//                                Thread.sleep(100);
//                            } catch (Exception e) {	}
//                        },
//                        System.out::println);

//        Observable.interval(1, TimeUnit.MILLISECONDS)
//                .observeOn(Schedulers.newThread())
//                .sample(100, TimeUnit.MILLISECONDS)
//                .subscribe(
//                        i -> {
//                            System.out.println(i);
//                            try {
//                                Thread.sleep(100);
//                            } catch (Exception e) {	}
//                        },
//                        System.out::println);

//        Observable.interval(10, TimeUnit.MILLISECONDS)
//                .observeOn(Schedulers.newThread())
//                .buffer(100, TimeUnit.MILLISECONDS)
//                .subscribe(
//                        i -> {
//                            System.out.println(i);
//                            try {
//                                Thread.sleep(100);
//                            } catch (Exception e) {	}
//                        },
//                        System.out::println);


        ControlledPullSubscriber<Integer> puller =
                new ControlledPullSubscriber<Integer>(System.out::println);

        Observable.range(0, 3)
                .doOnRequest(i -> System.out.println("Requested " + i))
                .subscribe(puller);

        puller.requestMore(2);
        puller.requestMore(1);

        System.in.read();

    }


    public static void testDeOnRequest() throws IOException {

//        Observable.range(0, 3)
//                .doOnRequest(i -> System.out.println("Requested " + i))
//                .subscribe(System.out::println);


//        Observable.range(0, 300)
//                .doOnRequest(i -> System.out.println("Requested " + i))
//                .zipWith(
//                        Observable.range(10, 300),
//                        (i1, i2) -> i1 + " - " + i2)
//                .take(300)
//                .subscribe();

//        Observable.interval(1, TimeUnit.MILLISECONDS)
//                .onBackpressureBuffer(1000)
//                .observeOn(Schedulers.newThread())
//                .subscribe(
//                        i -> {
//                            System.out.println(i);
//                            try {
//                                Thread.sleep(100);
//                            } catch (Exception e) {	}
//                        },
//                        System.out::println
//                );

        Observable.interval(1, TimeUnit.MILLISECONDS)
                .onBackpressureDrop()
                .observeOn(Schedulers.newThread())
                .subscribe(
                        i -> {
                            System.out.println(i);
                            try {
                                Thread.sleep(100);
                            } catch (Exception e) {	}
                        },
                        System.out::println);

        System.in.read();


    }




    public static void main(String[] args) throws IOException {
        testDeOnRequest();
    }


}
