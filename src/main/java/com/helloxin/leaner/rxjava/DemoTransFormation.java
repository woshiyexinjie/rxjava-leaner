package com.helloxin.leaner.rxjava;

import rx.Observable;

/**
 * Created by nandiexin on 2018/2/1.
 */
public class DemoTransFormation {

    public static void main(String[] args) {

        Observable.just("hello nandie - xin")
                .subscribe(s-> System.out.println(s));

        Observable.just("hello nandie").subscribe(s-> System.out.println(s+" - xin"));

    }
}
