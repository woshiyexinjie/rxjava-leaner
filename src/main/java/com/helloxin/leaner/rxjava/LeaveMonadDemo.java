package com.helloxin.leaner.rxjava;

import rx.Observable;
import rx.subjects.ReplaySubject;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by nandiexin on 2018/4/5.
 */
public class LeaveMonadDemo {

    public void foreachTest() throws IOException {

        Observable<Long> values = Observable.interval(100, TimeUnit.MILLISECONDS);

        values
                .take(5)
                .forEach(v -> System.out.println(v));
        System.out.println("Subscribed");

        System.in.read();


        System.out.println("---------------------------------------------------");

        Observable<Long> values2 = Observable.interval(100, TimeUnit.MILLISECONDS);

        values2
                .take(5)
                .toBlocking()
                .forEach(v -> System.out.println(v));
        System.out.println("Subscribed2");


    }

    public void noErrorHandler(){

        Observable<Long> values = Observable.error(new Exception("Oops"));

        try	{
            values
                    .take(5)
                    .toBlocking()
                    .forEach(v -> System.out.println(v));
        }
        catch (Exception e) {
            System.out.println("Caught: " + e.getMessage());
        }
        System.out.println("Subscribed");

    }

    public void firstTest(){

        Observable<Long> values = Observable.interval(100, TimeUnit.MILLISECONDS);

        long value = values
                .take(5)
                .toBlocking()
                .first(i -> i>2);
        System.out.println(value);

    }


    public void singleTest(){

        Observable<Long> values = Observable.interval(100, TimeUnit.MILLISECONDS);

        try {
            long value = values
                    .take(5)
                    .toBlocking()
                    .single(i -> i>2);
            System.out.println(value);
        }
        catch (Exception e) {
            System.out.println("Caught: " + e);
        }

    }

    public void iteratorTest(){

        Observable<Long> values = Observable.interval(500, TimeUnit.MILLISECONDS);

        Iterable<Long> iterable = values.take(5).toBlocking().toIterable();
        for (long l : iterable) {
            System.out.println(l);
        }

    }

    public void nestTest() throws InterruptedException {

        Observable<Long> values = Observable.interval(500, TimeUnit.MILLISECONDS);

        values.take(5)
                .subscribe(v -> System.out.println("Emitted: " + v));

        Iterable<Long> iterable = values.take(5).toBlocking().next();
        for (long l : iterable) {
            System.out.println(l);
            Thread.sleep(750);
        }


    }


    public void latestTest() throws InterruptedException {

        Observable<Long> values = Observable.interval(500, TimeUnit.MILLISECONDS);

        values.take(5)
                .subscribe(v -> System.out.println("Emitted: " + v));

        Iterable<Long> iterable = values.take(5).toBlocking().latest();
        for (long l : iterable) {
            System.out.println(l);
            Thread.sleep(750);
        }

    }


    public void mostRecent() throws InterruptedException {

        Observable<Long> values = Observable.interval(500, TimeUnit.MILLISECONDS);

        values.take(5)
                .subscribe(v -> System.out.println("Emitted: " + v));

        Iterable<Long> iterable = values.take(5).toBlocking().mostRecent(-1L);
        for (long l : iterable) {
            System.out.println(l);
            Thread.sleep(400);
        }

    }

    public void futureTest() throws ExecutionException, InterruptedException {


        Observable<Long> values = Observable.timer(500, TimeUnit.MILLISECONDS);

        values.subscribe(v -> System.out.println("Emitted: " + v));

        Future<Long> future = values.toBlocking().toFuture();
        System.out.println(future.get());


    }

    public void lockTest(){

        ReplaySubject<Integer> subject = ReplaySubject.create();

        subject.toBlocking().forEach(v -> System.out.println(v));
        subject.onNext(1);
        subject.onNext(2);
        subject.onCompleted();

    }


    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {

        LeaveMonadDemo leaveMonadDemo = new LeaveMonadDemo();
        leaveMonadDemo.lockTest();

    }

}
