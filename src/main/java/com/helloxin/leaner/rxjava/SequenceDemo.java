package com.helloxin.leaner.rxjava;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import rx.Observable;
import rx.Subscription;

import java.io.IOException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by nandiexin on 2018/3/13.
 */
public class SequenceDemo {


    public void justTest(){
        Observable<String> values = Observable.just("one", "two", "three");
        Subscription subscription = values.subscribe(
                v -> System.out.println("Received: " + v),
                e -> System.out.println("Error: " + e),
                () -> System.out.println("Completed")
        );

    }

    public void emptyTest(){
        Observable<String> values = Observable.empty();
        Subscription subscription = values.subscribe(
                v -> System.out.println("Received: " + v),
                e -> System.out.println("Error: " + e),
                () -> System.out.println("Completed")
        );

    }


    public void  neverTest(){
        Observable<String> values = Observable.never();
        Subscription subscription = values.subscribe(
                v -> System.out.println("Received: " + v),
                e -> System.out.println("Error: " + e),
                () -> System.out.println("Completed")
        );
    }

    public void errorTest(){
        Observable<String> values = Observable.error(new Exception("Oops"));
        Subscription subscription = values.subscribe(
                v -> System.out.println("Received: " + v),
                e -> System.out.println("Error: " + e),
                () -> System.out.println("Completed")
        );
    }

    public void defer(){
        Observable<Long> now = Observable.just(System.currentTimeMillis());

        now.subscribe(System.out::println);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        now.subscribe(System.out::println);

        System.out.println("----------------------------");
        Observable<Long> now2 = Observable.defer(() ->
                Observable.just(System.currentTimeMillis()));

        now2.subscribe(System.out::println);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        now2.subscribe(System.out::println);
    }


    public void createTest(){
        Observable<String> values = Observable.create(o -> {
            o.onNext("Hello");
            o.onCompleted();
        });
        Subscription subscription = values.subscribe(
                v -> System.out.println("Received: " + v),
                e -> System.out.println("Error: " + e),
                () -> System.out.println("Completed")
        );
    }

    public void intervalTest(){
        Observable<Long> values = Observable.interval(1000, TimeUnit.MILLISECONDS);
        Subscription subscription = values.subscribe(
                v -> System.out.println("Received: " + v),
                e -> System.out.println("Error: " + e),
                () -> System.out.println("Completed")
        );
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void timerTest(){
        Observable<Long> values = Observable.timer(1, TimeUnit.SECONDS);
        Subscription subscription = values.subscribe(
                v -> System.out.println("Received: " + v),
                e -> System.out.println("Error: " + e),
                () -> System.out.println("Completed")
        );
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("-----------------------------");

        Observable<Long> values2 = Observable.timer(2, 1, TimeUnit.SECONDS);
        Subscription subscription2 = values2.subscribe(
                v -> System.out.println("Received: " + v),
                e -> System.out.println("Error: " + e),
                () -> System.out.println("Completed")
        );
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void eventStreamTest(){

//        Observable<ActionEvent> events = Observable.create(o -> {
//            button2.setOnAction(new EventHandler<ActionEvent>() {
//                @Override public void handle(ActionEvent e) {
//                    o.onNext(e)
//                }
//            });
//        });
    }



    public  void  futureTest(){
        FutureTask<Integer> f = new FutureTask<Integer>(() -> {
            Thread.sleep(2000);
            return 21;
        });
        new Thread(f).start();

        Observable<Integer> values = Observable.from(f);

        Subscription subscription = values.subscribe(
                v -> System.out.println("Received: " + v),
                e -> System.out.println("Error: " + e),
                () -> System.out.println("Completed")
        );
    }

    public void collectionTest(){
        Integer[] is = {1,2,3};
        Observable<Integer> values = Observable.from(is);
        Subscription subscription = values.subscribe(
                v -> System.out.println("Received: " + v),
                e -> System.out.println("Error: " + e),
                () -> System.out.println("Completed")
        );
    }

    public static void main(String[] args) {

        SequenceDemo sequenceDemo = new SequenceDemo();
        sequenceDemo.collectionTest();
    }
}
