package com.helloxin.leaner.rxjava;

import rx.Observable;
import rx.Subscription;
import rx.observables.ConnectableObservable;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by nandiexin on 2018/9/5.
 */
public class HotAndColdObservablesDemo {

    public static void codeTest() throws InterruptedException, IOException {

        Observable<Long> cold = Observable.interval(200, TimeUnit.MILLISECONDS);

        cold.subscribe(i -> System.out.println("First: " + i));
        Thread.sleep(500);
        cold.subscribe(i -> System.out.println("Second: " + i));

        System.in.read();
    }

    public static  void hotTest() throws InterruptedException, IOException {

       /* ConnectableObservable<Long> cold = Observable.interval(200, TimeUnit.MILLISECONDS).publish();
        cold.connect();

        cold.subscribe(i -> System.out.println("First: " + i));
        Thread.sleep(500);
        cold.subscribe(i -> System.out.println("Second: " + i));*/


        /*ConnectableObservable<Long> connectable = Observable.interval(200, TimeUnit.MILLISECONDS).publish();
        Subscription s = connectable.connect();

        connectable.subscribe(i -> System.out.println(i));

        Thread.sleep(1000);
        System.out.println("Closing connection");
        s.unsubscribe();

        Thread.sleep(1000);
        System.out.println("Reconnecting");
        s = connectable.connect();
        connectable.subscribe(i -> System.out.println(i));*/

        /*ConnectableObservable<Long> connectable = Observable.interval(200, TimeUnit.MILLISECONDS).publish();
        Subscription s = connectable.connect();

        Subscription s1 = connectable.subscribe(i -> System.out.println("First: " + i));
        Thread.sleep(500);
        Subscription s2 = connectable.subscribe(i -> System.out.println("Second: " + i));

        Thread.sleep(500);
        System.out.println("Unsubscribing second");

        s2.unsubscribe();*/

        /*Observable<Long> cold = Observable.interval(200, TimeUnit.MILLISECONDS).publish().refCount();

        Subscription s1 = cold.subscribe(i -> System.out.println("First: " + i));
        Thread.sleep(500);
        Subscription s2 = cold.subscribe(i -> System.out.println("Second: " + i));
        Thread.sleep(500);
        System.out.println("Unsubscribe second");
        s2.unsubscribe();
        Thread.sleep(500);
        System.out.println("Unsubscribe first");
        s1.unsubscribe();

        System.out.println("First connection again");
        Thread.sleep(500);
        s1 = cold.subscribe(i -> System.out.println("First: " + i));*/

        /*ConnectableObservable<Long> cold = Observable.interval(200, TimeUnit.MILLISECONDS).replay();
        Subscription s = cold.connect();

        System.out.println("Subscribe first");
        Subscription s1 = cold.subscribe(i -> System.out.println("First: " + i));
        Thread.sleep(700);
        System.out.println("Subscribe second");
        Subscription s2 = cold.subscribe(i -> System.out.println("Second: " + i));
        Thread.sleep(500);*/

        /*ConnectableObservable<Long> source = Observable.interval(1000, TimeUnit.MILLISECONDS)
                .take(5)
                .replay(2);

        source.connect();
        Thread.sleep(4500);
        source.subscribe(System.out::println);*/

        /*ConnectableObservable<Long> source = Observable.interval(1000, TimeUnit.MILLISECONDS)
                .take(5)
                .replay(2000, TimeUnit.MILLISECONDS);

        source.connect();
        Thread.sleep(4500);
        source.subscribe(System.out::println);*/

       /* Observable<Long> obs = Observable.interval(100, TimeUnit.MILLISECONDS)
                .take(5)
                .cache();

        Thread.sleep(500);
        obs.subscribe(i -> System.out.println("First: " + i));
        Thread.sleep(300);
        obs.subscribe(i -> System.out.println("Second: " + i));*/

        Observable<Long> obs = Observable.interval(100, TimeUnit.MILLISECONDS)
                .take(5)
                .doOnNext(System.out::println)
                .cache()
                .doOnSubscribe(() -> System.out.println("Subscribed"))
                .doOnUnsubscribe(() -> System.out.println("Unsubscribed"));

        Subscription subscription = obs.subscribe();
        Thread.sleep(150);
        subscription.unsubscribe();

        System.in.read();
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        hotTest();
    }
}
