package me.whiteship.java8to11.section3;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("keesun");
        names.add("whiteship");
        names.add("toby");
        names.add("foo");

        /* 중개 오퍼레이션은 lazy 하다 */
        // 중개 오퍼레이션 : stream을 리턴한다.
        names.stream().map((s) -> {
            System.out.println(s); // 출력되지 않음
            return s.toUpperCase();
        });

        System.out.println("===================");

        /* 종료 오퍼레이션은 */
        // 종료 오퍼레이션 : stream을 리턴하지 않는다.
        List<String> collect = names.stream().map((s) -> {
            System.out.println(s); // 출력됨
            return s.toUpperCase();
        }).collect(Collectors.toList());

        System.out.println("===================");

        /* 병렬처리 (스레드가 다름) - parallelStream */
        List<String> collect2 = names.parallelStream().map(s -> {
            System.out.println(s + " "+Thread.currentThread().getName());
            return s.toUpperCase();
        }).collect(Collectors.toList());

        /* 출력결과 */
//        foo ForkJoinPool.commonPool-worker-2
//        whiteship ForkJoinPool.commonPool-worker-1
//        keesun ForkJoinPool.commonPool-worker-3
//        toby main
    }
}
