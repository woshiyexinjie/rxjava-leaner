package com.helloxin.leaner.rxjava;

import com.helloxin.leaner.rxjava.prepare.Inc;
import com.helloxin.leaner.rxjava.prepare.Indexed;
import com.helloxin.leaner.rxjava.prepare.PrintSubscriber;
import rx.Observable;
import rx.Subscription;
import rx.subjects.ReplaySubject;

/**
 * Created by nandiexin on 2018/4/1.
 */
public class TamingSequenceDemo {


    public void sideEffectTest(){

        Observable<String> values = Observable.just("No", "side", "effects", "please");

        Inc index = new Inc();
        Observable<String> indexed =
                values.map(w -> {
                    index.inc();
                    return w;
                });
        indexed.subscribe(w -> System.out.println(index.getCount() + ": " + w));


        System.out.println("------------------------------------------------------------");


        Observable<String> values2 = Observable.just("No", "side", "effects", "please");

        Inc index2 = new Inc();
        Observable<String> indexed2 =
                values.map(w -> {
                    index2.inc();
                    return w;
                });
        indexed2.subscribe(w -> System.out.println("1st observer: " + index2.getCount() + ": " + w));
        indexed2.subscribe(w -> System.out.println("2nd observer: " + index2.getCount() + ": " + w));


    }

    public void pipLineTest(){

        Observable<String> values = Observable.just("No", "side", "effects", "please");

        Observable<Indexed<String>> indexed =
                values.scan(
                        new Indexed<String>(0, null),
                        (prev,v) -> new Indexed<String>(prev.index+1, v))
                        .skip(1);
        indexed.subscribe(w -> System.out.println("1st observer: " + w.index + ": " + w.item));
        indexed.subscribe(w -> System.out.println("2nd observer: " + w.index + ": " + w.item));

    }

    public void doOnTest(){

        Observable<String> values = Observable.just("side", "effects");

        values
                .doOnEach(new PrintSubscriber("Log"))
                .map(s -> s.toString().toUpperCase())
                .subscribe(new PrintSubscriber("Process"));


        System.out.println("-------------------------------------");

        service()
                .map(s -> s.toUpperCase())
                .filter(s -> s.length() > 5)
                .subscribe(new PrintSubscriber("Process"));

    }

    static Observable<String> service() {
        return	Observable.just("First", "Second", "Third")
                .doOnEach(new PrintSubscriber("Log"));
    }

    public void doOnSubscribeTest(){

        ReplaySubject<Integer> subject = ReplaySubject.create();
        Observable<Integer> values = subject
                .doOnSubscribe(() -> System.out.println("New subscription"))
                .doOnUnsubscribe(() -> System.out.println("Subscription over"));

        Subscription s1 = values.subscribe(new PrintSubscriber("1st"));
        subject.onNext(0);
        Subscription s2 = values.subscribe(new PrintSubscriber("2st"));
        subject.onNext(1);
        s1.unsubscribe();
        subject.onNext(2);
        subject.onNext(3);
        subject.onCompleted();

    }


    public static void main(String[] args) {

        TamingSequenceDemo tamingSequenceDemo = new TamingSequenceDemo();
        tamingSequenceDemo.doOnSubscribeTest();

    }
}
