package com.helloxin.leaner.rxjava;

import rx.Scheduler;
import rx.Subscription;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by nandiexin on 2018/11/27.
 */
public class ScheduleDemo {


    public static void scheduleTest() throws IOException {

        Scheduler scheduler = Schedulers.newThread();
        Scheduler.Worker worker = scheduler.createWorker();
        worker.schedule(
                () -> System.out.println("Action"));

    }
    public static void scheduleTest2() throws IOException, InterruptedException {

       /* Scheduler scheduler = Schedulers.newThread();
        long start = System.currentTimeMillis();
        Scheduler.Worker worker = scheduler.createWorker();
        worker.schedule(
                () -> System.out.println(System.currentTimeMillis() - start),
                5, TimeUnit.SECONDS);
        worker.schedule(
                () -> System.out.println(System.currentTimeMillis() - start),
                5, TimeUnit.SECONDS);

        System.in.read();
         */

//        Scheduler scheduler = Schedulers.newThread();
//        long start = System.currentTimeMillis();
//        Scheduler.Worker worker = scheduler.createWorker();
//        worker.schedule(
//                () -> {
//                    System.out.println(System.currentTimeMillis()-start);
//                    worker.unsubscribe();
//                },
//                5, TimeUnit.SECONDS);
//        worker.schedule(
//                () -> System.out.println(System.currentTimeMillis()-start),
//                5, TimeUnit.SECONDS);


        Scheduler scheduler = Schedulers.newThread();
        long start = System.currentTimeMillis();
        Scheduler.Worker worker = scheduler.createWorker();
        worker.schedule(() -> {
            try {
                Thread.sleep(2000);
                System.out.println("Action completed");
            } catch (InterruptedException e) {
                System.out.println("Action interrupted");
            }
        });
        Thread.sleep(500);
        worker.unsubscribe();



    }


    public static void immediateTest() throws IOException {

        Scheduler scheduler = Schedulers.immediate();
        Scheduler.Worker worker = scheduler.createWorker();
        worker.schedule(() -> {
            System.out.println("Start");
            worker.schedule(() -> System.out.println("Inner"));
            System.out.println("End");
        });

        System.in.read();

    }

    public static void trampolineTest() throws IOException {

        Scheduler scheduler = Schedulers.trampoline();
        Scheduler.Worker worker = scheduler.createWorker();
        worker.schedule(() -> {
            System.out.println("Start");
            worker.schedule(() -> System.out.println("Inner"));
            System.out.println("End");
        });

        System.in.read();

    }

    public static void newThreadTest() throws IOException, InterruptedException {

        System.out.println("Main");
        Scheduler scheduler = Schedulers.newThread();
        Scheduler.Worker worker = scheduler.createWorker();
        worker.schedule(() -> {
            System.out.println("Start");
            worker.schedule(() -> System.out.println("Inner"));
            System.out.println("End");
        });

        Thread.sleep(500);
        worker.schedule(() -> System.out.println("Again"));  System.out.println("End");

        System.in.read();

    }

    public static void main(String[] args) throws IOException, InterruptedException {
        newThreadTest();
    }

}
