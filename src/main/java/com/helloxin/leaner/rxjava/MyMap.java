package com.helloxin.leaner.rxjava;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by nandiexin on 2018/10/18.
 */
class MyMap<T,R> implements Observable.Operator<R, T> {

    private Func1<T,R> transformer;

    public MyMap(Func1<T,R> transformer) {
        this.transformer = transformer;
    }

    @Override
    public Subscriber<? super T> call(Subscriber<? super R> subscriber) {
        return new Subscriber<T>() {

            @Override
            public void onCompleted() {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onCompleted();
                }
            }

            @Override
            public void onError(Throwable e) {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onError(e);
                }
            }

            @Override
            public void onNext(T t) {

                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext(transformer.call(t));
                }

            }

        };
    }

    public static void main(String[] args) {

        Observable.range(0, 5)
                .lift(new MyMap<Integer, String>(i -> i + "!"))
                .subscribe(System.out::println);

    }
}
