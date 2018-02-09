package com.helloxin.leaner.rxjava;

import rx.Observable;
import rx.Observer;
import rx.functions.Action1;

/**
 * Created by nandiexin on 2018/2/1.
 */
public class DemoSimplerCode {

    public static void main(String[] args) {


        Observable<String> myObserable = Observable.just("hello xin");

        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        };

        myObserable.subscribe(onNextAction);

        //再简单一点

        Observable.just("hello xin")
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println(s);
                    }
                });

        //lambdas
         Observable.just("hello xin")
                 .subscribe(s -> System.out.println(s));


    }
}
