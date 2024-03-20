package me.whiteship.java8to11.section3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Practice {
    public static void main(String[] args) {

        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring data jpa", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "rest api development", false));

        System.out.println("[문제] spring 으로 시작하는 수업");
        springClasses.stream()
                .filter(s -> s.getTitle().startsWith("spring"))
                .forEach(s -> System.out.println(s.getTitle()));


        System.out.println("[문제] closed 되지 않은 수업");
        springClasses.stream()
//                .filter(s -> !s.isClosed()) // good
                .filter(Predicate.not(OnlineClass::isClosed)) // better
                .forEach(s -> System.out.println(s.getTitle()));

        System.out.println("[문제] 수업 이름만 모아서 스트림 만들기");
        springClasses.stream()
                .map(OnlineClass::getTitle)
                .forEach(System.out::println);

        List<OnlineClass> javaClasses = new ArrayList<>();
        javaClasses.add(new OnlineClass(6, "The Java, Test", true));
        javaClasses.add(new OnlineClass(7, "The Java, Code Manipulation", true));
        javaClasses.add(new OnlineClass(8, "The Java, 8 to 11", false));

        List<List<OnlineClass>> keesunEvents = new ArrayList<>();
        keesunEvents.add(springClasses);
        keesunEvents.add(javaClasses);

        System.out.println("[문제] 두 수업 목록에 들어있는 모든 수업 아이디 출력");
        keesunEvents.stream().flatMap(Collection::stream) // 처음에 list 2개가 들어올텐데, 그걸 온라인클래스를 요소로 가지는 하나의 stream 요소로 평평하게? 만든다.
                .forEach(s -> System.out.println(s.getId()));


        System.out.println("[문제] 10부터 1까지 증가하는 무제한 스트림 중에서 앞에 빼고 최대 10개까지만");
        Stream.iterate(10, i -> i +1)
                .skip(10)
                .limit(10)
                .forEach(System.out::println);

        System.out.println("[문제] 자바 수업중에 Test가 들어있는 수업이 있는지 확인");
        boolean result = javaClasses.stream()
                .anyMatch(s -> s.getTitle().contains("Test")); // 종료형 오퍼레이터

        System.out.println(result);

        System.out.println("[문제] 스프링 수업중에 제목에 spring이 들어간것만 모아서 리스트로 만들기");
        List<String> resultList = springClasses.stream()
                .map(OnlineClass::getTitle)
                .filter(s -> s.contains("spring"))
                .collect(Collectors.toList());

        resultList.forEach(System.out::println);
    }
}
