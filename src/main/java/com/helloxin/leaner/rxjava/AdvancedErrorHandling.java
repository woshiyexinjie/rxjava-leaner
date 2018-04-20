package com.helloxin.leaner.rxjava;

import com.helloxin.leaner.rxjava.prepare.PrintSubscriber;
import rx.Observable;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by nandiexin on 2018/4/8.
 */
public class AdvancedErrorHandling {


    public void  errorTest(){


        Observable<String> values = Observable.create(o -> {
            o.onNext("Rx");
            o.onNext("is");
            o.onError(new Exception("adjective unknown"));
        });

        values
                .onErrorReturn(e -> "Error: " + e.getMessage())
                .subscribe(v -> System.out.println(v));

    }

    public void errorResumeTest(){


        Observable<Integer> values = Observable.create(o -> {
            o.onNext(1);
            o.onNext(2);
            o.onError(new Exception("Oops"));
        });

        values
                .onErrorResumeNext(Observable.just(Integer.MAX_VALUE))
                .subscribe(new PrintSubscriber("with onError: "));

    }

    public void exceptionResumeTest(){

        Observable<String> values = Observable.create(o -> {
            o.onNext("Rx");
            o.onNext("is");
            //o.onError(new Throwable() {}); // this won't be caught
            o.onError(new Exception()); // this will be caught
        });

        values
                .onExceptionResumeNext(Observable.just("hard"))
                .subscribe(v -> System.out.println(v));

    }

    public void retryTest(){

        Random random = new Random(1000);
        Observable<Integer> values = Observable.create(o -> {
            o.onNext(random.nextInt() % 20);
            o.onNext(random.nextInt() % 20);
            o.onError(new Exception());
        });

        values
                .retry(1)
                .subscribe(v -> System.out.println(v));


    }

    public void retryWhenTest() throws IOException {

        Observable<Integer> source = Observable.create(o -> {
            o.onNext(1);
            o.onNext(2);
            o.onError(new Exception("Failed"));
        });

        source.retryWhen((o) -> o
                .take(2)
                .delay(100, TimeUnit.MILLISECONDS))
                .timeInterval()
                .subscribe(
                        System.out::println,
                        System.out::println);

        System.in.read();
    }


    public void usingTest(){

        Observable<Character> values = Observable.using(
                () -> {
                    String resource = "MyResource";
                    System.out.println("Leased: " + resource);
                    return resource;
                },
                (resource) -> {
                    return Observable.create(o -> {
                        for (Character c : resource.toCharArray()) {
                            o.onNext(c);
                        }
                        o.onCompleted();
                    });
                },
                (resource) -> System.out.println("Disposed: " + resource));

        values
                .subscribe(
                        v -> System.out.println(v),
                        e -> System.out.println(e));

    }

    public static void main(String[] args) throws IOException {

        AdvancedErrorHandling advancedErrorHandling = new AdvancedErrorHandling();
        advancedErrorHandling.usingTest();

    }

}
