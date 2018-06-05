package com.helloxin.leaner.rxjava.concept;

import rx.Observable;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by nandiexin on 2018/6/1.
 */
public class BackPressure {

    public  void outPressure() throws IOException {

        Observable.interval(1, TimeUnit.MILLISECONDS)
                //.subscribeOn(Schedulers.newThread())
                //将观察者的工作放在新线程环境中
                .observeOn(Schedulers.newThread())
                //观察者处理每1000ms才处理一个事件
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(("TAG---->"+aLong));
                    }
                });

        System.in.read();


    }

    public static void main(String[] args) throws IOException {

        BackPressure backPressure = new BackPressure();
        backPressure.outPressure();

    }
}
