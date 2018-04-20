package com.helloxin.leaner.rxjava;

import com.helloxin.leaner.rxjava.prepare.PrintSubscriber;
import com.helloxin.leaner.rxjava.prepare.Range;
import rx.Observable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by nandiexin on 2018/3/30.
 */
public class TransformationDemo {

    public void mapTest(){

        Observable<Integer> values = Observable.range(0,4);

        values
                .map(i -> i + 3)
                .subscribe(new PrintSubscriber("Map"));


        System.out.println("------------------------------");

        Observable<Integer> values2 =
                Observable.just("0", "1", "2", "3")
                        .map(Integer::parseInt);

        values2.subscribe(new PrintSubscriber("Map"));
    }


    public void castTest(){

        Observable<Object> values = Observable.just(0, 1, 2, 3);

        values
                .cast(Integer.class)
                .subscribe(new PrintSubscriber("Map"));


        System.out.println("------------------------------");


        Observable<Object> values3 = Observable.just(0, 1, "2", 3);

        values3
                .ofType(Integer.class)
                .subscribe(new PrintSubscriber("Map"));


        System.out.println("------------------------------");

        Observable<Object> values2= Observable.just(0, 1, 2, "3");

        values2
                .cast(Integer.class)
                .subscribe(new PrintSubscriber("Map"));




    }


    public void timestampDemo() throws IOException {

//        Observable<Long> values = Observable.interval(100, TimeUnit.MILLISECONDS);
//
//        values.take(3)
//                .timestamp()
//                .subscribe(new PrintSubscriber("Timestamp"));
//
//        System.in.read();


        Observable<Long> values2 = Observable.interval(100, TimeUnit.MILLISECONDS);

        values2.take(3)
                .timeInterval()
                .subscribe(new PrintSubscriber("TimeInterval"));

        System.in.read();

    }


    public void materializeTest() throws IOException {

        Observable<Long> values = Observable.interval(100, TimeUnit.MILLISECONDS);

        values.take(3)
                .materialize()
                .subscribe(new PrintSubscriber("Materialize"));

        System.in.read();

    }

    public void flatMapTest() throws IOException {

        Observable<Integer> values = Observable.just(2);

        values
                .flatMap(i -> Observable.range(0,i))
                .subscribe(new PrintSubscriber("flatMap"));


        System.out.println("----------------------------");

        Observable<Integer> values2 = Observable.range(1,3);

        values2
                .flatMap(i -> Observable.range(0,i))
                .subscribe(new PrintSubscriber("flatMap"));

        System.out.println("----------------------------");

        Observable<Integer> values3 = Observable.just(1);

        values3
                .flatMap(i ->
                        Observable.just(
                                Character.valueOf((char)(i+64))
                        ))
                .subscribe(new PrintSubscriber("flatMap"));


        System.out.println("----------------------------");

        Observable<Integer> values4 = Observable.range(0,30);

        values4
                .flatMap(i -> {
                    if (0 < i && i <= 26) {
                        return Observable.just(Character.valueOf((char)(i+64)));
                    } else {
                        return Observable.empty();
                    }
                })
                .subscribe(new PrintSubscriber("flatMap"));


        System.out.println("-----------------------------");

        Observable.just(100, 150)
                .flatMap(i ->
                        Observable.interval(i, TimeUnit.MILLISECONDS)
                                .map(v -> i)
                )
                .take(10)
                .subscribe(new PrintSubscriber("flatMap"));

        System.in.read();

    }

    public void concatMapTest() throws IOException {


        Observable.just(100, 150)
                .concatMap(i ->
                        Observable.interval(i, TimeUnit.MILLISECONDS)
                                .map(v -> i)
                                .take(3))
                .subscribe(
                        System.out::println,
                        System.out::println,
                        () -> System.out.println("Completed"));

        System.in.read();

    }

    public static Iterable<Integer> range(int start, int count) {
        List<Integer> list = new ArrayList<>();
        for (int i=start ; i<start+count ; i++) {
            list.add(i);
        }
        return list;
    }

    public void flatMapIterableTest(){

        Observable.range(1, 3)
                .flatMapIterable(i -> range(1, i))
                .subscribe(System.out::println);


        System.out.println("----------------------------");

        Observable.range(1, 3)
                .flatMapIterable(
                        i -> range(1, i),
                        (ori, rv) -> ori * (Integer) rv)
                .subscribe(System.out::println);

        System.out.println("------------------------------");


        Observable.range(1, 3)
                .flatMapIterable(
                        i -> new Range(1, i),
                        (ori, rv) -> ori * (Integer) rv)
                .subscribe(System.out::println);

    }


    public static void main(String[] args) throws IOException {

        TransformationDemo transformationDemo = new TransformationDemo();
        transformationDemo.flatMapIterableTest();

    }


}
