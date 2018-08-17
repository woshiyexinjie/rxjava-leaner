package com.helloxin.leaner.rxjava;

import rx.Observable;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by nandiexin on 2018/8/11.
 */
public class DelayDemo {

    public void delayTest() throws IOException {
//        Observable.interval(100, TimeUnit.MILLISECONDS).take(5)
//                .delay(1, TimeUnit.SECONDS)
//                .timeInterval()
//                .subscribe(System.out::println);



//        Observable.interval(100, TimeUnit.MILLISECONDS).take(5)
//                .delay(i -> Observable.timer(i * 100, TimeUnit.MILLISECONDS))
//                .timeInterval()
//                .subscribe(System.out::println);

//        Observable.interval(100, TimeUnit.MILLISECONDS).take(5)
//                .delaySubscription(1000, TimeUnit.MILLISECONDS)
//                .timeInterval()
//                .subscribe(System.out::println);

        Observable.interval(100, TimeUnit.MILLISECONDS).take(5)
                .delaySubscription(() -> Observable.timer(1000, TimeUnit.MILLISECONDS))
                .timeInterval()
                .subscribe(System.out::println);

        System.in.read();
    }

    public static void main(String[] args) throws IOException {

        DelayDemo delayDemo = new DelayDemo();
        delayDemo.delayTest();
    }
}
