package com.helloxin.leaner.rxjava;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by nandiexin on 2018/2/1.
 */
public class Demo {

    public static void main(String[] args) {

        Observable<String> myObservable = Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        subscriber.onNext("Hello xin 1");
                        subscriber.onCompleted();
                    }
                }
        );

        Subscriber<String> mySubscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        };

        myObservable.subscribe(mySubscriber);


        Observable<String> myObservable2 =
                Observable.just("Hello, xin! 2");
        myObservable2.subscribe(s-> System.out.println(s));

        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        };
        myObservable.subscribe(onNextAction);

        //如果不在乎 onError
//        myObservable.subscribe(onNextAction, onErrorAction, onCompleteAction);

        Observable.just("Hello, xin! 3")
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println(s);
                    }
                });

        Observable.just("Hello, xin ! 4")
                .subscribe(s -> System.out.println(s));


    }
}
