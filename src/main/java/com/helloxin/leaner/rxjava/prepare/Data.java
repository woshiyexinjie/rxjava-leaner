package com.helloxin.leaner.rxjava.prepare;

import rx.Observable;

/**
 * Created by nandiexin on 2018/4/1.
 */
public class Data {
    public int id;
    public String name;
    public Data(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static void main(String[] args) {
        Observable<Data> data = Observable.just(
                new Data(1, "Microsoft"),
                new Data(2, "Netflix")
        );

        data.subscribe(d -> d.name = "Garbage");
        data.subscribe(d -> System.out.println(d.id + ": " + d.name));
    }
}
