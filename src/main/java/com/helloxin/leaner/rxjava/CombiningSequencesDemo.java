package com.helloxin.leaner.rxjava;

import com.helloxin.leaner.rxjava.prepare.PrintSubscriber;
import rx.Observable;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by nandiexin on 2018/4/20.
 */
public class CombiningSequencesDemo {

    public void concatTest(){
        Observable<Integer> seq1 = Observable.range(0, 3);
        Observable<Integer> seq2 = Observable.range(10, 3);

        Observable.concat(seq1, seq2)
                .subscribe(System.out::println);


        System.out.println("----------------------------------");

        Observable<String> words = Observable.just(
                "First",
                "Second",
                "Third",
                "Fourth",
                "Fifth",
                "Sixth"
        );

        Observable.concat(words.groupBy(v -> v.charAt(0)))
                .subscribe(System.out::println);


    }

    public void exampleConcatWith() {

        Observable<Integer> seq1 = Observable.range(0, 3);
        Observable<Integer> seq2 = Observable.range(10, 3);
        Observable<Integer> seq3 = Observable.just(20);

        seq1.concatWith(seq2)
                .concatWith(seq3)
                .subscribe(System.out::println);

    }

    public void repeatTest() throws IOException {

        Observable<Integer> words = Observable.range(0,2);

        words.repeat(2)
                .subscribe(System.out::println);


        System.out.println("---------------------------------");

        Observable<Long> values = Observable.interval(100, TimeUnit.MILLISECONDS);

        values
                .take(2)
                .repeatWhen(ob -> {
                    return ob.take(2);
                })
                .subscribe(new PrintSubscriber("repeatWhen"));

        System.in.read();

    }

    public void repeatWhenTest() throws IOException {


        Observable<Long> values = Observable.interval(100, TimeUnit.MILLISECONDS);

        values
                .take(5)
                .repeatWhen((ob)-> {
                    ob.subscribe();
                    return Observable.interval(2, TimeUnit.SECONDS);
                })
                .subscribe(new PrintSubscriber("repeatWhen"));

        System.in.read();


    }


    public void startWith(){

        Observable<Integer> values = Observable.range(0, 3);

        Observable.concat(
                Observable.just(-1,-2,-3),
                values).subscribe(System.out::println);

//        values.startWith(-1,-2)
//                .subscribe(System.out::println);


    }

    public void ambTest() throws IOException {

        Observable.amb(
                Observable.timer(100, TimeUnit.MILLISECONDS).map(i -> "First"),
                Observable.timer(50, TimeUnit.MILLISECONDS).map(i -> "Second"))
                .subscribe(System.out::println);

        System.in.read();
    }

    public void ambWithTest() throws IOException {

        Observable.timer(100, TimeUnit.MILLISECONDS).map(i -> "First")
                .ambWith(Observable.timer(50, TimeUnit.MILLISECONDS).map(i -> "Second"))
                .ambWith(Observable.timer(70, TimeUnit.MILLISECONDS).map(i -> "Third"))
                .subscribe(System.out::println);

        System.in.read();
    }


    public void mergeTest() throws IOException {

//        Observable.merge(
//                Observable.interval(250, TimeUnit.MILLISECONDS).map(i -> "First"),
//                Observable.interval(150, TimeUnit.MILLISECONDS).map(i -> "Second"))
//                .take(10)
//                .subscribe(System.out::println);
//
//        System.in.read();


//        System.out.println("--------------------------------------");
//
        Observable.interval(250, TimeUnit.MILLISECONDS).map(i -> "First")
                .mergeWith(Observable.interval(150, TimeUnit.MILLISECONDS).map(i -> "Second"))
                .take(10)
                .subscribe(System.out::println);

        System.in.read();
    }

    public void mergeDelayError() throws IOException {

//        Observable<Long> failAt200 =
//                Observable.concat(
//                        Observable.interval(100, TimeUnit.MILLISECONDS).take(2),
//                        Observable.error(new Exception("Failed")));
//        Observable<Long> completeAt400 =
//                Observable.interval(100, TimeUnit.MILLISECONDS)
//                        .take(4);
//
//        Observable.mergeDelayError(failAt200, completeAt400)
//                .subscribe(
//                        System.out::println,
//                        System.out::println);

        Observable<Long> failAt200 =
                Observable.concat(
                        Observable.interval(100, TimeUnit.MILLISECONDS).take(2),
                        Observable.error(new Exception("Failed")));
        Observable<Long> failAt300 =
                Observable.concat(
                        Observable.interval(100, TimeUnit.MILLISECONDS).take(3),
                        Observable.error(new Exception("Failed")));
        Observable<Long> completeAt400 =
                Observable.interval(100, TimeUnit.MILLISECONDS)
                        .take(4);

        Observable.mergeDelayError(failAt200, failAt300, completeAt400)
                .subscribe(
                        System.out::println,
                        System.out::println);


        System.in.read();
    }








    public static void main(String[] args) throws IOException {

        CombiningSequencesDemo combiningSequencesDemo = new CombiningSequencesDemo();
        combiningSequencesDemo.mergeDelayError();
    }
}
