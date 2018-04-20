package com.helloxin.leaner.rxjava.prepare;

import rx.subjects.BehaviorSubject;

/**
 * Created by nandiexin on 2018/4/1.
 */
public class BrakeableService {
//    public BehaviorSubject<String> items = BehaviorSubject.create("Greet");
////    public void play() {
////        items.onNext("Hello");
////        items.onNext("and");
////        items.onNext("goodbye");
////    }

    private final BehaviorSubject<String> items = BehaviorSubject.create("Greet");

    public BehaviorSubject<String> getValues() {
        return items;
    }

    public void play() {
        items.onNext("Hello");
        items.onNext("and");
        items.onNext("goodbye");
    }
}
