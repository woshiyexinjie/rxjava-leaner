package com.helloxin.leaner.rxjava;

import rx.Observable;
import rx.functions.Func1;


/**
 * Created by nandiexin on 2018/2/1.
 */
public class DemoOperators {

    public static void main(String[] args) {

        Observable.just("Hello, xin!")
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return s + " -ye";
                    }
                })
                .subscribe(s -> System.out.println(s));

        Observable.just("Hello, xin!")
                .map(s -> s + " -ye")
                .subscribe(s -> System.out.println(s));


        Observable.just("hello xin").map(new Func1<String, Object>() {

            @Override
            public Object call(String s) {
                return s + " nandie";
            }
        }).subscribe(s-> System.out.println(s));

        Observable.just("hello xin").map(s->s+" nandie").subscribe(s-> System.out.println(s));

        Observable.just("hello xin").map(new Func1<String, Object>() {
            @Override
            public Object call(String s) {
                return s.hashCode();
            }
        }).subscribe(s-> System.out.println(s));

        Observable.just("Hello, xin")
                .map(s -> s.hashCode())
                .subscribe(s -> System.out.println(Integer.toString(s)));  //  这边s -> System.out.println(s)  有区别吗

        Observable.just("Hello, world!")
                .map(s -> s.hashCode())
                .map(i -> Integer.toString(i))
                .subscribe(s -> System.out.println(s));

        Observable.just("Hello, world!")
                .map(s -> s + " -Dan")
                .map(s -> s.hashCode())
                .map(i -> Integer.toString(i))
                .subscribe(s -> System.out.println(s));   //这个 s 已经回不去了


    }
}
