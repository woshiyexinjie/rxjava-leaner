package com.helloxin.leaner.rxjava;

import rx.Observable;
import rx.Subscription;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by nandiexin on 2018/3/17.
 */
public class ReduceDemo {

    public void filterTest(){
        Observable<Integer> values = Observable.range(0,10);
        Subscription oddNumbers = values
                .filter(v -> v % 2 == 0)
                .subscribe(
                        v -> System.out.println(v),
                        e -> System.out.println("Error: " + e),
                        () -> System.out.println("Completed")
                );
    }

    public void distinctTest(){
        Observable<Integer> values = Observable.create(o -> {
            o.onNext(1);
            o.onNext(1);
            o.onNext(2);
            o.onNext(3);
            o.onNext(2);
            o.onCompleted();
        });

        Subscription subscription = values
                .distinct()
                .subscribe(
                        v -> System.out.println(v),
                        e -> System.out.println("Error: " + e),
                        () -> System.out.println("Completed")
                );

        System.out.println("-----------------------------");

        Observable<String> values2 = Observable.create(o -> {
            o.onNext("First");
            o.onNext("Second");
            o.onNext("Third");
            o.onNext("Fourth");
            o.onNext("Fifth");
            o.onCompleted();
        });

        Subscription subscription2 = values2
                .distinct(v -> v.charAt(0))
                .subscribe(
                        v -> System.out.println(v),
                        e -> System.out.println("Error: " + e),
                        () -> System.out.println("Completed")
                );
    }

    public void distinctUntilChangedTest(){

        Observable<Integer> values = Observable.create(o -> {
            o.onNext(1);
            o.onNext(1);
            o.onNext(2);
            o.onNext(3);
            o.onNext(2);
            o.onCompleted();
        });

        Subscription subscription = values
                .distinctUntilChanged()
                .subscribe(
                        v -> System.out.println(v),
                        e -> System.out.println("Error: " + e),
                        () -> System.out.println("Completed")
                );

        System.out.println("----------------------------");

        Observable<String> values2 = Observable.create(o -> {
            o.onNext("First");
            o.onNext("Second");
            o.onNext("Third");
            o.onNext("Fourth");
            o.onNext("Fifth");
            o.onCompleted();
        });

        Subscription subscriptio2 = values2

                .distinctUntilChanged(v -> v.charAt(0))
                .subscribe(
                        v -> System.out.println(v),
                        e -> System.out.println("Error: " + e),
                        () -> System.out.println("Completed")
                );

    }

    public void ignoreTest(){
        Observable<Integer> values = Observable.range(0, 10);

        Subscription subscription = values
                .ignoreElements()
                .subscribe(
                        v -> System.out.println(v),
                        e -> System.out.println("Error: " + e),
                        () -> System.out.println("Completed")
                );
    }

    public void takeTest(){
        Observable<Integer> values = Observable.range(0, 5);

        Subscription first2 = values
                .take(2)
                .subscribe(
                        v -> System.out.println(v),
                        e -> System.out.println("Error: " + e),
                        () -> System.out.println("Completed")
                );


        System.out.println("--------------------------");

        Observable<Integer> values2 = Observable.create(o -> {
            o.onNext(1);
            o.onError(new Exception("Oops"));
        });

        Subscription subscription = values2
                .take(1)
                .subscribe(
                        v -> System.out.println(v),
                        e -> System.out.println("Error: " + e),
                        () -> System.out.println("Completed")
                );

        System.out.println("---------------------------");

        Observable<Long> values3 = Observable.interval(100, TimeUnit.MILLISECONDS);

        Subscription subscription3 = values3
                .take(250, TimeUnit.MILLISECONDS)
                .subscribe(
                        v -> System.out.println(v),
                        e -> System.out.println("Error: " + e),
                        () -> System.out.println("Completed")
                );
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void skipTest(){

        Observable<Integer> values = Observable.range(0, 5);

        Subscription subscription = values
                .skip(2)
                .subscribe(
                        v -> System.out.println(v),
                        e -> System.out.println("Error: " + e),
                        () -> System.out.println("Completed")
                );

    }

    public void takeWhile(){

        Observable<Long> values = Observable.interval(100, TimeUnit.MILLISECONDS);

        Subscription subscription = values
                .takeWhile(v -> v < 2)
                .subscribe(
                        v -> System.out.println(v),
                        e -> System.out.println("Error: " + e),
                        () -> System.out.println("Completed")
                );

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void skipWhile(){

        Observable<Long> values = Observable.interval(100, TimeUnit.MILLISECONDS);

        Subscription subscription = values
                .skipWhile(v -> v < 2)
                .subscribe(
                        v -> System.out.println(v),
                        e -> System.out.println("Error: " + e),
                        () -> System.out.println("Completed")
                );

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void skipLastTest(){

        Observable<Integer> values = Observable.range(0,5);

        Subscription subscription = values
                .skipLast(2)
                .subscribe(
                        v -> System.out.println(v),
                        e -> System.out.println("Error: " + e),
                        () -> System.out.println("Completed")
                );

    }

    public void takeUntilTest(){

        Observable<Long> values = Observable.interval(100,TimeUnit.MILLISECONDS);
        Observable<Long> cutoff = Observable.timer(250, TimeUnit.MILLISECONDS);

        Subscription subscription = values
                .takeUntil(cutoff)
                .subscribe(
                        v -> System.out.println(v),
                        e -> System.out.println("Error: " + e),
                        () -> System.out.println("Completed")
                );

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void skilUntilTest(){

        Observable<Long> values = Observable.interval(100,TimeUnit.MILLISECONDS);
        Observable<Long> cutoff = Observable.timer(250, TimeUnit.MILLISECONDS);

        Subscription subscription = values
                .skipUntil(cutoff)
                .subscribe(
                        v -> System.out.println(v),
                        e -> System.out.println("Error: " + e),
                        () -> System.out.println("Completed")
                );

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        ReduceDemo reduceDemo = new ReduceDemo();
        reduceDemo.skilUntilTest();
    }
}
