package com.helloxin.leaner.rxjava;

import rx.Observable;
import com.helloxin.leaner.rxjava.prepare.*;
import rx.subjects.ReplaySubject;
import rx.subjects.Subject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by nandiexin on 2018/3/23.
 */
public class AggregationDemo {


    public void countTest(){

        Observable<Integer> values = Observable.range(0, 3);

        values.subscribe(new PrintSubscriber("Values"));
        values.count().subscribe(new PrintSubscriber("Count"));


    }

    public  void  firstTest(){

        Observable<Long> values = Observable.interval(100, TimeUnit.MILLISECONDS);

        values
                .first(v -> v>5)
                .subscribe(new PrintSubscriber("First"));

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void singleTest() throws IOException {

        Observable<Long> values = Observable.interval(100, TimeUnit.MILLISECONDS);

        values.take(10)
                .single(v -> v == 5L) // Emits a result
                .subscribe(new PrintSubscriber("Single1"));
        values
                .single(v -> v == 5L) // Never emits
                .subscribe(new PrintSubscriber("Single2"));

        System.in.read();


    }

    public void reduceTest() throws IOException {

        Observable<Integer> values = Observable.range(0,5);

        values
                .reduce((i1,i2) -> i1+i2)
                .subscribe(new PrintSubscriber("Sum"));
        values
                .reduce((i1,i2) -> (i1>i2) ? i2 : i1)
                .subscribe(new PrintSubscriber("Min"));


        System.out.println("--------------------------------");

        Observable<String> values2 = Observable.just("Rx", "is", "easy");

        values2
                .reduce(0, (acc,next) -> acc + 1)
                .subscribe(new PrintSubscriber("Count"));


    }


    public void scanTest(){

//        Observable<Integer> values = Observable.range(0,5);
//
//        values
//                .scan((i1,i2) -> i1+i2)
//                .subscribe(new PrintSubscriber("Sum"));


        System.out.println("-----------------------------------");

        Subject<Integer, Integer> values2 = ReplaySubject.create();

        values2
                .subscribe(new PrintSubscriber("Values"));
        values2
                .scan((i1,i2) -> (i1<i2) ? i1 : i2)
                .distinctUntilChanged()
                .subscribe(new PrintSubscriber("Min"));

        values2.onNext(2);
        values2.onNext(3);
        values2.onNext(1);
        values2.onNext(4);
        values2.onCompleted();

    }

    public void colletionTest(){

        Observable<Integer> values = Observable.range(10,5);

        values
                .reduce(
                        new ArrayList<Integer>(),
                        (acc, value) -> {
                            acc.add(value);
                            return acc;
                        })
                .subscribe(v -> System.out.println(v));
//
//        values.reduce(
//                new ArrayList<Integer>(),
//                (acc, value) -> {
//                    ArrayList<Integer> newAcc = (ArrayList<Integer>) acc.clone();
//                    newAcc.add(value);
//                    return newAcc;
//                });

    }

    public void collectDemo(){

        Observable<Integer> values = Observable.range(10,5);

        values.collect(
                        () -> new ArrayList<Integer>(),
                        (acc, value) -> acc.add(value))
                .subscribe(v -> System.out.println(v));

        System.out.println("-------------------------------");

        Observable<Integer> values2 = Observable.range(10,5);

        values2.toList()
                .subscribe(v -> System.out.println(v));

        System.out.println("--------------------------------");

        Observable<Integer> values3 = Observable.range(10,5);

        values3
                .toSortedList((i1,i2) -> i2 - i1)
                .subscribe(v -> System.out.println(v));

        System.out.println("---------------------------------");

        Observable<Person> values4 = Observable.just(
                new Person("Will", 25),
                new Person("Nick", 40),
                new Person("Saul", 35)
        );

        values4
                .toMap(person -> person.name)
                .subscribe(new PrintSubscriber("toMap"));
    }


    public void groupByTest(){

        Observable<String> values = Observable.just(
                "first",
                "second",
                "third",
                "forth",
                "fifth",
                "sixth"
        );

        values.groupBy(word -> word.charAt(0))
                .subscribe(
                        group -> group.last()
                                .subscribe(v -> System.out.println(group.getKey() + ": " + v))
                );


    }

    public void nestTest(){

        Observable.range(0, 3)
                .nest()
                .subscribe(ob -> ob.subscribe(System.out::println));

    }


    public static void main(String[] args) throws IOException {

//        AggregationDemo aggregationDemo = new AggregationDemo();
//        aggregationDemo.nestTest();



    }

}
