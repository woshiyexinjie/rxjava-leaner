package com.helloxin.leaner.rxjava;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;
import rx.subjects.AsyncSubject;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subjects.ReplaySubject;

import java.util.concurrent.TimeUnit;

/**
 * Created by nandiexin on 2018/2/28.
 */
public class DemoPart3 {



    public void publishSubject(){
        PublishSubject<Integer> subject = PublishSubject.create();

        subject.onNext(1);
        subject.subscribe(System.out::println);
        subject.onNext(2);
        subject.onNext(3);
        subject.onNext(4);
    }

    public void relaySubject() throws InterruptedException {
        ReplaySubject<Integer> s = ReplaySubject.create();
        s.subscribe(v -> System.out.println("Early:" + v));
        s.onNext(0);
        s.onNext(1);
        s.subscribe(v -> System.out.println("Late: " + v));
        s.onNext(2);

        System.out.println("------------------------------");

        ReplaySubject<Integer> s2 = ReplaySubject.createWithSize(2);
        s2.onNext(0);
        s2.onNext(1);
        s2.onNext(2);
        s2.subscribe(v -> System.out.println("Late: " + v));
        s2.onNext(3);

        System.out.println("------------------------------");

        ReplaySubject<Integer> s3 = ReplaySubject.createWithTime(150, TimeUnit.MILLISECONDS,
                Schedulers.immediate());
        s3.onNext(0);
        Thread.sleep(100);
        s3.onNext(1);
        Thread.sleep(100);
        s3.onNext(2);
        s3.subscribe(v -> System.out.println("Late: " + v));
        s3.onNext(3);

        //ReplaySubject.createWithTimeAndSize limits both, which ever comes first.
    }


    public void behaviorSubject(){
        BehaviorSubject<Integer> s = BehaviorSubject.create();
        s.onNext(0);
        s.onNext(1);
        s.onNext(2);
        s.subscribe(v -> System.out.println("Late: " + v));
        s.onNext(3);

        System.out.println("------------------------------");

        BehaviorSubject<Integer> s2 = BehaviorSubject.create();
        s2.onNext(0);
        s2.onNext(1);
        s2.onNext(2);
        s2.onCompleted();
        s2.subscribe(
                v -> System.out.println("Late: " + v),
                e -> System.out.println("Error"),
                () -> System.out.println("Completed")
        );

        System.out.println("------------------------------");

        BehaviorSubject<Integer> s3 = BehaviorSubject.create(0);
        s3.subscribe(v -> System.out.println(v));
        s3.onNext(1);
    }


    public void asyncSubject(){
        AsyncSubject<Integer> s = AsyncSubject.create();
        s.subscribe(v -> System.out.println(v));
        s.onNext(0);
        s.onNext(1);
        s.onNext(2);
        s.onCompleted();
    }

    public static void main(String[] args) throws InterruptedException {

        DemoPart3 demoPart3 = new DemoPart3();
        demoPart3.behaviorSubject();
    }
}
