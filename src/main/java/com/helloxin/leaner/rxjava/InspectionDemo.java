package com.helloxin.leaner.rxjava;

import rx.Observable;
import rx.Subscription;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by nandiexin on 2018/3/21.
 */
public class InspectionDemo {


    public void allTest(){
        Observable<Integer> values = Observable.create(o -> {
            o.onNext(0);
            o.onNext(10);
            o.onNext(10);
            o.onNext(2);
            o.onCompleted();
        });


        Subscription evenNumbers = values
                .all(i -> i % 2 == 0)
                .subscribe(
                        v -> System.out.println(v),
                        e -> System.out.println("Error: " + e),
                        () -> System.out.println("Completed")
                );


        System.out.println("--------------------------------");

        Observable<Integer> values3 = Observable.create(o -> {
            o.onNext(0);
            o.onNext(2);
            o.onError(new Exception());
        });

        Subscription subscription = values3
                .all(i -> i % 2 == 0)
                .subscribe(
                        v -> System.out.println(v),
                        e -> System.out.println("Error: " + e),
                        () -> System.out.println("Completed")
                );

        System.out.println("---------------------------------");


        Observable<Integer> values4 = Observable.create(o -> {
            o.onNext(1);
            o.onNext(2);
            o.onError(new Exception());
        });

        Subscription subscription4 = values
                .all(i -> i % 2 == 0)
                .subscribe(
                        v -> System.out.println(v),
                        e -> System.out.println("Error: " + e),
                        () -> System.out.println("Completed")
                );


        System.out.println("----------------------------------");

        Observable<Long> values2 = Observable.interval(150, TimeUnit.MILLISECONDS).take(5);

        Subscription subscription22 = values2
                .all(i -> i<3) // Will fail eventually
                .subscribe(
                        v -> System.out.println("All: " + v),
                        e -> System.out.println("All: Error: " + e),
                        () -> System.out.println("All: Completed")
                );
        Subscription subscription2 = values2
                .subscribe(
                        v -> System.out.println(v),
                        e -> System.out.println("Error: " + e),
                        () -> System.out.println("Completed")
                );

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exist(){

        Observable<Integer> values = Observable.range(0, 3);

        Subscription subscription = values
                .exists(i -> i > 2)
                .subscribe(
                        v -> System.out.println(v),
                        e -> System.out.println("Error: " + e),
                        () -> System.out.println("Completed")
                );


    }

    public void emptyTest(){

        Observable<Long> values = Observable.timer(1000, TimeUnit.MILLISECONDS);

        Subscription subscription = values
                .isEmpty()
                .subscribe(
                        v -> System.out.println(v),
                        e -> System.out.println("Error: " + e),
                        () -> System.out.println("Completed")
                );

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void containsTest(){

        Observable<Long> values = Observable.interval(100, TimeUnit.MILLISECONDS);

        Subscription subscription = values
                .contains(4L)
                .subscribe(
                        v -> System.out.println(v),
                        e -> System.out.println("Error: " + e),
                        () -> System.out.println("Completed")
                );

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void defaultEmpty(){

        Observable<Integer> values = Observable.empty();

        Subscription subscription = values
                .defaultIfEmpty(2)
                .subscribe(
                        v -> System.out.println(v),
                        e -> System.out.println("Error: " + e),
                        () -> System.out.println("Completed")
                );

        System.out.println("---------------------------");

        Observable<Integer> values2 = Observable.error(new Exception());

        Subscription subscription2 = values2
                .defaultIfEmpty(2)
                .subscribe(
                        v -> System.out.println(v),
                        e -> System.out.println("Error: " + e),
                        () -> System.out.println("Completed")
                );

    }

    public void elementAtTest(){

        Observable<Integer> values = Observable.range(100, 10);

        Subscription subscription = values
                .elementAt(2)
                .subscribe(
                        v -> System.out.println(v),
                        e -> System.out.println("Error: " + e),
                        () -> System.out.println("Completed")
                );

        System.out.println("--------------------------");

        Observable<Integer> values2 = Observable.range(100, 10);

        Subscription subscription2 = values2
                .elementAtOrDefault(22, 0)
                .subscribe(
                        v -> System.out.println(v),
                        e -> System.out.println("Error: " + e),
                        () -> System.out.println("Completed")
                );

    }

    public void sequenceEqualsTest(){

        Observable<String> strings = Observable.just("1", "2", "3");
        Observable<Integer> ints = Observable.just(1, 2, 3);

        Observable.sequenceEqual(strings, ints, (s,i) -> s.equals(i.toString()))
       //Observable.sequenceEqual(strings, ints)
                .subscribe(
                        v -> System.out.println(v),
                        e -> System.out.println("Error: " + e),
                        () -> System.out.println("Completed")
                );

        System.out.println("-------------------------------------");

        Observable<Integer> values = Observable.create(o -> {
            o.onNext(1);
            o.onNext(2);
            o.onError(new Exception());
        });

        Observable.sequenceEqual(values, values)
                .subscribe(
                        v -> System.out.println(v),
                        e -> System.out.println("Error: " + e),
                        () -> System.out.println("Completed")
                );
    }



    public static void main(String[] args) {

        InspectionDemo inspectionDemo = new InspectionDemo();
        inspectionDemo.sequenceEqualsTest();
    }
}
