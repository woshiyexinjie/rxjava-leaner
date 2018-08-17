package com.helloxin.leaner.rxjava;

import rx.Observable;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by nandiexin on 2018/8/2.
 */
public class BufferDemo {


    public void bufferTest(){

        Observable.range(0, 10)
                .buffer(4)
                .subscribe(System.out::println);
    }

    public void bufferTimeTest() throws IOException {

        Observable.interval(100, TimeUnit.MILLISECONDS).take(10)
                .buffer(250, TimeUnit.MILLISECONDS)
                .subscribe(System.out::println);

        System.in.read();

    }

    public void bufferTimeAndSizeTest() throws IOException {

//        Observable.interval(100, TimeUnit.MILLISECONDS)
////                .take(10)
////                .buffer(250, TimeUnit.MILLISECONDS, 2)
////                .subscribe(System.out::println);

//        Observable.interval(100, TimeUnit.MILLISECONDS).take(10)
//                .buffer(Observable.interval(250, TimeUnit.MILLISECONDS))
//                .subscribe(System.out::println);

//        Observable.range(0,10)
//                .buffer(4, 3)
//                .subscribe(System.out::println);

//        Observable.interval(100, TimeUnit.MILLISECONDS).take(10)
//                .buffer(350, 200, TimeUnit.MILLISECONDS)
//                .subscribe(System.out::println);

//        Observable.interval(100, TimeUnit.MILLISECONDS).take(10)
//                .buffer(
//                        Observable.interval(250, TimeUnit.MILLISECONDS),
//                        i -> Observable.timer(200, TimeUnit.MILLISECONDS))
//                .subscribe(System.out::println);

//        Observable.range(0, 5)
//                .takeLastBuffer(2)
//                .subscribe(System.out::println);

//        Observable.interval(100, TimeUnit.MILLISECONDS)
//                .take(5)
//                .takeLastBuffer(200, TimeUnit.MILLISECONDS)
//                .subscribe(System.out::println);

        Observable.interval(100, TimeUnit.MILLISECONDS)
                .take(5)
                .takeLastBuffer(2, 200, TimeUnit.MILLISECONDS)
                .subscribe(System.out::println);

        System.in.read();
    }

    public static void main(String[] args) throws IOException {

        BufferDemo bufferDemo = new BufferDemo();
        bufferDemo.bufferTimeAndSizeTest();
    }
}
