package com.helloxin.leaner.rxjava;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by nandiexin on 2018/10/26.
 */
public class SerializeDemo {

    public  static void serializeTest(){

        Observable<Integer> source = Observable.create(o -> {
            o.onNext(1);
            o.onNext(2);
            o.onCompleted();
            o.onNext(3);
            o.onCompleted();
        });

        source.doOnUnsubscribe(() -> System.out.println("Unsubscribed"))
                .subscribe(
                        System.out::println,
                        System.out::println,
                        () -> System.out.println("Completed"));

    }


    public static void serializeTest2(){

        Observable<Integer> source = Observable.create(o -> {
            o.onNext(1);
            o.onNext(2);
            o.onCompleted();
            o.onNext(3);
            o.onCompleted();
        });

        source.doOnUnsubscribe(() -> System.out.println("Unsubscribed"))
                .unsafeSubscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("Completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println(e);
                    }

                    @Override
                    public void onNext(Integer t) {
                        System.out.println(t);
                    }
                });

    }

    public static void serializeTest3(){

        Observable<Integer> source = Observable.create(o -> {
            o.onNext(1);
            o.onNext(2);
            o.onCompleted();
            o.onNext(3);
            o.onCompleted();
        })
                .cast(Integer.class)
                .serialize();;


        source.doOnUnsubscribe(() -> System.out.println("Unsubscribed"))
                .unsafeSubscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("Completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println(e);
                    }

                    @Override
                    public void onNext(Integer t) {
                        System.out.println(t);
                    }
                });

    }


    public static void main(String[] args) {

        serializeTest3();
    }
}
