package com.helloxin.leaner.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by nandiexin on 2018/12/14.
 */
public class FluxDemo {


    public static void main(String[] args) {
//        Flux.just(1, 2, 3, 4, 5, 6);
//        Mono.just(1);


//        Integer[] array = new Integer[]{1,2,3,4,5,6};
//        Flux.fromArray(array);
//        List<Integer> list = Arrays.asList(array);
//        Flux.fromIterable(list);
//        Stream<Integer> stream = list.stream();
//        Flux.fromStream(stream);

        // 只有完成信号的空数据流
//        Flux.just();
//        Flux.empty();
//        Mono.empty();
//        Mono.justOrEmpty(Optional.empty());
        // 只有错误信号的数据流
//        Flux.error(new Exception("some error"));
//        Mono.error(new Exception("some error"));


//        Flux.just(1, 2, 3, 4, 5, 6).subscribe(System.out::print);
//        System.out.println();
//        Mono.just(1).subscribe(System.out::println);
//
//        Flux.just(1, 2, 3, 4, 5, 6).subscribe(
//                System.out::println,
//                System.err::println,
//                () -> System.out.println("Completed!"));

        StepVerifier.create(Flux.just(1, 2, 3, 4, 5, 6))
                .expectNext(1, 2, 3, 4, 5, 6)
                .expectComplete()
                .verify();




    }
}
