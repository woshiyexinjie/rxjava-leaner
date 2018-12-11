package com.helloxin.leaner;

import rx.Observable;
import rx.observers.TestSubscriber;
import rx.schedulers.Schedulers;
import rx.schedulers.TestScheduler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by nandiexin on 2018/12/4.
 */
public class RxTest {

    public static void advanceTimeToTest(){

        TestScheduler s = Schedulers.test();

        s.createWorker().schedule(
                () -> System.out.println("Immediate"));
        s.createWorker().schedule(
                () -> System.out.println("20s"),
                20, TimeUnit.SECONDS);
        s.createWorker().schedule(
                () -> System.out.println("40s"),
                40, TimeUnit.SECONDS);

        System.out.println("Advancing to 1ms");
        s.advanceTimeTo(1, TimeUnit.MILLISECONDS);
        System.out.println("Virtual time: " + s.now());

        System.out.println("Advancing to 10s");
        s.advanceTimeTo(20, TimeUnit.SECONDS);
        System.out.println("Virtual time: " + s.now());

        System.out.println("Advancing to 40s");
        s.advanceTimeTo(40, TimeUnit.SECONDS);
        System.out.println("Virtual time: " + s.now());

    }


    public static void advanceTimeByTest(){

        TestScheduler s = Schedulers.test();

        s.createWorker().schedule(
                () -> System.out.println("Immediate"));
        s.createWorker().schedule(
                () -> System.out.println("20s"),
                20, TimeUnit.SECONDS);
        s.createWorker().schedule(
                () -> System.out.println("40s"),
                40, TimeUnit.SECONDS);

        System.out.println("Advancing by 1ms");
        s.advanceTimeBy(1, TimeUnit.MILLISECONDS);
        System.out.println("Virtual time: " + s.now());

        System.out.println("Advancing by 10s");
        s.advanceTimeBy(10, TimeUnit.SECONDS);
        System.out.println("Virtual time: " + s.now());

        System.out.println("Advancing by 40s");
        s.advanceTimeBy(40, TimeUnit.SECONDS);
        System.out.println("Virtual time: " + s.now());

    }

     public static void triggerActionsTest(){

         TestScheduler s = Schedulers.test();

         s.createWorker().schedule(
                 () -> System.out.println("Immediate"));
         s.createWorker().schedule(
                 () -> System.out.println("20s"),
                 20, TimeUnit.SECONDS);

         s.triggerActions();
         System.out.println("Virtual time: " + s.now());


     }


     public static void schedulingCollisionsTest(){

         TestScheduler s = Schedulers.test();

         s.createWorker().schedule(
                 () -> System.out.println("First"),
                 20, TimeUnit.SECONDS);
         s.createWorker().schedule(
                 () -> System.out.println("Second"),
                 20, TimeUnit.SECONDS);
         s.createWorker().schedule(
                 () -> System.out.println("Third"),
                 20, TimeUnit.SECONDS);

         s.advanceTimeTo(20, TimeUnit.SECONDS);

     }

     public static void testingTest(){

         TestScheduler scheduler = new TestScheduler();
         List<Long> expected = Arrays.asList(0L, 1L, 2L, 3L, 4L);
         List<Long> result = new ArrayList<>();
         Observable
                 .interval(1, TimeUnit.SECONDS, scheduler)
                 .take(5)
                 .subscribe(i -> result.add(i));
         System.out.println(result.isEmpty());
         scheduler.advanceTimeBy(5, TimeUnit.SECONDS);
         System.out.println(result.equals(expected));


     }


     public static void testSubscriberTest(){

         TestScheduler scheduler = new TestScheduler();
         TestSubscriber<Long> subscriber = new TestSubscriber<>();
         List<Long> expected = Arrays.asList(0L, 1L, 2L, 3L);
         Observable
                 .interval(1, TimeUnit.SECONDS, scheduler)
                 .take(5)
                 .subscribe(subscriber);
         System.out.println((subscriber.getOnNextEvents().isEmpty()));
         scheduler.advanceTimeBy(5, TimeUnit.SECONDS);
         subscriber.assertReceivedOnNext(expected);

     }



    public static void main(String[] args) {


        advanceTimeToTest();
//        advanceTimeByTest();
//        triggerActionsTest();
//        schedulingCollisionsTest();
//        testingTest();
//        testSubscriberTest();

    }


}
