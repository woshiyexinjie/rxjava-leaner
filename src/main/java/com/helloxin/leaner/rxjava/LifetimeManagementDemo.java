package com.helloxin.leaner.rxjava;

import rx.Subscription;
import rx.subjects.ReplaySubject;
import rx.subjects.Subject;
import rx.subscriptions.Subscriptions;

/**
 * Created by nandiexin on 2018/3/11.
 */
public class LifetimeManagementDemo{

    public void exceptionTest(){
        Subject<Integer, Integer> s = ReplaySubject.create();
        s.subscribe(
                v -> System.out.println(v),
                e -> System.err.println(e));
        s.onNext(0);
        s.onError(new Exception("Oops"));
    }

    public void unsubscribeTest(){
        Subject<Integer, Integer>  values = ReplaySubject.create();
        Subscription subscription = values.subscribe(
                v -> System.out.println(v),
                e -> System.err.println(e),
                () -> System.out.println("Done")
        );
        values.onNext(0);
        values.onNext(1);
        subscription.unsubscribe();
        values.onNext(2);


        System.out.println("-----------------------");

        Subject<Integer, Integer>  values2 = ReplaySubject.create();
        Subscription subscription1 = values2.subscribe(
                v -> System.out.println("First: " + v)
        );
        Subscription subscription2 = values2.subscribe(
                v -> System.out.println("Second: " + v)
        );
        values2.onNext(0);
        values2.onNext(1);
        subscription1.unsubscribe();
        System.out.println("Unsubscribed first");
        values2.onNext(2);
    }

    public void resourceTest(){
        Subscription s = Subscriptions.create(() -> System.out.println("Clean"));
        s.unsubscribe();
    }


    public void onErrorAndOnCompletedTest() {

        Subject<Integer, Integer> values = ReplaySubject.create();
        Subscription subscription1 = values.subscribe(
                v -> System.out.println("First: " + v),
                e -> System.out.println("First: " + e),
                () -> System.out.println("Completed")
        );
        values.onNext(0);
        values.onNext(1);
        values.onCompleted();
        values.onNext(2);
    }

    public static void main(String[] args) {
        LifetimeManagementDemo lifetimeManagementDemo = new LifetimeManagementDemo();
        lifetimeManagementDemo.resourceTest();
    }
}
